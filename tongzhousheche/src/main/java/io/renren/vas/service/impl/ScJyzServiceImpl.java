package io.renren.vas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import io.renren.common.utils.Query;
import io.renren.vas.dao.ScJyzDao;
import io.renren.vas.entity.ScJyzEntity;
import io.renren.vas.service.ScJyzService;


@Service("scJyzService")
public class ScJyzServiceImpl implements ScJyzService {
	@Autowired
	private ScJyzDao scJyzDao;
	
	@Override
	public ScJyzEntity queryObject(String id){
		return scJyzDao.queryObject(id);
	}
	
	@Override
	public List<ScJyzEntity> queryList(Map<String, Object> map){
		return scJyzDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scJyzDao.queryTotal(map);
	}
	
	@Override
	public void save(ScJyzEntity scJyz){
		scJyzDao.save(scJyz);
	}
	
	@Override
	public void update(ScJyzEntity scJyz){
		scJyzDao.update(scJyz);
	}
	
	@Override
	public void delete(String id){
		scJyzDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		scJyzDao.deleteBatch(ids);
	}

	@Override
	public List<ScJyzEntity> queryBycph(@RequestParam Map<String, Object> params) {
		return scJyzDao.queryBycph(params);
	}

	@Override
	public int cphTotal(Query query) {
		return scJyzDao.cphTotal(query);
	}
	
}
