package io.renren.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.vo.DtcldzdansVo;

@Mapper
public interface Sc_zdhmjfmxlDao {
	 
	 
	 @Select("select * from sc_dtcldzdan LIMIT 1 offset #{count}")
	 List<DtcldzdansVo> queryList(int count);
	 
	 @Select("update sc_dtcldzdan set obscured = 0 ")
	 void update();
	
	 @Update("update sc_dtcldzdan dt set dt.obscured=100 where  " + 
	 		 "(select count(1) from sc_violation t " + 
	 		 " where t.plateno=#{plate} and t.iscovered=1 " + 
	 		 ")>0 ")
	 void update1(@Param("plate") String plate);
	 
	 @Select("SELECT obscured FROM sc_dtcldzdan WHERE plate = #{plate} ")
	 List<DtcldzdansVo> getObscured(String plate);
}
