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
import io.renren.dao.Sc_sgycDao;
import io.renren.service.Sc_sgycService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vo.DtcldzdansVo;

/**
 * 事故异常
 * @author moulin
 *
 */
@Service("sc_sgycService")
public class Sc_sgycServiceImpl implements Sc_sgycService{
	
	@Autowired
	private Sc_sgycDao sc_sgycDao;
	
	@Autowired
	private Sc_dtcldzdanDao sc_dtcldzdanDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_sgycDao.queryList(map);
	    return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_sgycDao.queryTotal(map);
	}

	@Override
	public void setUpdate() {
		sc_sgycDao.setupdate();
	}

	@Override
	public void setAccident() {
		int count = sc_dtcldzdanDao.queryTotal();
		for (int i = 0; i < count; i++) {
			List<ScDtcldzdanEntity> scDtcldzdanEntity = sc_dtcldzdanDao.getScDtcldzdanEntity(i);
			if(scDtcldzdanEntity.size() == 0) {
				continue;
			}
			String plate = scDtcldzdanEntity.get(0).getPlate();
			sc_sgycDao.setupdate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			String time = sdf.format(c.getTime());
			sc_sgycDao.setAccident(plate, time);
			double f = sc_sgycDao.getAccident(plate).getAccident();
			if(f > 90) {
				ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
				alarminfoVo.setCarnum(plate);
				alarminfoVo.setTriggertime(new Date());
				alarminfoVo.setReadstate(0);
				alarminfoVo.setPushstate(1);
				alarminfoVo.setReceiver("admin1");
				alarminfoVo.setMessage(plate + "是事故频率异常车辆");
				sc_alarminfoDao.insert(alarminfoVo);
			}
		}
	}
}
