package com.example.demoservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(name = "first_name")
    private String firstName;


    @Column(name = "middle_name")
    private String middleName;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;


    @NotNull
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;


    @NotNull
    @Column(name = "date_of_joining")
    private LocalDate dateOfJoining;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "designation_id", referencedColumnName = "designation_id")
    private Designation designation;

    public void setDesignation(Designation designation) {
        if (Objects.nonNull(designation)) {
            this.designation = designation;
        }
    }

}
