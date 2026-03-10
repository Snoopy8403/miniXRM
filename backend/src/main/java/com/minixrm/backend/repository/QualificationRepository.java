package com.minixrm.backend.repository;

import com.minixrm.backend.model.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long> {

    Optional<Qualification> findByName(String name);

}
