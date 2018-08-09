package io.renren.vas.service.impl;

import io.renren.vas.dao.ScSfzdrDao;
import io.renren.vas.entity.ScSfzdrEntity;
import io.renren.vas.service.ScSfzdrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scSfzdrService")
public class ScSfzdrServiceImpl implements ScSfzdrService {
	@Autowired
	private ScSfzdrDao scSfzdrDao;
	
	@Override
	public ScSfzdrEntity queryObject(String dataid){
		return scSfzdrDao.queryObject(dataid);
	}
	
	@Override
	public List<ScSfzdrEntity> queryList(Map<String, Object> map){
		return scSfzdrDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scSfzdrDao.queryTotal(map);
	}
	
	@Override
	public void save(ScSfzdrEntity scSfzdr){
		scSfzdrDao.save(scSfzdr);
	}
	
	@Override
	public void update(ScSfzdrEntity scSfzdr){
		scSfzdrDao.update(scSfzdr);
	}
	
	@Override
	public void delete(String dataid){
		scSfzdrDao.delete(dataid);
	}
	
	@Override
	public void deleteBatch(String[] dataids){
		scSfzdrDao.deleteBatch(dataids);
	}

	@Override
	public List<ScSfzdrEntity> cx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return scSfzdrDao.cx(map);
	}
	
}
