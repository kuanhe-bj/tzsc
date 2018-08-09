package io.renren.vas.service.impl;

import io.renren.vas.dao.ScBdqclDao;
import io.renren.vas.entity.ScBdqclEntity;
import io.renren.vas.service.ScBdqclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scBdqclService")
public class ScBdqclServiceImpl implements ScBdqclService {
	@Autowired
	private ScBdqclDao scBdqclDao;
	
	@Override
	public ScBdqclEntity queryObject(String id){
		return scBdqclDao.queryObject(id);
	}
	
	@Override
	public List<ScBdqclEntity> queryList(Map<String, Object> map){
		return scBdqclDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scBdqclDao.queryTotal(map);
	}
	
	@Override
	public void save(ScBdqclEntity scBdqcl){
		scBdqclDao.save(scBdqcl);
	}
	
	@Override
	public void update(ScBdqclEntity scBdqcl){
		scBdqclDao.update(scBdqcl);
	}
	
	@Override
	public void delete(String id){
		scBdqclDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		scBdqclDao.deleteBatch(ids);
	}

	@Override
	public ScBdqclEntity getId() {
		return scBdqclDao.getId();
	}

	@Override
	public List<ScBdqclEntity> cx(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return scBdqclDao.cx(map);
	}
	
}
