package com.renevc.app.rest.infrastructure.handlers;

import com.renevc.app.rest.domain.exception.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private Integer httpCode;

    private ErrorCodes code;

    private String message;

    List<String> errors = new ArrayList<>();


}
