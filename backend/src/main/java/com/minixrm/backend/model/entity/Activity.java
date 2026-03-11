package com.minixrm.backend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String type;
    private String description;
    private int durationMinutes;
    private String responsibleName;
    @ManyToOne
    private Partner partner;

}