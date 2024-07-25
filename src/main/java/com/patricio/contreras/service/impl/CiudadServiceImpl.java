package com.patricio.contreras.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patricio.contreras.dao.ICiudadDao;
import com.patricio.contreras.dao.IGenericDao;
import com.patricio.contreras.entity.Ciudad;
import com.patricio.contreras.service.ICiudadService;

@Service
public class CiudadServiceImpl extends CRUDImpl<Ciudad, Long> implements ICiudadService  {

	@Autowired
	private ICiudadDao ciudadDao;
	
	@Override
	protected IGenericDao<Ciudad, Long> getDao() {
		
		return ciudadDao;
	}

}
