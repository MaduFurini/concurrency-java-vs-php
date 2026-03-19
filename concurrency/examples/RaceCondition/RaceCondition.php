<?php

/**
 * Race Condition Example (File)
 * ----------------------------------------
 * This script demonstrates a race condition
 * Multiple processes read and write the same file concurrently
 * 
 * 
 * https://stackoverflow.com/questions/68040064/how-can-i-prevent-a-race-condition-when-using-a-file-based-counter-in-php
 */

$file = __DIR__ . '/counter.txt';

// Create file if it does not exist
if (!file_exists($file)) {
    file_put_contents($file, "0");
}

// Read current value
$value = (int) file_get_contents($file);

// Simulate delay (forces race condition)
usleep(100000); // 100ms

// Increment
$value++;

// Write back
file_put_contents($file, $value);