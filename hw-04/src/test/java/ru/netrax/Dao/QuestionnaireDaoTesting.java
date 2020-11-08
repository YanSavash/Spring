package ru.netrax.Dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import ru.netrax.Domain.Questionnaire;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Тестирование класса QuestionnaireDaoSimple")
public class QuestionnaireDaoTesting {

    @Configuration
    class TestConfiguration {
        @Bean
        public QuestionnaireDao questionnaireDao() {
            return new QuestionnaireDaoSimple();
        }
    }

    @Autowired
    private QuestionnaireDao questionnaireDao;

    private final Resource resource = new DefaultResourceLoader().getResource("Questionnaire.csv");

    @Test
    @DisplayName("Успешное получение количества вопросов")
    void testingCorrectOptions() {
        List<Questionnaire> questionnaire = questionnaireDao.getQuestionnaire(resource);
        assertEquals(questionnaire.size(), 5);
    }
}