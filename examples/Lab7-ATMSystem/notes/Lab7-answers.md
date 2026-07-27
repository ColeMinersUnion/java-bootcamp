### What is the difference between checked and unchecked exceptions?
### Why should custom exceptions be used?
### What is exception propagation?
Exception propagation is the practice of propagating, or sending along, an exception to a point in the stack where it is more easily handled. For example, if a function throws an exception several functions
calls away from main, it may be useful to propagate the thrown exception back to main, where it can be handled.

### What is the purpose of finally?
The `finally` block runs code regardless of whether an exception was caught or not. 

### Why is try-with-resources preferred?
`try-with-resources` will automatically close resources if an exception is caught. This could prevent a memory leak from occuring from a file that didn't close.
### When should throw be used?
`throw` should be used to explicitly throw an exception, especially when the compiled code would not throw one itself. In the exercises, we threw an exception if a customer tried to overdraft their account. 

### When should throws be used?
Throws should be used to signify that an exception may be thrown or propagated through a given function. 

### Why is logging important in enterprise applications?
What happens if an exception is not handled?
How does proper exception handling improve software reliability?
(Forward look) How would a future CRM map domain exceptions (not found / validation) to API errors using the same boundary-catch + log pattern—without claiming CRM is implemented today?