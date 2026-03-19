# 🧠 Concurrency vs Distributed Systems

🌐 Language:

* 🇺🇸 English (current)
* 🇧🇷 [Português](./concurrency-vs-distributed.pt-BR.md)

---

## 📍 Distributed Systems (Where)

Distributed systems focus on **physical location**

* Components are separated across machines
* Communication happens over network
* Example: cloud computing

## ⏱️ Concurrent Systems (When)

Concurrent systems focus on **execution over time**

* Multiple tasks execute and compete for resources
* Tasks may run interleaved or simultaneously

## 🔗 Relationship

Every distributed system is inherently concurrent, 
but not every concurrent system is distributed.

* Distributed → always concurrent
* Concurrent → may run on a single machine

## ⚔️ Parallel Programming vs Concurrent Programming

### 🧵 Concurrent Programming

* Multiple tasks progress at the same time
* Tasks may compete for shared resources
* Examples:
    * Two threads accessing the same variable
    * One proceeds, the other waits

### ⚡ Parallel Programming

* Multiple parts of a program execute simultaneously
* Require multiple CPU cores
* Focus: performance improvement

#### ⚠️ Common Challenges

* Debugging is difficult
* Execution order is unpredictable
* Results may vary between runs

#### 📈 Parallelism Motivation

* Reduce execution time
* Optimize resource usage

#### 🧠 Moore’s Law

Processing power doubles over time — but has limits.

#### ⚖️ Amdahl’s Law

A program is never 100% parallel.

* Some parts are always sequential
* Limits performance gains

##### Formula Insight

Maximum speedup:

```
1 / (1 - P)
```

Where:

* `P` = parallel portion of the program

#### 📌 Example

If 90% of a program is parallel:
* Maximum speedup = 10x
* Even with infinite processors

#### 🚨 Starvation

Occurs when:
* A thread never gets CPU time
* Due to priority or scheduling

### 🧠 Key Difference

| Concept     | Focus                               |
| ----------- | ----------------------------------- |
| Concurrency | Managing access to shared resources |
| Parallelism | Executing tasks simultaneously      |


## 🧠 Important Concepts

### ⚠️ Race Condition

Occurs when multiple threads access the same resource simultaneously, causing unpredictable results

### 🔒 Critical Section

A part of the code where shared resources are accessed

* Must be protected
* Only one thread should execute it at a time

### 🧷 Mutual Exclusion

Ensures that when one thread enters a critical section, others must wait

## 🔧 Synchronization Tools

### 🧱 Monitor / Lock

* Controls access to a resource
* Only one thread can hold the lock at a time

### 🚦 Semaphore

* Allows multiple threads to access a resource (limited)
* Example:
    * Server supports 1000 requests at once
    * The 1001st must wait

## ⚡ Threads

### 📌 Definition

A thread is a lightweight process:
* Shares memory with other threads
* Has its own execution stack
* Executes instructions independently

### 🔁 Lifecycle 

New
Runnable
Running
Waiting / Blocked
Terminated

### 🧩 Key Methods

* `run()` → logic executed by the thread
* `start()` → starts execution
* `sleep()` → pauses execution
* `wait()` → waits for condition
* `join()` → waits for another thread to finish

### 🔄 Volatile Variables

* Ensure visibility across threads
* Updates in one thread are visible to others

### ⚡ Atomic Variables

* Guarantee operations are indivisible
* Prevent race conditions in simple operations