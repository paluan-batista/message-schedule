package br.com.message.schedule.providers;

import br.com.message.schedule.ConstantsTests;
import br.com.message.schedule.domain.model.entity.Recipient;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class RecipientEntityProviderForTests implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(Recipient.newBuilder().recipient(ConstantsTests.RECIPIENT_EMAIL).build())
                .map(Arguments::of);
    }

}

