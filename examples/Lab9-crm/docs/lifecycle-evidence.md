# Lifecycle evidence — Lab 9

Run each phase separately and paste a short excerpt.

| Phase | Command | BUILD SUCCESS? | Notes                                                                                                                                                                             |
| ----- | ------- |----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| validate | `mvn validate` | Success        | Is able to build from pom.xml                                                                                                                                                     |
| compile | `mvn compile` | Success        | Manages resources. Doesn't recompile if all classes are up to date.                                                                                                               |
| test | `mvn test` | Success        | See target/surefire-reports/com.northstar.crm.PlaceholderTest.txt for more details.                                                                                               |
| package | `mvn package` | Success        | target/customer-service.jar                                                                                                                                                       |
| verify | `mvn verify` | Success        | Checked resources and reran tests.                                                                                                                                                |
| install | `mvn install` | Success        | Installed /Users/chansen/java-bootcamp/examples/Lab9-crm/pom.xml → /Users/chansen/.m2/repository/com/northstar/customer-service/0.1.0-SNAPSHOT/customer-service-0.1.0-SNAPSHOT.pom |
