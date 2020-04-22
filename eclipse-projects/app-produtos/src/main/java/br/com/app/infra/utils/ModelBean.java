package br.com.app.infra.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import br.com.app.infra.logger.AppLogger;

@Component
public class ModelBean {

    @Autowired
    private ApplicationContext applicationContext;

    public <T> T get(Class<T> bean) {
        try {
            return applicationContext.getBean(bean);
        } catch (Exception ex) {
            AppLogger.LOGGER.error(String.format("Bean '%s' not found", bean.getSimpleName()), ex);
            throw ex;
        }
    }
}