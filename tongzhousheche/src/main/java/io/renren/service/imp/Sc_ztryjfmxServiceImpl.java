package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_ztryjfmxDao;
import io.renren.service.Sc_ztryjfmxService;
import io.renren.vo.DtcldzdansVo;

/**
 * 在逃人员
 * @author moulin
 *
 */
@Service("sc_ztryjfmxService")
public class Sc_ztryjfmxServiceImpl implements Sc_ztryjfmxService{
	
	@Autowired
	private Sc_ztryjfmxDao sc_ztryjfmxDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_ztryjfmxDao.queryList(map);
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_ztryjfmxDao.queryTotal(map);
	}

}
