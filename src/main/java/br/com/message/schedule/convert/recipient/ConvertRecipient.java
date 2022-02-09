package br.com.message.schedule.convert.recipient;


import br.com.message.schedule.convert.recipient.validation.ValidationRecipient;
import br.com.message.schedule.domain.model.entity.Recipient;
import br.com.message.schedule.dto.RecipientDTO;
import br.com.message.schedule.exception.RecipientInvalidException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConvertRecipient {

    public static Recipient toEntity(RecipientDTO recipient) {
        if (ValidationRecipient.isEmail(recipient.getRecipient())
                || ValidationRecipient.isPhone(recipient.getRecipient()))
            return Recipient.newBuilder().recipient(recipient.getRecipient()).build();

        throw new RecipientInvalidException(recipient.getRecipient());
    }

    public static RecipientDTO toDataTransferObject(Recipient recipient) {
        return RecipientDTO.newBuilder().recipient(recipient.getRecipient()).build();
    }
}
