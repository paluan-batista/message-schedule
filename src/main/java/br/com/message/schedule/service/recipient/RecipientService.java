package br.com.message.schedule.service.recipient;

import br.com.message.schedule.domain.model.entity.Recipient;
import br.com.message.schedule.dto.RecipientDTO;

public interface RecipientService {
    Recipient save(RecipientDTO dto);
}