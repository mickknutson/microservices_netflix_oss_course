package poetry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 */
@RestController
@SpringBootApplication
public class PoetryService {

    private static String ZUUL_URL = "http://localhost:8080/";
    private static String FIRE_AND_ICE = "poem-service/fire-and-ice/";

    private static Logger log = LoggerFactory.getLogger(PoetryService.class);

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    // add task executor
    @Autowired
    private TaskExecutor taskExecutor;

    public static void main(String[] args) {
        SpringApplication.run(PoetryService.class, args);
    }

    @RequestMapping("/poem")
    public String getPoem() {

        StringBuilder sb = new StringBuilder();

        sb.append(restTemplate.getForObject(
                ZUUL_URL + FIRE_AND_ICE + "title",
                String.class));

        // get the rest of the poem
        sb.append(restTemplate.getForObject(
                ZUUL_URL + FIRE_AND_ICE + "first-line",
                String.class));

        sb.append(restTemplate.getForObject(
                ZUUL_URL + FIRE_AND_ICE + "second-line",
                String.class));

        sb.append(restTemplate.getForObject(
                ZUUL_URL + FIRE_AND_ICE + "third-line",
                String.class));

        sb.append(restTemplate.getForObject(
                ZUUL_URL + FIRE_AND_ICE + "author",
                String.class));


        log.info(sb.toString());

        return sb.toString();
    }

    // Add getPoemAsync() here
    @RequestMapping("/poem-async")
    public String getPoemAsync() throws ExecutionException, InterruptedException {

        CompletableFuture<String> title =
                CompletableFuture.supplyAsync(
                        () -> getPoemSection("title"), taskExecutor);
        CompletableFuture<String> firstLine =
                CompletableFuture.supplyAsync(
                        () -> getPoemSection("first-line"), taskExecutor);
        CompletableFuture<String> secondLine =
                CompletableFuture.supplyAsync(
                        () -> getPoemSection("second-line"), taskExecutor);
        CompletableFuture<String> thirdLine =
                CompletableFuture.supplyAsync(
                        () -> getPoemSection("third-line"), taskExecutor);
        CompletableFuture<String> author =
                CompletableFuture.supplyAsync(
                        () -> getPoemSection("author"), taskExecutor);

        // wait for all results
        CompletableFuture.allOf(
                title,
                firstLine,
                secondLine,
                thirdLine,
                author).join();

        String poem = title.get() +
                firstLine.get() +
                secondLine.get() +
                thirdLine.get() +
                author.get();

        log.info(poem);
        return poem;
    }


    // Add the asynchronous getPoemSection
    @Async
    public String getPoemSection(String sectionName) {
        String url = ZUUL_URL + FIRE_AND_ICE + sectionName;
        String section = restTemplate.getForObject(url, String.class);
        return section;
    }
}

// create the ThreadConfig class here
@Configuration
class ThreadConfig {
    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);
        executor.setMaxPoolSize(6);
        executor.setThreadNamePrefix("default_task_executor_thread");
        executor.initialize();
        return executor;
    }
}