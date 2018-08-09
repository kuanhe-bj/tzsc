package io.renren.service.imp;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.renren.dao.Sc_cyclfxDao;
import io.renren.service.Sc_cyclfxService;
import io.renren.vo.DtcldzdansVo;

/**
 * 存疑车辆
 * @author moulin
 *
 */
@Service("sc_cyclfxService")
public class Sc_cyclfxServiceImpl implements Sc_cyclfxService{
	
	@Autowired
	private Sc_cyclfxDao sc_cyclfxDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_cyclfxDao.queryList(map);		
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_cyclfxDao.queryTotal(map);
	}

}
