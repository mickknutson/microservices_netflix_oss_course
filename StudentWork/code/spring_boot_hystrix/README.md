Instructions to start this section
---

1. The Eureka Server must be running.
2. At least one Eureka client must be running to serve as the service for this Client.

Next
---

**Running Hystrix Client**

`mvn clean spring-boot:run -e`

Next:

On the command-line, enter a name:
`Chuck Norris` (or yourself)

**View Hystrix stream from Client:**

[http://localhost:8090/actuator/hystrix.stream](http://localhost:8090/actuator/hystrix.stream)

>`data:{"type":"ping"} data:{"type":"ping"}`

**Verify detailed health actuator:**

[http://localhost:8090/actuator/health](http://localhost:8090/actuator/health)

>`{"status":"UP","details":{"hystrix":{"status":"UP"},"diskSpace":{"status":"UP","details":{"total":1000345825280,"free":88683868160,"threshold":10485760}},"refreshScope":{"status":"UP"},"discoveryComposite":{"status":"UP","details":{"discoveryClient":{"status":"UP","details":{"services":[]}},"eureka":{"description":"Remote status from Eureka server","status":"UNKNOWN","details":{"applications":{}}}}}}}`

