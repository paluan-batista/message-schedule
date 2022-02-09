package br.com.message.schedule.convert.schedule;

import br.com.message.schedule.domain.model.entity.Schedule.ScheduleBuilder;
import br.com.message.schedule.convert.recipient.ConvertRecipient;
import br.com.message.schedule.convert.schedule.validation.ValidationScheduleType;
import br.com.message.schedule.domain.model.entity.Recipient;
import br.com.message.schedule.domain.model.entity.Schedule;
import br.com.message.schedule.domain.model.entity.Status;
import br.com.message.schedule.domain.model.entity.Type;
import br.com.message.schedule.dto.RecipientDTO;
import br.com.message.schedule.dto.ScheduleDTO;
import br.com.message.schedule.exception.RecipientInvalidException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConvertSchedule {

    public static Schedule toEntity(ScheduleDTO dto, Recipient recipient) {

        if (ValidationScheduleType.isInvalid(dto.getRecipient().getRecipient())) {
            throw new RecipientInvalidException(dto.getRecipient().getRecipient());
        }

        ScheduleBuilder builder = Schedule.newBuilder().message(dto.getMessage()).recipient(recipient)
                .sendDate(dto.getSendDate()).status(Status.PENDING);

        if (ValidationScheduleType.isEmail(dto.getRecipient().getRecipient())) {
            return builder.type(Type.EMAIL).build();
        }

        if (ValidationScheduleType.isPhone(dto.getRecipient().getRecipient())) {
            builder.type(Type.find(dto.getType()));
        }
        return builder.build();
    }

    public static ScheduleDTO toDataTransferObject(Schedule schedule) {
        RecipientDTO recipientDTO =
                ConvertRecipient.toDataTransferObject(schedule.getRecipient());

        return ScheduleDTO.newBuilder().message(schedule.getMessage())
                .recipient(recipientDTO).sendDate(schedule.getSendDate())
                .status(schedule.getStatus().name()).type(schedule.getType().name()).build();
    }
}