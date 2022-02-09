package br.com.message.schedule.exception;

public class RecipientNotFoundException extends RuntimeException {

    public RecipientNotFoundException(String recipient) {
        super("Recipient not found with [" + recipient + "]");
    }

}
