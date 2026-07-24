# Lab-4 Memory Management

## Memory Leak Demo
This demonstration has two modes: leak and fix. 

### Step 10 Results
| Objects | Used Memory (approx) | Execution Time |
| ------- | -------------------- | -------------- |
|10          |1936488.0      |0.0               |
|100         |1948160.0      |0.0               |
|1000        |1951760.0      |0.0               |
|100000      |1.2433528E7    |3.0               |
|1000000     |1.12319144E8   |42.0              |

===== JVM Memory Report: Start ===== \
Total Memory : 130 MB \
Free Memory  : 127 MB \
Used Memory  : 2 MB \
Max Memory   : 512 MB

-----------------------------

Additional measurements:\
Elapsed Time: 1.000 msElapsed Time: 2.000 ms\
===== JVM Memory Report: Before Large byte[] =====\
Total Memory : 128 MB\
Free Memory  : 122 MB\
Used Memory  : 5 MB\
Max Memory   : 512 MB\
-----------------------------

===== JVM Memory Report: After Allocating Large byte[] =====\
Total Memory : 128 MB\
Free Memory  : 120 MB\
Used Memory  : 7 MB\
Max Memory   : 512 MB
-----------------------------

===== JVM Memory Report: After Releasing Large byte[] =====\
Total Memory : 128 MB\
Free Memory  : 126 MB\
Used Memory  : 1 MB\
Max Memory   : 512 MB
-----------------------------


## Reflection Questions

### Stack vs Heap?

### Why locals on the Stack?

### Why objects on the Heap?

### When is an object GC-eligible?

### Does System.gc() guarantee collection?

### What caused the leak?

### How did clearing the list fix it?

### Why are WeakReferences useful?

### What happens when the heap is exhausted?

### Which laptop tool would you try first for rising heap—and why?

### How could a CRM unbounded cache repeat this leak?