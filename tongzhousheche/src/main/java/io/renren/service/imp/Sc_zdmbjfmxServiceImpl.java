package io.renren.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_alarminfoDao;
import io.renren.dao.Sc_clanjglfxDao;
import io.renren.dao.Sc_zdmbjfmxDao;
import io.renren.service.Sc_zdmbjfmxService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vo.DtcldzdansVo;

/**
 * 遮挡面部
 * @author moulin
 *
 */
@Service("sc_zdmbjfmxService")
public class Sc_zdmbjfmxServiceImpl implements Sc_zdmbjfmxService {
	
	@Autowired
	private Sc_zdmbjfmxDao sc_zdmbjfmxDao;
	
	@Autowired
	private Sc_clanjglfxDao sc_clanjglfxDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_zdmbjfmxDao.queryList(map);
			 return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_zdmbjfmxDao.queryTotal(map);
	}

	@Override
	public int dan_cs() {
		return sc_zdmbjfmxDao.dan_cs();
	}

	@Override
	public int dan_sz() {
		return sc_zdmbjfmxDao.dan_sz();
	}

	@Override
	public void faceCover() {
		int co = 1;
		int page = 0;
		while (co <= 1) {
			List<ScDtcldzdanEntity> list = sc_clanjglfxDao.getScDtcldzdanEntity(page*1000);
			if(list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					String plate = list.get(i).getPlate();
					sc_zdmbjfmxDao.faceCover(plate);
					List<DtcldzdansVo> listFace = sc_zdmbjfmxDao.getFaceCover(plate);
					if(listFace.size() != 0) {
						double faceCover = listFace.get(0).getFaceCover();
						if(faceCover == 1) {
							ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
							alarminfoVo.setCarnum(plate);
							alarminfoVo.setTriggertime(new Date());
							alarminfoVo.setReadstate(0);
							alarminfoVo.setPushstate(1);
							alarminfoVo.setReceiver("admin1");
							alarminfoVo.setMessage(plate + "是遮挡面部车辆");
							sc_alarminfoDao.insert(alarminfoVo);
						}
					}
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
