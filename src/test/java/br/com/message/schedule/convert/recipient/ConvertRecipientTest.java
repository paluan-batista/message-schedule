package br.com.message.schedule.convert.recipient;

import br.com.message.schedule.domain.model.entity.Recipient;
import br.com.message.schedule.dto.RecipientDTO;
import br.com.message.schedule.exception.RecipientInvalidException;
import br.com.message.schedule.providers.RecipientDTOProviderForTests;
import br.com.message.schedule.providers.RecipientEntityProviderForTests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConvertRecipientTest {

    @ParameterizedTest
    @ArgumentsSource(RecipientDTOProviderForTests.class)
    void shouldConvertDataTransferObjectToEntity(RecipientDTO dto) {
        Recipient recipient = ConvertRecipient.toEntity(dto);
        assertEquals(dto.getRecipient(), recipient.getRecipient());
    }

    @ParameterizedTest
    @ArgumentsSource(RecipientEntityProviderForTests.class)
    void shouldConvertEntityToDataTransferObject(Recipient recipient) {
        RecipientDTO dto = ConvertRecipient.toDataTransferObject(recipient);
        assertEquals(recipient.getRecipient(), dto.getRecipient());
    }

    @Test
    void shouldReturnException() {
        RecipientDTO dtoInvalid =
                RecipientDTO.newBuilder().recipient("anything").build();
        Exception exception = assertThrows(RecipientInvalidException.class, () -> {
            ConvertRecipient.toEntity(dtoInvalid);
        });
        assertTrue(exception.getMessage().equals("Recipient [anything] has space or/and is invalid!"));
        assertTrue(exception instanceof RecipientInvalidException);
    }
}
