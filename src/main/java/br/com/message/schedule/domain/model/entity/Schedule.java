package br.com.message.schedule.domain.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder(builderMethodName = "newBuilder")
@Getter
@Setter
@Entity
@Table(name = "schedule")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Schedule extends BaseEntity {

    private static final long serialVersionUID = -8364326632135381856L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(name = "send_date", nullable = false)
    private LocalDateTime sendDate;

    @Column(nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_uuid", referencedColumnName = "uuid",
            foreignKey = @ForeignKey(name = "uuid"), nullable = false)
    private Recipient recipient;

}
