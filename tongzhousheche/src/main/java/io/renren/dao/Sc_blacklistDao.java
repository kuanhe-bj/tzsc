package io.renren.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.vas.entity.ScBlacklistEntity;
import io.renren.vo.Sc_jdc;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:39:58
 */
@Mapper
public interface Sc_blacklistDao  {
	
	@Select("<script>"
			+ "select * from sc_blacklist where carNum=#{carNum}"
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
	List<ScBlacklistEntity> checkList(Map<String, Object> map);
	
	@Select("select * from sc_blacklist where carNum=#{carNum}")
	List<ScBlacklistEntity> find(String carNum);
	
	@Select("SELECT count(1) FROM sc_jdcjbxx WHERE CPH = #{carNum} OR exists (SELECT * FROM sc_wsjdcjbxx WHERE CPH = #{carNum})")
	List<Sc_jdc> check(String carNum);
	
	@Select("select max(id) from Sc_blacklist;")
	String idMax();
	
	@Insert("insert into Sc_blacklist ("
		  + "carNum," 
		  + "createTime,"
		  + "createUser," 
		  + "createReason," 
		  + "auditUser," 
		  + "auditTime," 
		  + "auditResults," 
		  + "auditOpinion"
		  + ") values ("
		  + "#{carnum}," 
		  + "#{createtime}," 
		  + "#{createuser}," 
		  + "#{createreason}," 
		  + "#{audituser}," 
		  + "#{audittime}," 
		  + "${auditresults}," 
		  + "#{auditopinion})")
	void save(ScBlacklistEntity scBlacklistEntity);
	
	@Select("<script> "
			  + "select * from Sc_blacklist "
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
	List<ScBlacklistEntity> queryList(Map<String, Object> map);
	
	@Select("select count(1) from Sc_blacklist ")
	int queryTotal(Map<String, Object> map);
		
	@Select("select count(1) from Sc_blacklist WHERE carNum = #{carNum}")
	int total(Map<String, Object> map);
		
	@Select("select * from sc_blacklist where carNum=#{cph} limit 1 offset 0")
	ScBlacklistEntity checkCph(String cph);
		
		
	@Insert("insert into Sc_blacklist ("
				  + "carNum," 
				  + "createUser," 
				  + "createTime,"
				  + "createReason" 
				  + ") values ("
				  + "#{carNum}," 
				  + "#{createUser}," 
				  + "#{createTime}," 
				  + "#{createReason})")
	void insertQB(Map<String, Object> params);
		
	@Update("UPDATE sc_blacklist SET auditResults = 1,auditTime = NOW(),auditUser = #{username},auditOpinion = #{result} WHERE carNum = #{cph}")
	void updatePass(@Param("username") String username, @Param("result") String result, @Param("cph") String cph);
		
	@Update("UPDATE sc_blacklist SET auditResults = 2,auditTime = NOW(),auditUser = #{username} WHERE carNum = #{cph}")
	void updateNotPass(@Param("username") String username, @Param("cph") String cph);
		
	@Update("UPDATE sc_blacklist SET status = #{state},bkid=#{bkid} WHERE carNum = #{plateNoType}")
	void updateDisposition(@Param("state") String state, @Param("plateNoType") String plateNoType,@Param("bkid") String bkid);
		
	@Select("select bkid from Sc_blacklist where carNum = #{cph}")
	int findcar(@Param("plateNoType") String plateNoType);
	
	@Update("update sc_blacklist set bkstatus = 1 where carnum = #{carnum} ")
	int updateBkStatus(@Param("carnum") String carnum);
}
