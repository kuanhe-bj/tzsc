package io.renren.vas.service.impl;

import io.renren.common.utils.Query;
import io.renren.vas.dao.ScJsrsjDao;
import io.renren.vas.entity.ScJsrsjEntity;
import io.renren.vas.service.ScJsrsjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scJsrsjService")
public class ScJsrsjServiceImpl implements ScJsrsjService {
	@Autowired
	private ScJsrsjDao scJsrsjDao;
	
	@Override
	public ScJsrsjEntity queryObject(String dataid){
		return scJsrsjDao.queryObject(dataid);
	}
	
	@Override
	public List<ScJsrsjEntity> queryList(Map<String, Object> map){
		return scJsrsjDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scJsrsjDao.queryTotal(map);
	}
	
	@Override
	public void save(ScJsrsjEntity scJsrsj){
		scJsrsjDao.save(scJsrsj);
	}
	
	@Override
	public void update(ScJsrsjEntity scJsrsj){
		scJsrsjDao.update(scJsrsj);
	}
	
	@Override
	public void delete(String dataid){
		scJsrsjDao.delete(dataid);
	}
	
	@Override
	public void deleteBatch(String[] dataids){
		scJsrsjDao.deleteBatch(dataids);
	}

	@Override
	public List<ScJsrsjEntity> queryBy(Query query) {
		// TODO Auto-generated method stub
		return scJsrsjDao.queryBy(query);
	}

	@Override
	public int sfzTotal(Query query) {
		// TODO Auto-generated method stub
		return scJsrsjDao.sfzTotal(query);
	}
	
}
