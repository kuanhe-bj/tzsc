package io.renren.service.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.renren.dao.Sc_vehiclegsDao;
import io.renren.service.Sc_vehiclegsService;
import io.renren.vo.VehiclegsVo;
import lombok.extern.slf4j.Slf4j;

/**
 * 归属地
 * @author moulin
 *
 */
@Slf4j
@Service
public class Sc_vehiclegsServiceImpl implements Sc_vehiclegsService {
	
	@Autowired
	private Sc_vehiclegsDao vehiclegsdao;
	
	@Override
	public List<VehiclegsVo> queryList(Map<String, Object> params) {
		List<VehiclegsVo> list = this.vehiclegsdao.queryList(params);
		return list;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		map.put("date", date);
		return vehiclegsdao.queryTotal(map);
		
	}
	
	@Override
	public void gsd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String start = sdf.format(date) + " 00:00:00";
		String end = sdf.format(date) + " 23:59:59";
		int num = vehiclegsdao.getTotal(start, end);
		List<VehiclegsVo> listZone = vehiclegsdao.list();
		for (VehiclegsVo vehiclegsVo : listZone) {
			String jiancheng = vehiclegsVo.getJiancheng();
			String province = vehiclegsVo.getProvince();
			int count = vehiclegsdao.gsd(jiancheng, start, end);
			double a = 0;
			if(count == 0) {
				a = 0;
			} else {
				a = (double)count/num;
			}
			double f = Double.parseDouble(String.format("%.2f", a));
			VehiclegsVo gsdEntity = new VehiclegsVo();
			gsdEntity.setJiancheng(jiancheng);
			gsdEntity.setProvince(province);
			gsdEntity.setPercent(f);
			gsdEntity.setCarnum(count);
			gsdEntity.setCreatetime(sdf.format(date));
			vehiclegsdao.saveGsd(gsdEntity);
		}
	}
	
	//高危地区
	@Override
	public List<VehiclegsVo> highriskList() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		return vehiclegsdao.highriskList(date);
	}
	
	//归属地实时分析
	@Override
	public List<VehiclegsVo> dList(Map<String, Object> params) {
		List<VehiclegsVo> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String start = params.get("entertime").toString();
		String end = params.get("exittime").toString();
		long time;
		try {
			time = sdf.parse(end).getTime() - sdf.parse(start).getTime();
			int day = (int) (time / (1000 * 60 * 60 * 24));
			int count = vehiclegsdao.getCount(params);
			int total = vehiclegsdao.totalCount(params);
			double a = 0;
			if(count == 0) {
				a = 0;
			} else {
				a = (double) count/total;
			}
			double f = Double.parseDouble(String.format("%.2f", a));
			VehiclegsVo vehiclegsVo = new VehiclegsVo();
			vehiclegsVo.setProvince(params.get("province").toString());
			vehiclegsVo.setCarnum(count/day);
			vehiclegsVo.setPercent(f);
			list.add(vehiclegsVo);
			return list;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public VehiclegsVo getcarnum() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		return vehiclegsdao.getcarnum(date);
	}
	
	@Override
	public List<VehiclegsVo> selecttime(Map<String, Object> params) {
		List<VehiclegsVo> list = new ArrayList<>();
		List<VehiclegsVo> jcTotal = vehiclegsdao.jcTotal(params);
		log.info("jcTotal：{}", jcTotal);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String start = params.get("entertime").toString();
		String end = params.get("exittime").toString();
		try {
			long time = sdf.parse(end).getTime() - sdf.parse(start).getTime();
			int day = (int) (time / (1000 * 60 * 60 * 24));
			for(int i = 0;i < jcTotal.size(); i++) {
				String province = jcTotal.get(i).getProvince();
				params.put("province", province);
				int count = vehiclegsdao.getCount(params);
				int total = vehiclegsdao.totalCount(params);
				double a = 0;
				if(count == 0) {
					a = 0;
				} else {
					a = (double) count/total;
				}
				double f = Double.parseDouble(String.format("%.2f", a));
				VehiclegsVo vehiclegsVo = new VehiclegsVo();
				vehiclegsVo.setProvince(params.get("province").toString());
				vehiclegsVo.setCarnum(count/day);
				vehiclegsVo.setPercent(f);
				list.add(vehiclegsVo);
			}
			return list;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//获取随机颜色
	@Override
	public String getRandColorCode() {
		  String r,g,b;  
		  Random random = new Random();  
		  r = Integer.toHexString(random.nextInt(256)).toUpperCase();  
		  g = Integer.toHexString(random.nextInt(256)).toUpperCase();  
		  b = Integer.toHexString(random.nextInt(256)).toUpperCase();  
		     
		  r = r.length()==1 ? "0" + r : r ;  
		  g = g.length()==1 ? "0" + g : g ;  
		  b = b.length()==1 ? "0" + b : b ;  
		     
		  return "#"+r+g+b;  
	}

	@Override
	public int queryTotals(Map<String, Object> params) {
		return vehiclegsdao.queryTotals(params);
	}
	
}
