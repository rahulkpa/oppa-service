package com.nirsb.oppa.oppaservice.dao;

import com.nirsb.oppa.oppaservice.model.DocumentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<DocumentDTO, Long> {
}
