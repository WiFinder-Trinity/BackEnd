package insaif.rsdm.wifinder.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {
        "insaif.rsdm.wifinder.endpoint",
        "insaif.rsdm.wifinder.service"})
@EntityScan(basePackages = "insaif.rsdm.wifinder.model")
@EnableJpaRepositories(basePackages = "insaif.rsdm.wifinder.repository")
@EnableSwagger2
public class WifinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WifinderApplication.class, args);
    }

    @Bean
    public Docket produceApi(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("insaif.rsdm.wifinder.endpoint"))
                .paths(PathSelectors.any())//regex("/crowdsensing"))
                .build();

    }

    // Describe your apis
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Wifinder REST APIs")
                .description("This page lists all the rest APIs for Wifinder App.")
                .version("0.0.1-SNAPSHOT")
                .build();
    }
}
