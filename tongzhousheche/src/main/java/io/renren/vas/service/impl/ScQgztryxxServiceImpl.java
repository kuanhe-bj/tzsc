package io.renren.vas.service.impl;

import io.renren.vas.dao.ScQgztryxxDao;
import io.renren.vas.entity.ScQgztryxxEntity;
import io.renren.vas.service.ScQgztryxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scQgztryxxService")
public class ScQgztryxxServiceImpl implements ScQgztryxxService {
	@Autowired
	private ScQgztryxxDao scQgztryxxDao;
	
	@Override
	public ScQgztryxxEntity queryObject(String rybh){
		return scQgztryxxDao.queryObject(rybh);
	}
	
	@Override
	public List<ScQgztryxxEntity> queryList(Map<String, Object> map){
		return scQgztryxxDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scQgztryxxDao.queryTotal(map);
	}
	
	@Override
	public void save(ScQgztryxxEntity scQgztryxx){
		scQgztryxxDao.save(scQgztryxx);
	}
	
	@Override
	public void update(ScQgztryxxEntity scQgztryxx){
		scQgztryxxDao.update(scQgztryxx);
	}
	
	@Override
	public void delete(String rybh){
		scQgztryxxDao.delete(rybh);
	}
	
	@Override
	public void deleteBatch(String[] rybhs){
		scQgztryxxDao.deleteBatch(rybhs);
	}

	@Override
	public List<ScQgztryxxEntity> find(Map<String, Object> map) {
		return scQgztryxxDao.find(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return scQgztryxxDao.total(map);
	}

	@Override
	public ScQgztryxxEntity getRybh() {
		return scQgztryxxDao.getRybh();
	}
	
}
