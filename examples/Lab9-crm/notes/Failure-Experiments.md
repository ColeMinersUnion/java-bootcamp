# Failure Experiments

## Experiment 1:
Set spring.version to nonsense; mvn compile

![bad spring version](screenshots/Bad_SpringVersion.png)

## Experiment 2: 
Change PlaceholderTest to assertTrue(false); mvn test / mvn verify

![bad assertion](screenshots/Bad_Assertion.png)

## Experiment 3: 
Run mvn install twice
![Second installation](screenshots/SecondInstallation.png)
This installation rewrites over the previous installation.

## Experiment 4:
Compare cold vs warm mvn -B verify wall-clock

The first verify took almost a full second. 
![Initial Verify](screenshots/InitialVerify.png)

The second verify was about 40% faster.
![Second Verify](screenshots/SecondVerify.png)

## Experiment 5:
Remove <scope>test</scope> from JUnit temporarily; re-tree	

![JUnit Scope](screenshots/CompilationJUnit.png)

