package br.com.app.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppLogger {

    private AppLogger() {}

    public static final Logger LOGGER = LogManager.getLogger(AppLogger.class);

}
