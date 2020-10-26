package ru.netrax.Dao;

import org.springframework.core.io.Resource;
import ru.netrax.Domain.Questionnaire;

import java.util.List;

public interface QuestionnaireDao {

    List<Questionnaire> getQuestionnaire(Resource resource);
}
