package io.renren.dao;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vas.entity.ScScajEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface DsjDao extends BaseDao<ScDtcldzdanEntity> {

    @Select("<script>"
    	  + "select * from sc_scaj"
    	  + "<where>"
    	  + "<if test='number!=null and name!=null '>" 
    	  + " AND ajbh = #{number} and ajmc = #{name}"
    	  + "</if>"
    	  + "<if test='number!=null and name==null '>" 
    	  + " AND ajbh = #{number}"
    	  + "</if>"
    	  + "<if test='number==null and name!=null '>" 
    	  + " ANd ajmc = #{name}"
    	  + "</if>"
    	  + "</where>"
    	  + "</script>")
    List<ScScajEntity> fin_ds(Map<String, Object> map);
    
    @Select("select * from sc_scaj where ajbh = #{number} and ajmc=#{name}")
    List<ScScajEntity> fin_dsall(Map<String, Object> map);
    
    @Select("<script>"
    	  + "select * from sc_scaj where 1=1"
    	  + "<if test='number!=null'>" 
    	  + " and ajbh = #{number}"
    	  + "</if>"
    	  + "<if test='name!=null'>" 
    	  + " and ajmc = #{name}"
    	  + "</if>"
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
    	  + "</script>")
    List<ScScajEntity> fin(Map<String, Object> map);
    
    @Select("<script>"
    	  + "select count(1) from sc_scaj where 1=1"
    	  + "<if test='number!=null'>" 
    	  + " and ajbh = #{number}"
    	  + "</if>"
    	  + "<if test='name!=null'>" 
    	  + " and ajmc = #{name}"
    	  + "</if>"
    	  + "</script>")
    int total(Map<String, Object> map);
    
    @Select("select * from sc_scaj where ajbh = #{ajbh} or ajmc=#{ajmc}")
    List<ScScajEntity> x(@Param("ajbh")String ajbh,@Param("ajmc")String ajmc);
    
    @Select("select * from sc_scaj where cph = #{cph}")
    List<ScScajEntity> jw(@Param("cph") String cph); 
    
    
    /*大事件数据查询*/
    @Select("<script>"
    	  + " select jingdu,weidu,car_number AS carNumber,count(eid) AS count"
    	  + " from sc_etcptjd "
    	  + "<where>"
    	  + " ENTER_TIME between '${qsj}' and '${hsj}' "
    	  + "<if test = 'et == 1'>"
    	  + "  AND sjly = 'k' "
    	  + "</if>"
    	  + "<if test = 'zf == 1'>"
    	  + "  AND car_number IN (SELECT plate FROM sc_dtcldzdan WHERE nightout >= 90) "
    	  + "</if>"
    	  + "<if test = 'wd == 1'>"
    	  + "  AND car_number NOT LIKE '%京%' "
    	  + "</if>"
    	  + "<if test = 'gw == 1'>"
    	  + "  AND car_number IN (SELECT plate FROM sc_dtcldzdan WHERE highrisk >= 90) "
    	  + "</if>"
    	  + "<if test = 'sa == 1'>"
    	  + "  AND car_number IN (SELECT plate FROM sc_dtcldzdan WHERE isInvolved = 1) "
    	  + "</if>"
    	  + "<if test = 'cx == 1'>"
    	  + "  AND car_number IN (SELECT plate FROM sc_dtcldzdan WHERE abnormal >= 90) "
    	  + "</if>"
    	  + "</where>"
    	  + " GROUP BY jingdu,weidu,car_number "
    	  + " HAVING jingdu BETWEEN ${jingdu} - ${count} * 0.01 AND ${jingdu} + ${count} * 0.01 "
    	  + " AND weidu BETWEEN ${weidu} - ${count} * 0.01 AND ${weidu} + ${count} * 0.01 "
    	  + " ORDER BY count DESC "
    	  + " LIMIT 500 OFFSET 0	"
    	  + "</script>")
    List<ScEtcptjdEntity> allcar(Map<String, Object> map);
     
}
