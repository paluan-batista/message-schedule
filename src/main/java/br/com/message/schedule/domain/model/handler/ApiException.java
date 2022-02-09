package br.com.message.schedule.domain.model.handler;

import lombok.*;

@Builder(builderMethodName = "newBuilder")
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiException {
    private String message;
    private int status;
}
