package com.ditraacademy.travelagency.utils;

// une classe qui gére nous erreurs c'est nous qu'on manipule l'erreur

public class ErrorResponseModel {

    private String message;

    public ErrorResponseModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
