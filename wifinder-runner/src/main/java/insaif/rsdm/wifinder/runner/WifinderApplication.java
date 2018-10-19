package insaif.rsdm.wifinder.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class WifinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WifinderApplication.class, args);
    }
}
