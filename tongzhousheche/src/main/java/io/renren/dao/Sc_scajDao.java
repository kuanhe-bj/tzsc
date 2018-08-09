package io.renren.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import io.renren.vas.entity.ScScajEntity;

@Mapper
public interface Sc_scajDao {
	
	@Insert("insert into  sc_scaj( "
			+ "id,ajbh,ajmc,pcsgxid,jq,faddid,faddxz,"
			+ "fakssj,cph,jindu,weidu,jyaq "
			+ " ) values ( "
			+ "#{id},#{ajbh},#{ajmc},"
			+ "#{pcsgxid},#{jq},#{faddid},#{faddxz},#{fakssj},#{cph},"
			+ "${jindu},${weidu},#{jyaq})")
	void save(ScScajEntity scScajEntity);
	
	@Update("update sc_scaj set "
			+ "ajbh=#{ajbh},ajmc=#{ajmc},"
			+ "pcsgxid=#{pcsgxid},jq=#{jq}, "
			+ "faddid=#{faddid},faddxz=#{faddxz}, " 
			+ "fakssj='${fakssj}',cph=#{cph}, "
			+ "jindu=${jindu},weidu=${weidu},jyaq=#{jyaq} " 
			+ "where id = #{id}")
	void update(ScScajEntity scScajEntity);
}
