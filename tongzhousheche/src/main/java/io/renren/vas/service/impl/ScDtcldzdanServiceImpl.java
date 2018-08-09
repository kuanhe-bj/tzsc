package io.renren.vas.service.impl;

import io.renren.vas.dao.ScDtcldzdanDao;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.service.ScDtcldzdanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;





@Service("scDtcldzdanService")
public class ScDtcldzdanServiceImpl implements ScDtcldzdanService {
	@Autowired
	private ScDtcldzdanDao scDtcldzdanDao;
	
	@Override
	public ScDtcldzdanEntity queryObject(Integer id){
		return scDtcldzdanDao.queryObject(id);
	}
	
	@Override
	public List<ScDtcldzdanEntity> queryList(Map<String, Object> map){
		return scDtcldzdanDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scDtcldzdanDao.queryTotal(map);
	}
	
	@Override
	public void save(ScDtcldzdanEntity scDtcldzdan){
		scDtcldzdanDao.save(scDtcldzdan);
	}
	
	@Override
	public void update(ScDtcldzdanEntity scDtcldzdan){
		scDtcldzdanDao.update(scDtcldzdan);
	}
	
	@Override
	public void delete(Integer id){
		scDtcldzdanDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		scDtcldzdanDao.deleteBatch(ids);
	}

	@Override
	public List<ScDtcldzdanEntity> all() {
		
		return scDtcldzdanDao.all();
	}
	
}
