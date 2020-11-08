package ru.netrax.Service;

import ru.netrax.Config.AppConfig;

import java.util.Locale;

public interface LocaleService {
    Locale getLocale();
    String checkLocale(Locale locale, AppConfig appConfig);
}
