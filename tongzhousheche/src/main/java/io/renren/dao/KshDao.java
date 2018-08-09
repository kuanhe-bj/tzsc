package io.renren.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.renren.vo.KshVo;

@Mapper
public interface KshDao {
	
@Select("<script>"
		+ "select e.car_number cph,e.jingdu jingdu,e.weidu weidu,count(eid) AS num "
		+ " from sc_etcptjd e "
		+ " <if test=\'zd == true and sa != true\'>"
		+ " join (select plate from sc_dtcldzdan where isSuspects = 1) p"
		+ " on e.car_number = p.plate"
		+ " </if>"
		+ " <if test=\'zd != true and sa == true \'>"
		+ " join (select plate from sc_dtcldzdan where isInvolved = 1) p"
		+ " on e.car_number = p.plate"
		+ " </if>"
		+ " <if test=\'zd == true and sa == true \'>"
		+ " join (select plate from sc_dtcldzdan where isSuspects = 1 and isInvolved = 1) p"
		+ " on e.car_number = p.plate"
		+ " </if>"
		+ " <if test=\'zfyc == true or csyc == true or jpdp == true or yncl == true or xxcl == true \'>"
		+ " join (select plate from sc_dtcldzdan "
		+ "<where>"
		+ "<if test=\'zfyc == true\'>"
		+ "  and nightOut >= 90 "
		+ "</if>"
		+ "<if test=\'csyc == true\'>"
		+ "  AND times = 1 "
		+ "</if>"
		+ "<if test=\'jpdp == true\'>"
		+ "  AND isFake = 1 "
		+ "</if>"
		+ "<if test=\'yncl == true\'>"
		+ "  AND hidden = 1 "
		+ "</if>"
		+ "<if test=\'xxcl == true\'>"
		+ "  AND limits >= 90 "
		+ "</if>"
		+ "</where>) AS t "
		+ " on e.car_number = t.plate"
		+ " </if>"
		+ " where e.ENTER_TIME between '${qssj}' and '${jssj}'"
		+ " GROUP BY e.car_number,e.jingdu,e.weidu"
		+ " HAVING jingdu BETWEEN ${jingd} - ${count} * 0.01 AND ${jingd} + ${count} * 0.01 "
    	+ " AND weidu BETWEEN ${weid} - ${count} * 0.01 AND ${weid} + ${count} * 0.01 "
		+ " AND count(*) >= ${num} "
		+ " limit 50 OFFSET 0"
		+ "</script>")
List<KshVo> query(Map<String, Object> params);
}
