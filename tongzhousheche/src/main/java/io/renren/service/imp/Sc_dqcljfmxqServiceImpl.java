package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_dqcljfmxqDao;
import io.renren.service.Sc_dqcljfmxqService;
import io.renren.vo.DtcldzdansVo;

/**
 * 盗抢车辆
 * @author moulin
 *
 */
@Service("sc_dqcljfmxqService")
public class Sc_dqcljfmxqServiceImpl implements Sc_dqcljfmxqService{
	
	@Autowired
	private Sc_dqcljfmxqDao sc_dqcljfmxqDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_dqcljfmxqDao.queryList(map);
			 return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_dqcljfmxqDao.queryTotal(map);
	}

}
