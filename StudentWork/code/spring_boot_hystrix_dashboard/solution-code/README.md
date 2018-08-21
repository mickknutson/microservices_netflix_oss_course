# Hystrix Dashboard

To run locally:

````
mvn clean spring-boot:run
````

In your browser, go to [http://localhost:7979/](http://localhost:7979/) # port configurable in `application.yml`

On the home page is a form where you can
enter the URL for an event stream to monitor, for example (the
customers service running locally):
`http://localhost:8090/hystrix.stream`

To view the Mock Stream:
`http://localhost:7979/mock.stream`

Any app that uses
`@EnableHystrix` will expose the stream.

To aggregate many streams together you can use the
[Turbine sample](https://github.com/spring-cloud-samples/turbine).
