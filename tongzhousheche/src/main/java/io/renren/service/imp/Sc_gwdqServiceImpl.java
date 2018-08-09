package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_gwdqDao;
import io.renren.dao.Sc_vehiclegsDao;
import io.renren.service.Sc_gwdqService;
import io.renren.vo.DtcldzdansVo;
import io.renren.vo.VehiclegsVo;

/**
 * 高危地区
 * @author moulin
 *
 */
@Service("sc_gwdqService")
public class Sc_gwdqServiceImpl implements Sc_gwdqService {
	
	@Autowired
	private Sc_gwdqDao sc_gwdqDao;
	
	@Autowired
	private Sc_vehiclegsDao vehiclegsDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		List<DtcldzdansVo> list = this.sc_gwdqDao.queryList(map);
			 return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_gwdqDao.queryTotal(map);
	}

	@Override
	public void gwdq() {
		List<VehiclegsVo> listZone = vehiclegsDao.list();
		for (VehiclegsVo vehiclegsVo : listZone) {
			String jiancheng = vehiclegsVo.getJiancheng();
			//System.out.println(jiancheng);
			if(jiancheng.matches("京")) {
				sc_gwdqDao.update0(jiancheng);
			} else if(jiancheng.matches("沪")) {
				sc_gwdqDao.update10(jiancheng);
			} else if(jiancheng.matches("/^津|苏|浙|皖$/")) {
				sc_gwdqDao.update20(jiancheng);
			} else if(jiancheng.matches("/^渝|冀|晋|闽|赣|鲁|鄂|湘|粤|琼$/")) {
				sc_gwdqDao.update30(jiancheng);
			} else if(jiancheng.matches("/^蒙|辽|吉|黑|豫|桂|川$/")) {
				sc_gwdqDao.update40(jiancheng);
			} else if(jiancheng.matches("贵")) {
				sc_gwdqDao.update50(jiancheng);
			} else if(jiancheng.matches("/^云|陕$/")) {
				sc_gwdqDao.update80(jiancheng);
			} else if(jiancheng.matches("/^藏|甘|青|宁|新$/")) {
				sc_gwdqDao.update90(jiancheng);
			}
			
		}
	}

}
