package io.renren.service;



import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vo.ScEtcpEntity;

/**
 *
 *
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:53:44
 */
public interface Sc_alarminfoService {

	void insert(ScAlarminfoEntity scAlarminfoEntity);

	void checkCph(ScEtcpEntity sEEntity);
	
	void update1(String id);
	
	void updateBatch(String[] ids);
	
	void update2(String cph);
	
	int find(String username);
}
