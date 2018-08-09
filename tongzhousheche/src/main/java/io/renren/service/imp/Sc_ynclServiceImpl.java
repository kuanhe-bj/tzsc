package io.renren.service.imp;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.renren.dao.Sc_ynclDao;
import io.renren.service.Sc_ynclService;
import io.renren.vo.DtcldzdansVo;

/**
 * 隐匿车辆
 * @author moulin
 *
 */
@Service("sc_ynclService")
public class Sc_ynclServiceImpl implements Sc_ynclService{
	
	@Autowired
	private Sc_ynclDao sc_ynclDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_ynclDao.queryList(map);
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_ynclDao.queryTotal(map);
	}

}
