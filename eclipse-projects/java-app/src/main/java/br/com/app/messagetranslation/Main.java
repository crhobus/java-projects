package br.com.app.messagetranslation;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        System.out.println("Tradução de mensagens:");

        Date data = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date horario = Date.from(ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault()).toInstant());

        System.out.println("\n-------------------------------------------------------------------------------------\n");

        String locale = br.com.app.messagetranslation.Locale.PT_BR;
        System.out.println(locale);
        String str = Resource.resourceBundle(locale).getResource("app.credito_empresa");

        MessageFormat messageFormat = new MessageFormat(str, Locale.forLanguageTag(locale));
        String message = messageFormat.format(new Object[] { 0, "João da Silva", 0, "123.456.789-00", "Novas S.A", 150.25, "020", "Banco XP", "5971", "08789575", "8", data, horario });

        System.out.println(message);
        System.out.println("\n-------------------------------------------------------------------------------------\n");

        locale = br.com.app.messagetranslation.Locale.EN_US;
        System.out.println(locale);
        str = Resource.resourceBundle(locale).getResource("app.credito_empresa");

        messageFormat = new MessageFormat(str, Locale.forLanguageTag(locale));
        message = messageFormat.format(new Object[] { "César de Souza", "123.456.789-00", 150.25, "Novas S.A", "020", "Banco XP", "5971", "08789575", "8", data, horario });

        System.out.println(message);
        System.out.println("\n-------------------------------------------------------------------------------------\n");

        locale = br.com.app.messagetranslation.Locale.ES_ES;
        System.out.println(locale);
        str = Resource.resourceBundle(locale).getResource("app.credito_empresa");

        messageFormat = new MessageFormat(str, Locale.forLanguageTag(locale));
        message = messageFormat.format(new Object[] { 1, "Maria Almeida", "123.456.789-00", "Novas S.A", 150.25, "020", "Banco XP", "5971", "08789575", "8", data, horario });

        System.out.println(message);
    }

}
