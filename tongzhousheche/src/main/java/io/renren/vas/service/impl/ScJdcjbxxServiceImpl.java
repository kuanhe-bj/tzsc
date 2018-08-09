package io.renren.vas.service.impl;

import io.renren.vas.dao.ScJdcjbxxDao;
import io.renren.vas.entity.ScJdcjbxxEntity;
import io.renren.vas.service.ScJdcjbxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scJdcjbxxService")
public class ScJdcjbxxServiceImpl implements ScJdcjbxxService {
	@Autowired
	private ScJdcjbxxDao scJdcjbxxDao;
	
	@Override
	public ScJdcjbxxEntity queryObject(String dataid){
		return scJdcjbxxDao.queryObject(dataid);
	}
	
	@Override
	public List<ScJdcjbxxEntity> queryList(Map<String, Object> map){
		return scJdcjbxxDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scJdcjbxxDao.queryTotal(map);
	}
	
	@Override
	public void save(ScJdcjbxxEntity scJdcjbxx){
		scJdcjbxxDao.save(scJdcjbxx);
	}
	
	@Override
	public void update(ScJdcjbxxEntity scJdcjbxx){
		scJdcjbxxDao.update(scJdcjbxx);
	}
	
	@Override
	public void delete(String dataid){
		scJdcjbxxDao.delete(dataid);
	}
	
	@Override
	public void deleteBatch(String[] dataids){
		scJdcjbxxDao.deleteBatch(dataids);
	}

	@Override
	public List<ScJdcjbxxEntity> find(Map<String, Object> map) {
		return scJdcjbxxDao.find(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return scJdcjbxxDao.total(map);
	}
	
}
