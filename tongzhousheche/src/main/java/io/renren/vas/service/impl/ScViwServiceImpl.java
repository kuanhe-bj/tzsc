package io.renren.vas.service.impl;

import io.renren.vas.dao.ScViwDao;
import io.renren.vas.entity.ScViwEntity;
import io.renren.vas.service.ScViwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scViwService")
public class ScViwServiceImpl implements ScViwService {
	@Autowired
	private ScViwDao scViwDao;
	
	@Override
	public ScViwEntity queryObject(String id){
		return scViwDao.queryObject(id);
	}
	
	@Override
	public List<ScViwEntity> queryList(Map<String, Object> map){
		return scViwDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scViwDao.queryTotal(map);
	}
	
	@Override
	public void save(ScViwEntity scViw){
		scViwDao.save(scViw);
	}
	
	@Override
	public void update(ScViwEntity scViw){
		scViwDao.update(scViw);
	}
	
	@Override
	public void delete(String id){
		scViwDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		scViwDao.deleteBatch(ids);
	}

	@Override
	public List<ScViwEntity> find(Map<String, Object> map) {
		return scViwDao.find(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return scViwDao.total(map);
	}
	
}
