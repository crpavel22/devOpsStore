package com.castillo.pavel.store.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonRootName("error")
public class ErrorResponse extends Generic {

    @JsonProperty("message")
    private String message;
    @JsonProperty("url")
    private String url;

    public ErrorResponse(HttpStatus status, String message, String url) {
        super(status);
        this.message = message;
        this.url = url;
    }
}
