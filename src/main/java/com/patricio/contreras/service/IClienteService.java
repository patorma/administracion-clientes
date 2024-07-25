package com.patricio.contreras.service;

import java.util.List;

import com.patricio.contreras.entity.Ciudad;
import com.patricio.contreras.entity.Cliente;

public interface IClienteService extends ICRUD<Cliente, Long>{

	public List<Ciudad>findAllCiudades();
}
