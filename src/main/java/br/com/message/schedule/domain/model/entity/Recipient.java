package br.com.message.schedule.domain.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Builder(builderMethodName = "newBuilder")
@Getter
@Setter
@Entity
@Table(name = "recipient", uniqueConstraints = {@UniqueConstraint(columnNames = {"recipient"})})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Recipient extends BaseEntity {

    private static final long serialVersionUID = -2674827418854848988L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(nullable = false)
    private String recipient;

    @OneToMany(mappedBy = "recipient", fetch = FetchType.LAZY)
    private List<Schedule> schedules;

}

