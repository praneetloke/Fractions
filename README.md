### Simple fractions calculator

#### Pre-requisites
- [Java JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

**Note**: For the commands that follow, it is assumed that you are in the root directory of the project in your favorite terminal application.

#### To build

Run `.\gradlew.bat bootJar` to build and assemble an executable Spring Boot JAR with all of its dependencies.

#### To run

- Run `java -jar .\build\libs\fractions-0.0.1-SNAPSHOT.jar`.
- This will bring up the shell prompt.
- The only command that is supported right now is the `calculate` command.
- Here are some examples for running the calculate command:
> Use a space to separate operands and the operator. Multiple continuous spaces are handled by the application.
```shell
shell:>calculate "1 + 1/2"
shell:>calculate " 1     -    2/3"
shell:>calculate "2_2/3  +  9_9/2"
shell:>calculate "1/2 + 1/2"
```

#### Features

- Supply *only* two operands at most, with one of the operators: `+, -, /, *`
- To enter mixed fractions, enter them as `wholeNumber_numerator/denominator`.
  - For example, `3_1/3`.
- Improper fractions, i.e., `16/5`, and whole numbers are allowed.
- Integers in the negative range are allowed as well.
- Plus, all of the features that [Spring Shell](https://docs.spring.io/spring-shell/docs/current-SNAPSHOT/reference/htmlsingle/#_what_is_spring_shell) provides.

