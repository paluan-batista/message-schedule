package br.com.message.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Builder(builderMethodName = "newBuilder")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class ScheduleDTO {

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("send_date")
    private LocalDateTime sendDate;

    @NonNull
    private RecipientDTO recipient;

    @NonNull
    private String message;

    private String type;

    private String status;
}
