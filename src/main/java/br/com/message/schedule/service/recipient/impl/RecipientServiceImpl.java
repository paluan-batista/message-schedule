package br.com.message.schedule.service.recipient.impl;

import br.com.message.schedule.convert.recipient.ConvertRecipient;
import br.com.message.schedule.domain.model.entity.Recipient;
import br.com.message.schedule.domain.repository.RecipientRepository;
import br.com.message.schedule.dto.RecipientDTO;
import br.com.message.schedule.service.recipient.RecipientService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
class RecipientServiceImpl implements RecipientService {

    final RecipientRepository repository;

    public Recipient save(RecipientDTO dto) {
        Optional<Recipient> recipientOptional = repository.findByRecipient(dto.getRecipient());
        if (recipientOptional.isPresent()) {
            return recipientOptional.get();
        }
        return repository.save(ConvertRecipient.toEntity(dto));
    }

}
