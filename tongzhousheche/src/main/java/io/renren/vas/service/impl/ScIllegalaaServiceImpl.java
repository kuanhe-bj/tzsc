package io.renren.vas.service.impl;

import io.renren.vas.dao.ScIllegalaaDao;
import io.renren.vas.entity.ScIllegalaaEntity;
import io.renren.vas.service.ScIllegalaaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scIllegalaaService")
public class ScIllegalaaServiceImpl implements ScIllegalaaService {
	@Autowired
	private ScIllegalaaDao scIllegalaaDao;
	
	@Override
	public ScIllegalaaEntity queryObject(String id){
		return scIllegalaaDao.queryObject(id);
	}
	
	@Override
	public List<ScIllegalaaEntity> queryList(Map<String, Object> map){
		return scIllegalaaDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scIllegalaaDao.queryTotal(map);
	}
	
	@Override
	public void save(ScIllegalaaEntity scIllegalaa){
		scIllegalaaDao.save(scIllegalaa);
	}
	
	@Override
	public void update(ScIllegalaaEntity scIllegalaa){
		scIllegalaaDao.update(scIllegalaa);
	}
	
	@Override
	public void delete(String id){
		scIllegalaaDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		scIllegalaaDao.deleteBatch(ids);
	}
	
}
