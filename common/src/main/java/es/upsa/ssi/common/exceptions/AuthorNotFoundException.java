package es.upsa.ssi.common.exceptions;

public class AuthorNotFoundException extends EntityNotFoundException {

    public AuthorNotFoundException() {
        super("Autor no encontrado.");
    }

    public AuthorNotFoundException(String id) {
        super("Autor no encontrado con id: " + id, id);
    }
}
