package com.musala.dronefleetservice.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.dronefleetservice.model.AuditEntry;

public interface AuditEntryRepository extends JpaRepository<AuditEntry, Timestamp> {
}
