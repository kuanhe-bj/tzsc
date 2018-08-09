package io.renren.service.imp;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.renren.dao.Sc_dtcldzdansDao;
import io.renren.service.Sc_dtcldzdansService;

import io.renren.vo.DtcldzdansVo;
import io.renren.vo.VehiclegsVo;

/**
 * 电子档案
 * @author moulin
 *
 */
@Service("sc_dtcldzdansService")
public class Sc_dtcldzdansServiceImpl implements Sc_dtcldzdansService{

	@Autowired
	private Sc_dtcldzdansDao scDynamicVehicleEctronicFileDao;
	
	@Override
	public List<DtcldzdansVo> queryList(Map<String, Object> map) {
		String province = (String) map.get("province");
		if(province == null || province.equals("")) {
			map.put("jiancheng", null);
		} else {
			VehiclegsVo vehiclegsVo = scDynamicVehicleEctronicFileDao.jiancheng(map);
			String jiancheng = vehiclegsVo.getJiancheng();
			map.put("jiancheng", jiancheng);
		}
		List<DtcldzdansVo> list = this.scDynamicVehicleEctronicFileDao.queryList(map);
		return list;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map) {
		return scDynamicVehicleEctronicFileDao.queryTotal(map);
	}
	
	
	
	
}
