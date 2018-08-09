package io.renren.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vo.VehiclegsVo;

@Mapper
public interface Sc_vehiclegsDao extends BaseDao<VehiclegsVo> {
	@Select("select jiancheng from sc_gsd order by id limit 1 offset ${count}")
	List<VehiclegsVo> jianchengcyc(@Param("count")int count);
	
	@Select("<script>"  
	        + "select province from sc_gsd"
	        + " WHERE createtime BETWEEN #{entertime} and #{exittime} "
	        + "<if test='province!=null '>"
	        + "	and province=#{province} " 
	        + "</if> " 
	        + "GROUP BY province "
	        + "order by province desc "
	        + "limit #{limit} offset #{offset}"
			+"</script>")
	List<VehiclegsVo> jcTotal(Map<String, Object> map);

	@Select("<script>"  
			 +  "select count(1) from "
	         + " (select province from sc_gsd"
	         + " WHERE createtime BETWEEN #{entertime} and #{exittime} "
	         + "<if test=\"province != null and province != ''\">" 
	         + 	"	and province=#{province} " 
	         + 	"</if> " 
	         + " GROUP BY province) aa"
			 +"</script>")
	int queryTotals(Map params);
	
	@Select("select jiancheng from sc_gsd where province =#{province}")
	VehiclegsVo jiancheng(Map<String, Object> map);
	
	@Select("SELECT count(1) FROM ( " + 
			"select car_number, count(eid) " + 
			"FROM sc_etcptjd " + 
			"where enter_time BETWEEN '${entertime}' and '${exittime}' " + 
			"AND car_number like '%${jiancheng}%' " + 
			"GROUP BY car_number " + 
			")")
	int selectdcount(Map<String, Object> map);
	
	@Select("select count(1) FROM(select car_number, count(eid) " + 
			"FROM sc_etcptjd " + 
			"where enter_time BETWEEN '${entertime}' and '${exittime}' " + 
			"GROUP BY car_number)")
	int total1(Map<String, Object> map);
	

	@Select("<script>" + 
			"SELECT * FROM sc_gsd " +
			"<where>" +
			" createtime = '1' " + 
	 		"<if test=\"province != null and province != ''\">" + 
	 		"	and province=#{province} " + 
	 		"</if> " + 
	 		"</where>" +
 	 		"<choose>" + 
	 		"   <when test=\"sidx != null and sidx.trim() != ''\"> " + 
	 		"     order by ${sidx} ${order} " + 
	 		"   </when>" + 
	 		"   <otherwise>" + 
	 		"     order by percent desc" + 
	 		"   </otherwise>" + 
	 		"</choose>" + 
	 		"<if test=\"offset != null and limit != null\">" + 
	 		"	limit #{limit} offset #{offset}" + 
	 		"</if>" + 
	 		"</script>")
	List<VehiclegsVo> queryList(Map<String, Object> map);
	 	
	@Select("<script>select count(*) from sc_gsd " +
	 		"<where>" +
			" createtime = '1' " +
	 		"<if test=\"province != null and province != ''\">" + 
	 		"	and province = #{province} " + 
	 		"</if>" +
	 		"</where>" +
	 		 "</script>")
	int queryTotal(Map<String, Object> map);
	
	@Select("SELECT count(eid) AS count FROM sc_etcptjd "
	 		  + "WHERE EXIT_TIME BETWEEN NOW() - interval '7 day' AND NOW() "
	 		  + "GROUP BY CAR_NUMBER")
	List<ScEtcptjdEntity> total();
	 	
	@Select("SELECT * FROM sc_gsd WHERE createtime = '1' ")
	List<VehiclegsVo> list();
	 	
	@Select("SELECT * FROM sc_gsd where province != '北京' AND createtime = '1' order by carnum desc")
	List<VehiclegsVo> listnotbeijing(@Param("date") String date);
	 	
	@Select("SELECT count(1) FROM "
          + " (SELECT car_number,count(eid) FROM sc_etcptjd "
          + "  WHERE car_number LIKE '${jiancheng}%' "
          + "  AND enter_time BETWEEN #{start} AND #{end} "
          + " GROUP BY car_number) ")
	int gsd(@Param("jiancheng") String jiancheng, @Param("start") String start, @Param("end") String end);
	 	
	@Select("SELECT * FROM sc_gsd " + 
	 		" where (province = '新疆' or province = '陕西' " + 
	 		"  or province = '甘肃' or province = '宁夏' " + 
	 		"  or province = '西藏') " +
	 		"  AND createtime = '1' " +
	 		" ORDER BY id")
	List<VehiclegsVo> highriskList(@Param("date") String date);
	 	
	@Select("select carnum from sc_gsd where province = '北京' AND createtime = '1' ")
	VehiclegsVo getcarnum(@Param("date") String date);
	 	
	@Select("SELECT count(1) FROM "
		  + " (SELECT count(eid) FROM sc_etcptjd "
		  + "  WHERE enter_time BETWEEN #{start} AND #{end} GROUP BY car_number) ") 	
	int getTotal(@Param("start") String start, @Param("end") String end);
	
	@Insert("INSERT INTO sc_gsd (id,province,percent,carnum,jiancheng,createtime) "
		  + " VALUES ((select coalesce(max(id),0)+1 from sc_gsd), "
		  + " #{province},${percent},${carnum},#{jiancheng},#{createtime}) ")
	void saveGsd(VehiclegsVo vehiclegsVo);
	
	@Select("SELECT sum(carnum) FROM sc_gsd "
		  + "WHERE province = #{province} "
		  + "AND createtime BETWEEN #{entertime} AND #{exittime} ")
	int getCount(Map<String, Object> map);
	
	@Select("SELECT sum(carnum) FROM sc_gsd "
		  + "WHERE createtime BETWEEN #{entertime} AND #{exittime} ")
	int totalCount(Map<String, Object> map);
	
	@Select("SELECT province,jiancheng,sum(carnum) "
		  + "FROM sc_gsd WHERE createtime != '1' "
		  + "GROUP BY province,jiancheng ORDER BY province ")
	List<VehiclegsVo> getProvinceTotal();
	
	@Select("SELECT sum(carnum) FROM sc_gsd ")
	int CountTotal();
	
	@Update("update sc_gsd SET carnum = ${c}, percent = ${f} WHERE province = #{province} AND createtime = '1'")
	void update(@Param("c") int c, @Param("f") double f, @Param("province") String province);

	
	
}
