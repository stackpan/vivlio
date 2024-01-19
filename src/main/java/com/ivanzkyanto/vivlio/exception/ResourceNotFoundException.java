package com.ivanzkyanto.vivlio.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;

    private String resourceId;

    @Override
    public String getMessage() {
        if (Objects.isNull(resourceName) || Objects.isNull(resourceId))
            return null;
        return String.format("Resource '%s' not found with id '%s'", resourceName, resourceId);
    }
}
