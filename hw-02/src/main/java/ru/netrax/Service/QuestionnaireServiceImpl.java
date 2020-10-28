package ru.netrax.Service;

import org.springframework.core.io.ResourceLoader;
import ru.netrax.Dao.QuestionnaireDao;
import ru.netrax.Dao.StudentDao;
import ru.netrax.Domain.Questionnaire;

import java.util.List;

public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final QuestionnaireDao dao;
    private final String resource;
    private final ResourceLoader resourceLoader;
    private final IOService ioService;
    private final StudentDao studentDao;
    private final int minCount;

    public QuestionnaireServiceImpl(QuestionnaireDao dao, String resource, ResourceLoader resourceLoader,
                                    IOService ioService, StudentDao studentDao, int minCount) {
        this.dao = dao;
        this.resource = resource;
        this.resourceLoader = resourceLoader;
        this.ioService = ioService;
        this.studentDao = studentDao;
        this.minCount = minCount;
    }

    @Override
    public void startTesting() {
        ioService.showMessageForStudent("Start testing. Enter your name and surname");
        studentDao.newStudent(ioService.getStudentNameOrSurname("Enter name"),
                ioService.getStudentNameOrSurname("Enter surname"));
        List<Questionnaire> questionnaireList = getQuestionnaire();
        var ref = new Object() {
            int count = 0;
        };
        questionnaireList.forEach(list -> {
            ioService.showMessageForStudent(list.getQuestion());
            list.getOptions().forEach(ioService::showMessageForStudent);
            if (ioService.getStudentAnswer().equalsIgnoreCase(list.getAnswer())) {
                ref.count++;
            }
        });
        if (ref.count >= minCount) {
            ioService.showMessageForStudent("Congratulation, you passed test. Your result is " + ref.count);
        } else {
            ioService.showMessageForStudent("Sorry, but you need study much more. Your result is " + ref.count);
        }
    }

    public List<Questionnaire> getQuestionnaire() {
        return dao.getQuestionnaire(resourceLoader.getResource(resource));
    }
}
