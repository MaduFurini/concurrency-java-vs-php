<?php

/**
 * File Lock Example (flock)
 * ----------------------------------------
 * This script fixes the race condition using file locking
 * Only one process can access the file at a time
 * 
 * https://www.php.net/manual/en/function.flock.php
 * https://www.webmasterworld.com/php/3165926.htm
 */

$file = __DIR__ . '/counter.txt';

// Open file
$fp = fopen($file, 'c+');

// Acquire exclusive lock
flock($fp, LOCK_EX);

// Read current value
$value = (int) fread($fp, 100);

// Increment safely
$value++;

// Rewrite file
ftruncate($fp, 0);
fwrite($fp, $value);

// Release lock
flock($fp, LOCK_UN);
fclose($fp);
