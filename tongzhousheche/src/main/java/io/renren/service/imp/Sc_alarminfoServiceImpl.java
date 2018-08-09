package io.renren.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_alarminfoDao;
import io.renren.service.Sc_alarminfoService;
import io.renren.service.Sc_blacklistService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScBlacklistEntity;
import io.renren.vo.ScEtcpEntity;

/**
 * 报警信息
 * @author moulin
 *
 */
@Service("sc_alarminfoService")
public class Sc_alarminfoServiceImpl implements Sc_alarminfoService {

	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;
	
	@Autowired
	private Sc_blacklistService sc_blacklistService;

	@Override
	public void insert(ScAlarminfoEntity scAlarminfoEntity) {
		sc_alarminfoDao.insert(scAlarminfoEntity);
	}

	@Override
	public void checkCph(ScEtcpEntity sEEntity) {
		ScBlacklistEntity scBlacklistEntityList = sc_blacklistService.checkCph(sEEntity.getCarNumber());
		if (scBlacklistEntityList != null) {
			List<ScAlarminfoEntity> alarminfoVoList=sc_alarminfoDao.checkCph(sEEntity.getCarNumber());
			if (alarminfoVoList==null) {
				ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
				alarminfoVo.setCarnum(sEEntity.getCarNumber());
				alarminfoVo.setReceiver("admin");
				alarminfoVo.setTriggertime(new Date());
				alarminfoVo.setDatasource("停车场数据");
				alarminfoVo.setDatasourceid(sEEntity.getEid());
				alarminfoVo.setBlackid(scBlacklistEntityList.getId() + "");
				alarminfoVo.setPushstate(1);
				alarminfoVo.setReadstate(0);
				alarminfoVo.setMessage("自动报警");
				sc_alarminfoDao.insert(alarminfoVo);
			}
		}
		
	}

	@Override
	public void update1(String id) {
		sc_alarminfoDao.update1(id);
	}
	
	@Override
	public void updateBatch(String[] ids) {
		sc_alarminfoDao.updateBatch(ids);
	}

	@Override
	public void update2(String cph) {
		sc_alarminfoDao.update2(cph);
	}

	@Override
	public int find(String username) {
		return sc_alarminfoDao.find(username);
	}


}
