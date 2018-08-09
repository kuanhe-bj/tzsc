package io.renren.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_crgltDao;
import io.renren.service.Sc_crgltService;
import io.renren.vo.Sc_rcglsVo;

/**
 * 车人关联图
 * @author moulin
 *
 */

@Service("sc_crgltService")
public class Sc_crgltServiceImpl implements Sc_crgltService{
	
	@Autowired
	private Sc_crgltDao sc_crgltDao;
	
	@Override
	public List<Sc_rcglsVo> queryList(String cp) {
		
		return sc_crgltDao.queryList(cp);
	}



	

}
