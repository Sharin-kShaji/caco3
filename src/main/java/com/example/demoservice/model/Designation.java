package com.example.demoservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "designation")
//@Table(name = "designation", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Designation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "designation_id")
    private long id;


    @NotEmpty
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

}
