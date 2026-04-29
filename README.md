# 🚀 Concurrency (Java & PHP)

🌐 Language:

* 🇺🇸 English (current)
* 🇧🇷 [Português](./README.pt-BR.md)

# 🧠 About

This repository documents my studies on **concurrency, threading, and distributed systems**, based on class notes and practical experiments.


# 📚 Learning Structure

## 📖 Class Notes (Theory)

### Fundamentals

* 📄 [Concurrency vs Distributed Systems](./classNotes/fundamentals/concurrency-vs-distributed.md)

## 🐘 PHP 

* Concurrency with database
* Transactions and locks
* Redis atomic operations

# 🧠 Key Concepts

* Concurrency vs Parallelism
* Race Conditions
* Thread Safety
* Atomic vs Volatile
* Distributed Systems

# 🎯 Purpose

The goal of this repository is to:

* Understand concurrency in depth
* Apply concepts in real scenarios
* Compare different backend approaches (Java vs PHP)

# ⭐ Key Insight

> Always ask: **Where is the shared state?**

* Java → Memory (threads)
* PHP → Database / Redis

# ⚔️ Java vs PHP

> 📊 Practical comparison of concurrency between languages

- ☕ [Java vs PHP – Concurrency](./concurrency/fundamentals/java-vs-php.md)  
- 🐘 [Java vs PHP – Portuguese Version](./concurrency/fundamentals/java-vs-php.pt-BR.md)


# 📚 References

## 📘 Class Material

Critical Section – Class slides (Prof. Cristiane Imamura)

## ☕ Java Concurrency

Java volatile vs Atomic classes

https://medium.com/@qingedaig/java-volatile-vs-atomic-classes-7599eb70a661

## 🐘 PHP Concurrency & Parallelism

PHP and Multithreading: A Lighthearted Look at Concurrency

https://infinitypaul.medium.com/php-and-multithreading-a-lighthearted-look-at-concurrency-dac4540f3726

Programação Paralela e Assíncrona com PHP

https://medium.com/@sschonss/programação-paralela-e-assíncrona-com-php-5969c49d0bba

Unlocking Parallel Processing in PHP

https://devriazul.medium.com/unlocking-parallel-processing-in-php-yes-its-possible-e915dab720b2

## ⚙️ PHP Architecture & Execution Model

PHP-FPM manages multiple processes and handles concurrent requests efficiently

Understanding PHP-FPM

https://dev.to/arsalanmee/understanding-php-fpm-a-comprehensive-guide-3ng8

## 🔒 Concurrency Control in PHP

PHP provides file locking mechanisms such as flock for mutual exclusion

Parallel processing in PHP (StackOverflow discussion)

https://stackoverflow.com/questions/6107339/parallel-processing-in-php-how-do-you-do-it

## 🧪 Additional Reading

PHP multithreading faking it

https://w-shadow.com/blog/2007/08/20/php-multithreading-faking-it/
