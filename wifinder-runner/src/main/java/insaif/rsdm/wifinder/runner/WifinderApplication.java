package insaif.rsdm.wifinder.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "insaif.rsdm.wifinder.endpoint",
        "insaif.rsdm.wifinder.service"})
@EntityScan(basePackages = "insaif.rsdm.wifinder.model")
@EnableJpaRepositories(basePackages = "insaif.rsdm.wifinder.repository")
public class WifinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WifinderApplication.class, args);
    }
}
