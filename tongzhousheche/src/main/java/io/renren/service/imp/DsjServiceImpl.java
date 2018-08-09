package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.DsjDao;
import io.renren.service.DsjService;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vas.entity.ScScajEntity;

/**
 * 大事件
 * @author moulin
 *
 */
@Service("dsjService")
public class DsjServiceImpl implements DsjService {

	@Autowired
	private DsjDao dsjDao;

	@Override
	public List<ScScajEntity> fin(Map<String, Object> map) {
		return dsjDao.fin(map);
	}

	@Override
	public List<ScScajEntity> jw(String cph) {

		return dsjDao.jw(cph);
	}

	@Override
	public List<ScEtcptjdEntity> allcar(Map<String, Object> map) {
		return dsjDao.allcar(map);
	}

	@Override
	public List<ScScajEntity> x(String ajbh, String ajmc) {
		return dsjDao.x(ajbh, ajmc);
	}

	@Override
	public int total(Map<String, Object> map) {
		return dsjDao.total(map);
	}

	@Override
	public List<ScScajEntity> fin_ds(Map<String, Object> map) {
		return dsjDao.fin_ds(map);
	}

	@Override
	public List<ScScajEntity> fin_dsall(Map<String, Object> map) {
		return dsjDao.fin_dsall(map);
	}

}
