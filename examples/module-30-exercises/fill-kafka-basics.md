# Lab 30 — Fill Kafka Basics TODOs

1. A topic is a named stream of records.
2. A partition is an ordered subset of a topic; offsets are per partition.
3. The offset is the consumer's position in a partition.
4. Consumers in the same consumer group compete for partitions; different groups each get a copy.


> group `crm-notifications` shares partitions; group `crm-audit` reads all `CUS-1001`/`CUS-1002` events independently.


## Scope
Pre-lab only — do not finish the full graded lab in this exercise.