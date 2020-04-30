package br.com.app.infra.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

import br.com.app.infra.logger.AppLogger;

public class CacheEventLogger implements CacheEventListener<Object, Object> {

    @Override
    public void onEvent(CacheEvent<? extends Object, ? extends Object> event) {
        AppLogger.LOGGER.info("Cache event = {}, Key = {},  Old value = {}, New value = {}", event.getType(), event.getKey(), event.getOldValue(), event.getNewValue());
    }

}
