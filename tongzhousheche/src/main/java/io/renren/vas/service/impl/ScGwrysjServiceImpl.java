package io.renren.vas.service.impl;

import io.renren.vas.dao.ScGwrysjDao;
import io.renren.vas.entity.ScGwrysjEntity;
import io.renren.vas.service.ScGwrysjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scGwrysjService")
public class ScGwrysjServiceImpl implements ScGwrysjService {
	@Autowired
	private ScGwrysjDao scGwrysjDao;
	
	@Override
	public ScGwrysjEntity queryObject(String drybh){
		return scGwrysjDao.queryObject(drybh);
	}
	
	@Override
	public List<ScGwrysjEntity> queryList(Map<String, Object> map){
		return scGwrysjDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scGwrysjDao.queryTotal(map);
	}
	
	@Override
	public void save(ScGwrysjEntity scGwrysj){
		scGwrysjDao.save(scGwrysj);
	}
	
	@Override
	public void update(ScGwrysjEntity scGwrysj){
		scGwrysjDao.update(scGwrysj);
	}
	
	@Override
	public void delete(String drybh){
		scGwrysjDao.delete(drybh);
	}
	
	@Override
	public void deleteBatch(String[] drybhs){
		scGwrysjDao.deleteBatch(drybhs);
	}

	@Override
	public List<ScGwrysjEntity> find(Map<String, Object> map) {
		return scGwrysjDao.find(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return scGwrysjDao.total(map);
	}

	@Override
	public ScGwrysjEntity getDrybh() {
		return scGwrysjDao.getDrybh();
	}
	
}
