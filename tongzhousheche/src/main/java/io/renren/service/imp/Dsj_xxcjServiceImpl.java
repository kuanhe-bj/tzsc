package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.renren.dao.Dsj_xxcjDao;
import io.renren.service.Dsj_xxcjService;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScScajEntity;

/**
 * 大事件信息采集
 * @author moulin
 *
 */
@Service("dsj_xxcjService")
public class Dsj_xxcjServiceImpl implements Dsj_xxcjService {
	
	@Autowired
	private Dsj_xxcjDao dsj_xxcjDao;

	@Override
	public List<ScScajEntity> list(Map<String, Object> query) {

		return dsj_xxcjDao.list(query);
	}

	@Override
	public int insert(int id, String ajbh, String ajmc, String pcsgx, String jq, String fadd, String faddxz,
			String fakssj, String sacph, double jd, double wd, String jyaq, double gisx, double gisy) {
		return dsj_xxcjDao.insert(id, ajbh, ajmc, pcsgx, jq, fadd, faddxz, fakssj, sacph, jd, wd, jyaq, gisx, gisy);
	}

	@Override
	public List<ScScajEntity> list_ID(int id) {
		return dsj_xxcjDao.list_ID(id);
	}

	@Override
	public int update(String ajbh, String ajmc, String pcsgx, String jq, String fadd, String faddxz, String fakssj,
			String sacph, double jd, double wd, String jyaq, String id, double gisx, double gisy) {
		return dsj_xxcjDao.update(ajbh, ajmc, pcsgx, jq, fadd, faddxz, fakssj, sacph, jd, wd, jyaq, id, gisx, gisy);
	}

	@Override
	public List<ScDtcldzdanEntity> list_dan(String plate) {
		return dsj_xxcjDao.list_dan(plate);
	}

	@Override
	public int count(Map<String, Object> query) {
		return dsj_xxcjDao.count(query);
	}

	@Override
	public int update_dan(String plate) {
		return dsj_xxcjDao.update_dan(plate);
	}

	@Override
	public int insert_blick(String cph, String user, String createReason) {
		return dsj_xxcjDao.insert_blick(cph, user, createReason);
	}

	@Override
	public List<ScScajEntity> id_cz() {
		return dsj_xxcjDao.id_cz();
	}

	@Override
	public List<ScScajEntity> cx(Map<String, Object> query) {
		return dsj_xxcjDao.cx(query);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return dsj_xxcjDao.queryTotal(map);
	}

}
