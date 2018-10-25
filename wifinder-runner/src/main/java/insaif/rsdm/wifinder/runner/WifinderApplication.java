package insaif.rsdm.wifinder.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"insaif.rsdm.wifinder.endpoint"})
public class WifinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WifinderApplication.class, args);
    }
}
