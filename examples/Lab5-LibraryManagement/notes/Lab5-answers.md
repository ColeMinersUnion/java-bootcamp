# Lab 5 Reflection Questions

### When choose List over Set?
A list should be used when duplicate values should be handled, not disregarded. Lists are also indexed, whereas sets are not.

### Why HashSet before inserting a book ID?
BookIDs should be unique values, meant to identify a book. Therefore, a hashset, where the IDs can be indexed and duplicates are not allowed.

### Why a Map for “currently borrowed” vs only a boolean?
The bookIDs tie the borrowing status to the book. Without the map, the index of the books would have to be the IDs which may cause errors whenever books are added or removed from the library.

### HashMap vs TreeMap in this lab?
While both HashMaps and TreeMaps have their advantages, hashMaps allow us to index values in the map. A tree map, is moreso focused on sorting the 
contents of the map, and sorting is not required as per the constraints of this library service.

### Comparable vs Comparator for books?
The comparator object allows us to quickly perform boolean comparison operations. This is more in line with the style of the codebase, which focuses on OOP and object based handling.

### Which iteration style would you use most in production—and why?
For loops tend to be the simplest to read, implement and debug. While I prefer for each loops, they are prone to off-by-one errors, and trying to iterate through null objects.

### CRM: which collection for customer list / unique emails / id→customer lookup?
Customer List, unique emails and ID should be a HashSet. A hashset allows for quick lookup times, while also stipulating that all entries must be unique.
