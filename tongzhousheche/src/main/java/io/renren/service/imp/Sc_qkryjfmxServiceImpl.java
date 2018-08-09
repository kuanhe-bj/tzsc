package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_qkryjfmxDao;
import io.renren.service.Sc_qkryjfmxService;
import io.renren.vo.DtcldzdansVo;

/**
 * 前科人员
 * @author moulin
 *
 */
@Service("sc_qkryjfmxService")
public class Sc_qkryjfmxServiceImpl implements Sc_qkryjfmxService{
	
	@Autowired
	private Sc_qkryjfmxDao sc_qkryjfmxDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_qkryjfmxDao.queryList(map);
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_qkryjfmxDao.queryTotal(map);
	}

}
