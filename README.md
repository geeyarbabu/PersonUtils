#Persons Util

To create a jar file, run the following command: 
`mvm clean install`


To run a jar file, run the following command:
`java -jar coding.exercise-1.0-SNAPSHOT.jar`

To get the api documentation, you can follow to the swagger ui (while the application is running):
###swagger url: http://localhost:9008/v2/api-docs

The Json from the swagger ui could be used to import into postman, and they could be used to call the apis hosted by the application 

The database used is H2, and the h2 console is available at:
http://localhost:9008/h2-console/

`username: sa`

`password: {please find the password in application.yml under resources}`
