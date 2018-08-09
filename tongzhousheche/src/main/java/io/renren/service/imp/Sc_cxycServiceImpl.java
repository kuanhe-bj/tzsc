package io.renren.service.imp;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_cxycDao;
import io.renren.service.Sc_cxycService;
import io.renren.vo.DtcldzdansVo;

/**
 * 出行异常
 * @author moulin
 *
 */
@Service("sc_cxycService")
public class Sc_cxycServiceImpl implements Sc_cxycService{
	
	@Autowired
	private Sc_cxycDao sc_cxycDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_cxycDao.queryList(map);
			 return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_cxycDao.queryTotal(map);
	}

}
