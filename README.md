### Simple fractions calculator

#### Pre-requisites
- [Java JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

**Note**: For the commands that follow, it is assumed that you are in the root directory of the project in your favorite terminal application.

#### To build

Run `.\gradlew.bat bootJar` to build and assemble an executable Spring Boot JAR with all of its dependencies.

#### To run

- Run `java -jar .\build\libs\fractions-0.0.1-SNAPSHOT.jar`.
- This will bring up the shell prompt. 

#### Features

- Supply *only* two operands at most, with one of the operators: `+, -, /, *`
- To enter mixed fractions, enter them as `wholeNumber_numerator/denominator`.
  - For example, `3_1/3`.
- Improper fractions, i.e., `16/5`, and whole numbers are allowed.
- Integers in the negative range are allowed as well.
- Plus, all of the features that [Spring Shell](https://docs.spring.io/spring-shell/docs/current-SNAPSHOT/reference/htmlsingle/#_what_is_spring_shell) provides.

