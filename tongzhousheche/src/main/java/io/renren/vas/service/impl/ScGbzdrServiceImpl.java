package io.renren.vas.service.impl;

import io.renren.vas.dao.ScGbzdrDao;
import io.renren.vas.entity.ScGbzdrEntity;
import io.renren.vas.service.ScGbzdrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scGbzdrService")
public class ScGbzdrServiceImpl implements ScGbzdrService {
	@Autowired
	private ScGbzdrDao scGbzdrDao;
	
	@Override
	public ScGbzdrEntity queryObject(String drybh){
		return scGbzdrDao.queryObject(drybh);
	}
	
	@Override
	public List<ScGbzdrEntity> queryList(Map<String, Object> map){
		return scGbzdrDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scGbzdrDao.queryTotal(map);
	}
	
	@Override
	public void save(ScGbzdrEntity scGbzdr){
		scGbzdrDao.save(scGbzdr);
	}
	
	@Override
	public void update(ScGbzdrEntity scGbzdr){
		scGbzdrDao.update(scGbzdr);
	}
	
	@Override
	public void delete(String drybh){
		scGbzdrDao.delete(drybh);
	}
	
	@Override
	public void deleteBatch(String[] drybhs){
		scGbzdrDao.deleteBatch(drybhs);
	}

	@Override
	public List<ScGbzdrEntity> find(Map<String, Object> map) {
		return scGbzdrDao.find(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return scGbzdrDao.total(map);
	}
	
}
