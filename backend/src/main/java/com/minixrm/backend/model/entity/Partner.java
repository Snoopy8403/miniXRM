package com.minixrm.backend.model.entity;

import com.minixrm.backend.model.enums.PartnerStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE partner SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String taxNumber;
    private String address;
    @Enumerated(EnumType.STRING)
    private PartnerStatus status;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Qualification> qualifications = new HashSet<>();
    private boolean deleted = false;
}