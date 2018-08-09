package io.renren.dao;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vas.entity.SensitivepointEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-25 14:37:56
 */
@Mapper
public interface SensitivePointDao extends BaseDao<SensitivepointEntity> {
	
	@Select("<script>" 
			+ "select * from sensitivepoint "
			+ "WHERE center = #{center} AND sType = #{stype}" 
			+ "<choose>" 
			+ "<when test='sidx.trim() !=\"\"  and  sidx != null  '>" 
			+ "  order by ${sidx} ${order}"
			+ "</when>" 
			+ "<otherwise>" 
			+ "  order by id asc" 
			+ "</otherwise>" 
			+ " </choose>"
			+ "<if test='offset != null and limit != null'>" 
			+ "	 limit #{limit} offset #{offset}" 
			+ "</if>" 
			+ "</script>")
	List<SensitivepointEntity> list(Map<String, Object> map);
	
	@Select("<script>"
			+ "select * from sensitivepoint "
			+ "<choose>" 
			+ "<when test='sidx.trim() !=\"\"  and  sidx != null  '>" 
			+ "  order by ${sidx} ${order}"
			+ "</when>" 
			+ "<otherwise>" 
			+ "  order by id asc" 
			+ "</otherwise>" 
			+ " </choose>"
			+ "<if test='offset != null and limit != null'>" 
			+ "	 limit #{limit} offset #{offset}" 
			+ "</if>" 
			+ "</script>")
	List<SensitivepointEntity> queryList(Map<String, Object> map);
	
	@Select("select count(1) from sensitivepoint ")
	int queryTotal(Map<String, Object> map);
	
	@Select("select * from sensitivepoint ")
	List<SensitivepointEntity> getList();
	
	@Select("SELECT car_number,jingdu,weidu FROM sc_etcptjd WHERE CAR_NUMBER = "
		  + "(SELECT plate from sc_dtcldzdan GROUP BY id LIMIT 1 offset ${count}) " 
		  + "AND ENTER_TIME BETWEEN #{start} AND #{end} ")
	List<ScEtcptjdEntity> check(@Param("count") int count, @Param("start") String start, @Param("end") String end);
	
	@Update("UPDATE sc_dtcldzdan SET sensitive = ${a} WHERE plate = #{plate}")
	void update(@Param("a") double a, @Param("plate") String plate);
	
}
