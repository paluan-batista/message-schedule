package br.com.message.schedule.exception;

public class StatusNotFoundException extends RuntimeException {

    public StatusNotFoundException(String status) {
        super("Status informed [" + status + "] not found!");
    }

}

