package es.upsa.ssi.common.exceptions;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends BookshopException {
    private String id;

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, String id) {
        super(message);
        this.id = id;
    }
}
