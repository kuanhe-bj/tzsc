package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.ScETCPDao;
import io.renren.dao.Sc_redlistDao;
import io.renren.service.Sc_redlistService;
import io.renren.vas.entity.ScRedlistEntity;
import io.renren.vo.ScEtcpEntity;
import io.renren.vo.Sc_jdc;

/**
 * 红名单
 * @author moulin
 *
 */
@Service("sc_redlistService")
public class Sc_redlistServiceImpl implements Sc_redlistService {
	
	@Autowired
	private Sc_redlistDao sc_redlistDao;

	@Autowired
	private ScETCPDao scETCPDao;

	@Override
	public List<ScRedlistEntity> checkList(Map<String, Object> params) {
		return sc_redlistDao.checkList(params);
	}

	@Override
	public List<Sc_jdc> check(String carNun) {
		return sc_redlistDao.check(carNun);
	}

	@Override
	public String idMax() {
		return sc_redlistDao.idMax();
	}

	@Override
	public void save(ScRedlistEntity scRedlist) {
		List<ScEtcpEntity> List=scETCPDao.checkReds(scRedlist.getCarnum());
		if (List!=null) {
			for (int i = 0; i < List.size(); i++) {
				scETCPDao.saveRedList(List.get(i));
			}
			scETCPDao.deleteByPlate(scRedlist.getCarnum());
		}
		sc_redlistDao.save(scRedlist);
	}

	@Override
	public List<ScRedlistEntity> queryList(Map<String, Object> map) {
		return sc_redlistDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_redlistDao.queryTotal();
	}

	@Override
	public int total(Map<String, Object> map) {
		return sc_redlistDao.total(map);
	}

	@Override
	public void updatePass(String username, String result, String cph) {
		sc_redlistDao.updatePass(username, result, cph);
	}

	@Override
	public void updateNotPass(String username, String cph) {
		sc_redlistDao.updateNotPass(username, cph);
	}

	@Override
	public ScRedlistEntity getId() {
		return sc_redlistDao.getId();
	}

	@Override
	public List<ScRedlistEntity> find(String carNum) {
		return sc_redlistDao.find(carNum);
	}

}
