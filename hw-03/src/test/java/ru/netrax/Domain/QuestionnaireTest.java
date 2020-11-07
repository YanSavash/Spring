package ru.netrax.Domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Проверка класса Questionnaire")
class QuestionnaireTest {
    private Questionnaire questionnaire;

    @BeforeEach
    void setQuestionnaire(){
        questionnaire = new Questionnaire();
        questionnaire.setQuestion("Does java support operator overloading?");
        questionnaire.setAnswer("No");
        questionnaire.setOptions(new ArrayList<>(Arrays.asList("Yes", "No")));
    }

    @Test
    @DisplayName("Успешная проверка ответа")
    void testingCorrectAnswer() {
        assertEquals("No", questionnaire.getAnswer());
    }

    @Test
    @DisplayName("Успешная проверка вариантов ответа")
    void testingCorrectOptions() {
        assertEquals(new ArrayList<>(Arrays.asList("Yes", "No")), questionnaire.getOptions());
    }

    @Test
    @DisplayName("Успешная проверка вопроса")
    void testingCorrectQuestion() {
        assertEquals("Does java support operator overloading?", questionnaire.getQuestion());
    }
}