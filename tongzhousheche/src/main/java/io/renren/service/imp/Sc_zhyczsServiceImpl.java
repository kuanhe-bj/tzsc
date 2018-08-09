package io.renren.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_alarminfoDao;
import io.renren.dao.Sc_zhyczsDao;
import io.renren.service.Sc_zhyczsService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vo.DtcldzdansVo;

/**
 * 综合指数
 * @author moulin
 *
 */
@Service("sc_zhyczsService")
public class Sc_zhyczsServiceImpl implements Sc_zhyczsService{
	
	@Autowired
	private Sc_zhyczsDao sc_zhyczsDao;
	
	@Autowired
	private Sc_alarminfoDao sc_alarminfoDao;
	
	@Override
	public void zhyczs() {
		double summary = 0.0;
		int total = sc_zhyczsDao.queryTotal();
		int co = 1;
		int page = 0;
		while (co <= 1) {
			List<DtcldzdansVo> list = sc_zhyczsDao.querylist(page * 1000);
			if(list.size() > 0) {
				Double isFake = list.get(0).getIsFake(); 
				Double isInDoubt = list.get(0).getIsInDoubt(); 
				Double isInvolved = list.get(0).getIsInvolved(); 
				Double isSuspects = list.get(0).getIsSuspects(); 
				Double isFirstIn = list.get(0).getIsFirstIn(); 
				Double times = list.get(0).getTimes(); 
				Double onlyEnter = list.get(0).getOnlyEnter();
				if(isFake == 1) {
					isFake = 100.0; 
				}else {
					isFake = 0.0;
				}
				if(isInDoubt == 1) {
					isInDoubt = 100.0; 
				}else {
					isInDoubt = 0.0;
				}
				if(isInvolved == 1) {
					isInvolved = 100.0; 
				}else {
					isInvolved = 0.0;
				}
				if(isSuspects == 1) {
					isSuspects = 100.0; 
				}else {
					isSuspects = 0.0;
				}
				if(isFirstIn == 1) {
					isFirstIn = 100.0; 
				}else {
					isFirstIn = 0.0;
				}
				if(times == 1) {
					times = 100.0; 
				}else {
					times = 0.0;
				}
				if(onlyEnter == 1) {
					onlyEnter = 100.0; 
				}else {
					onlyEnter = 0.0;
				}
				summary = (list.get(0).getAbnormal() + list.get(0).getViolation() + list.get(0).getNightOut() 
						+ list.get(0).getHighrisk() + list.get(0).getAccident() + list.get(0).getHidden() 
						+ isFake + isInDoubt + isInvolved + isSuspects + isFirstIn + times + onlyEnter 
						+ list.get(0).getLimits() + list.get(0).getOverSpeed() + list.get(0).getContViolation()
						+ list.get(0).getAbTravel() + list.get(0).getWander() + list.get(0).getEfence() 
						+ list.get(0).getMultiPlate() + list.get(0).getSensitive() + list.get(0).getRobbery() 
						+ list.get(0).getObscured() + list.get(0).getFaceCover() + list.get(0).getNoPlate()
						+ list.get(0).getRecords() + list.get(0).getDrug() + list.get(0).getAtLarge() 
						+ list.get(0).getRelative() + list.get(0).getMorning() + list.get(0).getAddress()
						+ list.get(0).getTimeAbnormal() + list.get(0).getLongStay() + list.get(0).getHugeAccident()
						+ list.get(0).getTendency() + list.get(0).getBlack())/(total-7);
				if(summary > 90) {
					ScAlarminfoEntity alarminfoVo = new ScAlarminfoEntity();
					alarminfoVo.setCarnum(list.get(0).getPlate());
					alarminfoVo.setReceiver("admin1");
					alarminfoVo.setMessage(list.get(0).getPlate() + "综合指数异常");
					sc_alarminfoDao.insert(alarminfoVo);
				}
				sc_zhyczsDao.update(summary,list.get(0).getPlate());
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
