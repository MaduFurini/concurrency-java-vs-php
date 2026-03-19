<?php

/**
 * Database Concurrency Example (SQLite)
 * ----------------------------------------
 * Demonstrates safe concurrent updates using transactions
 * SQLite DB is created automatically in the project folder
 * 
 * https://laravel.com/docs/12.x/database#manually-using-transactions
 * https://stackoverflow.com/questions/26493266/concurrent-database-access#:~:text=Therefore,%20it%27s%20important%20to%20write,User,%20you%20use%20a%20transaction.&text=to%20choose%20the%20user%20and,tables%20relating%20to%20that%20user.&text=and%20all%20your%20changes%20will,MyISAM%20doesn%27t%20support%20them.
 */

$dbFile = __DIR__ . '/test.db'; 

try {
    $pdo = new PDO("sqlite:" . __DIR__ . $dbFile);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $pdo->beginTransaction();

    $stmt = $pdo->prepare("
        UPDATE counter
        SET value = value + 1
        WHERE id = 1
    ");
    $stmt->execute();

    $pdo->commit();
} catch (Exception $e) {
    $pdo->rollBack();
}
