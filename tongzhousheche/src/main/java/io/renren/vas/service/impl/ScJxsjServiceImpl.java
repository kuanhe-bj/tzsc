package io.renren.vas.service.impl;

import io.renren.vas.dao.ScJxsjDao;
import io.renren.vas.entity.ScJxsjEntity;
import io.renren.vas.service.ScJxsjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scJxsjService")
public class ScJxsjServiceImpl implements ScJxsjService {
	@Autowired
	private ScJxsjDao scJxsjDao;
	
	@Override
	public ScJxsjEntity queryObject(String dataid){
		return scJxsjDao.queryObject(dataid);
	}
	
	@Override
	public List<ScJxsjEntity> queryList(Map<String, Object> map){
		return scJxsjDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scJxsjDao.queryTotal(map);
	}
	
	@Override
	public void save(ScJxsjEntity scJxsj){
		scJxsjDao.save(scJxsj);
	}
	
	@Override
	public void update(ScJxsjEntity scJxsj){
		scJxsjDao.update(scJxsj);
	}
	
	@Override
	public void delete(String dataid){
		scJxsjDao.delete(dataid);
	}
	
	@Override
	public void deleteBatch(String[] dataids){
		scJxsjDao.deleteBatch(dataids);
	}

	@Override
	public List<ScJxsjEntity> cx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return scJxsjDao.cx(map);
	}
	
}
