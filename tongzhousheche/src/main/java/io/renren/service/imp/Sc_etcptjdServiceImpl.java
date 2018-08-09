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
import io.renren.dao.Sc_etcptjdDao;
import io.renren.service.Sc_etcptjdService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScEtcptjdEntity;

/**
 * etcp行车记录
 * @author moulin
 *
 */
@Service("sc_etcptjdService")
public class Sc_etcptjdServiceImpl implements Sc_etcptjdService {
	
	@Autowired
	private Sc_etcptjdDao sc_etcptjdDao;
	
	@Autowired
	private Sc_dtcldzdanDao sc_dtcldzdanDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;


	@Override
	public List<ScEtcptjdEntity> findByCPH(Map<String, Object> params) {
		return sc_etcptjdDao.findByCPH(params);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_etcptjdDao.queryTotal(map);
	}
	
	@Override
	public int total(Map<String, Object> map) {
		return sc_etcptjdDao.total(map).size();
	}

	@Override
	public List<ScEtcptjdEntity> queryList(Map<String, Object> map) {
		return sc_etcptjdDao.queryList(map);
	}

	@Override
	public void taopai() {
		int total = sc_dtcldzdanDao.queryTotal();
		for(int j = 0; j < total;j++) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String end = sdf.format(date);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			String start = sdf.format(c.getTime());
			List<ScEtcptjdEntity> list = sc_etcptjdDao.check(j, start, end);
			if(list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String plate = list.get(i).getCarNumber();
					//System.out.println(plate);
					double j1 = Double.parseDouble(list.get(0).getJingdu());
					double w1 = Double.parseDouble(list.get(0).getWeidu());
					double j2 = Double.parseDouble(list.get(i).getJingdu());
					double w2 = Double.parseDouble(list.get(i).getWeidu());
					double dis = getDistance(j1, w1, j2, w2);
					long time1 = list.get(0).getEnterTime().getTime();
					long time2 = list.get(i).getEnterTime().getTime();
					double s = dis * 1000 * 60 * 60/(time2 - time1);
					if(s > 160) {
						sc_etcptjdDao.update(plate);
						ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
						alarminfoVo.setCarnum(plate);
						alarminfoVo.setTriggertime(new Date());
						alarminfoVo.setReadstate(0);
						alarminfoVo.setPushstate(1);
						alarminfoVo.setReceiver("admin1");
						alarminfoVo.setMessage(plate + "是套牌车辆");
						sc_alarminfoDao.insert(alarminfoVo);
						break;
					}
				}
			} else {
				continue;
			}
		}
	} 
	
	public double getDistance(double j1, double w1, double j2, double w2) {  
		  
        double lon1 = (Math.PI / 180) * j1;  
        double lon2 = (Math.PI / 180) * j2;  
        double lat1 = (Math.PI / 180) * w1;  
        double lat2 = (Math.PI / 180) * w2;  
  
        // double Lat1r = (Math.PI/180)*(gp1.getLatitudeE6()/1E6);  
        // double Lat2r = (Math.PI/180)*(gp2.getLatitudeE6()/1E6);  
        // double Lon1r = (Math.PI/180)*(gp1.getLongitudeE6()/1E6);  
        // double Lon2r = (Math.PI/180)*(gp2.getLongitudeE6()/1E6);  
  
        // 地球半径  
        double R = 6371;  
  
        // 两点间距离 km，如果想要米的话，结果*1000就可以了  
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;  
  
        return d;  
    }

	@Override
	public void hidden() {
		int total = sc_dtcldzdanDao.queryTotal();
		for(int j = 0; j < total; j++) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String end = sdf.format(date);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			String start = sdf.format(c.getTime());
			List<ScEtcptjdEntity> list = sc_etcptjdDao.hidden(j, start, end);
			if(list.size() != 0) {
				String plate = list.get(0).getCarNumber();
				sc_etcptjdDao.setHidden(plate);
				ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
				alarminfoVo.setCarnum(plate);
				alarminfoVo.setTriggertime(new Date());
				alarminfoVo.setReadstate(0);
				alarminfoVo.setPushstate(1);
				alarminfoVo.setReceiver("admin1");
				alarminfoVo.setMessage(plate + "是隐匿车辆");
				sc_alarminfoDao.insert(alarminfoVo);
			} else { 
				continue;
			}
		}
	}

	@Override
	public void onlyEnter() {
		int total = sc_dtcldzdanDao.queryTotal();
		for(int j = 0; j < total;j++) {
			//String cph = sc_etcptjdDao.getPlate(j);
			List<ScEtcptjdEntity> list = sc_etcptjdDao.OnlyEnter(j);
			//System.out.println(list.size());
			if(list.size() == 0) {
				continue;
			} else {
				for (ScEtcptjdEntity scEtcptjdEntity : list) {
					String plate = list.get(0).getCarNumber();
					Date exitTime = scEtcptjdEntity.getExitTime();
					if(exitTime == null) {
						sc_etcptjdDao.setOnlyEnter(plate);
						ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
						alarminfoVo.setCarnum(plate);
						alarminfoVo.setTriggertime(new Date());
						alarminfoVo.setReadstate(0);
						alarminfoVo.setPushstate(1);
						alarminfoVo.setReceiver("admin1");
						alarminfoVo.setMessage(plate + "是有进无出车辆");
						sc_alarminfoDao.insert(alarminfoVo);
						break;
					} else {
						sc_etcptjdDao.updateOnlyEnter();
					}
				}
			}
		}
	}

	@Override
	public int num() {
		return sc_etcptjdDao.num();
	}

}
