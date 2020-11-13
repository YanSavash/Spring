package ru.netrax.Shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.netrax.Config.AppConfig;
import ru.netrax.Service.LocaleService;
import ru.netrax.Service.QuestionnaireServiceImpl;

import java.util.Locale;

@RequiredArgsConstructor
@ShellComponent
public class ApplicationEventsCommands {
    private final AppConfig appConfig;
    private final LocaleService localeService;
    private final QuestionnaireServiceImpl questionnaireService;
    private Locale locale;
    private String fileName;
    private Integer count;


    @ShellMethod(value = "Greetings", key = {"g", "greetings"})
    public void greetings() {
        locale = questionnaireService.greetings();
    }

    @ShellMethod(value = "CheckLocale", key = {"c", "check"})
    @ShellMethodAvailability(value = "isLocaleAvailable")
    public void checkLocale() {
        fileName = localeService.checkLocale(locale, appConfig);
    }

    @ShellMethod(value = "Testing", key = {"t", "testing"})
    @ShellMethodAvailability(value = "isLocaleAndFileNameAvailable")
    public void testing() {
        count = questionnaireService.testing(fileName, locale);
    }

    @ShellMethod(value = "ShowResult", key = {"s", "show"})
    @ShellMethodAvailability(value = "isTestingResultAvailable")
    public void showResult() {
        questionnaireService.showResult(count, locale);
        count = null;
    }

    private Availability isLocaleAvailable() {
        return locale == null ? Availability.unavailable("First, choose locale") : Availability.available();
    }

    private Availability isLocaleAndFileNameAvailable() {
        return locale == null || fileName == null ? Availability.unavailable("Choose locale and file name") : Availability.available();
    }

    private Availability isTestingResultAvailable() {
        return count == null ? Availability.unavailable("Before finish testing") : Availability.available();
    }
}
