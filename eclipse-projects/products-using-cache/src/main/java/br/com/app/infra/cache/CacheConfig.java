package br.com.app.infra.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.app.infra.logger.AppLogger;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String PRODUTO_CACHE = "produtoCache";

    /*
     * Limpa todo o cache a meia noite
     * 
     * <segundo> <minuto> <hora> <dia> <mês> <dia da semana>
     */
    @CacheEvict(allEntries = true, value = { PRODUTO_CACHE })
    @Scheduled(cron = "* * 0 * * *")
    public void clearCache() {
        AppLogger.LOGGER.info("Limpando o cache");
    }
}
