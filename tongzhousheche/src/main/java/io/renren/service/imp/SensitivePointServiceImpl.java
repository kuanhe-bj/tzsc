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
import io.renren.dao.SensitivePointDao;
import io.renren.service.SensitivePointService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vas.entity.SensitivepointEntity;

/**
 * 敏感地区
 * @author moulin
 *
 */
@Service("sensitivePointService")
public class SensitivePointServiceImpl implements SensitivePointService {
	
	@Autowired
	private SensitivePointDao sensitivePointDao;
	
	@Autowired
	private Sc_dtcldzdanDao sc_dtcldzdanDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;
	
	@Override
	public SensitivepointEntity queryObject(Integer id){
		return sensitivePointDao.queryObject(id);
	}
	
	@Override
	public List<SensitivepointEntity> queryList(Map<String, Object> map){
		return sensitivePointDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sensitivePointDao.queryTotal(map);
	}
	
	@Override
	public void save(SensitivepointEntity sensitivepoint){
		sensitivePointDao.save(sensitivepoint);
	}
	
	@Override
	public void update(SensitivepointEntity sensitivepoint){
		sensitivePointDao.update(sensitivepoint);
	}
	
	@Override
	public void delete(Integer id){
		sensitivePointDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		sensitivePointDao.deleteBatch(ids);
	}

	@Override
	public List<SensitivepointEntity> list(Map<String, Object> map) {
		return sensitivePointDao.list(map);
	}

	@Override
	public void sensitive() {
		int total = sc_dtcldzdanDao.queryTotal();
		double s = 0;
		double a = 0;
		for(int j = 0; j < total; j++) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String end = sdf.format(date);
				Calendar c = Calendar.getInstance();
				c.add(Calendar.MONTH, -1);
				String start = sdf.format(c.getTime());
				List<ScEtcptjdEntity> list = sensitivePointDao.check(j, start, end);
				String plate;
				if(list.size() > 0) {
					plate = list.get(0).getCarNumber();
					for (ScEtcptjdEntity scEtcptjdEntity : list) {
						double jingdu = Double.parseDouble(scEtcptjdEntity.getJingdu());
						double weidu = Double.parseDouble(scEtcptjdEntity.getWeidu());
						List<SensitivepointEntity> listSensitive = sensitivePointDao.getList();
						for (SensitivepointEntity sensitivepointEntity : listSensitive) {
							String center = sensitivepointEntity.getCenter();
							String[] str = center.trim().split(" ");
							double jingdu1 = Double.parseDouble(str[0]);
							double weidu1 = Double.parseDouble(str[1]);
							Integer g = sensitivepointEntity.getGrade();
							if(sensitivepointEntity.getStype() == 0) {//圆形区域
								double d = getDistance(jingdu, weidu, jingdu1, weidu1);
								if(d <= sensitivepointEntity.getX()) {
									s = s + g;
									break;
								}
							} else if(sensitivepointEntity.getStype() == 1) {//方形区域
								double d1 = getDistance(jingdu1, weidu1, jingdu1, weidu);
								double d2 = getDistance(jingdu1, weidu1, jingdu, weidu1);
								if(d2 <= sensitivepointEntity.getX() && d1 <= sensitivepointEntity.getY()) {
									s = s + g;
									break;
								}
							} else if(sensitivepointEntity.getStype() == 2) {//椭圆区域
								//a和b是椭圆的长半轴和短半轴
								double x = sensitivepointEntity.getX()/2;
								double y = sensitivepointEntity.getY()/2;
								double d = getDis(jingdu1, weidu1, x, y, jingdu, weidu);
								if(d <= 2 * x) {
									s = s + g;
									break;
								}
							}
						}
					}
				} else {
					continue;
				}
				
				a = Math.toDegrees(Math.atan(s/90)*2/Math.PI);
				if(a > 90) {
					ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
					alarminfoVo.setCarnum(plate);
					alarminfoVo.setTriggertime(new Date());
					alarminfoVo.setReadstate(0);
					alarminfoVo.setPushstate(1);
					alarminfoVo.setReceiver("admin1");
					alarminfoVo.setMessage(plate + "是敏感地区出行车辆");
					sc_alarminfoDao.insert(alarminfoVo);
				}
				sensitivePointDao.update(a, plate);
			}
	}
	
	public static double getDistance(double j1, double w1, double j2, double w2) {  
		  
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
	
	//获取焦点的经度
	public static double getJingdu(double j1, double w1, double w2, double d) {
		double j2 = 0;
	    double lon1 = (Math.PI / 180) * j1;  
        double lat1 = (Math.PI / 180) * w1;  
        double lat2 = (Math.PI / 180) * w2;
        double R = 6371; 
        j2 = (180/Math.PI)*(Math.acos((Math.cos(d/R)-Math.sin(lat1)*Math.sin(lat2))/(Math.cos(lat1)*Math.cos(lat2)))+lon1);
		return j2;
	}
	
	//计算动点到两个焦点的距离和
	public static double getDis(double j1,double w1,double a,double b,double pX,double pY){
		//半焦距
		double c = Math.sqrt(a*a - b*b);
		//焦点的经度
		double fx1 = getJingdu(j1, w1, w1, c);
		double fx2 = j1 - (fx1 - j1); 
		double d1 = getDistance(pX, pY, fx1, w1);
		double d2 = getDistance(pX, pY, fx2, w1);
		double d = d1 + d2;
		return d;
	}

}
