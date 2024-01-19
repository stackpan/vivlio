package com.ivanzkyanto.vivlio.util;

import com.ivanzkyanto.vivlio.exception.ResourceNotFoundException;

import java.util.UUID;

public class UUIDs {

    public static UUID toUuid(String id, Class<?> model) {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException(model.getSimpleName(), id);
        }
    }

}
