package io.renren.vas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.vas.dao.CljsyxxDao;
import io.renren.vas.entity.ScJdcjbxxEntity;
import io.renren.vas.service.CljsyxxService;

@Service("cljsyxxService")
public class CljsyxxServiceImpl implements CljsyxxService{
@Autowired
private CljsyxxDao cljsyxxDao;

@Override
public List<ScJdcjbxxEntity> finj(Map<String, Object> map) {
	List<ScJdcjbxxEntity> list=cljsyxxDao.finj(map);
	return list;
}

}
