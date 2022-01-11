# martian-robots

This application leverages Spring Boot to take advantage of its IoC framework.

To run:
```
./gradlew clean build bootRun
```

An assumption has been made that robots are able to share the same coordinates at the same time.

This readme is concise as hopefully the code should speak for itself.

Given more time the next steps would be to add interface segregation and inject the Mars object in a more consistent manner - I don't like that it has had to be injected into the Robot domain object.
