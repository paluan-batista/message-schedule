package br.com.message.schedule.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecipientDataTransferObjectTest {

    @Test
    void shouldReturnException() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            RecipientDTO.newBuilder().recipient(null).build();
        });

        assertTrue(exception instanceof NullPointerException);
        assertTrue(exception.getMessage().equals("recipient is marked non-null but is null"));
    }
}
