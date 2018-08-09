package io.renren.vas.service.impl;

import io.renren.vas.dao.ScJqDao;
import io.renren.vas.entity.ScJqEntity;
import io.renren.vas.service.ScJqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("scJqService")
public class ScJqServiceImpl implements ScJqService {
    @Autowired
    private ScJqDao scJqDao;

    @Override
    public ScJqEntity queryObject(String id){
        return scJqDao.queryObject(id);
    }

    @Override
    public List<ScJqEntity> queryList(Map<String, Object> map){
        return scJqDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map){
        return scJqDao.queryTotal(map);
    }

    @Override
    public void save(ScJqEntity scJq){
        scJqDao.save(scJq);
    }

    @Override
    public void update(ScJqEntity scJq){
        scJqDao.update(scJq);
    }

    @Override
    public void delete(String id){
        scJqDao.delete(id);
    }

    @Override
    public void deleteBatch(String[] ids){
        scJqDao.deleteBatch(ids);
    }

	@Override
	public List<ScJqEntity> find(Map<String, Object> map) {
		return scJqDao.find(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return scJqDao.total(map);
	}

}
