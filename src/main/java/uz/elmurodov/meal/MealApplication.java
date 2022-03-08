package uz.elmurodov.meal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uz.elmurodov.meal.properties.OpenApiProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        OpenApiProperties.class
})
public class MealApplication {

    public static void main(String[] args) {
        SpringApplication.run(MealApplication.class, args);
    }

}
