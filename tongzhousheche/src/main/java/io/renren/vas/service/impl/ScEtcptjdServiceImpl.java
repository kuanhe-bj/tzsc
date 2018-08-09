package io.renren.vas.service.impl;

import io.renren.vas.dao.ScEtcptjdDao;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vas.service.ScEtcptjdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("scEtcptjdService")
public class ScEtcptjdServiceImpl implements ScEtcptjdService {
	@Autowired
	private ScEtcptjdDao scEtcptjdDao;
	
	@Override
	public ScEtcptjdEntity queryObject(String eid){
		return scEtcptjdDao.queryObject(eid);
	}
	
	@Override
	public List<ScEtcptjdEntity> queryList(Map<String, Object> map){
		return scEtcptjdDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return scEtcptjdDao.queryTotal(map);
	}
	
	@Override
	public void save(ScEtcptjdEntity scEtcptjd){
		scEtcptjdDao.save(scEtcptjd);
	}
	
	@Override
	public void update(ScEtcptjdEntity scEtcptjd){
		scEtcptjdDao.update(scEtcptjd);
	}
	
	@Override
	public void delete(String eid){
		scEtcptjdDao.delete(eid);
	}
	
	@Override
	public void deleteBatch(String[] eids){
		scEtcptjdDao.deleteBatch(eids);
	}
	
}
