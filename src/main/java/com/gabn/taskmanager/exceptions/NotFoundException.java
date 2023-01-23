package com.gabn.taskmanager.exceptions;

import java.io.Serial;

public class NotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = -3435095436926222029L;

    public NotFoundException(String message) {
        super(message);
    }
}
