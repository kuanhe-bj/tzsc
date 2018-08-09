package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import io.renren.dao.Sc_xxfxDao;

import io.renren.service.Sc_xxfxService;
import io.renren.vo.DtcldzdansVo;


/**
 * 限行分析
 * @author moulin
 *
 */
@Service("sc_xxfxService")
public class Sc_xxfxServiceImpl implements Sc_xxfxService{
	
	@Autowired
	private Sc_xxfxDao sc_xxfxDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_xxfxDao.queryList(map);	
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_xxfxDao.queryTotal(map);
	}

}
