package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_sdryjfmxDao;
import io.renren.service.Sc_sdryjfmxService;
import io.renren.vo.DtcldzdansVo;

/**
 * 涉毒人员
 * @author moulin
 *
 */
@Service("sc_sdryjfmxService")
public class Sc_sdryjfmxServiceImpl implements Sc_sdryjfmxService{
	
	@Autowired
	private Sc_sdryjfmxDao sc_sdryjfmxDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_sdryjfmxDao.queryList(map);
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_sdryjfmxDao.queryTotal(map);
	}

}
