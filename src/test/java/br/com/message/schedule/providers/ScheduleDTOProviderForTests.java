package br.com.message.schedule.providers;

import br.com.message.schedule.ConstantsTests;
import br.com.message.schedule.dto.RecipientDTO;
import br.com.message.schedule.dto.ScheduleDTO;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class ScheduleDTOProviderForTests implements ArgumentsProvider, ConstantsTests {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(ScheduleDTO.newBuilder().message(MESSAGE)
                .recipient(RecipientDTO.newBuilder().recipient(RECIPIENT_EMAIL).build())
                .sendDate(LocalDateTime.now().plusDays(7L)).status(PENDING.name()).type(EMAIL.name())
                .build()).map(Arguments::of);
    }

}
