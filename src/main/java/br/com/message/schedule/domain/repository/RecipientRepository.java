package br.com.message.schedule.domain.repository;

import br.com.message.schedule.domain.model.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RecipientRepository extends JpaRepository<Recipient, UUID> {
    Optional<Recipient> findByRecipient(String recipient);
}
