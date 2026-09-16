package es.upsa.ssi.common.exceptions;

public class BookNotFoundException extends EntityNotFoundException{

    public BookNotFoundException() {
        super("Libro no encontrado");
    }

    public BookNotFoundException(String id) {
        super("Libro no encontrado con id: " + id, id);
    }
}
