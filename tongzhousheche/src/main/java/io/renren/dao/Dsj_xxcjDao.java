package io.renren.dao;

import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScScajEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface Dsj_xxcjDao {

	@Select("<script> "
			+ "select * from sc_scaj where 1=1"			
		    + "<choose>"
            + "<when test='sidx.trim() !=\"\"  and  sidx != null  '>"
            + " order by ${sidx} ${order}"
            + "</when>"
			+ "<otherwise>"
            + " order by fakssj desc"
			+ "</otherwise>"
            + " </choose>"
		    + "<if test='offset != null and limit != null'>"
			+ "	limit  #{limit} offset #{offset}"
		    + "</if>"
		    + " </script>")
	List<ScScajEntity> list(Map<String, Object> query);

	@Select("select count(1) from sc_scaj where ajbh=#{ajbh} or ajmc LIKE '%${ajmc}%' ")
	int count(Map<String, Object> map);
	
	@Select("select count(1) from sc_scaj ")
	int queryTotal(Map<String, Object> map);

	@Select("select * from sc_dtcldzdan where plate=#{plate}")
	List<ScDtcldzdanEntity> list_dan(@Param("plate") String plate);

	@Select("select *  from sc_scaj where id = #{id}")
	List<ScScajEntity> list_ID(@Param("id") int id);

	@Update("update sc_scaj set ajbh=#{ajbh}," 
		  + "ajmc=#{ajmc},pcsgxid=#{pcsgx},jq=#{jq},"
		  + "faddid=#{fadd},faddxz=#{faddxz}," 
		  + "fakssj='${fakssj}',cph=#{sacph},"
		  + "jindu=${jd},weidu=${wd},jyaq=#{jyaq}," 
		  + "gis=(point'(${gisx},${gisy})')"
		  + "where id = #{id}")
	int update(@Param("ajbh") String ajbh, @Param("ajmc") String ajmc, @Param("pcsgx") String pcsgx,
			@Param("jq") String jq, @Param("fadd") String fadd, @Param("faddxz") String faddxz,
			@Param("fakssj") String fakssj, @Param("sacph") String sacph, @Param("jd") double jd,
			@Param("wd") double wd, @Param("jyaq") String jyaq, @Param("id") String id, @Param("gisx") Double gisx,
			@Param("gisy") Double gisy);

	@Insert("insert into  sc_scaj(id,ajbh,ajmc,pcsgxid,jq,faddid,faddxz,"
		  + "fakssj,cph,jindu,weidu,jyaq,gis) values(#{id},#{ajbh},#{ajmc},"
		  + "#{pcsgx},#{jq},#{fadd},#{faddxz},#{fakssj},#{sacph},"
		  + "${jd},${wd},#{jyaq}, point'(${gisx},${gisy})')")
	int insert(@Param("id") int id,@Param("ajbh") String ajbh, @Param("ajmc") String ajmc, @Param("pcsgx") String pcsgx,
			@Param("jq") String jq, @Param("fadd") String fadd, @Param("faddxz") String faddxz,
			@Param("fakssj") String fakssj, @Param("sacph") String sacph, @Param("jd") double jd,
			@Param("wd") double wd, @Param("jyaq") String jyaq, @Param("gisx") Double gisx, @Param("gisy") Double gisy);

	@Update("update sc_dtcldzdan set isInvolved='1' where plate=#{plate}")
	int update_dan(@Param("plate") String plate);

	@Insert("insert into  sc_blacklist(carNum,createUser,createReason) values(#{cph},#{user},#{createReason})")
	int insert_blick(@Param("cph") String cph, @Param("user") String user, @Param("createReason") String createReason);

	@Select("select max(id) as id from sc_scaj")
	List<ScScajEntity> id_cz();
	

	@Select("<script> "
		  + "select * from sc_scaj "
		  + "<where> "
		  + "<if test='ajbh != \"\" and ajbh != null' > "
		  + " AND ajbh = #{ajbh} "
		  + "</if>"
		  + "<if test='ajmc != \"\" and ajmc != null' > "
		  + " AND ajmc LIKE '%${ajmc}%' "
		  + "</if>"
		  + "</where>"			
		  + "<choose>"
		  + "<when test='sidx.trim() !=\"\"  and  sidx != null  '>"
		  + " order by ${sidx} ${order}"
		  + "</when>"
		  + "<otherwise>"
		  + " order by fakssj desc"
		  + "</otherwise>"
		  + " </choose>"
		  + "<if test='offset != null and limit != null'>"
		  + "	limit #{limit} offset #{offset}"
		  + "</if>"
		  + "</script>")
	List<ScScajEntity> cx(Map<String, Object> query);
}
