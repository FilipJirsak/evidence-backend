package net.czela.backend.evidence.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author Filip
 */
public class DateUtils {
  public static final ZoneId ZONE_UTC = ZoneId.of("UTC");
  public static final ZoneId ZONE_EUROPE_PRAGUE = ZoneId.of("Europe/Prague");

  public static Date toDate(LocalDate date) {
    if (date == null) {
      return null;
    }
    return Date.from(date.atStartOfDay(ZONE_EUROPE_PRAGUE).toInstant());
  }

  public static Date toDate(LocalDateTime datetime) {
    if (datetime == null) {
      return null;
    }
    return Date.from(datetime.atZone(ZONE_EUROPE_PRAGUE).toInstant());
  }

  public static LocalDate toLocalDate(Date date) {
    if (date == null) {
      return null;
    }
    return LocalDate.ofInstant(date.toInstant(), ZONE_EUROPE_PRAGUE);
  }

  public static LocalDateTime toLocalDateTime(Date date) {
    if (date == null) {
      return null;
    }
    return ZonedDateTime.ofInstant(date.toInstant(), ZONE_EUROPE_PRAGUE).toLocalDateTime();
  }
}
