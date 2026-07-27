# Reflection Questions

### What are the advantages of Streams over loops?
Streams are optimized ways to perform some standardized operations on all objects in an iterable. They run faster, produce less overhead, and are generally considered cleaner code.



### When should Streams be preferred?
Streams are preferred when the operation happens across the entire iterable. This includes a specific operation to all objects in the iterable.
This also includes operations such as finding the sum, max, etc for the iterable.

### What is the difference between filter() and map()?
`filter()` is a stream operation which passes the objects in an iterable through a filter, such that any subsequent operations are only done on the objects that passed the filter.
`map()` is a stream operation which 'maps' an operation to each object in the iterable. This may be an object method, attribute, etc.

### Why is reduce() useful?
`reduce()` is a stream operation used to reduce the number of objects in an iterable. This may be helpful for finding the maximum or minimum value in an iterable.


### What does Collectors.groupingBy() do?
`Collectors.groupingBy()` is stream operation used to make groups of objects using some method. This might be used to group objects in an iterable
with a shared attribute.

### What is the benefit of using Optional?
Optional objects are used to when null objects may cause runtime errors. This let's uninitialized objects exist without disrupting the main program flow.

### Why are Lambda Expressions more readable?
Lambda Expressions are more readable because they are able to capture the meaning of an expression with much less code. Moreover, they reduce "boilerplate" code which may obfuscate the meaning of the function.

### When should method references be used?
Method references can be used in streams operations to mask more complex logic.

### Which stream operation is terminal? Give three examples from your lab.
The `.collect()` operation is terminal. I used it to find the employee with the second highest salary. 
`double secondHighestSalary = employees.stream().map(e -> e.getSalary()).sorted().collect(Collectors.toList()).get(1);`

The `.count()` operation is also terminal.
`long activeCount = employees.stream().filter(Employee::isActive).count();`

The `.reduce()` operation is terminal. And was used to find the highest salary.
`Optional<Double> highest = employees.stream().map(Employee::getSalary).reduce(Double::max);`


### How do Streams improve enterprise Java applications?
Streams improve legibility and runtime for simple and common iterable operations. They utilize the tools of the collections library, and perform better. 
In enterprise code, they also tend to be very parallelizable, leading to performance boosts on cloud or other high performance computing systems. 

### (Forward look) How would a future CRM use filter / map / groupingBy on customers the same way this lab uses them on employees—without claiming the CRM is implemented today?
Filter / Map / GroupingBy could be used to generate important statistics to influence major business decisions and make informed decisions on customer relations.
