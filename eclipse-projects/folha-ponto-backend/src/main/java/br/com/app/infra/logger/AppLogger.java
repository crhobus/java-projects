package br.com.app.infra.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {

    private AppLogger() {}

    public static final Logger LOGGER = LoggerFactory.getLogger(AppLogger.class.getName());

}
