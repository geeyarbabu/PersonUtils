package accala.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = {"accala.test"})
public class CodingExerciseApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CodingExerciseApplication.class).run(args);
    }
}
