package io.renren.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_scajDao;
import io.renren.service.Sc_scajService;
import io.renren.vas.entity.ScScajEntity;

/**
 * 涉车案件
 * @author moulin
 *
 */
@Service
public class Sc_scajServiceImpl implements Sc_scajService {
	
	@Autowired
	private Sc_scajDao sc_scajDao;

	@Override
	public void save(ScScajEntity scScajEntity) {
		sc_scajDao.save(scScajEntity);
	}

	@Override
	public void update(ScScajEntity scScajEntity) {
		sc_scajDao.update(scScajEntity);
	}

	

}
