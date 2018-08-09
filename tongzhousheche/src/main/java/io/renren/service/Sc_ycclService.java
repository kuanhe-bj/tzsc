package io.renren.service;

import java.util.List;


import io.renren.vas.entity.ScEtcptjdEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public interface Sc_ycclService {
	
	List<ScEtcptjdEntity> destination(String cph, String atime, String etime, int num);
	
	ScEtcptjdEntity listObject(String cph);
	
	List<ScEtcptjdEntity> getJingduAndWeidu(String cph);
	
}
