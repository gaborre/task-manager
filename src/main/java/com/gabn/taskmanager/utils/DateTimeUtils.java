package com.gabn.taskmanager.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNullElse;

@Slf4j
public final class DateTimeUtils {

    public static String getStringDateFromLocalDateTime(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return Optional.of(localDateTime)
            .orElse(LocalDateTime.now(ZoneOffset.UTC))
            .atOffset(ZoneOffset.UTC)
            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public static LocalDateTime getLocalDateTimeFromStringDate(String stringLocalDateTime) {
        return LocalDateTime.parse(
            requireNonNullElse(
                stringLocalDateTime,
                getStringDateFromLocalDateTime(LocalDateTime.now(ZoneOffset.UTC))
            ),
            DateTimeFormatter.ISO_OFFSET_DATE_TIME
        ).atOffset(ZoneOffset.UTC).toLocalDateTime();
    }

    public static LocalDateTime getLocalDateTimeForCriteria(String stringLocalDateTime) {
        if (StringUtils.isBlank(stringLocalDateTime)) {
            return null;
        }
        return getLocalDateTimeFromStringDate(stringLocalDateTime);
    }
}
