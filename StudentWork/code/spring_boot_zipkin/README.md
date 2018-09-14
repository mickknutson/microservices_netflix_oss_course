Instructions to start this section
---

1. The Eureka Server must be running.
2. The Zuul Gateway must be started
3. Start the `poetry_service`
4. Use Postman to verify service `http://localhost:9000/poetry`
5. Start the `poem_service` on SERVER_PORT=9001
6. Use Postman to verify service `http://localhost:9001/poem`
7. Use Postman to verify service `http://localhost:8080/poem-service/fire-and-ice/second-line`
8. Start the `zipkin_service`
9. Open a browser to [http://localhost:9411/zipkin/](http://localhost:9411/zipkin/)


Next
---

**Running Hystrix Client**

`mvn clean spring-boot:run -e`


http://localhost:9000/poem




http://localhost:9001/fire-and-ice/title
http://localhost:9001/fire-and-ice/first-line
http://localhost:9001/fire-and-ice/second-line
http://localhost:9001/fire-and-ice/third-line
http://localhost:9001/fire-and-ice/author




http://localhost:8080/poem-service/fire-and-ice/title
http://localhost:8080/poem-service/fire-and-ice/first-line
http://localhost:8080/poem-service/fire-and-ice/second-line
http://localhost:8080/poem-service/fire-and-ice/third-line
http://localhost:8080/poem-service/fire-and-ice/author


http://localhost:8080/poem-service/poem


http://localhost:9411/zipkin/






Deprecated
https://sivalabs.in/2018/03/microservices-part-6-distributed-tracing-with-spring-cloud-sleuth-and-zipkin/
