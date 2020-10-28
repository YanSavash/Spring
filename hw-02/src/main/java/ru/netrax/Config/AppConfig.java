package ru.netrax.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ResourceLoader;
import ru.netrax.Dao.QuestionnaireDao;
import ru.netrax.Dao.QuestionnaireDaoSimple;
import ru.netrax.Dao.StudentDao;
import ru.netrax.Service.IOService;
import ru.netrax.Service.QuestionnaireServiceImpl;

@ComponentScan(basePackages = "ru.netrax")
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    QuestionnaireDaoSimple questionnaireDao() {
        return new QuestionnaireDaoSimple();
    }

    @Bean
    QuestionnaireServiceImpl questionnaireService(QuestionnaireDao daoSimple, @Value("${db.resource}") String resource,
                                                  ResourceLoader resourceLoader, IOService ioService, StudentDao studentDao,
                                                  @Value("${test.min}") int minCount) {
        return new QuestionnaireServiceImpl(daoSimple, resource, resourceLoader, ioService, studentDao, minCount);
    }
}
