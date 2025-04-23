package com.fhce.inv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fhce.inv.model.tipoModel;

@Repository
public interface tipoDao extends JpaRepository<tipoModel, Long> {
}
