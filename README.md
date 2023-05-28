###### Some decisions made:
- Brand and Model are unique by the name.
- Brand and Model, once created, cannot be modified via application
- Creation or modification of entities happens via <Entity>CreationModificationRequest classes
- Returning the data to the client (REST-client) happens via <Entity>Dto.
- Although both maven and gradle builds are provided and working, I have favoured maven.
- The environment details: Java(Temurin-11.0.13+8), maven(3.8.6), gradle(8.1.1), tested on (MacOs Ventura 13.3.1)


##### Points to improve on:
- Logging
- Error handling could be improved. Right now errors are being reported as a part of "message" property of the error thrown by spring-boot
- The example customer with the customer's DOB in the format **dd.MM.yyyy** could be provided right it failed until the user reads the feedback and passes the date in the right format.
- More controller tests cases could be written -especially regarding the data validations.
- Search API for customers could be provided and customers could be made unique (right now firstName, lastName, DOB is **NOT** the unique key)
- Searches for vehicles, customers and contracts should be paginated.

##### Running the project
1. After checking out the project, run wither of the commands `sh run_gradle.sh` or `sh run_mvn.sh`.
2. Use `sh stop.sh` to stop the application.
##### Important links:
- [Swagger-ui](http://localhost:8080/swagger-ui.html)
- [Open-API](http://localhost:8080/v3/api-docs/)