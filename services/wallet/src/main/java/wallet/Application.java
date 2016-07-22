package wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by edgardneto on 6/22/16.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setDefaultProperties(new ApplicationPropertyInitializer(args).getPropertiesMap());
        springApplication.run(args);
    }

}
