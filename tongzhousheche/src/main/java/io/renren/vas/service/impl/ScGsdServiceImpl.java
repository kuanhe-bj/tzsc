package io.renren.vas.service.impl;

import io.renren.vas.dao.ScGsdDao;
import io.renren.vas.entity.ScGsdEntity;
import io.renren.vas.service.ScGsdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service("scGsdService")
public class ScGsdServiceImpl implements ScGsdService {
	@Autowired
	private ScGsdDao scGsdDao;
	
	@Override
	public ScGsdEntity queryObject(Integer id){
		return scGsdDao.queryObject(id);
	}
	
	@Override
	public List<ScGsdEntity> queryList(Map<String, Object> map){
		return scGsdDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scGsdDao.queryTotal(map);
	}
	
	@Override
	public void save(ScGsdEntity scGsd){
		scGsdDao.save(scGsd);
	}
	
	@Override
	public void update(ScGsdEntity scGsd){
		scGsdDao.update(scGsd);
	}
	
	@Override
	public void delete(Integer id){
		scGsdDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		scGsdDao.deleteBatch(ids);
	}
	
}
