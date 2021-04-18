package com.example.demo.response;



public class GenericResponse {

    public GenericResponse(String status) {
        this.status = status;
    }

    public GenericResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String status;
    private String message;
}
