package io.renren.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_alarminfoDao;
import io.renren.dao.Sc_dtcldzdanDao;
import io.renren.dao.Sc_zdhmjfmxlDao;
import io.renren.service.Sc_zdhmjfmxlService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;

/**
 * 遮挡号牌分析模型
 * @author moulin
 *
 */
@Service("sc_zdhmjfmxlService")
public class Sc_zdhmjfmxlServiceImpl implements Sc_zdhmjfmxlService{
	
	@Autowired
	private Sc_zdhmjfmxlDao sc_zdhmjfmxDao;

	@Autowired
	private Sc_dtcldzdanDao sc_dtcldzdanDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;
	
	@Override
	public void zdhmjfmxl() {
		int count = sc_dtcldzdanDao.queryTotal();
		for(int i=0; i < count; i++) {
			List<ScDtcldzdanEntity> scDtcldzdanEntity = sc_dtcldzdanDao.getScDtcldzdanEntity(i);
			if(scDtcldzdanEntity.size() == 0) {
				continue;
			}
			String plate = scDtcldzdanEntity.get(0).getPlate();
			sc_zdhmjfmxDao.update1(plate);
			float obscured = sc_zdhmjfmxDao.getObscured(plate).get(0).getObscured();
			if(obscured > 90) {
				ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
					alarminfoVo.setCarnum(plate);
					alarminfoVo.setTriggertime(new Date());
					alarminfoVo.setReadstate(0);
					alarminfoVo.setPushstate(1);
					alarminfoVo.setReceiver("admin1");
					alarminfoVo.setMessage(plate + "是遮挡号牌车辆");
					sc_alarminfoDao.insert(alarminfoVo);
			 }
			 
		}
		
	}
	

}
