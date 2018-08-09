package io.renren.vas.service.impl;

import io.renren.vas.dao.ScWsjdcjbxxDao;
import io.renren.vas.entity.ScWsjdcjbxxEntity;
import io.renren.vas.service.ScWsjdcjbxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scWsjdcjbxxService")
public class ScWsjdcjbxxServiceImpl implements ScWsjdcjbxxService {
	@Autowired
	private ScWsjdcjbxxDao scWsjdcjbxxDao;
	
	@Override
	public ScWsjdcjbxxEntity queryObject(String dataid){
		return scWsjdcjbxxDao.queryObject(dataid);
	}
	
	@Override
	public List<ScWsjdcjbxxEntity> queryList(Map<String, Object> map){
		return scWsjdcjbxxDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scWsjdcjbxxDao.queryTotal(map);
	}
	
	@Override
	public void save(ScWsjdcjbxxEntity scWsjdcjbxx){
		scWsjdcjbxxDao.save(scWsjdcjbxx);
	}
	
	@Override
	public void update(ScWsjdcjbxxEntity scWsjdcjbxx){
		scWsjdcjbxxDao.update(scWsjdcjbxx);
	}
	
	@Override
	public void delete(String dataid){
		scWsjdcjbxxDao.delete(dataid);
	}
	
	@Override
	public void deleteBatch(String[] dataids){
		scWsjdcjbxxDao.deleteBatch(dataids);
	}

	@Override
	public List<ScWsjdcjbxxEntity> find(Map<String, Object> map) {
		return scWsjdcjbxxDao.find(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return scWsjdcjbxxDao.total(map);
	}
	
}
