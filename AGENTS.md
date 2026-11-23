# Agent guidance

## Project layout and architecture

- This is a single-module Maven learning repository for Java book exercises, organized under `src/main/java/chapter1` through `chapter14`, not a deployed service. `pom.xml` declares no external dependencies.
- Treat chapters as separate learning stages. Keep changes and imports within the requested chapter rather than consolidating similarly named classes across chapters (for example, `chapter13.project.entity.dinosaur.Dinosaur` and its `chapter14` counterpart).
- Early chapters use standalone `ExerciseN_M` and `Project` entry points. Later chapters have `chapterN.project` applications; chapters 13–14 put standalone examples in `exercises/`.
- For the later park-management applications, follow `src/main/java/chapter14/project/App.java`: it seeds in-memory data, constructs services and controllers directly, and passes one shared `Scanner` to controllers. The flow is console menu → `api/*Controller` → `service/*Service` → `entity/*`; `api` is not an HTTP API.
- Chapter 13–14 sorting helpers live in `project/collections/` (for example, `DinosaurComparator.BY_NAME`). Reuse the target chapter's comparators when extending sorted displays.

## Build, run, and validation

- Use JDK 25: `pom.xml` sets both compiler source and target to `25`, with UTF-8 encoding. Run commands from the repository root; use installed Maven (`mvn`), since no Maven wrapper scripts are present.
- Compile with `mvn -B compile`; validate with `mvn -B test`. The latter currently compiles successfully but has no automated tests to execute: `src/test/java` is empty and no test framework is declared.
- After compiling, run an individual example with `java -cp target/classes chapter14.exercises.TestPredicate`, or the interactive park application with `java -cp target/classes chapter14.project.App`. In IntelliJ, run/debug the corresponding `main` method with the repository root as the working directory.
- Classes named `Test*` under `src/main/java` are console demonstrations, not unit tests; inspect their output when validating changes to those examples. The chapter 14 park application's main-menu option `8` exits via `System.exit(0)`.
- `target/` is generated build output; edit sources under `src/`, not compiled classes.

## Local implementation patterns

- Match neighboring Java files: two-space indentation, package names matching chapter directories, and descriptive Javadoc plus separator comments in the larger project classes. Preserve the instructional style of the chapter rather than modernizing unrelated examples.
- In chapter 14 controllers, reuse `api/Util.java` for input parsing and date/time formats. Preserve the `nextLine()` consumption after token reads such as `nextInt()`; see `DinosaurController.manageDinosaurs()`.
- Follow the target chapter's existing error contracts. Chapter 14 input helpers use `InputException` and `AppException`, caught by console menu handlers; dinosaur-care operations also have domain exceptions under `project/exception/`.
- Services are in-memory collection owners. For example, chapter 14 `DinosaurService` uses a `Set<Dinosaur>`, enforces `App.MAX_DINOSAURS`, and returns a snapshot via `Set.copyOf()` from `getAllDinosaurs()`; mutate through service methods rather than that returned set.
- File examples use repository-relative paths, not classpath resource loading: `chapter11/ReadFileExample.java` reads `src/main/resources/input.txt`, and `chapter12/ScannerFile.java` reads `src/main/resources/age.txt`. Running `chapter11/WriteFileExample.java` overwrites `src/main/resources/output.txt`; avoid treating it as a side-effect-free smoke test.
