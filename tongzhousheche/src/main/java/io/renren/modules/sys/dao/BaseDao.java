package io.renren.modules.sys.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import io.renren.vas.entity.ScJczxxEntity;
import io.renren.vas.entity.ScJxsjEntity;
import io.renren.vo.KakouRecordVo;
import io.renren.vo.KakoubsVo;
import io.renren.vo.Sc_jdc;
import io.renren.vo.VehicleRecordVo;


/**
 * 基础Dao(还需在XML文件里，有对应的SQL语句)
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016��8�上�:31:36
 */
public interface BaseDao<T> {
	
	void save(T t);
	
	void save(Map<String, Object> map);
	
	void saveBatch(List<T> list);
	
	int update(T t);
	
	int update(Map<String, Object> map);
	
	int delete(Object id);
	
	int delete(Map<String, Object> map);
	
	int deleteBatch(Object[] id);

	T queryObject(Object id);
	
	List<T> queryList(Map<String, Object> map);
	
	List<T> hlsjList(Map<String, Object> map);
	
	List<T> checkList(Object cph);
	List<T> mohucheck(Map<String, Object> map);
	
	List<KakouRecordVo> hotPoints(String etcp);
	
	List<T> queryList(Object id);
	
	List<T> checkCarNum(String carNum);
	
	List<Sc_jdc> check(String carNum);
	
	List<VehicleRecordVo> checkEtcp(@Param("carNum") String carNum, @Param("start") String start, @Param("end") String end);
	
	List<T> findByCPH(String carNum);
	
	List<T> list(String carNum);
	
	List<T> findKkxx(Map<String, Object> map);
	
	List<KakoubsVo> checkKkbs(@Param("cph") String cph, @Param("start") String start, @Param("end") String end);
	
	List<T> find(@Param("kid") String kid, @Param("start") String start, @Param("end") String end);
	
	int queryTotal(Map<String, Object> map);
	
	List<T> all();

	int queryTotal();
	
	String idMax();
	
	ScJczxxEntity idMax1();
	
	List<ScJxsjEntity> cz(@Param("licensePlate") String licensePlate);
}
