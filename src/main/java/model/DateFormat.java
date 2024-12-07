package model;

import lombok.Getter;
import java.time.format.DateTimeFormatter;

@Getter
public enum DateFormat {
    DATE_FORMAT("dd.MM.yyyy");

    private final DateTimeFormatter formatter;

    DateFormat(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);

    }

}

