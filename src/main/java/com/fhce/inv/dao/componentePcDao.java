package com.fhce.inv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fhce.inv.model.componentePcModel;

@Repository
public interface componentePcDao extends JpaRepository<componentePcModel, Long> {
	List<componentePcModel> findByEquipoIdequipo(Long idequipo);
}
