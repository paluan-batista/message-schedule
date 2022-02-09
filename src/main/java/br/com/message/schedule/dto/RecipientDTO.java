package br.com.message.schedule.dto;
import org.hibernate.validator.constraints.Length;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder")
public class RecipientDTO {
    @NonNull
    @Length(min = 15)
    private String recipient;
}
