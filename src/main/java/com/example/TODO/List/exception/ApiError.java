package com.example.todo.list.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Класс для форматирования формата возвращаемой ошибке
 * @autor Пётр
 */
@Data
public class ApiError {
    private HttpStatus status;
    private String message;
    private String debugMessage;

    ApiError() {
    }

    ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
    }
}
