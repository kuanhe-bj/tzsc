package io.renren.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.vo.Sc_jkrwVo;
import io.renren.vo.Sc_ssjkVo;

@Mapper
public interface Sc_jkrwDao {
	
	@Select("<script>" +
		    "SELECT * FROM sc_jkrw " +
			"<where>" +
		    " <if test=\"username != 'admin'\">\r\n" + 
	 		"    AND username = #{username} \r\n" + 
	 		" </if>\n" +
	 		"</where>" +
		    " <choose>\n" + 
		 	"    <when test=\"sidx != null and sidx.trim() != ''\">\n" + 
		 	"        order by ${sidx} ${order}\n" + 
		 	"    </when>\n" + 
		 	"	 <otherwise>\n" + 
		 	"        order by id desc\n" + 
		 	"	 </otherwise>\n" + 
		 	" </choose>\n" + 
		 	"	<if test=\"offset != null and limit != null\">\n" + 
		 	"	     limit #{limit} offset #{offset}\n" + 
		 	"	</if>" +
		 	"</script>")
	List<Sc_jkrwVo> queryList(Map<String, Object> map);
	
	@Select("<script>" +
		    " SELECT count(id) FROM sc_jkrw " +
			"<where>" +
		    " <if test=\"username != 'admin'\">" + 
	 		"    AND username = #{username} " + 
	 		" </if>" +
	 		"</where>" +
	 		"</script>")
	int queryTotal(Map<String, Object> map);
	
	@Insert("INSERT INTO sc_jkrw (id,username,mytype,createtime,parameter,valid "
		  + ") VALUES ("
		  + "#{id},#{username},${mytype},#{createtime},#{parameter},${valid} )")
	void insert(Sc_jkrwVo sc_jkrwVo);
	
	@Select("<script>" +
		    " delete from sc_jkrw where ID in " + 
			"	<foreach item=\"id\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
			"		#{id} " + 
			"	</foreach>" +
			"</script>")
	void deleteBatch(String[] ids);
	
	@Update("UPDATE sc_jkrw SET valid = 1 WHERE id = #{id} ")
	void update1(String id);
	
	@Update("UPDATE sc_jkrw SET valid = 0 WHERE id = #{id} ")
	void update2(String id);
	
	@Select("SELECT * FROM sc_jkrw WHERE id = #{id} ")
	List<Sc_jkrwVo> list(String id);
	
	@Select("SELECT * FROM sc_jkrw WHERE username = #{username} AND mytype = ${mytype} AND valid = 1 ")
	List<Sc_jkrwVo> check(@Param("username") String username, @Param("mytype") int mytype);
	
	@Insert("INSERT INTO sc_ssjk (id,taskid,plateno) VALUES (#{id},#{taskid},#{plateno}) ")
	void save(Sc_ssjkVo sc_ssjkVo);
	
	@Select("SELECT * FROM sc_ssjk WHERE taskid = #{id} ")
	List<Sc_ssjkVo> find(String taskid);
}
