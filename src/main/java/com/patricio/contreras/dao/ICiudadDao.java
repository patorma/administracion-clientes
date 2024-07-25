package com.patricio.contreras.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.patricio.contreras.entity.Ciudad;
import com.patricio.contreras.entity.Region;

public interface ICiudadDao extends IGenericDao<Ciudad, Long> {

	@Query("from Region")
	public List<Region> findAllRegiones();
}
