package com.zi.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "address")
@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {

    public static final String ADDRESS_SEQUENCE_NAME = "adresse_seq";

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(generator = "sequenceGeneratorAddress")
    @GenericGenerator(
            name = "sequenceGeneratorAddress",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = ADDRESS_SEQUENCE_NAME),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    private String city;
}
