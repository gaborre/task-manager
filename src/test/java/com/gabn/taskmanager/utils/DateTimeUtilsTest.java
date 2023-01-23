package com.gabn.taskmanager.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class DateTimeUtilsTest {

    @Test
    void dateTimeUtilsConstructor() {
        assertAll(DateTimeUtils::new);
    }
}