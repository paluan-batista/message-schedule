package br.com.message.schedule.convert.recipient.validation;

import br.com.message.schedule.ConstantsTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationRecipientTest {

    @Test
    void shouldValidate() {
        boolean emailValidated = ValidationRecipient.isEmail(ConstantsTests.RECIPIENT_EMAIL);
        boolean phoneValidated = ValidationRecipient.isPhone(ConstantsTests.RECIPIENT_PHONE);

        assertTrue(emailValidated);
        assertTrue(phoneValidated);
    }

    @Test
    void shouldReturnInvalid() {
        boolean emailValidated = ValidationRecipient.isEmail(ConstantsTests.RECIPIENT_EMAIL_NOT_VALID);
        boolean phoneValidated = ValidationRecipient.isPhone(ConstantsTests.RECIPIENT_PHONE_NOT_VALID);

        assertFalse(emailValidated);
        assertFalse(phoneValidated);
    }

}