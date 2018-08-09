package io.renren.service.imp;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_alarminfoDao;
import io.renren.dao.Sc_dtcldzdanDao;
import io.renren.service.Sc_dtcldzdanService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vas.entity.ScJdcjbxxEntity;
import io.renren.vo.DtcldzdansVo;
import io.renren.vo.ScEtcpEntity;
import io.renren.vo.XianXingVo;

/**
 * 动态电子档案
 * @author moulin
 *
 */
@Service("sc_dtcldzdanService")
public class Sc_dtcldzdanServiceImpl implements Sc_dtcldzdanService {

	@Autowired
	private Sc_dtcldzdanDao sc_dtcldzdanDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;

	@Override
	public List<ScDtcldzdanEntity> queryList(Map<String, Object> map) {
		return sc_dtcldzdanDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_dtcldzdanDao.queryTotal();
	}

	@Override
	public List<ScDtcldzdanEntity> sfzByList(String sfz) {
		return sc_dtcldzdanDao.sfzByList(sfz);
	}

	@Override
	public void sfzUpdate(String sfz) {
		sc_dtcldzdanDao.sfzUpdate(sfz);
	}

	@Override
	public List<ScDtcldzdanEntity> plateByList(String plate) {
		return sc_dtcldzdanDao.plateByList(plate);
	}

	@Override
	public void plateUpdate(String plate) {
		sc_dtcldzdanDao.plateUpdate(plate);
	}

	@Override
	public List<XianXingVo> queryXX() {

		return sc_dtcldzdanDao.queryCp();
	}

	@Override
	public int xxUpdate(String plate, float limits) {
		return sc_dtcldzdanDao.xxUpdate(plate, limits);
	}

	@Override
	public List<XianXingVo> queryWH(Map<String, Object> map) {
		return sc_dtcldzdanDao.queryWH(map);
	}

	//限行分析模型
	@Override
	public void xianxing() {

	}
	//连续违章分析模型
		@Override
		public void lxWeiZhang() {
			sc_dtcldzdanDao.updateContViolation();
			//修改动态车辆电子档案的限行参数
			sc_dtcldzdanDao.weizhangUpdate();
			int total = sc_dtcldzdanDao.queryTotal();
			for (int i = 0; i < total; i++) {
				//每次查询一条记录
				ScDtcldzdanEntity dt = sc_dtcldzdanDao.queryOne(i);
				if(dt.getContviolation() > 90) {
					ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
					alarminfoVo.setCarnum(dt.getPlate());
					alarminfoVo.setTriggertime(new Date());
					alarminfoVo.setReadstate(0);
					alarminfoVo.setPushstate(1);
					alarminfoVo.setReceiver("admin1");
					alarminfoVo.setMessage(dt.getPlate() + "是违章车辆");
					//车辆限行报警
					sc_alarminfoDao.insert(alarminfoVo);
				}
			}
		}
	
	public void xianxing1() {
		Map<String, Object> map = new HashMap<String, Object>();
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time2 = df.format(d);
		String time1 = df.format(new Date(d.getTime() - (long) 90 * 24 * 60 * 60 * 1000));
		map.put("time1", time1);
		map.put("time2", time2);
		// 保留小数点后两位
		DecimalFormat dd = new DecimalFormat("#.00");
		//获取车牌号
		List<XianXingVo> listCP = sc_dtcldzdanDao.queryCp();
		//车辆限行次数
		float count = 0F;
		for (int i = 0; i < listCP.size(); i++) {
			String cph = listCP.get(i).getCph();
			if(cph == null || "".equals(cph)){
				continue;
			}
			String cphWH = cph.substring(cph.length()-1);
			if("0123456789".indexOf(cphWH)==-1){
				cphWH = "0";
			}
			Integer wh = Integer.parseInt(cphWH);
			map.put("cph", wh);
			//获取当前前90天的行车记录
			List<XianXingVo> listXC = sc_dtcldzdanDao.queryXC(map);
			for (int j = 0; j < listXC.size(); j++) {
				Date time = listXC.get(i).getTime1();
				String date = getWeekOfDate(time);
				if("zhou1".equals(date)){
					map.put("zhou1", date);
				}else if("zhou2".equals(date)){
					map.put("zhou2", date);
				}else if("zhou3".equals(date)){
					map.put("zhou3", date);
				}else if("zhou4".equals(date)){
					map.put("zhou4", date);
				}else if("zhou5".equals(date)){
					map.put("zhou5", date);
				}else{
					continue;
				}
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
				String timeXC = df1.format(time);
				map.put("timeXC", timeXC);
				List<XianXingVo> listWH = sc_dtcldzdanDao.queryWH(map);
				if(listWH.size() > 0){
					count=count+1;
				}
			}
			if(count>0){
				String plate = cph ;
				
				float f = (float) (Math.atan(count/60) *2 /Math.PI);
				String s = dd.format(f);
				float limits = Float.parseFloat(s);
				
				
				count = sc_dtcldzdanDao.xxUpdate(plate, limits);
			}
		}
	}
		//判断日期是周几
	 private static String getWeekOfDate(Date dt) {
	      String[] weekDays = {"zhou0", "zhou1", "zhou2", "zhou3", "zhou4", "zhou5", "zhou6"};
	      Calendar cal = Calendar.getInstance();
	      cal.setTime(dt);
	
	      int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	      if (w < 0)
	          w = 0;	
	      return weekDays[w];
	 }

	@Override
	public List<Integer> getCount() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			List<ScEtcptjdEntity> listSE = sc_dtcldzdanDao.getCount(i);
			int num = 0;
			if(listSE.size() == 0) {
				num = 0;
			} else {
				num = Integer.parseInt(listSE.get(0).getCount());
			}
			list.add(num);
		}
		return list;
	}

	@Override
	public void saveDtcldzdan() {
		int co = 1;
		int page = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(new Date());
		int year = Integer.parseInt(time.split("-")[0]);
		int month = Integer.parseInt(time.split("-")[1]);
		int day = Integer.parseInt(time.split("-")[2]);
		while (co <= 1) {
			List<ScEtcpEntity> list = sc_dtcldzdanDao.getCarNumber(day, month, year, page * 1000);
			if(list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					String carNum = list.get(i).getCarNumber();
					String valueColor = list.get(i).getVehicleColor();
					String valueModel = list.get(i).getVehicleModel();
					String valueBrand = list.get(i).getVehicleBrand();
					String color = sc_dtcldzdanDao.getColor(valueColor).get(0).getColor();
					String model = sc_dtcldzdanDao.getModel(valueModel).get(0).getModel();
					String brand = sc_dtcldzdanDao.getBrand(valueBrand).get(0).getContact();
					List<ScJdcjbxxEntity> listJbxx = sc_dtcldzdanDao.getSfzAndName(carNum);
					String owner = "";
					String sfz = "";
					if(listJbxx.size() != 0) {
						owner = listJbxx.get(0).getSyr();
						sfz = listJbxx.get(0).getSfzmhm();
					}
					DtcldzdansVo dtcldzdansVo = new DtcldzdansVo();
					dtcldzdansVo.setPlate(carNum);
					dtcldzdansVo.setColor(color);
					dtcldzdansVo.setModel(model);
					dtcldzdansVo.setBrand(brand);
					dtcldzdansVo.setOwner(owner);
					dtcldzdansVo.setSfz(sfz);
					sc_dtcldzdanDao.saveDtcldzdansVo(dtcldzdansVo);
				}
				if (list.size() < 1000) {
					co = 2;
				}
			} else {
				continue;
			}
			page++;
		}
	}
	 
	 
}
