package com.project.application.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorAttributes {

    int status;

    String message;
}
