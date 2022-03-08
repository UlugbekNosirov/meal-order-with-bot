package uz.elmurodov.meal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.elmurodov.meal.domain.AuthUser;
import uz.elmurodov.meal.enums.AuthRole;
import uz.elmurodov.meal.enums.Department;
import uz.elmurodov.meal.enums.Position;
import uz.elmurodov.meal.enums.Status;
import uz.elmurodov.meal.properties.OpenApiProperties;
import uz.elmurodov.meal.repository.AuthUserRepository;

@SpringBootApplication
@EnableConfigurationProperties({
        OpenApiProperties.class
})
public class MealApplication {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public MealApplication(
            AuthUserRepository authUserRepository,
            PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public static void main(String[] args) {
        SpringApplication.run(MealApplication.class, args);
    }


//    @Bean
    CommandLineRunner runner() {
        return (args) -> {
            authUserRepository.deleteAll();
            String encode = passwordEncoder.encode("123");
            System.out.println("encode = " + encode);

            AuthUser admin = AuthUser.childBuilder()
                    .username("admin")
                    .password(encode)
                    .role(AuthRole.ADMIN)
                    .position(Position.CEO)
                    .status(Status.ACTIVE)
                    .department(Department.SELLS)
                    .fullName("adminov admin adminovich")
                    .phone("+998906543210")
                    .build();
            authUserRepository.save(admin);
        };
    }

}
