package com.patricio.contreras.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patricio.contreras.dao.IGenericDao;
import com.patricio.contreras.dao.IRegionDao;
import com.patricio.contreras.entity.Region;
import com.patricio.contreras.service.IRegionService;

@Service
public class RegionServiceImpl extends CRUDImpl<Region, Long> implements IRegionService {

	@Autowired
	private IRegionDao regionDao;
	
	@Override
	protected IGenericDao<Region, Long> getDao() {
		
		return regionDao;
	}

}
