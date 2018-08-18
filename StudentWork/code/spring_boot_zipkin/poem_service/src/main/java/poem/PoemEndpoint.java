package poem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fire-and-ice")
public class PoemEndpoint {

    private static Logger log = LoggerFactory.getLogger(PoemEndpoint.class);

    // add delay function here
    private static int DELAY_TIME = 100;

    private static void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // add /title handler here
    @RequestMapping("/title")
    public String getTitle() {
        String title = "Fire and Ice\n\n";

        delay(DELAY_TIME);

        log.info(title);
        return title;

    }

    @RequestMapping("/first-line")
    public String getFirstLine() {

        StringBuilder sb = new StringBuilder();
        sb.append("Some say the world will end in fire,\n");
        sb.append("Some say in ice.\n");
        String firtLine = sb.toString();

        delay(DELAY_TIME);

        log.info(firtLine);
        return firtLine;
    }

    // add /second-line handler
    @RequestMapping("/second-line")
    public String getSecondLine() {
        StringBuilder sb = new StringBuilder();
        sb.append("From what I've tasted of desire\n");
        sb.append("I hold with those who favor fire.\n");
        String secondLine = sb.toString();

        delay(DELAY_TIME);

        log.info(secondLine);
        return secondLine;
    }

    // add /third-line handler
    @RequestMapping("/third-line")
    public String getThirdLine() {
        StringBuilder sb = new StringBuilder();
        sb.append("But if it had to perish twice,\n");
        sb.append("I think I know enough of hate\n");
        sb.append("To say that for destruction ice\n");
        sb.append("Is also great\n");
        sb.append("And would suffice.\n");
        String thirdLine = sb.toString();

        delay(DELAY_TIME);

        log.info(thirdLine);
        return thirdLine;
    }

    // add /author handler
    @RequestMapping("/author")
    public String getAuthor() {
        String author = "\nby Robert Frost\n";

        delay(DELAY_TIME);

        log.info(author);
        return author;

    }

}