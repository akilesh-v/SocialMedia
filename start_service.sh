#Build Project
mvn clean install

#Start the service
java -Dspring.config.location=src/main/resources/application.yml  -jar build/libs/socialmedia.jar