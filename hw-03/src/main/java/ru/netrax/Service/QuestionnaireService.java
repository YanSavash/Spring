package ru.netrax.Service;

import ru.netrax.Domain.Questionnaire;

import java.util.List;

public interface QuestionnaireService {

    void startTesting();

    List<Questionnaire> getQuestionnaire(String resource);
}
