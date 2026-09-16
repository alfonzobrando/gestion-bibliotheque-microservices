package es.upsa.ssi.ws_books.application.rest.resources.beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class ErrorMessage
{
    private String message;
    private int code;
}
