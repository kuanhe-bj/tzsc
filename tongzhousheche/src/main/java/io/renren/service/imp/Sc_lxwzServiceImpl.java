package io.renren.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_alarminfoDao;
import io.renren.dao.Sc_lxwzDao;
import io.renren.service.Sc_lxwzService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScTravelaaEntity;
import io.renren.vo.DtcldzdansVo;

/**
 * 连续违章
 * @author moulin
 *
 */
@Service("sc_lxwzService")
public class Sc_lxwzServiceImpl implements Sc_lxwzService{
	
	@Autowired
	private Sc_lxwzDao sc_lxwzDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_lxwzDao.queryList(map);
			 return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_lxwzDao.queryTotal(map);
	}
	
	@Override
	public int lxwzCheck(Map<String, Object> map) {
		return sc_lxwzDao.queryTotal(map);
	}

	@Override
	public void ContViolation() {
		int total = sc_lxwzDao.total();
		double f = 0;
		for(int j = 0; j < total;j++) {
			ScTravelaaEntity scTravelaaEntity = sc_lxwzDao.ContViolation(j);
			Integer times = Integer.parseInt(scTravelaaEntity.getAbnormal3month());
			double a = Math.toDegrees(Math.atan(times)*2/Math.PI);
			f = Double.parseDouble(String.format("%.2f", a));
			String plate = scTravelaaEntity.getCarnum();
			sc_lxwzDao.setContViolation(f, plate);
			if(f > 90) {
				ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
				alarminfoVo.setCarnum(plate);
				alarminfoVo.setTriggertime(new Date());
				alarminfoVo.setReadstate(0);
				alarminfoVo.setPushstate(1);
				alarminfoVo.setReceiver("admin1");
				alarminfoVo.setMessage(plate + "是连续违章车辆");
				sc_alarminfoDao.insert(alarminfoVo);
			}
		}
	}

	@Override
	public int setContViolation(double f, String plate) {
		return sc_lxwzDao.setContViolation(f, plate);
	}

	@Override
	public int total() {
		return sc_lxwzDao.total();
	}

}
