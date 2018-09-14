Github Repository location
--------------------------

#### [https://github.com/mickknutson/microservices_netflix_oss_course](https://github.com/mickknutson/microservices_netflix_oss_course)


---


Spring Cloud Data Flow Demo
---------------------------

**Steps in order to reproduce:**

docker run --name dataflow-rabbit -p 15672:15672 -p 5672:5672 -d rabbitmq:management


docker run --name dataflow-redis -p 6379:6379 -d redis


docker run --name dataflow-mysql -e MYSQL_ROOT_PASSWORD=dataflow -e MYSQL_DATABASE=scdf -p 3306:3306 -d mysql:5.7



java -jar spring-cloud-dataflow-server-local-1.6.1.RELEASE.jar --spring.datasource.url=jdbc:mysql://localhost:3306/scdf --spring.datasource.username=root --spring.datasource.password=dataflow --spring.datasource.driver-class-name=org.mariadb.jdbc.Driver --spring.rabbitmq.host=127.0.0.1 --spring.rabbitmq.port=5672 --spring.rabbitmq.username=guest --spring.rabbitmq.password=guest



http://localhost:9393/dashboard



Source – These are the available sources of data. You start your streaming pipelines from them.
Processor – These take data and send them further in the processing pipeline. They sit in the middle.
Sink – They are the endpoints for the streams. This is where the data ends in the end.





http://bit.ly/Celsius-SR3-stream-applications-rabbit-maven




http --port=7171 | transform --expression=payload.toUpperCase() | file --directory=/Users/mickknutson/dataflow-output


Rabbit MQ:
http://localhost:15672




Send messages:

http://localhost:7171



---

    mvn sonar:sonar \
      -Dsonar.projectKey=mickknutson_microservices_netflix_oss_course \
      -Dsonar.organization=mickknutson-github \
      -Dsonar.host.url=https://sonarcloud.io \
      -Dsonar.login=215c023bae5b7d8e10116f40ed6d4bfb271e4ed6


