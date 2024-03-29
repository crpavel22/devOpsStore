package com.castillo.pavel.store.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
public class Generic {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy HH:mm:ss")
    @JsonProperty("date")
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    private HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }
}
