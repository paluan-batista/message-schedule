package br.com.message.schedule.convert.schedule;

import br.com.message.schedule.ConstantsTests;
import br.com.message.schedule.domain.model.entity.Recipient;
import br.com.message.schedule.domain.model.entity.Schedule;
import br.com.message.schedule.domain.model.entity.Type;
import br.com.message.schedule.dto.ScheduleDTO;
import br.com.message.schedule.exception.RecipientInvalidException;
import br.com.message.schedule.providers.ScheduleDTOProviderForTests;
import br.com.message.schedule.providers.ScheduleEntityProviderForTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConvertScheduleTest implements ConstantsTests {

    @ParameterizedTest
    @ArgumentsSource(ScheduleDTOProviderForTests.class)
    void convertDataTransferObjectToEntity(ScheduleDTO dto) {
        Recipient recipient = Recipient.newBuilder().recipient(RECIPIENT_EMAIL).build();
        Schedule schedule = ConvertSchedule.toEntity(dto, recipient);

        assertEquals(dto.getMessage(), schedule.getMessage());
        assertEquals(dto.getRecipient().getRecipient(), schedule.getRecipient().getRecipient());
        assertEquals(dto.getSendDate(), schedule.getSendDate());
        assertEquals(dto.getStatus(), schedule.getStatus().name());
        assertEquals(dto.getType(), schedule.getType().name());
    }

    @ParameterizedTest
    @ArgumentsSource(ScheduleEntityProviderForTests.class)
    void convertEntityToDataTransferObject(Schedule schedule) {
        ScheduleDTO dto = ConvertSchedule.toDataTransferObject(schedule);

        assertEquals(schedule.getMessage(), dto.getMessage());
        assertEquals(schedule.getRecipient().getRecipient(), dto.getRecipient().getRecipient());
        assertEquals(schedule.getSendDate(), dto.getSendDate());
        assertEquals(schedule.getType().name(), dto.getType());
        assertEquals(schedule.getStatus().name(), dto.getStatus());
    }

    @ParameterizedTest
    @ArgumentsSource(ScheduleDTOProviderForTests.class)
    void shouldReturnExceptionForRecipientInvalid(ScheduleDTO dto) {
        dto.getRecipient().setRecipient("a n y t h i n g");
        Exception exception = assertThrows(RecipientInvalidException.class, () -> {
            ConvertSchedule.toEntity(dto, Recipient.newBuilder().recipient(RECIPIENT_EMAIL).build());
        });
        assertTrue(exception instanceof RecipientInvalidException);
        assertEquals("Recipient [a n y t h i n g] has space or/and is invalid!",
                exception.getMessage());

    }

    @ParameterizedTest
    @ArgumentsSource(ScheduleDTOProviderForTests.class)
    @DisplayName("Deve setar o tipo da Entidade Schedule como EMAIL")
    void shouldSetTypeEmailInEntitySchedule(ScheduleDTO dto) {
        Schedule schedule =
                ConvertSchedule.toEntity(dto, Recipient.newBuilder().recipient(RECIPIENT_EMAIL).build());
        assertEquals(Type.EMAIL, schedule.getType());
    }

    @ParameterizedTest
    @ArgumentsSource(ScheduleDTOProviderForTests.class)
    void shouldSetTypeShortMessageServiceInEntitySchedule(ScheduleDTO dto) {
        dto.getRecipient().setRecipient(RECIPIENT_PHONE);
        dto.setType(Type.SMS.name());
        Schedule schedule =
                ConvertSchedule.toEntity(dto, Recipient.newBuilder().recipient(RECIPIENT_PHONE).build());

        assertEquals(Type.SMS, schedule.getType());
    }

    @ParameterizedTest
    @ArgumentsSource(ScheduleDTOProviderForTests.class)
    void shouldSetTypeWhatsappEntitySchedule(ScheduleDTO dto) {
        dto.getRecipient().setRecipient(RECIPIENT_PHONE);
        dto.setType(Type.WHATSAPP.name());
        Schedule schedule =
                ConvertSchedule.toEntity(dto, Recipient.newBuilder().recipient(RECIPIENT_PHONE).build());

        assertEquals(Type.WHATSAPP, schedule.getType());
    }

    @ParameterizedTest
    @ArgumentsSource(ScheduleDTOProviderForTests.class)
    void shouldSetTypeEmailInEntityScheduleEvenWhenDTOPassesDifferentType(
            ScheduleDTO dto) {
        dto.getRecipient().setRecipient(RECIPIENT_EMAIL);
        dto.setType(Type.WHATSAPP.name());
        Schedule schedule =
                ConvertSchedule.toEntity(dto, Recipient.newBuilder().recipient(RECIPIENT_EMAIL).build());

        assertEquals(Type.EMAIL, schedule.getType());
    }
}
