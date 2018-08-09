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
import io.renren.dao.Sc_ycgjDao;
import io.renren.service.Sc_ycgjService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vo.DtcldzdansVo;

/**
 * 异常轨迹
 * @author moulin
 *
 */
@Service("sc_ycgjService")
public class Sc_ycgjServiceImpl implements Sc_ycgjService {
	
	@Autowired
	private Sc_ycgjDao sc_ycgjDao;
	
	@Autowired
	private Sc_dtcldzdanDao sc_dtcldzdanDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_ycgjDao.queryList(map);	
			 return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {		
		return sc_ycgjDao.queryTotal(map);
	}

	@Override
	public void ycgj() {
		int count = sc_dtcldzdanDao.queryTotal();
		for (int i = 0; i < count; i++) {
			List<ScDtcldzdanEntity> scDtcldzdanEntity = sc_dtcldzdanDao.getScDtcldzdanEntity(i);
			if(scDtcldzdanEntity.size() == 0) {
				continue;
			}
			String plate = scDtcldzdanEntity.get(0).getPlate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String end = sdf.format(date);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			String start = sdf.format(c.getTime());
			List<ScEtcptjdEntity> list = sc_ycgjDao.showList(plate, start, end);
			double a = Math.toDegrees(Math.atan(list.size()/90)*2/Math.PI);
			double f = Double.parseDouble(String.format("%.2f", a));
			sc_ycgjDao.ycgj(f, plate);
			if(f >= 90) {
				ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
				alarminfoVo.setCarnum(plate);
				alarminfoVo.setTriggertime(new Date());
				alarminfoVo.setReadstate(0);
				alarminfoVo.setPushstate(1);
				alarminfoVo.setReceiver("admin1");
				alarminfoVo.setMessage(plate + "是异常轨迹车辆");
				sc_alarminfoDao.insert(alarminfoVo);
			}
		}
	}

}
