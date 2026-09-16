package es.upsa.ssi.common.mappers;

import es.upsa.ssi.common.dtos.UnidentifiedAuthor;
import es.upsa.ssi.common.dtos.UnidentifiedBook;
import es.upsa.ssi.common.entity.Author;
import es.upsa.ssi.common.entity.Book;

public class Mapper {

    public Author toAuthor(UnidentifiedAuthor unidentifiedAuthor) {
        return Author.builder()
                .withName(unidentifiedAuthor.name())
                .withNationality(unidentifiedAuthor.nationality())
                .build();
    }

    public UnidentifiedAuthor toUnidentifiedAuthor(Author author) {
        return UnidentifiedAuthor.builder()
                .withName(author.name())
                .withNationality(author.nationality())
                .build();
    }

    public Book toBook(UnidentifiedBook unidentifiedBook) {
        return Book.builder()
                .withTitle(unidentifiedBook.title())
                .withYear(unidentifiedBook.year())
                .withAuthor_id(unidentifiedBook.author_id())
                .build();
    }

    public UnidentifiedBook toUnidentifiedBook(Book book) {
        return UnidentifiedBook.builder()
                .withTitle(book.title())
                .withYear(book.year())
                .withAuthor_id(book.author_id())
                .build();
    }
}
