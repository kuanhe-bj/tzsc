package io.renren.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_alarminfoDao;
import io.renren.dao.Sc_scrcDao;
import io.renren.service.Sc_scrcService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vo.DtcldzdansVo;

/**
 * 首次入城
 * @author moulin
 *
 */
@Service("sc_scrcService")
public class Sc_scrcServiceImpl implements Sc_scrcService {
	
	@Autowired
	private Sc_scrcDao sc_scrcDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_scrcDao.queryList(map);
			 return list; 
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_scrcDao.queryTotal(map);
	}

	@Override
	public List<DtcldzdansVo> list() {
		return sc_scrcDao.list();
	}

	@Override
	public List<ScEtcptjdEntity> list_etcp() {
		return sc_scrcDao.list_etcp();
	}

	@Override
	public List<DtcldzdansVo> list_dan() {
		return sc_scrcDao.list_dan();
	}

	@Override
	public void scrc() {
		int co = 1;
		int page = 0;
		while (co <= 1) {
			List<ScDtcldzdanEntity> list = sc_scrcDao.getScrc(page * 1000);
			if(list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					String plate = list.get(i).getPlate();
					List<ScEtcptjdEntity> listTime = sc_scrcDao.getTime(plate);
					if(listTime.size() != 0) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date date = listTime.get(0).getEnterTime();
						String time = sdf.format(date);
						List<ScEtcptjdEntity> listScrc = sc_scrcDao.scrc(plate, time);
						if(listScrc.size() == 0) {
							sc_scrcDao.updateScrc(plate);
							ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
							alarminfoVo.setCarnum(plate);
							alarminfoVo.setTriggertime(new Date());
							alarminfoVo.setReadstate(0);
							alarminfoVo.setPushstate(1);
							alarminfoVo.setReceiver("admin1");
							alarminfoVo.setMessage(plate + "是首次入城车辆");
							sc_alarminfoDao.insert(alarminfoVo);
						} else {
							continue;
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
