package br.com.message.schedule.service.schedule.impl;

import br.com.message.schedule.ConstantsTests;
import br.com.message.schedule.domain.model.entity.Recipient;
import br.com.message.schedule.domain.model.entity.Schedule;
import br.com.message.schedule.domain.repository.ScheduleRepository;
import br.com.message.schedule.dto.RecipientDTO;
import br.com.message.schedule.dto.ScheduleDTO;
import br.com.message.schedule.exception.ScheduleDateInvalidException;
import br.com.message.schedule.providers.ScheduleEntityProviderForTests;
import br.com.message.schedule.service.recipient.RecipientService;
import br.com.message.schedule.service.schedule.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ScheduleServiceImplTest implements ConstantsTests {

    private ScheduleRepository repository;

    private ScheduleService service;
    private RecipientService recipientService;

    private ScheduleDTO scheduleDTO;
    private RecipientDTO recipientDTO;

    @BeforeEach
    void setUp() {
        this.recipientDTO =
                RecipientDTO.newBuilder().recipient(RECIPIENT_EMAIL).build();

        this.scheduleDTO = ScheduleDTO.newBuilder().message(MESSAGE)
                .sendDate(SEND_DATE).sendDate(LocalDateTime.now().plusDays(7L)).status(PENDING.name())
                .type(EMAIL.name()).recipient(this.recipientDTO).build();

        this.repository = Mockito.spy(ScheduleRepository.class);
        this.recipientService = Mockito.spy(RecipientService.class);

        this.service = new ScheduleServiceImpl(this.repository, this.recipientService);
    }

    @ParameterizedTest
    @ArgumentsSource(ScheduleEntityProviderForTests.class)
    void shouldSaveSchedule(Schedule schedule) {
        Recipient recipient = Recipient.newBuilder().build();

        BDDMockito.given(this.repository.saveAndFlush(any(Schedule.class))).willReturn(schedule);
        BDDMockito.given(this.recipientService.save(recipientDTO)).willReturn(recipient);

        this.service.save(this.scheduleDTO);

        verify(this.recipientService, times(1)).save(any(RecipientDTO.class));
        verify(this.repository, times(1)).saveAndFlush(any(Schedule.class));
    }

    @ParameterizedTest
    @ArgumentsSource(ScheduleEntityProviderForTests.class)
    void shouldDeleteLogicallySchedule(Schedule schedule) {
        UUID uuid = UUID.randomUUID();
        BDDMockito.given(this.repository.findByUuidAndStatus(uuid, PENDING))
                .willReturn(Optional.of(schedule));
        BDDMockito.given(this.repository.save(any(Schedule.class))).willReturn(schedule);

        this.service.delete(uuid.toString());

        verify(repository, times(1)).findByUuidAndStatus(uuid, PENDING);
        verify(repository, times(1)).save(any(Schedule.class));
    }

    @ParameterizedTest
    @ArgumentsSource(ScheduleEntityProviderForTests.class)
    void shouldThrowExceptionWithSendDateInvalid(Schedule schedule) throws Exception {
        Recipient recipient = Recipient.newBuilder().build();

        BDDMockito.given(this.repository.saveAndFlush(any(Schedule.class))).willReturn(schedule);
        BDDMockito.given(this.recipientService.save(recipientDTO)).willReturn(recipient);
        scheduleDTO.setSendDate(LocalDateTime.now());
        Thread.sleep(2000);
        Exception exception = assertThrows(ScheduleDateInvalidException.class, () -> {
            this.service.save(this.scheduleDTO);
        });

        assertTrue(exception instanceof ScheduleDateInvalidException);
        assertTrue(exception.getMessage()
                .equals("send_date [" + scheduleDTO.getSendDate() + "] is invalid"));
    }

}
