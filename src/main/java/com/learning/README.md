# Java & Spring Boot — Revision Notes

> A practical revision guide for Java backend development and Spring Boot.
>
> These notes focus on concepts, commands, implementation patterns, and comparisons with the Node.js ecosystem to make learning easier.

## Table of Contents

[Java and Maven](#1-java-and-maven)

- [What is Maven?](#11-what-is-maven)
- [Maven vs npm](#12-maven-vs-npm)
- [Maven coordinates](#13-maven-coordinates)
- [Dependency scopes](#14-dependency-scopes)
- [Maven project structure](#15-maven-project-structure)
- [Maven build lifecycle](#16-maven-build-lifecycle)
- [Frequently used Maven commands](#17-frequently-used-maven-commands)
- [Important Maven directories](#18-important-maven-directories)

---

# 1. Java and Maven

## 1.1 What is Maven?

Maven is a Java build automation and dependency management tool.

It helps with:

- Managing external libraries and their dependencies.
- Compiling Java source code.
- Running tests.
- Packaging applications into JAR or WAR files.
- Installing build artifacts into the local Maven repository.
- Deploying artifacts to remote repositories.

Without Maven, we would need to manually download JAR files, manage classpaths, compile source files, execute tests, and package applications.

### Node.js comparison

| Java ecosystem                | Node.js ecosystem                                      |
| ----------------------------- | ------------------------------------------------------ |
| Maven                         | npm (partial equivalent)                               |
| `pom.xml`                     | `package.json`                                         |
| Maven dependency declarations | npm dependency declarations                            |
| `~/.m2/repository`            | No exact equivalent; npm cache is not the same         |
| Maven build lifecycle         | npm scripts / build-tool commands                      |
| `target/`                     | `dist/` (common convention)                            |
| `.jar`                        | Packaged application artifact (depends on build setup) |

**Important distinction:**

Maven is more than a package manager. It provides a standardized build lifecycle.

npm primarily manages packages and runs scripts. Build automation in Node.js is often handled by tools such as TypeScript, Webpack, Vite, and npm scripts.

---

## 1.2 Maven vs npm

### Dependency installation

Node.js:

```bash
npm install
```

Maven:

```bash
mvn dependency:resolve
```

Both resolve project dependencies, although their dependency resolution and storage mechanisms differ.

Maven automatically resolves declared dependencies when executing normal build lifecycle commands.

### Adding a dependency

Node.js:

```bash
npm install express
```

Maven:

Declare the dependency inside `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

Maven coordinates identify the dependency.

### Running tests

Node.js:

```bash
npm test
```

Maven:

```bash
mvn test
```

### Building the application

Node.js:

```bash
npm run build
```

Maven:

```bash
mvn package
```

### Cleaning previous build output

Node.js:

```bash
rm -rf dist
```

Maven:

```bash
mvn clean
```

### Important difference: `npm install` vs `mvn install`

These commands are NOT equivalent.

| Command                  | Purpose                                                                    |
| ------------------------ | -------------------------------------------------------------------------- |
| `npm install`            | Installs project dependencies                                              |
| `mvn dependency:resolve` | Resolves Maven dependencies                                                |
| `mvn install`            | Builds and installs the resulting artifact into the local Maven repository |

Remember this distinction.

---

## 1.3 Maven coordinates

Maven identifies an artifact using three primary coordinates:

- `groupId`
- `artifactId`
- `version`

Example:

```xml
<groupId>com.company</groupId>
<artifactId>payment-service</artifactId>
<version>1.0.0</version>
```

Together, these identify:

```text
com.company:payment-service:1.0.0
```

This is called the Maven coordinate or GAV coordinate.

| Field        | Meaning                   |
| ------------ | ------------------------- |
| `groupId`    | Organization or namespace |
| `artifactId` | Project or library name   |
| `version`    | Artifact version          |

Example:

```text
com.company:payment-service:1.0.0
com.company:payment-service:2.0.0
```

These are two different versions of the same artifact.

Versioning allows projects to use different releases of the same library.

---

## 1.4 Dependency scopes

Maven dependency scopes determine when a dependency is available during the build and execution process.

### Common scopes

| Scope     | Compile | Test | Runtime | Example                |
| --------- | ------- | ---- | ------- | ---------------------- |
| `compile` | Yes     | Yes  | Yes     | Spring Web             |
| `test`    | No      | Yes  | No      | JUnit                  |
| `runtime` | No      | Yes  | Yes     | PostgreSQL JDBC Driver |

### 1. Compile

This is the default scope.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

Used when the application needs the dependency during compilation and execution.

### 2. Test

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <scope>test</scope>
</dependency>
```

Used for testing purposes.

Similar to:

```json
{
  "devDependencies": {
    "jest": "..."
  }
}
```

However, Maven's test scope is more specific than npm's devDependencies because it defines classpath availability in Maven's build lifecycle.

### 3. Runtime

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

The application needs the dependency during execution, but not for compiling its own source code.

For example, a database driver can be loaded at runtime by the database framework.

### Other scopes

Maven also supports:

- `provided`: Available during compilation and testing, but expected to be provided by the runtime environment.
- `system`: Uses a JAR from a specified local filesystem path. Generally avoid this.
- `import`: Used with `pom` dependencies inside dependency management to import a BOM.

For now, focus on `compile`, `test`, and `runtime`.

---

## 1.5 Maven project structure

Maven follows a standard directory convention.

```text
banking-app/
│
├── pom.xml
│
└── src/
    │
    ├── main/
    │   ├── java/
    │   │   └── com/example/banking/
    │   │       └── BankAccount.java
    │   │
    │   └── resources/
    │       └── application.properties
    │
    └── test/
        ├── java/
        │   └── com/example/banking/
        │       └── BankAccountTest.java
        │
        └── resources/
            └── application.properties
```

### Important directories

| Directory            | Purpose                                 |
| -------------------- | --------------------------------------- |
| `src/main/java`      | Application Java source code            |
| `src/main/resources` | Application resources and configuration |
| `src/test/java`      | Test source code                        |
| `src/test/resources` | Resources used by tests                 |
| `target`             | Generated build output                  |
| `pom.xml`            | Maven project configuration             |

### Node.js comparison

```text
Node.js:
src/
dist/
package.json

Java:
src/main/java/
src/main/resources/
src/test/java/
target/
pom.xml
```

### Resource handling

Suppose we have:

```text
src/main/resources/application.properties
```

After compiling:

```text
target/classes/application.properties
```

Maven copies resources into the build output.

Similarly, compiled Java files are placed inside:

```text
target/classes/
```

The resulting JAR includes compiled classes and application resources.

### Understanding `target`

`target` is conceptually similar to `dist` in many Node.js projects.

It contains generated artifacts such as:

- Compiled `.class` files.
- Copied application resources.
- Test reports.
- Generated sources.
- Packaged JAR or WAR files.

It is generated output and should generally not be committed to Git.

---

## 1.6 Maven build lifecycle

Maven provides three built-in lifecycles:

1. `default` — Builds and deploys the application.
2. `clean` — Cleans previous build output.
3. `site` — Generates project documentation.

### Default lifecycle

Some important phases:

| Phase      | Purpose                                               |
| ---------- | ----------------------------------------------------- |
| `validate` | Validates project configuration                       |
| `compile`  | Compiles application source code                      |
| `test`     | Executes tests                                        |
| `package`  | Packages the application                              |
| `verify`   | Performs additional verification checks               |
| `install`  | Installs the artifact into the local Maven repository |
| `deploy`   | Publishes the artifact to a remote repository         |

### Important lifecycle rule

Maven executes lifecycle phases sequentially.

For example:

```bash
mvn package
```

Automatically executes the preceding phases, including compilation and testing.

You don't need to execute these separately:

```bash
mvn compile
mvn test
mvn package
```

Likewise:

```bash
mvn install
```

Runs the required earlier phases before installing the artifact.

### Clean lifecycle

```bash
mvn clean
```

Removes the previous `target` directory.

### Combining lifecycles

```bash
mvn clean package
```

This:

1. Cleans previous build output.
2. Compiles the application.
3. Runs tests.
4. Packages the application.

This is a common command for producing a fresh build.

---

## 1.7 Frequently used Maven commands

These are the commands worth remembering for everyday Spring Boot development.

| Maven command             | npm / Node.js comparison               | Usage                                               |
| ------------------------- | -------------------------------------- | --------------------------------------------------- |
| `mvn clean`               | `rm -rf dist`                          | Removes previous build output                       |
| `mvn compile`             | `npm run build` (partial)              | Compiles Java source code                           |
| `mvn test`                | `npm test`                             | Executes tests                                      |
| `mvn package`             | `npm run build` (partial)              | Creates a JAR or WAR                                |
| `mvn clean package`       | Clean + build script                   | Performs a fresh package build                      |
| `mvn verify`              | Test + additional checks               | Runs verification checks                            |
| `mvn install`             | No direct equivalent                   | Installs artifact in local Maven repository         |
| `mvn dependency:resolve`  | `npm install` (partial)                | Resolves declared dependencies                      |
| `mvn dependency:tree`     | Dependency inspection tools            | Displays dependency hierarchy                       |
| `mvn spring-boot:run`     | `npm run dev` (partial)                | Runs a Spring Boot application                      |
| `mvn -DskipTests package` | Build without tests                    | Packages without executing tests                    |
| `mvn -U clean package`    | No direct equivalent                   | Forces checks for updated remote snapshots/releases |
| `mvn help:effective-pom`  | Inspect resolved project configuration | Displays the effective POM                          |

### Common commands explained

#### 1. Clean

```bash
mvn clean
```

Removes the `target` directory.

Useful when you want to start with a fresh build.

#### 2. Compile

```bash
mvn compile
```

Compiles source files inside:

```text
src/main/java/
```

Output:

```text
target/classes/
```

#### 3. Test

```bash
mvn test
```

Compiles and executes tests.

Test source files are located inside:

```text
src/test/java/
```

#### 4. Package

```bash
mvn package
```

Builds and packages the application.

Typical output:

```text
target/banking-app-1.0.0.jar
```

#### 5. Clean and package

```bash
mvn clean package
```

Performs a fresh build and packages the application.

#### 6. Install

```bash
mvn install
```

Builds the project and places its artifact into:

```text
~/.m2/repository/
```

Useful when another local Maven project needs to consume your library.

#### 7. Dependency tree

```bash
mvn dependency:tree
```

Displays direct and transitive dependencies.

Useful when investigating:

- Dependency conflicts.
- Unexpected transitive dependencies.
- Different versions of the same library.

#### 8. Run Spring Boot

```bash
mvn spring-boot:run
```

Starts a Spring Boot application using the Spring Boot Maven plugin.

Similar in purpose to:

```bash
npm run dev
```

#### 9. Skip tests

```bash
mvn -DskipTests package
```

Skips test execution but still compiles test sources.

To also skip test compilation:

```bash
mvn -Dmaven.test.skip=true package
```

Use these options carefully. Skipping tests should not become your normal production build strategy.

#### 10. Force dependency update checks

```bash
mvn -U clean package
```

Forces Maven to check for updated snapshots and releases in configured repositories rather than relying only on normal update-check intervals.

---

## 1.8 Important Maven directories

### Local Maven repository

Default location:

```text
~/.m2/repository/
```

Maven stores downloaded dependency artifacts here.

Unlike `node_modules`, this repository is generally shared across multiple Maven projects on the same machine.

### Build output

```text
target/
```

Contains generated build output.

### Maven wrapper

A Maven project can include:

```text
mvnw
mvnw.cmd
.mvn/
```

These files allow developers to use a project-specific Maven version without requiring a globally installed Maven version.

Commands:

macOS/Linux:

```bash
./mvnw clean package
./mvnw test
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd clean package
mvnw.cmd test
mvnw.cmd spring-boot:run
```

**Recommendation:** Prefer the Maven wrapper (`./mvnw`) when a repository provides it. It helps ensure consistent Maven versions across developer machines and CI environments.

---

## Maven — Quick Revision Checklist

- [ ] Maven is both a build automation and dependency management tool.
- [ ] `pom.xml` defines project metadata and dependencies.
- [ ] Maven coordinates consist of `groupId`, `artifactId`, and `version`.
- [ ] Dependencies have scopes that determine classpath availability.
- [ ] `src/main/java` contains application source code.
- [ ] `src/test/java` contains test source code.
- [ ] `src/main/resources` contains application resources.
- [ ] `target` contains generated build output.
- [ ] `.java` files are compiled into `.class` files.
- [ ] JAR files package compiled classes and resources.
- [ ] Maven lifecycle phases execute sequentially.
- [ ] `mvn package` automatically runs earlier required phases.
- [ ] `mvn install` installs the built artifact into the local Maven repository.
- [ ] `mvn dependency:tree` helps investigate dependency resolution.
- [ ] `mvn spring-boot:run` starts the Spring Boot application.
- [ ] Prefer `./mvnw` when the project provides Maven Wrapper.
