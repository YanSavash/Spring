package ru.netrax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.netrax.Config.AppConfig;
import ru.netrax.Service.QuestionnaireService;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class Application {
    public static void main(String[] args) {
        var applicationContext = SpringApplication.run(Application.class, args);
        applicationContext.getBean(QuestionnaireService.class).startTesting();
    }
}