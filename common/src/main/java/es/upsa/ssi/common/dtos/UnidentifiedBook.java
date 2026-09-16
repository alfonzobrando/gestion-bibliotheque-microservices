package es.upsa.ssi.common.dtos;

import lombok.Builder;
import lombok.With;

@Builder(setterPrefix = "with")
@With
public record UnidentifiedBook(
        String title,
        String year,
        String author_id
) {
}
