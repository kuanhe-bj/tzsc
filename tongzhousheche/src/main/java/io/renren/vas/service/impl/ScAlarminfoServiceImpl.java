package io.renren.vas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.vas.dao.ScAlarminfoDao;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.service.ScAlarminfoService;


@Service("scAlarminfoService")
public class ScAlarminfoServiceImpl implements ScAlarminfoService {
	@Autowired
	private ScAlarminfoDao scAlarminfoDao;
	
	@Override
	public ScAlarminfoEntity queryObject(int id){
		return scAlarminfoDao.queryObject(id);
	}
	
	@Override
	public List<ScAlarminfoEntity> queryList(Map<String, Object> map){
		return scAlarminfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scAlarminfoDao.queryTotal(map);
	}
	
	@Override
	public void save(ScAlarminfoEntity scAlarminfo){
		scAlarminfoDao.save(scAlarminfo);
	}
	
	@Override
	public void update(ScAlarminfoEntity scAlarminfo){
		scAlarminfoDao.update(scAlarminfo);
	}
	
	@Override
	public void delete(String id){
		scAlarminfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids) {
		scAlarminfoDao.deleteBatch(ids);
	}

	@Override
	public List<ScAlarminfoEntity> find(Map<String, Object> map) {
		return scAlarminfoDao.find(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return scAlarminfoDao.total(map);
	}

	
}
