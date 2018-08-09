package io.renren.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_ycclDao;
import io.renren.service.Sc_ycclService;
import io.renren.vas.entity.ScEtcptjdEntity;

/**
 * 预测车辆
 * @author moulin
 *
 */
@Service("sc_ycclService")
public class Sc_ycclServiceImpl implements Sc_ycclService {
	
	@Autowired
	private Sc_ycclDao sc_ycclDao;

	@Override
	public List<ScEtcptjdEntity> destination(String cph,String atime,String etime,int num) {
		List<ScEtcptjdEntity> list = sc_ycclDao.getJingduAndWeidu(cph);
		String jingdu = "";
		String weidu = "";
		if(list.size() != 0) {
			jingdu = list.get(0).getJingdu();
			weidu = list.get(0).getWeidu();
		}
		return sc_ycclDao.destination(cph, atime, etime, weidu, jingdu, num);
	}

	@Override
	public ScEtcptjdEntity listObject(String cph) {
		return sc_ycclDao.listObject(cph);
	}

	@Override
	public List<ScEtcptjdEntity> getJingduAndWeidu(String cph) {
		return sc_ycclDao.getJingduAndWeidu(cph);
	}
 
}
