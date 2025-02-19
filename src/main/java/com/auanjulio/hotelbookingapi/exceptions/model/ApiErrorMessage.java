package com.auanjulio.hotelbookingapi.exceptions.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ApiErrorMessage(

        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime timestamp,

        Integer cdError,

        String txStatus,

        List<String> errors
) {
}
