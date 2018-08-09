package io.renren.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_alarminfoDao;
import io.renren.dao.Sc_clanjglfxDao;
import io.renren.service.Sc_clanjglfxService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScScajEntity;
import io.renren.vo.DtcldzdansVo;

/**
 * 涉案车辆
 * @author moulin
 *
 */
@Service("sc_clanjglfxService")
public class Sc_clanjglfxServiceImpl implements Sc_clanjglfxService {
	
	@Autowired
	private Sc_clanjglfxDao sc_clanjglfxDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_clanjglfxDao.queryList(map);
			 return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_clanjglfxDao.queryTotal(map);
	}

	@Override
	public void clajgl() {
//		int count = sc_dtcldzdanDao.queryTotal();
		int co=1;
		int page=0;
		while (co<=1) {
			List<ScDtcldzdanEntity> scDtcldzdanEntity = sc_clanjglfxDao.getScDtcldzdanEntity(page*1000);
			if (scDtcldzdanEntity!=null) {
				for (int i = 0; i < scDtcldzdanEntity.size(); i++) {
					String plate = scDtcldzdanEntity.get(i).getPlate();
					List<ScScajEntity> list = sc_clanjglfxDao.getScScajEntity(plate);
					if(list.size() != 0) {
						sc_clanjglfxDao.clajgl(plate);
						ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
						alarminfoVo.setCarnum(plate);
						alarminfoVo.setTriggertime(new Date());
						alarminfoVo.setReadstate(0);
						alarminfoVo.setPushstate(1);
						alarminfoVo.setReceiver("admin1");
						alarminfoVo.setMessage(plate + "是涉案车辆");
						sc_alarminfoDao.insert(alarminfoVo);
					}
				}
				if (scDtcldzdanEntity.size()<1000) {
					co=2;
				}
			}else {
				co=2;	
			}
			page++;
			
		}
//		for (int i = 0; i < count; i++) {
//			List<ScDtcldzdanEntity> scDtcldzdanEntity = sc_dtcldzdanDao.getScDtcldzdanEntity(i);
//			if(scDtcldzdanEntity.size() == 0) {
//				continue;
//			}
//			String plate = scDtcldzdanEntity.get(0).getPlate();
//			List<ScScajEntity> list = sc_clanjglfxDao.getScScajEntity(plate);
//			if(list.size() != 0) {
//				sc_clanjglfxDao.clajgl(plate);
//				ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
//				alarminfoVo.setCarnum(plate);
//				alarminfoVo.setTriggertime(new Date());
//				alarminfoVo.setReadstate(0);
//				alarminfoVo.setPushstate(1);
//				alarminfoVo.setReceiver("admin1");
//				alarminfoVo.setMessage(plate + "是涉案车辆");
//				sc_alarminfoDao.insert(alarminfoVo);
//			}
//		}
	}

}
