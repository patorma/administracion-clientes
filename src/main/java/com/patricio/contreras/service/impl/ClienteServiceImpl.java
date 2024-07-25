package com.patricio.contreras.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patricio.contreras.dao.IClienteDao;
import com.patricio.contreras.dao.IGenericDao;
import com.patricio.contreras.entity.Ciudad;
import com.patricio.contreras.entity.Cliente;
import com.patricio.contreras.service.IClienteService;

@Service
public class ClienteServiceImpl extends CRUDImpl<Cliente, Long> implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	protected IGenericDao<Cliente, Long> getDao() {
		return clienteDao; 
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ciudad> findAllCiudades() {
		
		return clienteDao.findAllCiudades();
	}

}
