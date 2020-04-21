package br.com.app.infra.database;

import java.io.InputStream;

import br.com.app.infra.logger.AppLogger;

public class NativeQueryHelper {

    private NativeQueryHelper() {}

    public static String readQueryFromClasspath(String path) throws Exception {
        try (InputStream inputStream = NativeQueryHelper.class.getClassLoader().getResourceAsStream(path)) {
            return new String(inputStream.readAllBytes());
        } catch (Exception e) {
            AppLogger.LOGGER.error("Couldn't read file in path {}", path);
            AppLogger.LOGGER.error("Error log:", e);
            throw new Exception("Error read file");
        }
    }
}
