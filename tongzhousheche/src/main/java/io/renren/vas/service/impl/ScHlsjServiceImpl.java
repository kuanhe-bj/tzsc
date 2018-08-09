package io.renren.vas.service.impl;

import io.renren.vas.dao.ScHlsjDao;
import io.renren.vas.entity.ScHlsjEntity;
import io.renren.vas.service.ScHlsjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scHlsjService")
public class ScHlsjServiceImpl implements ScHlsjService {
	@Autowired
	private ScHlsjDao scHlsjDao;
	
	@Override
	public ScHlsjEntity queryObject(String checkinfoid){
		return scHlsjDao.queryObject(checkinfoid);
	}
	
	@Override
	public List<ScHlsjEntity> queryList(Map<String, Object> map){
		return scHlsjDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scHlsjDao.queryTotal(map);
	}
	
	@Override
	public void save(ScHlsjEntity scHlsj){
		scHlsjDao.save(scHlsj);
	}
	
	@Override
	public void update(ScHlsjEntity scHlsj){
		scHlsjDao.update(scHlsj);
	}
	
	@Override
	public void delete(String checkinfoid){
		scHlsjDao.delete(checkinfoid);
	}
	
	@Override
	public void deleteBatch(String[] checkinfoids){
		scHlsjDao.deleteBatch(checkinfoids);
	}

	@Override
	public List<ScHlsjEntity> find(Map<String, Object> map) {
		return scHlsjDao.find(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return scHlsjDao.total(map);
	}
	
}
