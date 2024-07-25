package com.patricio.contreras.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patricio.contreras.dao.IClienteDao;
import com.patricio.contreras.dao.IGenericDao;
import com.patricio.contreras.entity.Cliente;
import com.patricio.contreras.service.IClienteService;

@Service
public class ClienteServiceImpl extends CRUDImpl<Cliente, Long> implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	protected IGenericDao<Cliente, Long> getDao() {
		return clienteDao; //quede aca
	}

}
