package br.com.message.schedule;

import br.com.message.schedule.domain.model.entity.Status;
import br.com.message.schedule.domain.model.entity.Type;

import java.time.LocalDateTime;

public interface ConstantsTests {
    static final String RECIPIENT_EMAIL = "paluan.desenvolvimento@gmail.com";
    static final String RECIPIENT_PHONE = "55-16-997305457";

    static final LocalDateTime SEND_DATE = LocalDateTime.now().plusDays(7L);
    static final String MESSAGE = "VOCÊ PASSOU EM NOSSO PROCESSO SELETIVO, BEM VINDO!";
    static final Status PENDING = Status.PENDING;
    static final Type EMAIL = Type.EMAIL;

    static final String PROFILE_TEST = "test";

    static final String RECIPIENT_EMAIL_NOT_VALID = "paluan.desenvolvimentogmail.com";
    static final String RECIPIENT_PHONE_NOT_VALID = "55 16 99730 5457";
}