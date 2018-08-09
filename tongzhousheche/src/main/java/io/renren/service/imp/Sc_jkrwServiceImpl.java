package io.renren.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_jkrwDao;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.service.Sc_jkrwService;
import io.renren.vo.Sc_jkrwVo;
import io.renren.vo.Sc_ssjkVo;

/**
 * 监控任务
 * @author moulin
 *
 */
@Service("sc_jkrwService")
public class Sc_jkrwServiceImpl implements Sc_jkrwService {
	
	@Autowired
	private Sc_jkrwDao sc_jkrwDao;

	@Override
	public List<Sc_jkrwVo> queryList(Map<String, Object> map) {
		return sc_jkrwDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_jkrwDao.queryTotal(map);
	}
	
	@Override
	public void deleteBatch(String[] ids) {
		sc_jkrwDao.deleteBatch(ids);
	}

	@Override
	public List<Sc_jkrwVo> list(Map<String, Object> map) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int mytype = Integer.parseInt(map.get("mytype").toString());
		String qssj = map.get("start").toString();
		String jssj = map.get("end").toString();
		SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
		String username = user.getUsername();
		Sc_jkrwVo sc_jkrwVo = new Sc_jkrwVo();
		String id = UUID.randomUUID().toString();
		Sc_ssjkVo sc_ssjkVo = new Sc_ssjkVo();
		sc_ssjkVo.setId(UUID.randomUUID().toString());
		sc_ssjkVo.setTaskid(id);
		String plateno = map.get("cph").toString();
		sc_ssjkVo.setPlateno(plateno);
		sc_jkrwDao.save(sc_ssjkVo);
		if(mytype == 1) {
			sc_jkrwVo.setId(id);
			sc_jkrwVo.setUsername(username);
			sc_jkrwVo.setCreatetime(sdf.format(new Date()));
			sc_jkrwVo.setMytype(1);
			sc_jkrwVo.setParameter("开始时间=" + qssj + "&结束时间=" + jssj);
			sc_jkrwVo.setValid(0);
			sc_jkrwDao.insert(sc_jkrwVo);
			map.put("username", username);
			return sc_jkrwDao.queryList(map);
		} else if(mytype == 2) {
			sc_jkrwVo.setId(id);
			sc_jkrwVo.setUsername(username);
			sc_jkrwVo.setCreatetime(sdf.format(new Date()));
			sc_jkrwVo.setMytype(2);
			sc_jkrwVo.setValid(0);
			sc_jkrwDao.insert(sc_jkrwVo);
			map.put("username", username);
			return sc_jkrwDao.queryList(map);
		} else if(mytype == 3) {
			int r = Integer.parseInt(map.get("num").toString());
			double jingdu = Double.parseDouble(map.get("jingdu").toString());
			double weidu = Double.parseDouble(map.get("weidu").toString());
			sc_jkrwVo.setId(id);
			sc_jkrwVo.setUsername(username);
			sc_jkrwVo.setCreatetime(sdf.format(new Date()));
			sc_jkrwVo.setMytype(3);
			sc_jkrwVo.setValid(0);
			sc_jkrwVo.setParameter("经度=" + jingdu + "&纬度=" +weidu + "&半径=" + r);
			sc_jkrwDao.insert(sc_jkrwVo);
			map.put("username", username);
			return sc_jkrwDao.queryList(map);
		}
		return null;
	}

	@Override
	public void update1(String id) {		
		sc_jkrwDao.update1(id);
	}

	@Override
	public void update2(String id) {
		sc_jkrwDao.update2(id);
	}

	@Override
	public List<Sc_jkrwVo> list(String id) {
		return sc_jkrwDao.list(id);
	}

	@Override
	public List<Sc_jkrwVo> check(String username, int mytype) {
		return sc_jkrwDao.check(username, mytype);
	}

	@Override
	public List<Sc_ssjkVo> find(String taskid) {
		return sc_jkrwDao.find(taskid);
	}




}
