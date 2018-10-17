# Boot_MicroServices
`scheduleIngest`
#### Built On
 - Java 8
 - Spring Boot
 - Gradle
 #### Scope
  To schedule and publish content based on publish_schedule flag.
 #### Run
  - With gradle build and generate jar file.
#### API's
  - /scheduleAndPublish - Check scheduled content in past since now, Future tasks will be scheduled. Past tasks will be run immediately
  - /checkVersion - Will return simple application name with version
