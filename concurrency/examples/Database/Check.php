<?php

$pdo = new PDO("sqlite:test.db");

$value = $pdo->query("SELECT value FROM counter WHERE id = 1")->fetchColumn();

echo "Final value: $value\n";