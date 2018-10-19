package insaif.rsdm.wifinder.repository;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "insaif.rsdm.wifinder.model",
        "insaif.rsdm.wifinder.repository"
})
public class TestApplication {
}
