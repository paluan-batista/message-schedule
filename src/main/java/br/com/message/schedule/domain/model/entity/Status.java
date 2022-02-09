package br.com.message.schedule.domain.model.entity;

import br.com.message.schedule.exception.StatusNotFoundException;

import java.util.Arrays;

public enum Status {
    PENDING("PENDING"), SENT("SENT"), DELETED("DELETED");

    private String status;

    private Status(String status) {
        this.status = status;
    }

    private String getStatus() {
        return status;
    }

    public static Status find(String status) {
        return Arrays.asList(Status.values()).stream()
                .filter(s -> s.getStatus().equals(status.toUpperCase())).findFirst()
                .orElseThrow(() -> new StatusNotFoundException(status));
    }

}