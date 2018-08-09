package io.renren.service.imp;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.renren.dao.Sc_zdqyphfxDao;
import io.renren.service.Sc_zdqyphfxService;
import io.renren.vo.DtcldzdansVo;

/**
 * 重点区域徘徊分析
 * @author moulin
 *
 */
@Service("sc_zdqyphfxService")
public class Sc_zdqxphfxServiceImpl implements Sc_zdqyphfxService{
	
	@Autowired
	private Sc_zdqyphfxDao sc_zdqyphfxDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_zdqyphfxDao.queryList(map);
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_zdqyphfxDao.queryTotal(map);
	}

}
