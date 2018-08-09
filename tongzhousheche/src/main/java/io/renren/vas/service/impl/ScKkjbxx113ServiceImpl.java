package io.renren.vas.service.impl;

import io.renren.vas.dao.ScKkjbxx113Dao;
import io.renren.vas.entity.ScKkjbxx113Entity;
import io.renren.vas.service.ScKkjbxx113Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scKkjbxx113Service")
public class ScKkjbxx113ServiceImpl implements ScKkjbxx113Service {
	@Autowired
	private ScKkjbxx113Dao scKkjbxx113Dao;
	
	@Override
	public ScKkjbxx113Entity queryObject(String id){
		return scKkjbxx113Dao.queryObject(id);
	}
	
	@Override
	public List<ScKkjbxx113Entity> queryList(Map<String, Object> map){
		return scKkjbxx113Dao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scKkjbxx113Dao.queryTotal(map);
	}
	
	@Override
	public void save(ScKkjbxx113Entity scKkjbxx113){
		scKkjbxx113Dao.save(scKkjbxx113);
	}
	
	@Override
	public void update(ScKkjbxx113Entity scKkjbxx113){
		scKkjbxx113Dao.update(scKkjbxx113);
	}
	
	@Override
	public void delete(String id){
		scKkjbxx113Dao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		scKkjbxx113Dao.deleteBatch(ids);
	}

	@Override
	public ScKkjbxx113Entity getIdMax() {
		return scKkjbxx113Dao.getIdMax();
	}

	@Override
	public List<ScKkjbxx113Entity> find(Map<String, Object> map) {
		return scKkjbxx113Dao.find(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return scKkjbxx113Dao.total(map);
	}

	
}
