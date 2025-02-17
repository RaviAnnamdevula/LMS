package com.project.minor1.response;

public class GenericResponse<T> {
    private T data;

    private String error;

    private String message;  //success/failure

    private String code;
}
