# Java Handling of Japanese Characters in File Names

Comparing problem characters in Java 8 with both Gradle & Maven 

It appears as though Filenames containing supplementary characters / surrogate pairs cannot be accessed with the Java File class.
http://stackoverflow.com/questions/1545625/java-cant-open-a-file-with-surrogate-unicode-values-in-the-filename?noredirect=1&lq=1

Further more there appear to be differences between Gradle and Maven. 
* Maven works fine
* Gradle cannot copy files with supplementary characters to the build folder.

# Build Instructions
This project contains both maven and gradle build files to allow testing with both.

## Maven
`mvn clean install`

## Gradle
`./gradlew clean build`

