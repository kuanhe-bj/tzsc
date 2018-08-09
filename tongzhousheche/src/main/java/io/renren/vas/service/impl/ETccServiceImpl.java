package io.renren.vas.service.impl;


import io.renren.vas.dao.ETccDao;
import io.renren.vas.entity.ETccEntity;
import io.renren.vas.service.ETccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;




@Service("eTccService")
public class ETccServiceImpl implements ETccService {
	@Autowired
	private ETccDao eTccDao;
	
	@Override
	public ETccEntity queryObject(String eId){
		return eTccDao.queryObject(eId);
	}
	
	@Override
	public List<ETccEntity> queryList(Map<String, Object> map){
		return eTccDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return eTccDao.queryTotal(map);
	}
	
	@Override
	public void save(ETccEntity eTcc){
		eTccDao.save(eTcc);
	}
	
	@Override
	public void update(ETccEntity eTcc){
		eTccDao.update(eTcc);
	}
	
	@Override
	public void delete(String eId){
		eTccDao.delete(eId);
	}
	
	@Override
	public void deleteBatch(String[] eIds){
		eTccDao.deleteBatch(eIds);
	}
	
}
