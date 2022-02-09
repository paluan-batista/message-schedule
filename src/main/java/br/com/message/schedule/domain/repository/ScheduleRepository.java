package br.com.message.schedule.domain.repository;

import br.com.message.schedule.domain.model.entity.Recipient;
import br.com.message.schedule.domain.model.entity.Schedule;
import br.com.message.schedule.domain.model.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    Optional<Schedule> findByRecipient(Recipient find);

    Optional<Schedule> findByUuidAndStatus(UUID uuid, Status status);

    Page<Schedule> findByStatus(Status status, Pageable pageable);
}
