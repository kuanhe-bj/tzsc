package io.renren.vas.service.impl;

import io.renren.vas.dao.SensitivepointDao;
import io.renren.vas.entity.SensitivepointEntity;
import io.renren.vas.service.SensitivepointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;





@Service("sensitivepointService")
public class SensitivepointServiceImpl implements SensitivepointService {
	@Autowired
	private SensitivepointDao sensitivepointDao;
	
	@Override
	public SensitivepointEntity queryObject(Integer id){
		return sensitivepointDao.queryObject(id);
	}
	
	@Override
	public List<SensitivepointEntity> queryList(Map<String, Object> map){
		return sensitivepointDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sensitivepointDao.queryTotal(map);
	}
	
	@Override
	public void save(SensitivepointEntity sensitivepoint){
		sensitivepointDao.save(sensitivepoint);
	}
	
	@Override
	public void update(SensitivepointEntity sensitivepoint){
		sensitivepointDao.update(sensitivepoint);
	}
	
	@Override
	public void delete(Integer id){
		sensitivepointDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		sensitivepointDao.deleteBatch(ids);
	}
	
}
