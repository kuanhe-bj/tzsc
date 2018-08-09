package io.renren.vas.service.impl;

import io.renren.vas.dao.ScRedlistDao;
import io.renren.vas.entity.ScRedlistEntity;
import io.renren.vas.service.ScRedlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scRedlistService")
public class ScRedlistServiceImpl implements ScRedlistService {
	@Autowired
	private ScRedlistDao scRedlistDao;
	
	@Override
	public ScRedlistEntity queryObject(String id){
		return scRedlistDao.queryObject(id);
	}
	
	@Override
	public List<ScRedlistEntity> queryList(Map<String, Object> map){
		return scRedlistDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scRedlistDao.queryTotal(map);
	}
	
	@Override
	public void save(ScRedlistEntity scRedlist){
		scRedlistDao.save(scRedlist);
	}
	
	@Override
	public void update(ScRedlistEntity scRedlist){
		scRedlistDao.update(scRedlist);
	}
	
	@Override
	public void delete(String id){
		scRedlistDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		scRedlistDao.deleteBatch(ids);
	}

	@Override
	public String idMax() {
		return scRedlistDao.idMax();
	}
	
}
