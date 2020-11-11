package ru.netrax.Config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class AppConfig {

    private Locale locale;
    private String resource;
    private int minCount;
}
