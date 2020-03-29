package br.com.app.infra.locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Resource {

    @Autowired
    private MessageSource messageSource;

    public String getResource(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    public String getResource(String key, Object[] parameters) {
        return messageSource.getMessage(key, parameters, LocaleContextHolder.getLocale());
    }
}
