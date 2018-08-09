package io.renren.service.imp;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.renren.dao.Sc_wzycDao;
import io.renren.service.Sc_wzycService;
import io.renren.vo.DtcldzdansVo;

/**
 * 违章异常
 * @author moulin
 *
 */
@Service("sc_wzycService")
public class Sc_wzycServiceImpl implements Sc_wzycService{
	
	@Autowired
	private Sc_wzycDao sc_wzycdao;

	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_wzycdao.queryList(map);		
		return list;		
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_wzycdao.queryTotal(map);
	}
	
}
