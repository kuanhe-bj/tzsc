package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_blacklistDao;
import io.renren.service.Sc_blacklistService;
import io.renren.vas.entity.ScBlacklistEntity;


/**
 * 黑名单
 * @author moulin
 *
 */
@Service("sc_blacklistService")
public class Sc_blacklistServiceImpl implements Sc_blacklistService {
	@Autowired
	private Sc_blacklistDao sc_blacklistDao;

	@Override
	public List<ScBlacklistEntity> checkList(Map<String, Object> params) {
		return sc_blacklistDao.checkList(params);
	}

	@Override
	public int check(String carNun) {
		return sc_blacklistDao.check(carNun).size();
	}


	@Override
	public ScBlacklistEntity checkCph(String carNun) {
		return sc_blacklistDao.checkCph(carNun);
	}
	
	@Override
	public String idMax() {
		return sc_blacklistDao.idMax();
	}

	@Override
	public void save(ScBlacklistEntity scBlacklistEntity) {
		sc_blacklistDao.save(scBlacklistEntity);
	}

	@Override
	public List<ScBlacklistEntity> queryList(Map<String, Object> map) {
		return sc_blacklistDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_blacklistDao.queryTotal(map);
	}
	
	@Override
	public void insertQB(Map<String, Object> params){
		sc_blacklistDao.insertQB(params);
	}

	@Override
	public int total(Map<String, Object> map) {
		return sc_blacklistDao.total(map);
	}
	
	
	@Override
	public void updatePass(String username,  String result, String cph) {
		sc_blacklistDao.updatePass(username, result, cph);
	}

	@Override
	public void updateNotPass(String username, String cph) {
		sc_blacklistDao.updateNotPass(username, cph);
	}

	@Override
	public List<ScBlacklistEntity> find(String carNum) {
		return sc_blacklistDao.find(carNum);
	}

	@Override
	public int updateBkStatus(String carnum) {
		return sc_blacklistDao.updateBkStatus(carnum);
	}
}
