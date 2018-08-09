package io.renren.vas.service.impl;

import io.renren.vas.dao.ScScajDao;
import io.renren.vas.entity.ScScajEntity;
import io.renren.vas.service.ScScajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scScajService")
public class ScScajServiceImpl implements ScScajService {
	@Autowired
	private ScScajDao scScajDao;
	
	@Override
	public ScScajEntity queryObject(String id){
		return scScajDao.queryObject(id);
	}
	
	@Override
	public List<ScScajEntity> queryList(Map<String, Object> map){
		return scScajDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scScajDao.queryTotal(map);
	}
	
	@Override
	public void save(ScScajEntity scScaj){
		scScajDao.save(scScaj);
	}
	
	@Override
	public void update(ScScajEntity scScaj){
		scScajDao.update(scScaj);
	}
	
	@Override
	public void delete(String id){
		scScajDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		scScajDao.deleteBatch(ids);
	}

	@Override
	public List<ScScajEntity> find(Map<String, Object> map) {
		return scScajDao.find(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return scScajDao.total(map);
	}
	
}
