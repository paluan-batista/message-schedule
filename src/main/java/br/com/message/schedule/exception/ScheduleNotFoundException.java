package br.com.message.schedule.exception;

public class ScheduleNotFoundException extends RuntimeException {

    public ScheduleNotFoundException(String uuid) {
        super("Schedule not found with uuid [ " + uuid + " ] informed ");
    }

}

