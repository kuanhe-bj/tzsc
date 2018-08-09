package io.renren.service.imp;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.renren.dao.Sc_dzwlgwdqDao;
import io.renren.service.Sc_dzwlgwdqService;
import io.renren.vo.DtcldzdansVo;

/**
 * 电子围栏
 * @author moulin
 *
 */
@Service("sc_dzwlgwdqService")
public class Sc_dzwlgwdqServiceImpl implements Sc_dzwlgwdqService{
	
	@Autowired
	private Sc_dzwlgwdqDao sc_dzwlgwdqDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_dzwlgwdqDao.queryList(map);	
			 return list;	
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_dzwlgwdqDao.queryTotal(map);
	}

}
