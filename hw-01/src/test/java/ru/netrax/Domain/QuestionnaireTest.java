package ru.netrax.Domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QuestionnaireTest {

    @Test
    @DisplayName("Получение вопроса")
    void testingCorrectFields() {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setQuestion("Does java support operator overloading?");
        questionnaire.setAnswer("No");
        questionnaire.setOptions(new ArrayList<>(Arrays.asList("Yes", "No")));
        assertEquals("Does java support operator overloading?", questionnaire.getQuestion());
        assertEquals("No", questionnaire.getAnswer());
        assertEquals(new ArrayList<>(Arrays.asList("Yes", "No")), questionnaire.getOptions());
    }
}