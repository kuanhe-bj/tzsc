package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_lccxjfmxDao;
import io.renren.service.Sc_lccxjfmxService;
import io.renren.vo.DtcldzdansVo;

/**
 * 凌晨出行
 * @author moulin
 *
 */
@Service("sc_lccxjfmxService")
public class Sc_lccxjfmxServiceImpl implements Sc_lccxjfmxService{
	
	@Autowired
	private Sc_lccxjfmxDao sc_lccxjfmxDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_lccxjfmxDao.queryList(map);
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_lccxjfmxDao.queryTotal(map);
	}

}
