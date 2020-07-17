package id.web.sukenda.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication(scanBasePackages = "id.web.sukenda.*")
@EnableReactiveMongoRepositories(basePackages = "id.web.sukenda.*")
public class SpringBoilerplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoilerplateApplication.class, args);
    }

}
