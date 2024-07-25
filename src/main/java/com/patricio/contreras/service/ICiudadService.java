package com.patricio.contreras.service;

import java.util.List;

import com.patricio.contreras.entity.Ciudad;
import com.patricio.contreras.entity.Region;

public interface ICiudadService extends ICRUD<Ciudad, Long>{

	public List<Region> findAllRegiones();
}
