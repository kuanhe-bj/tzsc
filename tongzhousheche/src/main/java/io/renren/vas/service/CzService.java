package io.renren.vas.service;

import java.util.List;

import io.renren.vas.entity.ScJxsjEntity;


public interface CzService {
	
	List<ScJxsjEntity> cz(String licensePlate);
}
