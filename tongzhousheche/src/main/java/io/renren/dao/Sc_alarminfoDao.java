package io.renren.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.vas.entity.ScAlarminfoEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:53:44
 */
@Mapper
public interface Sc_alarminfoDao {
	
	@Insert("INSERT INTO sc_alarminfo ( "
		  + "carnum,"
		  + "triggerTime,"
		  + "blackId,"
		  + "dataSource,"
		  + "dataSourceId,"
		  + "readState,"
		  + "pushState,"
		  + "receiver,"
		  + "message"
		  + ") VALUES ("
		  + "#{carnum},"
		  + "#{triggertime},"
		  + "#{blackid},"
		  + "#{datasource},"
		  + "#{datasourceid},"
		  + "${readstate},"
		  + "${pushstate},"
		  + "#{receiver},"
		  + "#{message}"
		  + ")")
	void insert(ScAlarminfoEntity scAlarminfoEntity);
	
	@Select("SELECT * FROM sc_alarminfo WHERE carnum = #{cph}")
	List<ScAlarminfoEntity> checkCph(String cph);
	
	@Update("UPDATE sc_alarminfo SET readstate = 1 WHERE id = ${value} ")
	void update1(String id);
	
	@Update("<script>" +
			" UPDATE sc_alarminfo SET readstate = 1 WHERE id in " + 
			" <foreach item=\"id\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
			"  ${id} " + 
			" </foreach>" +
			"</script>")
	void updateBatch(String[] ids);
	
	@Update("UPDATE sc_alarminfo SET readstate = 0 WHERE carnum = #{cph} ")
	void update2(@Param("cph") String cph);
	
	@Select("SELECT count(1) FROM sc_alarminfo WHERE receiver = #{username} AND (readstate <> 1 OR readstate IS NULL)")
	int find(String username);
}
