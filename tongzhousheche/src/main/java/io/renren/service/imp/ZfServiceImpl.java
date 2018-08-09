package io.renren.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_alarminfoDao;
import io.renren.dao.Sc_clanjglfxDao;
import io.renren.dao.ZfDao;
import io.renren.service.ZfService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vas.entity.ScGwrysjEntity;
import io.renren.vas.entity.ScScajEntity;
import io.renren.vas.entity.ZfssEntity;

/**
 * 昼伏夜出
 * @author moulin
 *
 */
@Service("zfService")
public class ZfServiceImpl implements ZfService {
	
	@Autowired
	private ZfDao zfdDao;
	
	@Autowired
	private Sc_clanjglfxDao sc_clanjglfxDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;

	@Override
	public List<ScDtcldzdanEntity> pl(Map map) {
		return zfdDao.pl(map);
	}

	@Override
	public int total(Map map) {
		return zfdDao.total();
	}

	@Override
	public List<ScDtcldzdanEntity> al(Map map) {
		
		return zfdDao.al(map);
	}

	@Override
	public List<ScDtcldzdanEntity> baojing() {
		return zfdDao.baojing();
	}

	@Override
	public int info(String receiver, String message) {
		return zfdDao.info(receiver, message);
	}

	@Override
	public int update() {
		return zfdDao.update();
	}

	@Override
	public List<ScDtcldzdanEntity> etcp_list() {
		return zfdDao.etcp_list();
	}

	@Override
	public List<ScEtcptjdEntity> etcp() {
		return zfdDao.etcp();
	}

	@Override
	public int cr_n(float n,String plate) {
		return zfdDao.cr_n(n,plate);
	}

	@Override
	public List<ScAlarminfoEntity> bd(String message) {
		return zfdDao.bd(message);
	}

	@Override
	public List<ScEtcptjdEntity> ss_c(String fxc, String fxd, String btc, String btd, int btcxc, int btcxd) {
		return zfdDao.ss_c(fxc, fxd, btc, btd, btcxc, btcxd);
	}

	@Override
	public List<ScEtcptjdEntity> etcp_all(String time) {
		return zfdDao.etcp_all(time);
	}

	@Override
	public int etcp_count(String time) {
		return zfdDao.etcp_count(time);
	}

	@Override
	public double etcp_numb(String time) {
		return zfdDao.etcp_numb(time);
	}

	@Override
	public List<ScEtcptjdEntity> ss_all(Map map) {
		return zfdDao.ss_all(map);
	}

	@Override
	public int ss_total() {
		return zfdDao.ss_total();
	}

	@Override
	public int insert(String plate, String num, String count, String exit_time, String uu_id) {
		return zfdDao.insert(plate, num, count, exit_time, uu_id);
	}

	@Override
	public List<ZfssEntity> select(Map map) {
		return zfdDao.select(map);
	}

	@Override
	public void delete() {
		zfdDao.delete();
	}

	@Override
	public List<String> plate() {
		return zfdDao.plate();
	}

	@Override
	public List<ScEtcptjdEntity>  all_cardt(String plate,double jingdu,
			double weidu,int count) {
		return zfdDao.all_cardt(plate,jingdu,weidu,count);
	}

	@Override
	public List<ScEtcptjdEntity> card(Map<String, Object> map) {
		return zfdDao.card(map);
	}

	@Override
	public List<ScEtcptjdEntity> zdrsj(String plate) {
		return zfdDao.zdrsj(plate);
	}

	@Override
	public ScDtcldzdanEntity dan(String plate) {
		return zfdDao.dan(plate);
	}

	@Override
	public List<ScEtcptjdEntity> wd(String plate) {
		return zfdDao.wd( plate);
	}

	@Override
	public ScScajEntity sa(String plate) {
		return zfdDao.sa(plate);
	}

	@Override
	public ScGwrysjEntity gw(String plate) {
		return zfdDao.gw(plate);
	}

	@Override
	public List<ScDtcldzdanEntity> cxyc(String plate) {
		return zfdDao.cxyc(plate);
	}

	@Override
	public int queryTotal(Map map) {
		return zfdDao.queryTotal(map);
	}

	@Override
	public List<Integer> getCount() {
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 21; i < 24; i++) {
			int a = zfdDao.getCount(i);
			list.add(a);
		}
		for (int i = 0; i < 6; i++) {
			int b = zfdDao.getCount(i);
			list.add(b);
		}
		return list;
	}

	@Override
	public void zfyc() {
		int co = 1;
		int page = 0;
		while (co <= 1) {
			List<ScDtcldzdanEntity> list = sc_clanjglfxDao.getScDtcldzdanEntity(page*1000);
			if(list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					String plate = list.get(i).getPlate();
					zfdDao.zfycUpdate(plate);
					List<ScDtcldzdanEntity> listZfyc = zfdDao.getNightout(plate);
					if(listZfyc.size() != 0) {
						double zfyc = listZfyc.get(0).getNightout();
						if(zfyc >= 90) {
							ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
							alarminfoVo.setCarnum(plate);
							alarminfoVo.setTriggertime(new Date());
							alarminfoVo.setReadstate(0);
							alarminfoVo.setPushstate(1);
							alarminfoVo.setReceiver("admin1");
							alarminfoVo.setMessage(plate + "是昼伏夜出车辆");
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
