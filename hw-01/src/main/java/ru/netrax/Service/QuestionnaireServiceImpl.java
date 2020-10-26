package ru.netrax.Service;

import org.springframework.core.io.Resource;
import ru.netrax.Dao.QuestionnaireDao;
import ru.netrax.Domain.Questionnaire;

import java.util.List;

public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final QuestionnaireDao dao;
    private final Resource resource;

    public QuestionnaireServiceImpl(QuestionnaireDao dao, Resource resource) {
        this.dao = dao;
        this.resource = resource;
    }

    public List<Questionnaire> getQuestionnaire() {
        return dao.getQuestionnaire(resource);
    }
}
