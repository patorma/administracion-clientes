package com.patricio.contreras.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.patricio.contreras.entity.Ciudad;
import com.patricio.contreras.entity.Cliente;

public interface IClienteDao extends IGenericDao<Cliente, Long>{

	@Query("from Ciudad")
	public List<Ciudad> findAllCiudades();
}
