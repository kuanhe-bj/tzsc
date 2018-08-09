package io.renren.service.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_csycDao;
import io.renren.dao.Sc_dqcljfmxDao;
import io.renren.dao.Sc_dtcldzdanDao;
import io.renren.dao.Sc_etcptjdDao;
import io.renren.dao.Sc_missionDao;
import io.renren.dao.Sc_ycgjDao;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.service.Sc_missionService;
import io.renren.vas.entity.ScBdqclEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vo.GwrysjVo;
import io.renren.vo.MissionVo;
import io.renren.vo.ResultVo;
import io.renren.vo.ViolationVo;

/**
 * 实时任务
 * @author moulin
 *
 */
@Service("sc_missionService")
public class Sc_missionServiceImpl implements Sc_missionService{
	
	@Autowired
	private Sc_missionDao sc_missionDao;
	
	@Autowired
	private Sc_etcptjdDao sc_etcptjdDao;
	
	@Autowired
	private Sc_dtcldzdanDao sc_dtcldzdanDao;
	
	@Autowired
	private Sc_ycgjDao sc_ycgjDao;
	
	@Autowired
	private Sc_csycDao sc_csycDao;
	
	@Autowired
	private Sc_dqcljfmxDao sc_dqcljfmxDao;
	
	@Override
	public List<MissionVo> queryList(Map<String, Object> map) {
		SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
		String username = user.getUsername();
		map.put("owner", username);
		List<MissionVo> list = this.sc_missionDao.queryList(map);	
			 return list;	
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
		String username = user.getUsername();
		map.put("owner", username);
		return sc_missionDao.queryTotal(map);
	}
	
	@Override
	public int total(Map<String, Object> map) {
		return sc_missionDao.total(map);
	}
	
	@Override
	public void insert(MissionVo missionVo) {
		sc_missionDao.insert(missionVo);
	}
	
	//添加实时任务
	@Override
	public List<MissionVo> list(Map<String, Object> map) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mission = map.get("mission").toString();
		String start = map.get("start").toString();
		String end = map.get("end").toString();
		String btcxc = map.get("btcxc").toString();
		String btcxd = map.get("btcxd").toString();
		String wscxc = map.get("wscxc").toString();
		String wscxd = map.get("wscxd").toString();
		String fxc = map.get("fxc").toString();
		String fxd = map.get("fxd").toString();
		String btc = map.get("btc").toString();
		String btd = map.get("btd").toString();
		String ycc = map.get("ycc").toString();
		String ycd = map.get("ycd").toString();
		if(mission.equals("隐匿车辆分析")) {
			MissionVo missionVo = new MissionVo();
			SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
			String username = user.getUsername();
			missionVo.setCreator(username);
			missionVo.setOwner(username);
			missionVo.setTasktype("隐匿车辆分析");
			missionVo.setStatus(0);
			missionVo.setCreatetime(sdf.format(new Date()));
			missionVo.setParameters("start=" + start + "&end=" + end);
			missionVo.setContent("隐匿车辆分析,参数：开始时间=" + start + ",结束时间=" + end);
			int rid = sc_missionDao.getId() + 1;
			missionVo.setRid(rid);
			sc_missionDao.insert(missionVo);
			map.put("owner", username);
			return sc_missionDao.queryList(map);
		} else if(mission.equals("异常轨迹分析")) {
			MissionVo missionVo = new MissionVo();
			SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
			String username = user.getUsername();
			missionVo.setCreator(username);
			missionVo.setOwner(username);
			missionVo.setTasktype("异常轨迹分析");
			missionVo.setStatus(0);
			missionVo.setCreatetime(sdf.format(new Date()));
			missionVo.setParameters("start=" + start + "&end=" + end);
			missionVo.setContent("异常轨迹分析,参数：开始时间=" + start + ",结束时间=" + end);
			int rid = sc_missionDao.getId() + 1;
			missionVo.setRid(rid);
			sc_missionDao.insert(missionVo);
			map.put("owner", username);
			return sc_missionDao.queryList(map);
		} else if(mission.equals("限行分析")) {
			MissionVo missionVo = new MissionVo();
			SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
			String username = user.getUsername();
			missionVo.setCreator(username);
			missionVo.setOwner(username);
			missionVo.setTasktype("限行分析");
			missionVo.setStatus(0);
			missionVo.setCreatetime(sdf.format(new Date()));
			missionVo.setParameters("start=" + start + "&end=" + end);
			missionVo.setContent("限行分析,参数：开始时间=" + start + ",结束时间=" + end);
			int rid = sc_missionDao.getId() + 1;
			missionVo.setRid(rid);
			sc_missionDao.insert(missionVo);
			map.put("owner", username);
			return sc_missionDao.queryList(map);
		} else if(mission.equals("次数异常分析")) {
			int count = Integer.parseInt(map.get("num").toString());
			MissionVo missionVo = new MissionVo();
			SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
			String username = user.getUsername();
			missionVo.setCreator(username);
			missionVo.setOwner(username);
			missionVo.setTasktype("次数异常分析");
			missionVo.setStatus(0);
			missionVo.setCreatetime(sdf.format(new Date()));
			missionVo.setContent("次数异常分析,参数：倍数为" + count);
			missionVo.setParameters("count=" + count);
			int rid = sc_missionDao.getId() + 1;
			missionVo.setRid(rid);
			sc_missionDao.insert(missionVo);
			map.put("owner", username);
			return sc_missionDao.queryList(map);
		} else if(mission.equals("盗抢车辆分析")) {
			int count = Integer.parseInt(map.get("num").toString());
			MissionVo missionVo = new MissionVo();
			SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
			String username = user.getUsername();
			missionVo.setCreator(username);
			missionVo.setOwner(username);
			missionVo.setTasktype("盗抢车辆分析");
			missionVo.setStatus(0);
			missionVo.setCreatetime(sdf.format(new Date()));
			missionVo.setParameters("count=" + count);
			missionVo.setContent("盗抢车辆分析,参数：倍数为" + count);
			int rid = sc_missionDao.getId() + 1;
			missionVo.setRid(rid);
			sc_missionDao.insert(missionVo);
			map.put("owner", username);
			return sc_missionDao.queryList(map);
		} else if(mission.equals("套牌分析")) {
			MissionVo missionVo = new MissionVo();
			SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
			String username = user.getUsername();
			missionVo.setCreator(username);
			missionVo.setOwner(username);
			missionVo.setTasktype("套牌分析");
			missionVo.setStatus(0);
			missionVo.setCreatetime(sdf.format(new Date()));
			missionVo.setParameters("start=" + start + "&end=" + end);
			missionVo.setContent("套牌分析,参数：开始时间=" + start + ",结束时间=" + end);
			int rid = sc_missionDao.getId() + 1;
			missionVo.setRid(rid);
			sc_missionDao.insert(missionVo);
			map.put("owner", username);
			return sc_missionDao.queryList(map);
		} else if(mission.equals("昼伏夜出分析")) {
			MissionVo missionVo = new MissionVo();
			SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
			String username = user.getUsername();
			missionVo.setCreator(username);
			missionVo.setOwner(username);
			missionVo.setTasktype("昼伏夜出分析");
			missionVo.setStatus(0);
			missionVo.setCreatetime(sdf.format(new Date()));
			missionVo.setParameters("btcxc=" + btcxc + "&btcxd=" + btcxd + "&wscxc=" + wscxc + "&wscxd=" + wscxd 
					+ "&fxc=" + fxc + "&fxd=" + fxd + "&btc=" + btc + "&btd=" + btd + "&ycc=" + ycc + "&ycd=" + ycd);
			missionVo.setContent("昼伏夜出分析,参数：分析日期从" + fxc + "到" + fxd + 
					",白天时间从" + btc + "点到" + btd + "点,出行次数从" + btcxc + "次到" + btcxd + "次,晚上时间从" 
					+ ycc + "点到" + ycd + "点,出行次数从" + wscxc + "次到" + wscxd);
			int rid = sc_missionDao.getId() + 1;
			missionVo.setRid(rid);
			sc_missionDao.insert(missionVo);
			map.put("owner", username);
			return sc_missionDao.queryList(map);
		}
		return null;
	}
	
	//昼伏夜出实时分析
	@Override
	public void zfyc(int id) {
		long startTime = System.currentTimeMillis();
		List<MissionVo> listM = sc_missionDao.find(id);
		MissionVo missionVo = listM.get(0);
		Map<String, Object> map = new HashMap<String, Object>();
		List<ScEtcptjdEntity> list = new ArrayList<>();
		String btcc = missionVo.getParameters().split("&")[0];
		Integer btcxc = Integer.parseInt(btcc.split("=")[1]);
		String btcd = missionVo.getParameters().split("&")[1];
		Integer btcxd = Integer.parseInt(btcd.split("=")[1]);
		String wscc = missionVo.getParameters().split("&")[2];
		Integer wscxc = Integer.parseInt(wscc.split("=")[1]);
		String wscd = missionVo.getParameters().split("&")[3];
		Integer wscxd = Integer.parseInt(wscd.split("=")[1]);
		String fxcT = missionVo.getParameters().split("&")[4];
		String fxc = fxcT.split("=")[1];
		String fxdT = missionVo.getParameters().split("&")[5];
		String fxd = fxdT.split("=")[1];
		String btcT = missionVo.getParameters().split("&")[6].split("=")[1];
		Integer btch = Integer.parseInt(btcT.split(":")[0]);
		Integer btcm = Integer.parseInt(btcT.split(":")[1]);
		Integer btcs = Integer.parseInt(btcT.split(":")[2]);
		String btdT = missionVo.getParameters().split("&")[7].split("=")[1];
		Integer btdh = Integer.parseInt(btdT.split(":")[0]);
		Integer btdm = Integer.parseInt(btdT.split(":")[1]);
		Integer btds = Integer.parseInt(btdT.split(":")[2]);
		String yccT = missionVo.getParameters().split("&")[8].split("=")[1];
		Integer ycch = Integer.parseInt(yccT.split(":")[0]);
		Integer yccm = Integer.parseInt(yccT.split(":")[1]);
		Integer yccs = Integer.parseInt(yccT.split(":")[2]);
		String ycdT = missionVo.getParameters().split("&")[9].split("=")[1];
		Integer ycdh = Integer.parseInt(ycdT.split(":")[0]);
		Integer ycdm = Integer.parseInt(ycdT.split(":")[1]);
		Integer ycds = Integer.parseInt(ycdT.split(":")[2]);
		map.put("btcxc", btcxc);
		map.put("btcxd", btcxd);
		map.put("wscxc", wscxc);
		map.put("wscxd", wscxd);
		map.put("fxc", fxc);
		map.put("fxd", fxd);
		map.put("btch", btch);
		map.put("btcm", btcm);
		map.put("btcs", btcs);
		map.put("btdh", btdh);
		map.put("btdm", btdm);
		map.put("btds", btds);
		map.put("ycch", ycch);
		map.put("yccm", yccm);
		map.put("yccs", yccs);
		map.put("ycdh", ycdh);
		map.put("ycdm", ycdm);
		map.put("ycds", ycds);
		List<ScEtcptjdEntity> list1 = sc_missionDao.zc(map);
		for (ScEtcptjdEntity scEtcptjdEntity : list1) {
			list.add(scEtcptjdEntity);
		}
		List<ScEtcptjdEntity> list2 = sc_missionDao.yc(map);
		for (ScEtcptjdEntity scEtcptjdEntity : list2) {
			list.add(scEtcptjdEntity);
		}
		String plates = "'1'";
		for (ScEtcptjdEntity scEtcptjdEntity : list) {
			plates += ",'" + scEtcptjdEntity.getCarNumber() + "'";
		}
		List<ScEtcptjdEntity> listzf = sc_missionDao.zfyc(plates);
		for (int j = 0; j < listzf.size(); j++) {
			String plate = listzf.get(j).getCarNumber();
			int count = Integer.parseInt(listzf.get(j).getCount());
			double a = Math.toDegrees(Math.atan(count/10)*200/Math.PI);
			double f = Double.parseDouble(String.format("%.2f", a));
			if(f > 90) {
				sc_missionDao.updateZfyc(f, plate);
				long time = System.currentTimeMillis() - startTime;
				ResultVo resultVo = new ResultVo();
				resultVo.setTaskid(missionVo.getRid());
				resultVo.setPlateno(plate);
				resultVo.setCreatetime((int)time);
				resultVo.setResult("昼伏夜出分析");
				resultVo.setRvalue(100);
				sc_missionDao.save(resultVo);
				sc_missionDao.update(id);
			}
		}
	}
	
	//隐匿车辆实时分析
	@Override
	public void hidden(int id) {
		List<MissionVo> listM = sc_missionDao.find(id);
		MissionVo missionVo = listM.get(0);
		String startT = missionVo.getParameters().split("&")[0];
		String start = startT.split("=")[1];
		String endT = missionVo.getParameters().split("&")[1];
		String end = endT.split("=")[1];
		int co = 1;
		int page = 0;
		while (co <= 1) {
			long startTime = System.currentTimeMillis();
			List<ScEtcptjdEntity> list = sc_missionDao.hidden(page * 1000, start, end);
			if(list.size() != 0) {
				String plates = "'1'";
				for (int i = 0; i < list.size(); i++) {
					String plate = list.get(i).getCarNumber();
					plates += ",'" + list.get(i).getCarNumber() + "'";
					ResultVo resultVo = new ResultVo();
					resultVo.setTaskid(missionVo.getRid());
					resultVo.setPlateno(plate);
					resultVo.setResult("隐匿车辆");
					resultVo.setRvalue(100);
					long time = System.currentTimeMillis() - startTime;
					resultVo.setCreatetime((int)time);
					sc_missionDao.save(resultVo);
				}
				sc_etcptjdDao.setHidden(plates);
			} else {
				continue;
			}
		}
		sc_missionDao.update(id);
	}
	
	//异常轨迹实时分析
	@Override
	public void ycgj(int id) {
		List<MissionVo> listM = sc_missionDao.find(id);
		MissionVo missionVo = listM.get(0);
		String startT = missionVo.getParameters().split("&")[0];
		String start = startT.split("=")[1];
		String endT = missionVo.getParameters().split("&")[1];
		String end = endT.split("=")[1];
		int co = 1;
		int page = 0;
		while (co <= 1) {
			long startTime = System.currentTimeMillis();
			List<ScDtcldzdanEntity> scDtcldzdanEntity = sc_dtcldzdanDao.getScDtcldzdanEntity(page * 1000);
			if(scDtcldzdanEntity.size() == 0) {
				break;
			}
			String plates = "'1'";
			for (int i = 0; i < scDtcldzdanEntity.size(); i++) {
				plates += ",'" + scDtcldzdanEntity.get(i).getPlate() + "'";
			}
			List<ScEtcptjdEntity> list = sc_missionDao.showList(start, end, plates);
			for (int i = 0; i < list.size(); i++) {
				String plate = list.get(i).getCarNumber();
				int count = Integer.parseInt(list.get(i).getCount());
				double a = Math.toDegrees(Math.atan(count/90)*2/Math.PI);
				double f = Double.parseDouble(String.format("%.2f", a));
				if(f >= 90) {
					try {
						sc_ycgjDao.ycgj(f, plate);
					} catch (Exception e) {
						e.printStackTrace();
					}
					long time = System.currentTimeMillis() - startTime;
					ResultVo resultVo = new ResultVo();
					resultVo.setTaskid(missionVo.getRid());
					resultVo.setPlateno(plate);
					resultVo.setCreatetime((int)time);
					resultVo.setResult("异常轨迹车辆");
					resultVo.setRvalue(f);
					sc_missionDao.save(resultVo);
				}
			}
			if (scDtcldzdanEntity.size() < 1000) {
				co = 2;
			}
			page++;
		}
		sc_missionDao.update(id);
	}
	
	//套牌分析实时分析
	@Override
	public void taopai(int id) {
		List<MissionVo> listM = sc_missionDao.find(id);
		MissionVo missionVo = listM.get(0);
		String startT = missionVo.getParameters().split("&")[0];
		String start = startT.split("=")[1];
		String endT = missionVo.getParameters().split("&")[1];
		String end = endT.split("=")[1];
		int total = sc_dtcldzdanDao.queryTotal();
		for(int j = 0; j < total; j++) {
			long startTime = System.currentTimeMillis();
			List<ScEtcptjdEntity> list = sc_missionDao.check(j, start, end);
			if(list.size() > 0) {
				String plate = list.get(0).getCarNumber();
				for (int i = 0; i < list.size(); i++) {
					double j1 = Double.parseDouble(list.get(0).getJingdu());
					double w1 = Double.parseDouble(list.get(0).getWeidu());
					double j2 = Double.parseDouble(list.get(i).getJingdu());
					double w2 = Double.parseDouble(list.get(i).getWeidu());
					double dis = getDistance(j1, w1, j2, w2);
					long time1 = list.get(0).getEnterTime().getTime();
					long time2 = list.get(i).getEnterTime().getTime();
					double s = dis * 1000 * 60 * 60/(time2 - time1);
					System.out.println("s" + s);
					if(s > 160) {
						sc_etcptjdDao.update(plate);
						long time = System.currentTimeMillis() - startTime;
						ResultVo resultVo = new ResultVo();
						resultVo.setTaskid(missionVo.getRid());
						resultVo.setPlateno(plate);
						resultVo.setCreatetime((int)time);
						resultVo.setResult("套牌车辆");
						resultVo.setRvalue(100);
						sc_missionDao.save(resultVo);
						break;
					}
				}
			} else {
				continue;
			}
		}
		sc_missionDao.update(id);
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
	
	//限行分析实时分析
	@Override
	public void xxfx(int id) {
		List<MissionVo> listM = sc_missionDao.find(id);
		MissionVo missionVo = listM.get(0);
		String startT = missionVo.getParameters().split("&")[0];
		String start = startT.split("=")[1];
		String endT = missionVo.getParameters().split("&")[1];
		String end = endT.split("=")[1];
		int co = 1;
		int page = 0;
		while (co <= 1) {
			long startTime = System.currentTimeMillis();
			List<ScDtcldzdanEntity> dt = sc_dtcldzdanDao.getScDtcldzdanEntity(page * 1000);
			if(dt.size() == 0) {
				break;
			}
			String plates = "'1'";
			for (int i = 0; i < dt.size(); i++) {
				plates += ",'" + dt.get(i).getPlate() + "'";
			}
			List<ViolationVo> list = sc_missionDao.queryXC(plates, start, end);
			for (int j = 0; j < list.size(); j++) {
				String plate = list.get(j).getPlateno();
				double a = Math.toDegrees(Math.atan(list.get(j).getCount()/10)*200/Math.PI);
				double count = Double.parseDouble(String.format("%.2f", a));
				if(count >= 90) {
					try {
						sc_dtcldzdanDao.xxUpdate2(count, plate);
					} catch (Exception e) {
						e.printStackTrace();
					}
					ResultVo resultVo = new ResultVo();
					resultVo.setTaskid(missionVo.getRid());
					resultVo.setPlateno(plate);
					resultVo.setResult("限行车辆");
					resultVo.setRvalue(100);
					long time = System.currentTimeMillis() - startTime;
					resultVo.setCreatetime((int)time);
					sc_missionDao.save(resultVo);
				}
			}	
			if (dt.size() < 1000) {
				co = 2;
			}
			page++;
		}
		sc_missionDao.update(id);
	}
	
	//次数异常实时分析
	@Override
	public void csfx(int id) {
		List<MissionVo> listM = sc_missionDao.find(id);
		MissionVo missionVo = listM.get(0);
		int count = Integer.parseInt(missionVo.getParameters().split("=")[1]);
		int co = 1;
		int page = 0;
		while (co <= 1) {
			long startTime = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String end = sdf.format(date);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			String start = sdf.format(c.getTime());
			List<ScEtcptjdEntity> list = sc_csycDao.getCount(page * 1000, start, end);
			int times = 0;
			if(list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					times = Integer.parseInt(list.get(i).getCount());
					double a = (double)times/90;
					String plate = list.get(i).getCarNumber();
					List<ScEtcptjdEntity> listT = sc_csycDao.times(plate, start, end);
					int num = 0;
					if(listT.size() == 0) {
						num = 0;
					}
					for(int j = 0; j < listT.size(); j++) {
						num = Integer.parseInt(listT.get(j).getCount());
						if(num > count * a) {
							try {
								sc_csycDao.updateTimes(plate);
							} catch (Exception e) {
								e.printStackTrace();
							}
							long time = System.currentTimeMillis() - startTime;
							ResultVo resultVo = new ResultVo();
							resultVo.setTaskid(missionVo.getRid());
							resultVo.setPlateno(plate);
							resultVo.setCreatetime((int)time);
							resultVo.setResult("次数异常车辆");
							resultVo.setRvalue(100);
							sc_missionDao.save(resultVo);
							break;
						}
					}
				}
			} else {
				break;
			}
			page++;


		}
		sc_missionDao.update(id);
	}
	
	//盗抢车辆实时分析
	@Override
	public void dqcl(int id) {
		List<MissionVo> listM = sc_missionDao.find(id);
		MissionVo missionVo = listM.get(0);
		//int count = Integer.parseInt(missionVo.getParameters().split("=")[1]);
		int co = 1;
		int page = 0;
		while (co <= 1) {
			long startTime = System.currentTimeMillis();
			List<GwrysjVo> list = sc_dqcljfmxDao.queryList(page * 1000);
			 if(list.size() != 0) {
				 for (int i = 0; i < list.size(); i++) {
					 String plate = list.get(i).getCP();
					 List<ScBdqclEntity> listDQ = sc_dqcljfmxDao.getPlate(plate);
					 if(listDQ.size() != 0) {
						 try {
							 sc_dqcljfmxDao.update(plate);
						 } catch (Exception e) {
							 e.printStackTrace();
						 }
						 long time = System.currentTimeMillis() - startTime;
						 ResultVo resultVo = new ResultVo();
						 resultVo.setTaskid(missionVo.getRid());
						 resultVo.setPlateno(plate);
						 resultVo.setCreatetime((int)time);
						 resultVo.setResult("盗抢车辆");
						 resultVo.setRvalue(100);
						 sc_missionDao.save(resultVo);
					 } else {
						 continue;
					 }
				 }
				if (list.size() < 1000) {
					co = 2;
				}
			 } else {
				 break;
			 }
			 page++;
		}
		sc_missionDao.update(id);
	}

	@Override
	public void deleteBatch(String[] ids) {
		
		int[] arr = new int[ids.length];
			for(int i=0;i<ids.length;i++) {
				String id1 = ids[i];
				int id2 = Integer.parseInt(id1);
				arr[i]=id2;
				
			}
		sc_missionDao.deleteBatch(arr);
		
	}

	@Override
	public List<MissionVo> find(int id) {
		return sc_missionDao.find(id);
	}

	@Override
	public void updating(int id) {
		sc_missionDao.updating(id);
	}
	
}
