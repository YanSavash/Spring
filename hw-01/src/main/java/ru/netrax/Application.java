package ru.netrax;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.netrax.Domain.Questionnaire;
import ru.netrax.Service.QuestionnaireService;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new
                ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionnaireService service = context.getBean(QuestionnaireService.class);
        List<Questionnaire> questionnaireList = service.getQuestionnaire();
        questionnaireList.forEach(e -> System.out.println(e.getQuestion()));
    }
}
