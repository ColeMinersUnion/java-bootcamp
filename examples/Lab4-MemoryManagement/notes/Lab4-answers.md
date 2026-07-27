# Lab-4 Memory Management

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
The stack holds local variables and function calls. The heap stores objects, 

### Why locals on the Stack?
Local variables need to exist in the same context as their call stack. Keeping locals on the stack avoids variable name conflicts, and enforces scope.

### Why objects on the Heap?
Objects are large, and would be difficult to pass on the stack. 

### When is an object GC-eligible?


### Does System.gc() guarantee collection?
System.gc() does not guarantee collection. It usually will collect garbage, but the JVM may ignore the "request" to collect garbage. 

### What caused the leak?
The leak was caused by objects that were created, but their references destroyed.

### How did clearing the list fix it?
Clearing the list destroys the objects in the list, such that when the list is deleted, there won't be any dereferenced objects causing a leak.

### Why are WeakReferences useful?
WeakReferences are useful as strong references will continue to point to an object after it has been deleted, where as a weak reference will acknowledge that an object has been deleted and return a null value.

### What happens when the heap is exhausted?
When the heap is exhausted, the JVM will throw an out of memory error, as there is no more memory for new objects to be created in the heap.  

### Which laptop tool would you try first for rising heap—and why?
I would tend to prefer `jstat`. It's reliable and terminal based, which are both things that I prefer. It also seems as though it would be easy to integrate into testing regiments. 
I also have had the most, albeit still limited, experience with it.

### How could a CRM unbounded cache repeat this leak?
An unbounded cache, would continue to try and add objects to the heap even though there is no more memory available. That cache may overwrite some objects, causing dangling references and a memory leak. 
