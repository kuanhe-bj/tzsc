package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_weizuyinxingDao;
import io.renren.service.Sc_weizuyinxingService;
import io.renren.vo.GwrysjVo;

/**
 * 维族隐形重点人
 * @author moulin
 *
 */
@Service("sc_weizuyinxingService")
public class Sc_weizuyinxingServiceImpl implements Sc_weizuyinxingService {
	
	@Autowired
	private Sc_weizuyinxingDao sc_weizuyinxingDao;
	

	@Override
	public List<GwrysjVo> queryList(Map<String, Object> map) {
		return sc_weizuyinxingDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_weizuyinxingDao.queryTotal(map);
	}

	@Override
	public void update(String cp) {
		sc_weizuyinxingDao.update(cp);
	}

	@Override
	public List<GwrysjVo> list(Map<String, Object> map) {
		return sc_weizuyinxingDao.list(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return sc_weizuyinxingDao.total(map);
	}

}
