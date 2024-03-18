package com.hsan.tuntrust.repository;

import com.hsan.tuntrust.model.Representant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentantRepository extends JpaRepository<Representant, Long> {
}