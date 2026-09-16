package es.upsa.ssi.common.dtos;

import lombok.Builder;
import lombok.With;

@Builder(setterPrefix = "with")
@With
public record UnidentifiedAuthor(
        String name,
        String nationality
) {
}
