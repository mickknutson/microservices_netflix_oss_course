package webage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;


// add zipkin annotations
@EnableZipkinServer
@EnableDiscoveryClient
@SpringBootApplication
public class ZipkinService {
  public static void main(String[] args) {
    SpringApplication.run(ZipkinService.class, args);
  }
}
