package io.renren.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.MainDao;
import io.renren.dao.Sc_alarminfoDao;
import io.renren.service.MainService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vo.ScEtcpEntity;

/**
 * 主页
 * @author moulin
 *
 */
@Service
public class MainServiceImpl implements MainService{
	
	@Autowired
	private MainDao mainDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;
	
	@Override
	public int query() {
		return 0;
	}

	@Override
	public int zfycQuery() {
		return mainDao.zfycQuery();
	}

	@Override
	public int tpxyQuery() {
		return mainDao.tpxyQuery();
	}

	@Override
	public int ymclQuery() {
		return mainDao.ymclQuery();
	}

	@Override
	public int scjcQuery() {
		return mainDao.scjcQuery();
	}

	@Override
	public int tcwQuery() {
		return 0;
	}

	@Override
	public int tcslQuery() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String start = sdf.format(date) + " 00:00:00";
		String end = sdf.format(date) + " 23:59:59";
		return mainDao.tcslQuery(start, end);
	}

	@Override
	public int jrrcQuery() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String start = sdf.format(date) + " 00:00:00";
		String end = sdf.format(date) + " 23:59:59";
		return mainDao.jrrcQuery(start, end);
	}

	@Override
	public int jrccQuery() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String start = sdf.format(date) + " 00:00:00";
		String end = sdf.format(date) + " 23:59:59";
		return mainDao.jrccQuery(start, end);
	}

	@Override
	public int bkzlQuery() {
		return mainDao.bkzlQuery();
	}

	@Override
	public int jrbkQuery() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String start = sdf.format(date) + " 00:00:00";
		String end = sdf.format(date) + " 23:59:59";
		return mainDao.jrbkQuery(start, end);
	}

	@Override
	public int ljbjQuery() {
		return mainDao.ljbjQuery();
	}

	@Override
	public int jrbjQuery() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String start = sdf.format(date) + " 00:00:00";
		String end = sdf.format(date) + " 23:59:59";
		return mainDao.jrbjQuery(start, end);
	}

	@Override
	public int kakouTotal() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String start = sdf.format(date) + " 00:00:00";
		String end = sdf.format(date) + " 23:59:59";
		return mainDao.kakouTotal(start, end);
	}

	@Override
	public int tccTotal() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String start = sdf.format(date) + " 00:00:00";
		String end = sdf.format(date) + " 23:59:59";
		return mainDao.tccTotal(start, end);
	}

	@Override
	public List<ScEtcpEntity> getInfo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String start = sdf.format(date) + " 00:00:00";
		String end = sdf.format(date) + " 23:59:59";
		List<ScEtcpEntity> list=mainDao.getInfo(start, end);
		return list;
	}

	@Override
	public List<ScEtcpEntity> getCarInfo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String start = sdf.format(date) + " 00:00:00";
		String end = sdf.format(date) + " 23:59:59";
		return mainDao.getInformation(start, end);
		
	}

	@Override
	public void alarm(List<ScEtcpEntity> list) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String start = sdf.format(date) + " 00:00:00";
		String end = sdf.format(date) + " 23:59:59";
		if(list.size() != 0) {
			for(int i = 0; i < list.size(); i++) {
				String cph = list.get(i).getCarNumber();
				//System.out.println(cph);
				List<ScAlarminfoEntity> alarm = mainDao.checkCph(cph, start, end);
				//System.out.println(alarm.size());
				if(alarm.size() == 0) {
					ScAlarminfoEntity scAlarminfoEntity = new ScAlarminfoEntity();
					scAlarminfoEntity.setCarnum(cph);
					scAlarminfoEntity.setTriggertime(date);
					scAlarminfoEntity.setReadstate(0);
					scAlarminfoEntity.setPushstate(1);
					scAlarminfoEntity.setReceiver("manager");
					scAlarminfoEntity.setMessage("政府附近徘徊");
					sc_alarminfoDao.insert(scAlarminfoEntity);
				} else {
					continue;
				}
				if(i == 3) {
					break;
				}
			}
		}
	}

}
