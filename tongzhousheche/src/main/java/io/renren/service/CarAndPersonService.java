package io.renren.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.vas.dao.ScJdcjbxxDao;
import io.renren.vas.entity.ScJdcjbxxEntity;

/**
 * 车，人信息
 *
 * @author zhangqiang
 * @date 2018/3/15
 */

@Service
public class CarAndPersonService {

	@Autowired
	private ScJdcjbxxDao scJdcjbxxDao;

	public List<ScJdcjbxxEntity> queryList(Map<String, Object> map){
		return scJdcjbxxDao.queryList(map);
	}

}
