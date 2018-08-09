package io.renren.service.imp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_alarminfoDao;
import io.renren.dao.Sc_csycDao;
import io.renren.dao.Sc_dtcldzdanDao;
import io.renren.service.Sc_csycService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vo.DtcldzdansVo;

/**
 * 次数异常
 * @author moulin
 *
 */
@Service("sc_csycService")
public class Sc_csycServiceImpl implements Sc_csycService{
	
	@Autowired
	private Sc_csycDao sc_csycDao;
	
	@Autowired
	private Sc_dtcldzdanDao sc_dtcldzdanDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;
	
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_csycDao.queryList(map);		
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_csycDao.queryTotal(map);
	}

	@Override
	public void times() {
	
	}

	@Override
	public void setTimes(String plate) {

	}

}
