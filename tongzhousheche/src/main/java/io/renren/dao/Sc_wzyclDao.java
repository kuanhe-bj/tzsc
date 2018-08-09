package io.renren.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.vo.DtcldzdansVo;

@Mapper
public interface Sc_wzyclDao {
	 
	 
	 @Select("select plate from sc_dtcldzdan LIMIT 1 offset #{count}")
	 List<DtcldzdansVo> queryList(int count);
	 
	 @Select("update sc_dtcldzdan set violation=0")
	 int update();
	
	 @Update("update sc_dtcldzdan dt set dt.violation=( " + 
	 		" select atan(count(1)/10)*200/3.1415926 FROM sc_violation t " + 
	 		" where t.plateno=#{plate} and t.vtime > #{time}) "
	 	  + "WHERE dt.plate = #{plate} ")
	 int update1(@Param("plate") String plate, @Param("time") String time);
	 
	 @Select("SELECT violation FROM sc_dtcldzdan WHERE plate = #{plate} ")
	 List<DtcldzdansVo> getViolation(String plate);
}
