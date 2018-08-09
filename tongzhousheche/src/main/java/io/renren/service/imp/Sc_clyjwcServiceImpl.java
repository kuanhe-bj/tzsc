package io.renren.service.imp;

import io.renren.dao.Sc_clyjwcDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.renren.service.Sc_clyjwcService;
import io.renren.vo.DtcldzdansVo;

/**
 * 车辆有进无出
 * 
 * @author moulin
 *
 */
@Service("sc_clyjwcService")
public class Sc_clyjwcServiceImpl implements Sc_clyjwcService {
	
	@Autowired
	private Sc_clyjwcDao sc_clyjwcDao;

	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_clyjwcDao.queryList(map);
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_clyjwcDao.queryTotal(map);
	}

}
