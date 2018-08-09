package io.renren.vas.service.impl;

import io.renren.vas.dao.ScJczxx113Dao;
import io.renren.vas.entity.ScJczxx113Entity;
import io.renren.vas.service.ScJczxx113Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scJczxx113Service")
public class ScJczxx113ServiceImpl implements ScJczxx113Service {
	@Autowired
	private ScJczxx113Dao scJczxx113Dao;
	
	@Override
	public ScJczxx113Entity queryObject(String id){
		return scJczxx113Dao.queryObject(id);
	}
	
	@Override
	public List<ScJczxx113Entity> queryList(Map<String, Object> map){
		return scJczxx113Dao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scJczxx113Dao.queryTotal(map);
	}
	
	@Override
	public void save(ScJczxx113Entity scJczxx113){
		scJczxx113Dao.save(scJczxx113);
	}
	
	@Override
	public void update(ScJczxx113Entity scJczxx113){
		scJczxx113Dao.update(scJczxx113);
	}
	
	@Override
	public void delete(String id){
		scJczxx113Dao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		scJczxx113Dao.deleteBatch(ids);
	}

	@Override
	public ScJczxx113Entity getId() {
		return scJczxx113Dao.getId();
	}

	@Override
	public List<ScJczxx113Entity> find(Map<String, Object> map) {
		return scJczxx113Dao.find(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return scJczxx113Dao.total(map);
	}
	
}
