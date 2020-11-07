package ru.netrax.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.netrax.Config.AppConfig;
import ru.netrax.Config.LocalizationConfig;

import java.util.Locale;

@Component
@AllArgsConstructor
public class LocaleServiceImpl implements LocaleService {
    private final IOService ioService;
    private final LocalizationConfig localizationConfig;

    @Override
    public Locale getLocale() {
        ioService.showMessageForStudent("Select language/Выберите язык");
        ioService.showMessageForStudent("en/ru");
        return new Locale(ioService.getStudentAnswer("Enter your answer"));
    }

    @Override
    public String checkLocale(Locale locale, AppConfig appConfig) {
        return locale.getLanguage().equalsIgnoreCase(localizationConfig.getEnglish().toString()) ?
                appConfig.getResource() :
                appConfig.getResource().substring(0, appConfig.getResource().indexOf(".")) + "_ru.csv";
    }
}
