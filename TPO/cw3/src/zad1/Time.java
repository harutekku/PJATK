/**
 * @author Pawłowicz Jakub S18688
 */

package zad1;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.time.format.DateTimeParseException;

import static java.time.temporal.ChronoUnit.*;

public class Time {
    public static String passed(String from, String to) {
        boolean time = from.contains("T") || to.contains("T");
        long days;
        double weeks;
        ZonedDateTime fromD, tod;

        String[] months = {"stycznia", "lutego", "marca", "kwietnia", "maja", "czerwca", "lipca", "sierpnia", "września", "października", "listopada", "grudnia"};
        String[] weekdays = {"poniedziałek", "wtorek", "środa", "czwartek", "piątek", "sobota", "niedziela"};

        try {
            fromD = time ? LocalDateTime.parse(from).atZone(ZoneId.of("Europe/Warsaw")) : LocalDate.parse(from).atStartOfDay(ZoneId.of("Europe/Warsaw"));
            tod = time ? LocalDateTime.parse(to).atZone(ZoneId.of("Europe/Warsaw")) : LocalDate.parse(to).atStartOfDay(ZoneId.of("Europe/Warsaw"));
        } catch (DateTimeParseException e) {
            return "*** " + e;
        }
        days = DAYS.between(fromD, tod);
        String formatDay = days == 1 ? " dzień" : " dni";
        weeks = (double) days / 7 % 1 == 0 ? (int) days / 7 : (double) days / 7;
        Double weekCountRounded = BigDecimal.valueOf(weeks).setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

        String passed;
        if (time) {
            long hoursCount, minutesCount;
            hoursCount = HOURS.between(fromD, tod);
            minutesCount = MINUTES.between(fromD, tod);
            String minutesFromFormat = fromD.getMinute() <= 9 ? "0" : "",
                    minutesToFormat = tod.getMinute() <= 9 ? "0" : "";
            passed = String.format("Od %d %s %d (%s) godz. %d:%s%d do %d %s %d (%s) godz. %d:%s%d\n - mija: %d%s, tygodni %s\n - godzin: %d, minut: %d", fromD.getDayOfMonth(), months[fromD.getMonth().getValue() - 1], fromD.getYear(), weekdays[fromD.getDayOfWeek().getValue() - 1], fromD.getHour(), minutesFromFormat, fromD.getMinute(), tod.getDayOfMonth(), months[tod.getMonth().getValue() - 1], tod.getYear(), weekdays[tod.getDayOfWeek().getValue() - 1], tod.getHour(), minutesToFormat, tod.getMinute(), days, formatDay, weekCountRounded==0.0?"0":weekCountRounded, hoursCount, minutesCount);
        } else {
            passed = String.format("Od %d %s %d (%s) do %d %s %d (%s)\n - mija: %d%s, tygodni %s", fromD.getDayOfMonth(), months[fromD.getMonth().getValue() - 1], fromD.getYear(), weekdays[fromD.getDayOfWeek().getValue() - 1], tod.getDayOfMonth(), months[tod.getMonth().getValue() - 1], tod.getYear(), weekdays[tod.getDayOfWeek().getValue() - 1], days, formatDay, weekCountRounded==0.0?"0":weekCountRounded);
        }
        if (days > 0) {
            Period calendar = Period.between(fromD.toLocalDate(), tod.toLocalDate());
            long yearCountCalendar = calendar.getYears(), monthCountCalendar = calendar.getMonths(), dayCountCalendar = calendar.getDays();
            String formatYear = yearCountCalendar == 1 ? " rok" : (yearCountCalendar >= 5 && yearCountCalendar <= 21) || yearCountCalendar % 10 > 5 ? " lat" : " lata",
                    formatMonth = monthCountCalendar == 1 ? " miesiąc" : " miesiące",
                    dayCountFormat = dayCountCalendar == 1 ? " dzień" : " dni";
            passed += "\n - kalendarzowo: ";
            if (yearCountCalendar > 0){
                passed += yearCountCalendar + formatYear;
                if(monthCountCalendar>0||dayCountCalendar>0)passed+=", ";
            }
            if (monthCountCalendar > 0){
                passed += monthCountCalendar + formatMonth;
                if(dayCountCalendar>0)passed+=", ";
            }
            if (dayCountCalendar > 0)
                passed += dayCountCalendar + dayCountFormat;
        }
        return passed;
    }
}
