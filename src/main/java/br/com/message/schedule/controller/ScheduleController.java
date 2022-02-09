package br.com.message.schedule.controller;

import br.com.message.schedule.domain.model.entity.Schedule;
import br.com.message.schedule.dto.ScheduleDTO;
import br.com.message.schedule.service.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("api/schedule")
@RestController
class ScheduleController {

    @Autowired
    private ScheduleService service;

    @GetMapping(path = "{status}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ScheduleDTO>> find(
            @PathVariable(name = "status") String status, Pageable pageable) {
        return ResponseEntity.ok(service.find(status, pageable));
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@Validated @RequestBody ScheduleDTO dto) {
        Schedule schedule = service.save(dto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/schedule/{status}").buildAndExpand(schedule.getStatus().name()).toUri())
                .body(schedule.getUuid());
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<Object> delete(@PathVariable(name = "uuid") String uuid) {
        service.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}