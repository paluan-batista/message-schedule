package br.com.message.schedule.service.schedule;

import br.com.message.schedule.domain.model.entity.Schedule;
import br.com.message.schedule.dto.ScheduleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {
    Schedule save(ScheduleDTO dto);

    void delete(String uuid);

    Page<ScheduleDTO> find(String status, Pageable pageable);
}