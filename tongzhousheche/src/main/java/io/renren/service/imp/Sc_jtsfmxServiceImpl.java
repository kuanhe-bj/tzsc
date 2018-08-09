package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.renren.dao.Sc_jtsfmxDao;
import io.renren.service.Sc_jtsfmxService;
import io.renren.vo.GwrysjVo;

/**
 * 集体上访
 * @author moulin
 *
 */
@Service("sc_jtsfmxService")
public class Sc_jtsfmxServiceImpl implements Sc_jtsfmxService{
	
	@Autowired
	private Sc_jtsfmxDao sc_jtsfmxDao;
	
	@Override
	public List<GwrysjVo> queryList(Map<String, Object> map) {
		List<GwrysjVo> list = this.sc_jtsfmxDao.queryList(map);
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_jtsfmxDao.queryTotal(map);
	}

	@Override
	public List<GwrysjVo> list(Map<String, Object> map) {
		return sc_jtsfmxDao.list(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return sc_jtsfmxDao.total(map);
	}

}
