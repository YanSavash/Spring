package ru.netrax;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netrax.Config.AppConfig;
import ru.netrax.Service.QuestionnaireService;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(QuestionnaireService.class).startTesting();
    }
}
