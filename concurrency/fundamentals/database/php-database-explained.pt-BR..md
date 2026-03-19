# Explicação de Race Condition em Banco de Dados com PHP


* 🇺🇸 [English](./php-database-explained.md)
* 🇧🇷 Português (atual)


## Visão Geral

Este documento explica diferentes abordagens para atualizar um contador
em um banco SQLite, com foco em concorrência, race conditions e por que
transações nem sempre são a solução mais segura

## Como Rodar o Teste de Concorrência (Windows PowerShell)

``` powershell
1..20 | ForEach-Object {
    Start-Process php -ArgumentList "Database.php" -NoNewWindow
}

Start-Sleep -Seconds 3
php check.php
```

Esperado: - ❌ Abordagens erradas → valores inconsistentes - ✅
Abordagem atômica → valor correto (20)


## Atualização Atômica (Melhor Abordagem)

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

### Por que funciona

-   Operação atômica
-   Sem race condition
-   O banco gerencia o lock internamente


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

### Problema

-   Múltiplos processos leem o mesmo valor
-   Ocorrem sobrescritas


## Transação (Erro Comum)

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

### Por que falha

-   Transações não bloqueiam leituras
-   Múltiplos processos ainda leem o mesmo valor
-   Race condition continua acontecendo


## Uso Correto: Transação + Operação Atômica

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

## Principais Conclusões

-   ❌ Transação sozinha NÃO previne race condition
-   ✅ Queries atômicas são mais seguras
-   ✅ Combine transação + operação atômica quando necessário
-   ❌ Evite padrão SELECT → UPDATE
