package io.renren.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vo.KakouRecordVo;
import io.renren.vo.KakoubsVo;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@Mapper
public interface Sc_kkxxDao extends BaseDao<KakouRecordVo> {
	
	//伴随查询
	@Select("<script>"
		  + "select count(1) as num,CAR_NUMBER from "
		  + " (select et.CAR_NUMBER,et.ENTER_TIME,et.jingdu,et.weidu from sc_etcptjd et join "
		  + " (select e.jingdu,e.weidu,e.ENTER_TIME from sc_etcptjd e  WHERE e.CAR_NUMBER=#{cph} "
		  + " and e.ENTER_TIME between #{start} and #{end}) as t1 "
		  + " on et.jingdu = t1.jingdu AND et.weidu = t1.weidu "
		  + " where et.ENTER_TIME BETWEEN t1.enter_time - interval '${time} min' "
		  + " and t1.enter_time + interval '${time} min') t2 "
		  + " WHERE CAR_NUMBER != #{cph} "
		  + " group by CAR_NUMBER having count(1) >= ${num}"
		  + "<choose>"
	      + "  <when test='sidx.trim() !=\"\"  and  sidx != null' >"
	      + "     order by ${sidx} ${order}"
	      + "  </when>"
		  + "  <otherwise> "
	      + "     order by num desc"
		  + "  </otherwise> "
	      + "</choose> "
		  + "<if test='offset != null and limit != null'>"
		  + "	  limit #{limit} offset #{offset}"
		  + "</if>"
		  + "</script>")
	List<KakouRecordVo> list(Map<String, Object> map);
	
	@Select("select * from sc_etcptjd  where CAR_NUMBER = #{cph} "
		  + " AND ENTER_TIME between #{start} and #{end}")
	List<KakouRecordVo> checkList(@Param("cph") Object carNum, @Param("start") Object start, @Param("end") Object end);
	
	//伴随车辆信息
	@Select("<script>"
		  + "select "
		  + " k1.CAR_NUMBER as cph1,"
		  + " k1.ENTER_TIME as pssj1,"
		  + " k1.DISTRICT_NM_ID as kdid,"
	   	  + " k2.ENTER_TIME as pssj2,"
		  + " k2.CAR_NUMBER as cph2,"
		  + " k1.JINGDU as jingdu,"
		  + " k1.WEIDU as weidu,"
		  + " k2.DISTRICT_NM_ID as kdid,k2.DISTRICT_NM_ID as kid "
		  + " from sc_etcptjd k1,sc_etcptjd k2 "
		  + " WHERE k1.CAR_NUMBER = #{cph1} AND k2.CAR_NUMBER = #{cph2} "
		  + " AND k1.JINGDU = k2.JINGDU AND k1.WEIDU = k2.WEIDU "
		  + " AND k1.ENTER_TIME BETWEEN k2.enter_time - interval '${time} min' "
		  + " and k2.enter_time + interval '${time} min' "
		  + "<choose>"
	      + "  <when test='sidx.trim() !=\"\"  and  sidx != null' >"
	      + "     order by ${sidx} ${order}"
	      + "  </when>"
		  + "  <otherwise> "
	      + "     order by k1.ENTER_TIME asc"
		  + "  </otherwise> "
	      + "</choose> "
		  + "<if test='offset != null and limit != null'>"
		  + "	  limit #{limit} offset #{offset}"
		  + "</if>"
		  + "</script>")
	List<KakoubsVo> kkbs(Map<String, Object> map);
	
	@Select("select count(eid) from sc_etcptjd ")
	int queryTotal(Map<String, Object> map);
	
	@Select("SELECT count(1) FROM"
		  + "(select count(1) as num,CAR_NUMBER from "
		  + "(select et.CAR_NUMBER,et.ENTER_TIME,et.jingdu,et.weidu from sc_etcptjd et join "
		  + "(select e.jingdu,e.weidu,e.ENTER_TIME from sc_etcptjd e  WHERE e.CAR_NUMBER=#{cph} "
		  + "and e.ENTER_TIME between #{start} and #{end}) as t1 "
		  + "on et.jingdu = t1.jingdu AND et.weidu = t1.weidu "
		  + "where et.ENTER_TIME >= t1.enter_time - interval '${time} min' "
		  + "and et.ENTER_TIME <= t1.enter_time + interval '${time} min') t2 "
		  + "WHERE CAR_NUMBER != #{cph} "
		  + "group by CAR_NUMBER having count(1) >= ${num}) AS t")
	int total(@Param("cph") Object carNum, 
			  @Param("start") Object start, 
			  @Param("end") Object end, 
			  @Param("num") Object num, 
			  @Param("time") Object time);
	
	@Select("select "
		  + " k1.CAR_NUMBER as cph1,"
		  + " k1.ENTER_TIME as pssj1,"
		  + " k1.DISTRICT_NM_ID as kdid,"
	   	  + " k2.ENTER_TIME as pssj2,"
		  + " k2.CAR_NUMBER as cph2,"
		  + " k1.JINGDU as jingdu,"
		  + " k1.WEIDU as weidu,"
		  + " k1.adress as adress,"
		  + " k2.DISTRICT_NM_ID as kdid,k2.DISTRICT_NM_ID as kid "
		  + " from sc_etcptjd k1,sc_etcptjd k2 "
		  + " WHERE k1.CAR_NUMBER = #{cph1} AND k2.CAR_NUMBER = #{cph2} "
		  + " AND k1.JINGDU = k2.JINGDU AND k1.WEIDU = k2.WEIDU "
		  + " AND k1.ENTER_TIME BETWEEN k2.enter_time - interval '${time} min' "
		  + " and k2.enter_time + interval '${time} min' "
		  + " ORDER BY k1.enter_time")
	List<KakoubsVo> getCount(@Param("cph1") String cph1, @Param("cph2") String cph2, @Param("time") int time);
}
