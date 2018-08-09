package io.renren.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScRedlistEntity;
import io.renren.vo.Sc_jdc;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:39:58
 */
@Mapper
public interface Sc_redlistDao extends BaseDao<ScRedlistEntity> {
	
	@Select("<script>"
			+ "select * from sc_redlist where carNum=#{carNum}"
			+ "<choose>"
	        + "  <when test='sidx.trim() !=\"\"  and  sidx != null' >"
	        + "     order by ${sidx} ${order}"
	        + "  </when>"
			+ "  <otherwise> "
	        + "     order by id desc"
			+ "  </otherwise> "
	        + "</choose> "
			+ "<if test='offset != null and limit != null'>"
			+ "	  limit #{limit} offset #{offset}"
		    + "</if>"
			+ "</script>")
	List<ScRedlistEntity> checkList(Object cph);
	
	@Select("SELECT * FROM sc_jdcjbxx "
		  + "WHERE CPH = #{carNum} OR exists "
		  + "(SELECT * FROM sc_wsjdcjbxx WHERE CPH = #{carNum})")
	List<Sc_jdc> check(String carNum);
	
	@Select("select * from sc_redlist where carNum=#{carNum}")
	List<ScRedlistEntity> find(String carNum);
	
	@Select("select max(id) from sc_redlist;")
	String idMax();
	
	@Insert("insert into sc_redlist ("
		  + "id," 
		  + "carNum," 
		  + "createTime,"
		  + "createUser," 
		  + "createReason," 
		  + "auditUser," 
		  + "auditTime," 
		  + "auditResults," 
		  + "auditOpinion"
		  + ") values ("
		  + "#{id}," 
		  + "#{carnum}," 
		  + "#{createtime}," 
		  + "#{createuser}," 
		  + "#{createreason}," 
		  + "#{audituser}," 
		  + "#{audittime}," 
		  + "#{auditresults}," 
		  + "#{auditopinion})")
	void save(ScRedlistEntity scRedlistEntity);
	
	@Select("select * from sc_redlist ORDER BY id DESC LIMIT 1 OFFSET 0")
	ScRedlistEntity getId();
	
	@Select("<script> "
		  + "select * from sc_redlist "
          + "<choose>"
          + "  <when test='sidx.trim() !=\"\"  and  sidx != null' >"
          + "     order by ${sidx} ${order}"
          + "  </when>"
		  + "  <otherwise> "
          + "     order by id desc"
		  + "  </otherwise> "
          + "</choose> "
		  + "<if test='offset != null and limit != null'>"
		  + "	  limit #{limit} offset #{offset}"
	      + "</if>"
		  + "</script>")
	List<ScRedlistEntity> queryList(Map<String, Object> map);
	
	@Select("select count(1) from sc_redlist ")
	int queryTotal(Map<String, Object> map);
	
	@Select("select count(1) from sc_redlist WHERE carNum = #{carNum}")
	int total(Map<String, Object> map);
	
	@Update("UPDATE sc_redlist SET auditResults = 1,auditTime = NOW(),auditUser = #{username},auditOpinion = #{result} WHERE carNum = #{cph}")
	void updatePass(@Param("username") String username, @Param("result") String result, @Param("cph") String cph);
	
	@Update("UPDATE sc_redlist SET auditResults = 2,auditTime = NOW(),auditUser = #{username} WHERE carNum = #{cph}")
	void updateNotPass(@Param("username") String username, @Param("cph") String cph);
}
