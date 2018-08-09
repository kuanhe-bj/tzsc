package io.renren.vas.service.impl;

import io.renren.vas.dao.ScRcglDao;
import io.renren.vas.entity.ScRcglEntity;
import io.renren.vas.service.ScRcglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;





@Service("scRcglService")
public class ScRcglServiceImpl implements ScRcglService {
	@Autowired
	private ScRcglDao scRcglDao;
	
	@Override
	public ScRcglEntity queryObject(String cp){
		return scRcglDao.queryObject(cp);
	}
	
	@Override
	public List<ScRcglEntity> queryList(Map<String, Object> map){
		return scRcglDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scRcglDao.queryTotal(map);
	}
	
	@Override
	public void save(ScRcglEntity scRcgl){
		scRcglDao.save(scRcgl);
	}
	
	@Override
	public void update(ScRcglEntity scRcgl){
		scRcglDao.update(scRcgl);
	}
	
	@Override
	public void delete(String cp){
		scRcglDao.delete(cp);
	}
	
	@Override
	public void deleteBatch(String[] cps){
		scRcglDao.deleteBatch(cps);
	}
	
}
