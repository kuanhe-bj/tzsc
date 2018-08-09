package io.renren.service.imp;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.renren.dao.Sc_tpfxDao;
import io.renren.service.Sc_tpfxService;
import io.renren.vo.DtcldzdansVo;

/**
 * 套牌分析
 * @author moulin
 *
 */
@Service("sc_tpfxService")
public class Sc_tpfxServiceImpl implements Sc_tpfxService{
	
	@Autowired
	private Sc_tpfxDao sc_tpfxDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_tpfxDao.queryList(map);		
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_tpfxDao.queryTotal(map);
	}

}
