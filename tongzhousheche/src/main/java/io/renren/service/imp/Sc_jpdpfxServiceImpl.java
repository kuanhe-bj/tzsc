package io.renren.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_alarminfoDao;
import io.renren.dao.Sc_dtcldzdanDao;
import io.renren.dao.Sc_jpdpfxDao;
import io.renren.service.Sc_jpdpfxService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vo.DtcldzdansVo;

/**
 * 假牌盗牌
 * @author moulin
 *
 */
@Service("sc_jpdpfxService")
public class Sc_jpdpfxServiceImpl implements Sc_jpdpfxService{
	
	@Autowired
	private Sc_jpdpfxDao sc_jpdpfxDao;
	
	@Autowired
	private Sc_dtcldzdanDao sc_dtcldzdanDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list1 = sc_jpdpfxDao.queryList(map);
		return list1;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_jpdpfxDao.queryTotal(map);
	}

	@Override
	public int isfake() {
		int rows = sc_jpdpfxDao.check();
		return rows;
	}

	@Override
	public void setJpdp() {
		int count = sc_dtcldzdanDao.queryTotal();
		for (int i = 0; i < count; i++) {
			List<ScDtcldzdanEntity> scDtcldzdanEntity = sc_dtcldzdanDao.getScDtcldzdanEntity(i);
			if(scDtcldzdanEntity.size() == 0) {
				continue;
			}
			String plate = scDtcldzdanEntity.get(0).getPlate();
			sc_jpdpfxDao.setIsFake(plate);
			int isFake = sc_jpdpfxDao.getIsFake(plate).get(0).getIsfake();
			if(isFake > 90) {
				ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
				alarminfoVo.setCarnum(plate);
				alarminfoVo.setTriggertime(new Date());
				alarminfoVo.setReadstate(0);
				alarminfoVo.setPushstate(1);
				alarminfoVo.setReceiver("admin1");
				alarminfoVo.setMessage(plate + "是假牌盗牌车辆");
				sc_alarminfoDao.insert(alarminfoVo);
			}
		}
	}

}
