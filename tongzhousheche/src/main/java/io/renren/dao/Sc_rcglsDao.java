package io.renren.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vo.Sc_rcglsVo;
@Mapper
public interface Sc_rcglsDao extends BaseDao<Sc_rcglsVo>{
	@Select("<script>\r\n" + 
			"select * from sc_rcgl  \r\n" + 
			"<where> \r\n" +
			" and cp is not null"+
			" and xm is not null"+
			" and sfz is not null"+
			"   <if test=\"cp != NULL and cp != ''\">\r\n" + 
			"			 and cp = #{cp}\r\n" + 
			"		</if>\r\n" + 
			"		<if test=\"cp == NULL and cp == ''\">\r\n" + 
			"		</if>\r\n" + 
			"		<if test=\"xm != NULL and xm != ''\">\r\n" + 
			"			 and xm = #{xm}\r\n" + 
			"		</if>\r\n" + 
			"		<if test=\"xm == NULL and xm == ''\">	\r\n" + 
					
			"		</if>\r\n" + 
			"		<if test=\"sfz != NULL and sfz != ''\">\r\n" + 
			"			 and sfz = #{sfz}\r\n" + 
			"		</if>\r\n" + 
			"		<if test=\"sfz == NULL and sfz == ''\">\r\n" + 
			"		</if>\r\n" + 
			"</where>\r\n" + 
			" <choose>\r\n" + 
			"            <when test=\"sidx != null and sidx.trim() != ''\">\r\n" + 
			"                order by ${sidx} ${order}\r\n" + 
			"            </when>\r\n" + 
			"			<otherwise>\r\n" + 
			"                order by cp desc\r\n" + 
			"			</otherwise>\r\n" + 
			" </choose>\r\n" + 
			"		<if test=\"offset != null and limit != null\">\r\n" + 
			"			limit #{limit} offset #{offset}\r\n" + 
			"		</if>\r\n" + 
			"</script>")
	List<Sc_rcglsVo> queryList(Map<String, Object> map);
	
	@Select("<script>\r\n" + 
			"select count(1) from sc_rcgl\r\n" + 
			"<where> \r\n" + 
			" and cp is not null"+
			" and xm is not null"+
			" and sfz is not null"+
			"		<if test=\"cp != NULL and cp != ''\">\r\n" + 
			"			 and cp = #{cp}\r\n" + 
			"		</if>\r\n" + 
			"		<if test=\"cp == NULL and cp == ''\">\r\n" + 
			"		</if>\r\n" + 
			"		<if test=\"xm != NULL and xm != ''\">\r\n" + 
			"			 and xm = #{xm}\r\n" + 
			"		</if>\r\n" + 
			"		<if test=\"xm == NULL and xm == ''\">	\r\n" + 
			"		</if>\r\n" + 
			"		<if test=\"sfz != NULL and sfz != ''\">\r\n" + 
			"			 and sfz = #{sfz}\r\n" + 
			"		</if>\r\n" + 
			"		<if test=\"sfz == NULL and sfz == ''\">\r\n" + 
			"		</if>\r\n" + 
			"</where>\r\n" + 
			"</script>")
	int queryTotal(Map<String, Object> map);
}
