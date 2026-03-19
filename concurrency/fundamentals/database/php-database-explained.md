# PHP Database Race Condition Explained

🌐 Idioma:

* 🇺🇸 English (current)
* 🇧🇷 [Português](./php-database-explained.pt-BR.md)

## Overview

This document explains different approaches to updating a counter in a
SQLite database, focusing on concurrency, race conditions, and why
transactions are not always the safest solution

## How to Run Concurrency Test (Windows PowerShell)

``` powershell
1..20 | ForEach-Object {
    Start-Process php -ArgumentList "Database.php" -NoNewWindow
}

Start-Sleep -Seconds 3
php check.php
```

Expected: - ❌ Wrong approaches → inconsistent values - ✅ Atomic
approach → correct value (20)


## Atomic Update (Best Approach)

``` php
<?php

try {
    $pdo = new PDO("sqlite:" . __DIR__ . "/test.db");
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $pdo->exec("UPDATE counter SET value = value + 1 WHERE id = 1");

    echo "PID: " . getmypid() . "\n";

} catch (Exception $e) {
    echo "Error: " . $e->getMessage() . "\n";
}
```

### Why it works

-   Atomic operation
-   No race condition
-   Database handles locking internally



## Read → Modify → Write (Race Condition)

``` php
<?php

try {
    $pdo = new PDO("sqlite:" . __DIR__ . "/test.db");
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $stmt = $pdo->query("SELECT value FROM counter WHERE id = 1");
    $value = (int) $stmt->fetchColumn();

    usleep(200000);

    $value++;

    $stmt = $pdo->prepare("UPDATE counter SET value = ? WHERE id = 1");
    $stmt->execute([$value]);

    echo "PID: " . getmypid() . " | Value: $value\n";

} catch (Exception $e) {
    echo "Error: " . $e->getMessage() . "\n";
}
```

### Problem

-   Multiple processes read same value
-   Overwrites occur


## Transaction (Common Mistake)

``` php
<?php

try {
    $pdo = new PDO("sqlite:" . __DIR__ . "/test.db");
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $pdo->beginTransaction();

    $stmt = $pdo->query("SELECT value FROM counter WHERE id = 1");
    $value = (int) $stmt->fetchColumn();

    usleep(200000);

    $value++;

    $stmt = $pdo->prepare("UPDATE counter SET value = ?");
    $stmt->execute([$value]);

    $pdo->commit();

    echo "PID: " . getmypid() . " | Value: $value\n";

} catch (Exception $e) {
    $pdo->rollBack();
    echo "Error: " . $e->getMessage() . "\n";
}
```

### Why this fails

-   Transactions do not lock reads
-   Multiple processes still read the same value
-   Race condition still happens



## Correct Use of Transaction + Atomic Update

``` php
<?php

try {
    $pdo = new PDO("sqlite:" . __DIR__ . "/test.db");
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $pdo->beginTransaction();

    $stmt = $pdo->prepare("
        UPDATE counter
        SET value = value + 1
        WHERE id = 1
    ");
    $stmt->execute();

    $pdo->commit();

    echo "PID: " . getmypid() . "\n";

} catch (Exception $e) {
    $pdo->rollBack();
    echo "Error: " . $e->getMessage() . "\n";
}
```

## Key Takeaways

-   ❌ Transaction alone does NOT prevent race condition
-   ✅ Atomic queries are safest
-   ✅ Combine transaction + atomic when needed
-   ❌ Avoid SELECT → UPDATE pattern


