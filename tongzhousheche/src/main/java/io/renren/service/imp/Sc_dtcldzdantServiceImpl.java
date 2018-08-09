package io.renren.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.renren.dao.Sc_dtcldzdantDao;
import io.renren.service.Sc_dtcldzdantService;
import io.renren.vo.DtcldzdansVo;

/**
 * 电子档案关联图
 * @author moulin
 *
 */
@Service("sc_dtcldzdantService")
public class Sc_dtcldzdantServiceImpl implements Sc_dtcldzdantService{
	
	@Autowired
	private Sc_dtcldzdantDao sc_dtcldzdantDao;
	
	@Override
	public DtcldzdansVo queryList(String plate) {
		
		return sc_dtcldzdantDao.queryList(plate);
	}



	

}
