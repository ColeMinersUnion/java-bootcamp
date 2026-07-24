# Collection choices

| # | Scenario | Need (order / unique / key→value / sorted) | Interface             | Implementation | Why                                                                                        |
| - | -------- |--------------------------------------------|-----------------------|----------------|--------------------------------------------------------------------------------------------|
| 1 | Ordered catalog; duplicate titles allowed | Order                                      | `List<String>`        | `ArrayList<>`  | Dynamic resizing for new books or increased inventory space.                               |                              |
| 2 | Unique registered book IDs | Unique                                     | `Set<String>`         | `HashSet<>`    | No Duplicates. A traditional set should work here, but the indexing of the hashes is nice. |
| 3 | Book ID → current borrower ID | key→ value                                 | `Map<String, String>` | `HashMap<>`    | A simple mapping between the book ID and the borrower ID.                                  |
| 4 | Alphabetically sorted categories | Sorted, Unique                             | `Set<String>`         | `TreeSet<>`    | Categories are unique and should be ordered.                                               |
| 5 | Category → count, sorted by category | key→ value, sorted                         | `Map<String, Int>`    | `TreeMap<>`    | A mapping between category and count. Must be sorted.                                      |
| 6 | Checkout history in event order | order,                                     | `List<String>`        | `ArrayList<>`  | Ordered List. Dynamic sizing for new checkouts.                                            |

### If unique IDs must also preserve registration order, what changes?
Instead of using a normal hashset, we would use a LinkedHashSet. This allows us to track order, while also inedxing with hashes and excluding duplicates

### If borrower lookup must preserve insertion order for display, what changes?
Instead of using a normal hash map for the look ups, we would use a LinkedHashMap to preserve order.

### If many insertions/removals occur in the middle, is LinkedList automatically best?
No. There are many factors like size requirements and access patterns that also influence the collection.