package com.gabn.taskmanager.exceptions;

import java.io.Serial;

public class EmptyNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = -8109255377773959278L;

    public EmptyNotFoundException(String message) {
        super(message);
    }
}
