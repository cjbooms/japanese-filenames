# Java Handling of Japanese Characters in File Names

Comparing problem characters in Java 8 with both Gradle & Maven 


Results for Gradle and Maven. 
* Maven works fine
* Gradle cannot copy files with supplementary characters to the build folder.

Furthermore it appears as though file names containing supplementary 
characters / surrogate pairs cannot be accessed with the Java File class 
on Mac OSX. The default locale on the OS must be set to a locale that 
includes those characters.
* http://bugs.java.com/bugdatabase/view_bug.do?bug_id=4733494
* http://stackoverflow.com/a/1545670/1026785

# Build Instructions
This project contains both maven and gradle build files to allow testing with both.
The File.exists check is skipped on MAC-OSX.

## Maven
`mvn clean install`

## Gradle
`./gradlew clean build`

