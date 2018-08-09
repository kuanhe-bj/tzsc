package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_gxryjfmxDao;
import io.renren.service.Sc_gxryjfmxService;
import io.renren.vo.DtcldzdansVo;

/**
 * 关系人员分析
 * @author moulin
 *
 */
@Service("sc_gxryjfmxService")
public class Sc_gxryjfmxServiceImpl implements Sc_gxryjfmxService{
	
	@Autowired
	private Sc_gxryjfmxDao sc_gxryjfmxDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_gxryjfmxDao.queryList(map);
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_gxryjfmxDao.queryTotal(map);
	}

}
