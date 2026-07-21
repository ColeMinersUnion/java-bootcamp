# Reflection Questions
1. Why must the package folder tree match package com.academy.student?
2. Why prefer nextLine() + parse over nextInt() in a menu app?
3. Why keep a studentCount instead of relying on students.length alone?
4. What belongs in Main versus StudentManager?
5. How does this console CRUD prepare you for later Spring/customer labs without implementing them here?

## Reflection Answers
1. The package folder tree must match `com.academy.student` so the classes can be loaded during compilation.
2. nextLine provides a more consistent handling of our input buffer. 
3. The `studentCount` allows us to reference the length in constant time. It also protects against off-by-one errors.
4. `Main` should handle the main program flow. This includes the menu display and handling initial user choices. 
Anything regarding the students, whether adding, searching, displaying, etc. should be in `StudentManager`. 
5. Console CRUD builds good habits, and teaches us the right way to think about applications when the stakes are low.