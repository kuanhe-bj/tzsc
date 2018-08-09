package io.renren.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScEtcptjdEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
@Mapper
public interface Sc_ycclDao extends BaseDao<ScEtcptjdEntity> {

	 @Select("SELECT adress,CAR_NUMBER as carNumber,"
		 	   + "count(1) as count,jingdu,weidu "
	           + "FROM sc_etcptjd "
	           + "WHERE CAR_NUMBER = #{cph} and ENTER_TIME>= '${atime}' and ENTER_TIME <= '${etime}' "
	           + "GROUP BY adress,CAR_NUMBER,jingdu,weidu " 
	           + "HAVING weidu BETWEEN #{weidu} - 5*0.01 AND #{weidu} + 5*0.01 " 
			   + " AND jingdu BETWEEN #{jingdu} - 5*0.01 AND #{jingdu} + 5*0.01 "
			   + " AND count(1) >= ${num} "
			   + " ORDER BY count(1) DESC ")
	 List<ScEtcptjdEntity> destination(@Param("cph") String cph, @Param("atime") String atime, 
			 						   @Param("etime") String etime, @Param("weidu") String weidu,
			 						   @Param("jingdu") String jingdu, @Param("num") int num);
	 
	 @Select("SELECT WEIDU,JINGDU FROM sc_etcptjd WHERE CAR_NUMBER = #{cph} "
		   + "ORDER BY ENTER_TIME DESC LIMIT 1 offset 0 ")
	 List<ScEtcptjdEntity> getJingduAndWeidu(@Param("cph") String cph);
		
	 @Select("SELECT *,count(eid) AS count FROM sc_etcptjd WHERE CAR_NUMBER = #{carNum} "
		   + " GROUP BY eid "
		   + " ORDER BY count,ENTER_TIME DESC " 
		   + " LIMIT 1 offset 0 ")
	 ScEtcptjdEntity listObject(String cph);
		
	 @Select("SELECT JINGDU,WEIDU FROM sc_etcptjd where CAR_NUMBER = #{cph}")
	 List<ScEtcptjdEntity> list(String cph);
	
 
}
