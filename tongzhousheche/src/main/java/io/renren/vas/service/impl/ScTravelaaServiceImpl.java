package io.renren.vas.service.impl;

import io.renren.vas.dao.ScTravelaaDao;
import io.renren.vas.entity.ScTravelaaEntity;
import io.renren.vas.service.ScTravelaaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;





@Service("scTravelaaService")
public class ScTravelaaServiceImpl implements ScTravelaaService
{
	@Autowired
	private ScTravelaaDao scTravelaaDao;
	
	@Override
	public ScTravelaaEntity queryObject(String id){
		return scTravelaaDao.queryObject(id);
	}
	
	@Override
	public List<ScTravelaaEntity> queryList(Map<String, Object> map){
		return scTravelaaDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scTravelaaDao.queryTotal(map);
	}
	
	@Override
	public void save(ScTravelaaEntity scTravelaa){
		scTravelaaDao.save(scTravelaa);
	}
	
	@Override
	public void update(ScTravelaaEntity scTravelaa){
		scTravelaaDao.update(scTravelaa);
	}
	
	@Override
	public void delete(String id){
		scTravelaaDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		scTravelaaDao.deleteBatch(ids);
	}
	
}
