package com.utils.java8.cap10Datas;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author crhobus
 */
public class Sistema {

    public static void main(String[] args) {
        Calendar mesQueVemCalendar = Calendar.getInstance();
        mesQueVemCalendar.add(Calendar.MONTH, 1);
        System.out.println(mesQueVemCalendar.getTime());

        //com nova api data
        LocalDate mesQueVem = LocalDate.now().plusMonths(1);//cria uma nova data adicionando mais um mês em relação ao atual, mesmo exemplo acima só agora com java 8
        System.out.println(mesQueVem);

        LocalDate dia = LocalDate.now().plusDays(1);//cria uma nova data adicionando mais um dia no atual mês
        System.out.println(dia);

        LocalDate ano = LocalDate.now().plusYears(1);//cria uma nova data adicionando mais um ano em relação ao atual
        System.out.println(ano);

        //para decrementar
        mesQueVem = LocalDate.now().minusMonths(1);
        System.out.println(mesQueVem);

        dia = LocalDate.now().minusDays(1);
        System.out.println(dia);

        ano = LocalDate.now().minusYears(1);
        System.out.println(ano);

        System.out.println("");
        //LocalDate somente a data, sem horario e timezone
        //LocalDateTime é data, horário e timezone
        //LocalTime é somente o horário
        System.out.println("");

        LocalDateTime agora = LocalDateTime.now();
        System.out.println(agora);

        LocalTime agora2 = LocalTime.now();
        System.out.println(agora2);

        LocalDateTime hojeAoMeioDia = LocalDate.now().atTime(12, 0);//atTime seta um horário a data
        System.out.println(hojeAoMeioDia);

        agora2 = LocalTime.now();
        LocalDate hoje = LocalDate.now();
        LocalDateTime dataEhora = hoje.atTime(agora2);
        System.out.println(dataEhora);

        ZonedDateTime dataComHoraETimezone = dataEhora.atZone(ZoneId.of("America/Sao_Paulo"));//cria uma data com timezone
        System.out.println(dataComHoraETimezone);

        LocalDateTime semTimeZone = dataComHoraETimezone.toLocalDateTime();//to.. conversão de datas para outras medidas
        System.out.println(semTimeZone);

        LocalDate date = LocalDate.of(2014, 12, 25);
        LocalDateTime dateTime = LocalDateTime.of(2014, 12, 25, 10, 30);
        System.out.println(date);
        System.out.println(dateTime);

        LocalDate dataDoPassado = LocalDate.now().withYear(1988);
        System.out.println(dataDoPassado);

        System.out.println(dataDoPassado.getYear());

        System.out.println("");

        hoje = LocalDate.now();
        LocalDate amanha = LocalDate.now().plusDays(1);
        System.out.println(hoje.isBefore(amanha));
        System.out.println(hoje.isAfter(amanha));
        System.out.println(hoje.isEqual(amanha));

        ZonedDateTime tokyo = ZonedDateTime.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
        ZonedDateTime saoPaulo = ZonedDateTime.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));
        System.out.println(tokyo.isEqual(saoPaulo));

        //
        tokyo = ZonedDateTime.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
        ZonedDateTime saoPaulo2 = ZonedDateTime.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));
        tokyo = tokyo.plusHours(12);
        System.out.println(tokyo.isEqual(saoPaulo2));

        System.out.println("");

        System.out.println("hoje é dia: " + MonthDay.now().getDayOfMonth());

        YearMonth ym = YearMonth.from(LocalDate.now());
        System.out.println(ym.getMonth() + " " + ym.getYear());

        System.out.println(LocalDate.of(2014, 12, 25));
        System.out.println(LocalDate.of(2014, Month.DECEMBER, 25));

        System.out.println(Month.DECEMBER.firstMonthOfQuarter());
        System.out.println(Month.DECEMBER.plus(2));
        System.out.println(Month.DECEMBER.minus(1));

        Locale pt = new Locale("pt");
        System.out.println(Month.DECEMBER.getDisplayName(TextStyle.FULL, pt));
        System.out.println(Month.DECEMBER.getDisplayName(TextStyle.SHORT, pt));

        LocalDate dt = LocalDate.now();
        System.out.println(dt.getDayOfWeek());

        System.out.println("");

        agora = LocalDateTime.now();
        String resultado = agora.format(DateTimeFormatter.ISO_LOCAL_TIME);
        System.out.println(resultado);

        resultado = agora.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println(resultado);

        resultado = agora.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(resultado);

        agora = LocalDateTime.now();
        System.out.println(agora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        agora = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        resultado = agora.format(formatador);
        LocalDate agoraEmData = LocalDate.parse(resultado, formatador);
        System.out.println(agoraEmData);

        LocalDate agora3 = LocalDate.now();
        LocalDate outraData = LocalDate.of(2017, Month.AUGUST, 30);
        long dias = ChronoUnit.DAYS.between(outraData, agora3);//diferença de datas, antes do java 8 usávamos Calendar
        System.out.println(dias);

        long meses = ChronoUnit.MONTHS.between(outraData, agora3);
        long anos = ChronoUnit.YEARS.between(outraData, agora3);
        System.out.printf("%s dias, %s meses e %s anos\n", dias, meses, anos);

        agora3 = LocalDate.now();
        outraData = LocalDate.of(1989, Month.JANUARY, 25);
        Period periodo = Period.between(outraData, agora3);
        System.out.printf("%s dias, %s meses e %s anos\n", periodo.getDays(), periodo.getMonths(), periodo.getYears());

        System.out.println("");

        agora3 = LocalDate.now();
        outraData = LocalDate.of(2019, Month.JANUARY, 25);
        Period periodo2 = Period.between(outraData, agora3);
        System.out.printf("%s dias, %s meses e %s anos\n", periodo2.getDays(), periodo2.getMonths(), periodo2.getYears());

        periodo = Period.between(outraData, agora3);
        if (periodo.isNegative()) {
            periodo = periodo.negated();
        }
        System.out.printf("%s dias, %s meses e %s anos\n", periodo.getDays(), periodo.getMonths(), periodo.getYears());

        System.out.println("");

        agora = LocalDateTime.now();
        LocalDateTime daquiAUmaHora = LocalDateTime.now().plusHours(1);
        Duration duration = Duration.between(agora, daquiAUmaHora);
        if (duration.isNegative()) {
            duration = duration.negated();
        }
        System.out.printf("%s horas, %s minutos e %s segundos\n", duration.toHours(), duration.toMinutes(), duration.getSeconds());

        System.out.println("");

        MonthDay monthDay = MonthDay.of(Month.DECEMBER, 25);
        YearMonth yearMonth = YearMonth.of(2017, Month.SEPTEMBER);
        System.out.println(monthDay);
        System.out.println(yearMonth);

        System.out.println("");

        LocalDateTime dataPrimeiroSegDia = LocalDate.now().atStartOfDay();
        System.out.println(dataPrimeiroSegDia);

        LocalDateTime dataUltimoSegDia = LocalDateTime.now();
        System.out.println(dataUltimoSegDia.with(lastSecondOfDay()));
    }

    private static TemporalAdjuster lastSecondOfDay() {
        return (temporal) -> temporal.with(LocalTime.MAX).with(ChronoField.NANO_OF_SECOND, 0);//seta último segundo do horário, mas com nanoseguntos zero
    }
}
