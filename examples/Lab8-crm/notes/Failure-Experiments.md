

## Experiment 1

Rename `pom.xml` temporarily; `run mvn compile`

![Renamed pom.xml](screenshots/Pom_Failure.png)

## Experiment 2

Temporarily import `com.northstar.crm.controller.CustomerController`
inside `CustomerRepository`

![Bad Import Failure](screenshots/Bad_Import_Failure.png)

## Experiment 3
I created a `.java` file in `src/`. I then compiled. The compilation ran fine, but maven did not compile the `NewClass.java` file.

![Random java File](screenshots/Out_Of_Place_Java.png)
