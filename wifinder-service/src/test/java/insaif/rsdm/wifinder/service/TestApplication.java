package insaif.rsdm.wifinder.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = {
        "insaif.rsdm.wifinder.model",
        "insaif.rsdm.wifinder.service",
})
@EntityScan(basePackages = "insaif.rsdm.wifinder.model")
public class TestApplication {
}
