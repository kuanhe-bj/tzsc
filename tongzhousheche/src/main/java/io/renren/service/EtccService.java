package io.renren.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.EtccDao;
import io.renren.vas.entity.ETccEntity;
import io.renren.vo.ScEtcpEntity;

/**
 * 热力图数据
 * @author moulin
 *
 */
@Service
public class EtccService {

	@Autowired
	private EtccDao edao;
	
	
	public ETccEntity queryObject(String eId) {
		return null;
	}

	public List<ETccEntity> queryList(Map<String, Object> params) {
		List<ETccEntity> list = edao.queryList(params);
		return list;
	}

	public int queryTotal(Map<String, Object> map) {
		return 0;
	}

	public void save(ETccEntity eTcc) {
		
	}

	public void update(ETccEntity eTcc) {
		
	}

	public void delete(String eId) {
		
	}

	public void deleteBatch(String[] eIds) {
		
	}

	public List<ETccEntity> queryList2(Map<String, Object> params) {
		return edao.queryList2(params);
	}
	
	public List<ScEtcpEntity> queryList3(Map<String, Object> params) {
		return edao.queryList3(params);
	}

}
