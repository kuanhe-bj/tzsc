package io.renren.service.imp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_alarminfoDao;
import io.renren.dao.Sc_dtcldzdanDao;
import io.renren.dao.Sc_wzyclDao;
import io.renren.service.Sc_wzyclService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;

/**
 *违章异常分析模型
 * @author moulin
 *
 */
@Service("sc_wzyclService")
public class Sc_wzyclServiceImpl implements Sc_wzyclService{
	
	@Autowired
	private Sc_wzyclDao sc_wzyclDao;

	@Autowired
	private Sc_dtcldzdanDao sc_dtcldzdanDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;
	
	@Override
	public void wzycl() {
		int count = sc_dtcldzdanDao.queryTotal();
		for(int i=0;i<count;i++) {
			List<ScDtcldzdanEntity> scDtcldzdanEntity = sc_dtcldzdanDao.getScDtcldzdanEntity(i);
			if(scDtcldzdanEntity.size() == 0) {
				continue;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			String time = sdf.format(c.getTime());
			String plate = scDtcldzdanEntity.get(0).getPlate();
			//sc_wzyclDao.update();
			sc_wzyclDao.update1(plate, time);
			double violation = sc_wzyclDao.getViolation(plate).get(0).getViolation();
			if(violation > 90) {
				ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
				alarminfoVo.setCarnum(plate);
				alarminfoVo.setTriggertime(new Date());
				alarminfoVo.setReadstate(0);
				alarminfoVo.setPushstate(1);
				alarminfoVo.setReceiver("admin1");
				alarminfoVo.setMessage(plate + "是违章频繁车辆");
				sc_alarminfoDao.insert(alarminfoVo);
			}
			 
		}
		
	}
	

}
