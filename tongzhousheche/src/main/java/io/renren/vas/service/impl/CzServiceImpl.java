package io.renren.vas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.vas.dao.CzDao;
import io.renren.vas.entity.ScJxsjEntity;
import io.renren.vas.service.CzService;

@Service("czService")
public class CzServiceImpl implements CzService{
@Autowired
private CzDao czDao;

@Override
public List<ScJxsjEntity> cz(String licensePlate) {
	List<ScJxsjEntity> list=czDao.cz(licensePlate);
	return list;
}

}
