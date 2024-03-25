# Carmageddon

Fullstack little game written in React and Java Quarkus

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

Then you can access the game at ```http://localhost:8080/``` and the development dahboard at ```http://localhost:8080/dev-frontend```

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.

## How to play ?

- Run the application
- Go to ```http://localhost:8080/```
- Create a new game or join an existing game
- Join the party by giving your pseudonyme
- Your car appears in a garage, you can now move with the arrow keys and try to demolish the other vehicules

## Architecture

- **frontend**: the source code of the frontend application
- **src/main/java/org/acme**: the source code of the backend application
- **src/test/java/org/acme**: the test files of the backend
- **src/main/resources/META-INF/resources**: the frontend's static files served when the Quarkus application is launched

## Compilation

You can test a compilator in this project. Just run ```src/main/java/org/acme/Compiler.java```and look at the output file: ```src/main/java/org/acme/compiled/Calculation.java```

### How it works ?

The compiler looks at all files in ```src/main/toBeCompiled```. Then, for each files with ```.compil``` as extension, it adds a method call with the filename in a router. Finally, it creates a method with the filename and for each line where "PLUS" is written, it returns +1 and for each line where "MOINS" is written, it returns -1. In case of there's no "PLUS" or "MOINS" in the file, the method returns 0.
