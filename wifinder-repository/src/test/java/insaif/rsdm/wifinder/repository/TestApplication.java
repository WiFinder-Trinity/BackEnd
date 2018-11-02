package insaif.rsdm.wifinder.repository;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "insaif.rsdm.wifinder.model",
        "insaif.rsdm.wifinder.repository"
})
@EntityScan(basePackages = "insaif.rsdm.wifinder.model")
public class TestApplication {
}
