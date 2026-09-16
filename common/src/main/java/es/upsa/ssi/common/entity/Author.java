package es.upsa.ssi.common.entity;

import lombok.Builder;
import lombok.With;

@Builder(setterPrefix = "with")
@With
public record Author (
    String id,
    String name,
    String nationality
) {
}
