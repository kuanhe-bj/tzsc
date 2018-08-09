package io.renren.vas.service.impl;

import io.renren.vas.dao.ScTwkkxx113Dao;
import io.renren.vas.entity.ScTwkkxx113Entity;
import io.renren.vas.service.ScTwkkxx113Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scTwkkxx113Service")
public class ScTwkkxx113ServiceImpl implements ScTwkkxx113Service {
	@Autowired
	private ScTwkkxx113Dao scTwkkxx113Dao;
	
	@Override
	public ScTwkkxx113Entity queryObject(String id){
		return scTwkkxx113Dao.queryObject(id);
	}
	
	@Override
	public List<ScTwkkxx113Entity> queryList(Map<String, Object> map){
		return scTwkkxx113Dao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scTwkkxx113Dao.queryTotal(map);
	}
	
	@Override
	public void save(ScTwkkxx113Entity scTwkkxx113){
		scTwkkxx113Dao.save(scTwkkxx113);
	}
	
	@Override
	public void update(ScTwkkxx113Entity scTwkkxx113){
		scTwkkxx113Dao.update(scTwkkxx113);
	}
	
	@Override
	public void delete(String id){
		scTwkkxx113Dao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		scTwkkxx113Dao.deleteBatch(ids);
	}

	@Override
	public ScTwkkxx113Entity getId() {
		return scTwkkxx113Dao.getId();
	}

	@Override
	public List<ScTwkkxx113Entity> find(Map<String, Object> map) {
		return scTwkkxx113Dao.find(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return scTwkkxx113Dao.total(map);
	}
	
}
