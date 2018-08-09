package io.renren.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_alarminfoDao;
import io.renren.dao.Sc_clanjglfxDao;
import io.renren.dao.Sc_wpcljfmxDao;
import io.renren.service.Sc_wpcljfmxService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vo.DtcldzdansVo;

/**
 * 无牌车辆
 * @author moulin
 *
 */
@Service("sc_wpcljfmxService")
public class Sc_wpcljfmxServiceImpl implements Sc_wpcljfmxService {
	
	@Autowired
	private Sc_wpcljfmxDao sc_wpcljfmxDao;
	
	@Autowired
	private Sc_clanjglfxDao sc_clanjglfxDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_wpcljfmxDao.queryList(map);
			 return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_wpcljfmxDao.queryTotal(map);
	}

	@Override
	public int wcp_cs() {
		return sc_wpcljfmxDao.wcp_cs();
	}

	@Override
	public int wcp_sz() {
		return sc_wpcljfmxDao.wcp_sz();
	}

	@Override
	public void wpcl() {
		int co = 1;
		int page = 0;
		while (co <= 1) {
			List<ScDtcldzdanEntity> list = sc_clanjglfxDao.getScDtcldzdanEntity(page*1000);
			if(list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					String plate = list.get(i).getPlate();
					sc_wpcljfmxDao.wpcl(plate);
					List<DtcldzdansVo> listNoplate = sc_wpcljfmxDao.getNoPlate(plate);
					if(listNoplate.size() != 0) {
						double noPlate = listNoplate.get(0).getNoPlate();
						if(noPlate == 1) {
							ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
							alarminfoVo.setCarnum(plate);
							alarminfoVo.setTriggertime(new Date());
							alarminfoVo.setReadstate(0);
							alarminfoVo.setPushstate(1);
							alarminfoVo.setReceiver("admin1");
							alarminfoVo.setMessage(plate + "是无牌车辆");
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
