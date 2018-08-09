package io.renren.service.imp;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_alarminfoDao;
import io.renren.dao.Sc_dqcljfmxDao;
import io.renren.service.Sc_dqcljfmxService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScBdqclEntity;
import io.renren.vo.GwrysjVo;

/**
 * 盗抢车辆
 * @author moulin
 *
 */
@Service("sc_dqcljfmxService")
public class Sc_dqcljfmxServiceImpl implements Sc_dqcljfmxService{
	
	@Autowired
	private Sc_dqcljfmxDao sc_dqcljfmxDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;
	
	@Override
	public void dqcljfmx() {
		int total = sc_dqcljfmxDao.queryTotal();
		for(int j = 0; j < total;j++) {
			 List<GwrysjVo> list = sc_dqcljfmxDao.queryList(j);
			 String plate = list.get(0).getCP();
			 List<ScBdqclEntity> listDQ = sc_dqcljfmxDao.getPlate(plate);
			 if(listDQ == null) {
				 continue;
			 } else {
				 sc_dqcljfmxDao.update(plate);
				 ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
				 alarminfoVo.setCarnum(list.get(0).getCP());
				 alarminfoVo.setTriggertime(new Date());
				 alarminfoVo.setReadstate(0);
				 alarminfoVo.setPushstate(1);
				 alarminfoVo.setReceiver("admin1");
				 alarminfoVo.setMessage(list.get(0).getCP() + "是盗抢车辆");
				 sc_alarminfoDao.insert(alarminfoVo);
			 }
		}
	}	
}
