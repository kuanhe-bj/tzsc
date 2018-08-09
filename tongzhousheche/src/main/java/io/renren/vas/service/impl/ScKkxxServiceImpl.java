package io.renren.vas.service.impl;

import io.renren.vas.dao.ScKkxxDao;
import io.renren.vas.entity.ScKkxxEntity;
import io.renren.vas.service.ScKkxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scKkxxService")
public class ScKkxxServiceImpl implements ScKkxxService {
	@Autowired
	private ScKkxxDao scKkxxDao;
	
	@Override
	public ScKkxxEntity queryObject(String id){
		return scKkxxDao.queryObject(id);
	}
	
	@Override
	public List<ScKkxxEntity> queryList(Map<String, Object> map){
		return scKkxxDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scKkxxDao.queryTotal(map);
	}
	
	@Override
	public void save(ScKkxxEntity scKkxx){
		scKkxxDao.save(scKkxx);
	}
	
	@Override
	public void update(ScKkxxEntity scKkxx){
		scKkxxDao.update(scKkxx);
	}
	
	@Override
	public void delete(String id){
		scKkxxDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		scKkxxDao.deleteBatch(ids);
	}

	@Override
	public List<ScKkxxEntity> cx(Map<String, Object> map) {
		return scKkxxDao.cx(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return scKkxxDao.total(map);
	}
	
}
