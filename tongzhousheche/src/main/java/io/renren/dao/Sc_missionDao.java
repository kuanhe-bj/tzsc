package io.renren.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vo.MissionVo;
import io.renren.vo.ResultVo;
import io.renren.vo.ViolationVo;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
@Mapper
public interface Sc_missionDao{
	 @Select("<script>" +
	 		 " SELECT * FROM sc_mission " +
	 		 "<if test=\"owner != 'admin'\">" + 
	 		 " where owner= #{owner}" + 
	 		 "</if>" + 
	 		 "<choose>" + 
	 		 "  <when test=\"sidx != null and sidx.trim() != ''\">" + 
	 		 "      order by ${sidx} ${order} " + 
	 		 "	</when>" + 
	 		 "	<otherwise>" + 
	 		 "      order by id desc " + 
	 		 "	</otherwise>" + 
	 		 "</choose>" + 
	 		 "	<if test=\"offset != null and limit != null\">" + 
	 		 "		limit #{limit} offset #{offset}" + 
		 	 "	</if>" +
		 	 "</script>")
	List<MissionVo> queryList(Map<String, Object> map);
		
	@Select("<script>" +
			" select count(id) from sc_mission" +
			"<if test=\"owner != admin\">" + 
			" where owner= #{owner}\r\n" + 
			"</if>" +
			"</script>")
	int queryTotal(Map<String, Object> map);
	
	@Select("<script>" +
			" delete from sc_mission where ID in " + 
			" <foreach item=\"id\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
			"  ${id} " + 
			" </foreach>" +
			"</script>")
	void deleteBatch(int[] ids);
	
	@Select("select coalesce(max(id),0) AS rid from sc_mission ")
	int getId();
	
	@Insert("INSERT INTO sc_mission ("
		  + "id,creator,owner,tasktype,status,createtime,content,parameters,rid"
		  + ") VALUES ( "
		  + "(select coalesce(max(id),0)+1 from sc_mission),"
		  + "#{creator},#{owner},#{tasktype},${status},#{createtime},#{content},#{parameters},${rid}) ")
	void insert(MissionVo missionVo);
	
	@Select("<script>" +
		    " SELECT * FROM sc_mission WHERE rid = ${rid} " +
		    " <choose>\n" + 
		    "    <when test=\"sidx != null and sidx.trim() != ''\">\n" + 
		 	"       order by ${sidx} ${order}\n" + 
		 	"    </when>\n" + 
		 	" <otherwise>\n" + 
		 	"       order by id desc\n" + 
		 	" </otherwise>\n" + 
		 	" </choose>\n" + 
		 	" <if test=\"offset != null and limit != null\">\n" + 
		 	"		limit #{limit} offset #{offset}\n" + 
		 	" </if>" +
		 	"</script>")
	List<MissionVo> show(Map<String, Object> map);
	
	@Select("select count(id) from sc_mission WHERE tasktype = #{tasktype} ")
	int total(Map<String, Object> map);
	
	@Insert("INSERT INTO sc_result "
		  + "(id,taskid,plateno,createtime,result,rvalue "
		  + ") VALUES ( "
		  + "(select coalesce(max(id),0)+1 from sc_result),"
		  + "${taskid},#{plateno},${createtime},#{result},${rvalue})")
	void save(ResultVo resultVo);
	
	//套牌
	@Select("SELECT * FROM sc_etcptjd WHERE CAR_NUMBER = "
		  + " (SELECT plate FROM sc_dtcldzdan ORDER BY id LIMIT 1 offset ${num})"
		  + " AND ENTER_TIME BETWEEN #{start} AND #{end} "
		  + " GROUP BY CAR_NUMBER,exit_time,eid ")
	List<ScEtcptjdEntity> check(@Param("num") int num, @Param("start") String start, @Param("end") String end);
	
	//隐匿
	@Select("SELECT CAR_NUMBER,exit_time FROM "
		  + " (SELECT car_number,exit_time FROM sc_etcptjd WHERE sjly = 'p' AND enter_time BETWEEN #{start} AND #{end}) AS a "
		  + " WHERE CAR_NUMBER in "
		  + " (SELECT plate FROM sc_dtcldzdan ORDER BY id LIMIT 1000 offset ${num}) "
		  + " AND EXIT_TIME < #{start} AND EXIT_TIME > #{end} "
		  + " GROUP BY CAR_NUMBER,exit_time ")
	List<ScEtcptjdEntity> hidden(@Param("num") int num, @Param("start") String start, @Param("end") String end);
	
	//异常轨迹
	@Select("SELECT CAR_NUMBER,count(eid) as count FROM sc_etcptjd " 
		  + " WHERE CAR_NUMBER in (${plates}) AND lower(sjly) = 'k' "
		  + " AND ENTER_TIME BETWEEN #{start} AND #{end} "
		  + " GROUP BY CAR_NUMBER ")
	List<ScEtcptjdEntity> showList(@Param("start") String start, @Param("end") String end, @Param("plates") String plates);
	
	//限行
	@Update("select count(1) as count,plateno "
		  + " FROM sc_violation where plateno in (${plate}) "
		  + " and vtime between #{start} and #{end} "
		  + " and islimitout = 1 "
		  + " group by plateno ")
	List<ViolationVo> queryXC(@Param("plate") String plate, @Param("start") String start, @Param("end") String end);
	
	@Select("SELECT * FROM sc_mission WHERE id = ${id}")
	List<MissionVo> find(@Param("id") int id);
	
	@Update("UPDATE sc_mission SET status = 1 WHERE id = ${id}")
	void updating(@Param("id") int id);
	
	@Update("UPDATE sc_mission SET status = 2 WHERE id = ${id}")
	void update(@Param("id") int id);
	
	//昼出车辆
	@Select("<script>" + 
			"select exit_time,t.car_number FROM sc_etcptjd t " + 
			" join sc_dtcldzdan dt " +
			" on dt.plate=t.car_number " + 
			" where t.PARK_NM='通州区' and t.sjly = 'p' " + 
			" AND t.exit_time BETWEEN #{fxc} and #{fxd} " +
			"<if test='btch &lt; btdh'> " + 
			" AND cast(extract(hour from t.exit_time) as integer ) > ${btch} AND cast(extract(hour from t.exit_time) as integer ) &lt; ${btdh} " +
			"</if>" +
			"<if test='btcm &lt; btdm and btch == btdh'> " + 
			" AND cast(extract(MINUTE from t.exit_time) as integer ) > ${btcm} AND cast(extract(MINUTE from t.exit_time) as integer ) &lt; ${btdm} " + 
			"</if>" +
			"<if test='btcs &lt; btds and btcm == btdm'> " + 
			" AND cast(extract(SECOND from t.exit_time) as integer ) > ${btcs} AND cast(extract(SECOND from t.exit_time) as integer ) &lt; ${btds} " +
			"</if>" +
			" group by t.exit_time,t.car_number " + 
			" having count(*) > ${btcxc} and count(*) &lt;= ${btcxd} " +
			 "</script>")
	List<ScEtcptjdEntity> zc(Map<String, Object> map);
	
	//夜出车辆
	@Select("<script>" +
			"select exit_time,t.car_number FROM sc_etcptjd t " + 
			" join sc_dtcldzdan dt " +
			" on dt.plate=t.car_number " + 
			" where t.PARK_NM='通州区' and t.sjly = 'p' " + 
			" AND t.exit_time BETWEEN #{fxc} and #{fxd} " + 
			"<if test='ycch != 0 and ycdh != 0'> " + 
			" AND cast(extract(hour from t.exit_time) as integer ) > ${ycch} AND cast(extract(hour from t.exit_time) as integer ) &lt; ${ycdh} " + 
			"</if>" +
			"<if test='yccm != 0 and ycdm != 0'> " + 
			" AND cast(extract(MINUTE from t.exit_time) as integer ) > ${yccm} AND cast(extract(MINUTE from t.exit_time) as integer ) &lt; ${ycdm} " +
			"</if>" +
			"<if test='yccs != 0 and ycds != 0'> " + 
			" AND cast(extract(SECOND from t.exit_time) as integer ) > ${yccs} AND cast(extract(SECOND from t.exit_time) as integer ) &lt; ${ycds} " +
			"</if>" +
			" group by t.exit_time,t.car_number " + 
			" having count(*) > ${wscxc} and count(*) &lt;= ${wscxd} " +
			"</script>")
	List<ScEtcptjdEntity> yc(Map<String, Object> map);
	
	@Update("update sc_dtcldzdan set " + 
			" nightout = ${f} " + 
			" WHERE plate = #{plate} ")
	void updateZfyc(@Param("f") double f, @Param("plate") String plate);
	
	@Select("select count(1) as count,car_number " + 
			" FROM sc_etcptjd " + 
			" where car_number in (${plates}) " +
			" group by car_number ")
	List<ScEtcptjdEntity> zfyc(@Param("plates") String plates);
}
