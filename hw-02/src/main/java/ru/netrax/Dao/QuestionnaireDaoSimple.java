package ru.netrax.Dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.Resource;
import ru.netrax.Domain.Questionnaire;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionnaireDaoSimple implements QuestionnaireDao {

    @Override
    public List<Questionnaire> getQuestionnaire(Resource resource) {
        List<Questionnaire> questionnaireList = new ArrayList<>();
        try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
            questionnaireList = new CsvToBeanBuilder<Questionnaire>(reader)
                    .withSeparator(';')
                    .withType(Questionnaire.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(questionnaireList);
        return questionnaireList;
    }
}
