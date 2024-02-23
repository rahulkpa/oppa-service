package com.nirsb.oppa.oppaservice.model;

import org.springframework.hateoas.RepresentationModel;

public class Response extends RepresentationModel {
    private String message;

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
