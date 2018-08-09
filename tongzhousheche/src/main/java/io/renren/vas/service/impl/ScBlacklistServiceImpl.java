package io.renren.vas.service.impl;

import io.renren.vas.dao.ScBlacklistDao;
import io.renren.vas.entity.ScBlacklistEntity;
import io.renren.vas.service.ScBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scBlacklistService")
public class ScBlacklistServiceImpl implements ScBlacklistService {
	@Autowired
	private ScBlacklistDao scBlacklistDao;
	
	@Override
	public ScBlacklistEntity queryObject(String id){
		return scBlacklistDao.queryObject(id);
	}
	
	@Override
	public List<ScBlacklistEntity> queryList(Map<String, Object> map){
		return scBlacklistDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scBlacklistDao.queryTotal(map);
	}
	
	@Override
	public void save(ScBlacklistEntity scBlacklist){
		scBlacklistDao.save(scBlacklist);
	}
	
	@Override
	public void update(ScBlacklistEntity scBlacklist){
		scBlacklistDao.update(scBlacklist);
	}
	
	@Override
	public void delete(String id){
		scBlacklistDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		scBlacklistDao.deleteBatch(ids);
	}

	@Override
	public String idMax() {
		return scBlacklistDao.idMax();
	}
	
}
