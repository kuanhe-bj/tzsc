package io.renren.vas.service.impl;

import io.renren.vas.dao.ScJczxxDao;
import io.renren.vas.entity.ScJczxxEntity;
import io.renren.vas.service.ScJczxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scJczxxService")
public class ScJczxxServiceImpl implements ScJczxxService {
	@Autowired
	private ScJczxxDao scJczxxDao;
	
	
	@Override
	public List<ScJczxxEntity> queryList(Map<String, Object> map){
		return scJczxxDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scJczxxDao.queryTotal(map);
	}
	
	@Override
	public void save(ScJczxxEntity scJczxx){
		scJczxxDao.save(scJczxx);
	}
	
	@Override
	public void update(ScJczxxEntity scJczxx){
		scJczxxDao.update(scJczxx);
	}
	
	@Override
	public void delete(String id){
		scJczxxDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		scJczxxDao.deleteBatch(ids);
	}

	@Override
	public ScJczxxEntity idMax1() {
		
		   ScJczxxEntity idMax1 = scJczxxDao.idMax1();
		return idMax1;
	}

	@Override
	public ScJczxxEntity queryObject(String id) {
		// TODO Auto-generated method stub
		return scJczxxDao.queryObject(id);
	}

	@Override
	public List<ScJczxxEntity> cx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return scJczxxDao.cx(map);
	}


}
