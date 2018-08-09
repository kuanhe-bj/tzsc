package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_zdhmjfmxDao;
import io.renren.service.Sc_zdhmjfmxService;
import io.renren.vo.DtcldzdansVo;

/**
 * 遮挡号牌
 * @author moulin
 *
 */
@Service("sc_zdhmjfmxService")
public class Sc_zdhmjfmxServiceImpl implements Sc_zdhmjfmxService{
	
	@Autowired
	private Sc_zdhmjfmxDao sc_zdhmjfmxDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_zdhmjfmxDao.queryList(map);
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_zdhmjfmxDao.queryTotal(map);
	}

}
