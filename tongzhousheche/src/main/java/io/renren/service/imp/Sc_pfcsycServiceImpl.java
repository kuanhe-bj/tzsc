package io.renren.service.imp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_alarminfoDao;
import io.renren.dao.Sc_dtcldzdanDao;
import io.renren.dao.Sc_pfcsycDao;
import io.renren.service.Sc_pfcsycService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vo.DtcldzdansVo;

/**
 * 频繁超速异常
 * @author moulin
 *
 */
@Service("sc_pfcsycService")
public class Sc_pfcsycServiceImpl implements Sc_pfcsycService {
	
	@Autowired
	private Sc_pfcsycDao sc_pfcsycDao;
	
	@Autowired
	private Sc_dtcldzdanDao sc_dtcldzdanDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;

	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_pfcsycDao.queryList(map);
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_pfcsycDao.queryTotal(map);
	}

	@Override
	public void setupdate() {
		sc_pfcsycDao.setupdate(); 
	}

	@Override
	public void setOverSpeed() {
		int count = sc_dtcldzdanDao.queryTotal();
		for (int i = 0; i < count; i++) {
			List<ScDtcldzdanEntity> scDtcldzdanEntity = sc_dtcldzdanDao.getScDtcldzdanEntity(i);
			if(scDtcldzdanEntity.size() == 0) {
				continue;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			String time = sdf.format(c.getTime());
			String plate = scDtcldzdanEntity.get(0).getPlate();
			sc_pfcsycDao.setupdate();
			sc_pfcsycDao.setOverSpeed(plate, time);
			double f = sc_pfcsycDao.getOverSpeed(plate).getOverspeed();
			if(f > 90) {
				ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
				alarminfoVo.setCarnum(plate);
				alarminfoVo.setTriggertime(new Date());
				alarminfoVo.setReadstate(0);
				alarminfoVo.setPushstate(1);
				alarminfoVo.setReceiver("admin1");
				alarminfoVo.setMessage(plate + "是频繁超速异常车辆");
				sc_alarminfoDao.insert(alarminfoVo);
			}
		}
	}
}
