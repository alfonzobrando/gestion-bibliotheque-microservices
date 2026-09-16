package es.upsa.ssi.common.entity;

import lombok.Builder;
import lombok.With;

@Builder(setterPrefix = "with")
@With
public record Book (
        String id,
        String title,
        String year,
        String author_id
) {
}
