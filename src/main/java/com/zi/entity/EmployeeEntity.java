package com.zi.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "employee")
@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {

    public static final String EMPLOYEE_SEQUENCE_NAME = "employee_seq";

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(generator = "sequenceGeneratorAddress")
    @GenericGenerator(
            name = "sequenceGeneratorAddress",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = EMPLOYEE_SEQUENCE_NAME),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    private String firstName;

    private String lastName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;
}
