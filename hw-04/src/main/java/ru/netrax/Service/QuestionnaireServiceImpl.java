package ru.netrax.Service;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.netrax.Config.AppConfig;
import ru.netrax.Dao.QuestionnaireDao;
import ru.netrax.Dao.StudentDao;
import ru.netrax.Domain.Questionnaire;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final QuestionnaireDao dao;
    private final ResourceLoader resourceLoader;
    private final IOService ioService;
    private final StudentDao studentDao;
    private final AppConfig appConfig;
    private final LocaleService localeService;
    private final MessageSource messageSource;

    @Override
    public void startTesting() {
        Locale locale = greetings();
        String fileName = localeService.checkLocale(locale, appConfig);
        int count = testing(fileName, locale);
        showResult(count, locale);
    }

    public void showResult(int count, Locale locale) {
        if (count >= appConfig.getMinCount()) {
            ioService.showMessageForStudent(messageSource.getMessage("Testing.goodResult", new Object[]{count}, locale));
        } else {
            ioService.showMessageForStudent(messageSource.getMessage("Testing.badResult", new Object[]{count}, locale));
        }
    }

    public int testing(String fileName, Locale locale) {
        AtomicInteger count = new AtomicInteger();
        getQuestionnaire(fileName).forEach(list -> {
            ioService.showMessageForStudent(list.getQuestion());
            list.getOptions().forEach(ioService::showMessageForStudent);
            if (ioService.getStudentAnswer(messageSource.getMessage("Testing.enter", null, locale))
                    .equalsIgnoreCase(list.getAnswer())) {
                count.getAndIncrement();
            }
        });
        return count.get();
    }

    public Locale greetings() {
        Locale locale = localeService.getLocale();
        ioService.showMessageForStudent(messageSource.getMessage("Testing.start", null, locale));
        studentDao.newStudent(ioService.getStudentNameOrSurname(messageSource.getMessage("Testing.name", null, locale)),
                ioService.getStudentNameOrSurname(messageSource.getMessage("Testing.surname", null, locale)));
        return locale;
    }

    public List<Questionnaire> getQuestionnaire(String resource) {
        return dao.getQuestionnaire(resourceLoader.getResource(resource));
    }
}
