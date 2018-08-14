package io.renren.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScBlacklistEntity;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vas.entity.ScKkxxEntity;
import io.renren.vo.ImportPlaceVo;
import io.renren.vo.ScEtcpEntity;

@Mapper
public interface ScETCPDao extends BaseDao<ScEtcptjdEntity> {

	// 有分
	@Select("<script>" 
	        + "SELECT car_number as carNumber,enter_time as enterTime,exit_time as exitTime,park_nm as parkNm,district_nm as districtNm,adress as adress FROM sc_etcptjd" 
	        + "<if test='cph!=null '>"
			+ " WHERE  CAR_NUMBER = #{cph} and enter_time >= '${qssj}' and enter_time &lt;= '${jssj}'"
			+ "</if>" 
			+ "<if test='cph==null '>"
			+ " WHERE enter_time >= '${qssj}' and enter_time &lt;= '${jssj}'"
			+ "</if>"
			+ "<if test='park!=null '>" 
			+ " AND park_nm like '%${park}%'"
			+ "</if>"
			+ "<if test='address!=null '>" 
			+ " AND adress like '%${address}%'"
			+ "</if>"
			+ " AND SJLY = 'p'"
			+ " ORDER BY ENTER_TIME DESC "
			+ " limit #{limit} offset #{offset}"
			+ "</script>")
	List<ScEtcpEntity> etcpList(Map params);

	@Select(" SELECT * FROM sc_etcptjd where CAR_NUMBER='${cph}'")
	List<ScEtcpEntity> shijian(@Param("cph")String cph);
	
	// 无分
	@Select("<script>" 
	        + "SELECT  car_number as carNumber,enter_time as enterTime,adress as adress,jingdu as jingdu,weidu as weidu,deviceid as DeviceID,direction as Direction,enter_img as enterImg from sc_etcptjd " 
			+ "<if test='cph!=null '>"
			+ " WHERE  CAR_NUMBER = #{cph} and enter_time >= '${qssj}' and enter_time &lt;= '${jssj}'"
			+ "</if>" 
			+ "<if test='cph==null '>"
			+ " WHERE  enter_time >= '${qssj}' and enter_time &lt;= '${jssj}'"
			+ "</if>" 
			+ " AND jingdu is not null "
			+ " AND weidu !='0.0' "
			+ " ORDER BY ENTER_TIME ASC " 
			+ " limit 100 offset 0"
			+ "</script>")
	List<ScEtcpEntity> etcpDataList(Map params);

	@Select("<script>" 
	        + "SELECT count(ENTER_TIME) FROM sc_etcptjd"
	        + " WHERE SJLY = 'p'" 
	        + "<if test='park!=null '>" 
	        + " AND park_nm like '%${park}%'"
			+ "</if>"
			+ "<if test='address!=null '>" 
			+ " AND adress like '%${address}%'"
			+ "</if>"
			+ "<if test='cph!=null '>"
			+ " AND  CAR_NUMBER = #{cph} and ENTER_TIME >= '${qssj}' and ENTER_TIME &lt;= '${jssj}'"
			+ "</if>" 
			+ "<if test='cph==null '>"
			+ " AND ENTER_TIME >= '${qssj}' and ENTER_TIME &lt;= '${jssj}'"
			+ "</if>" 
			+ "</script>")
	int queryTotals(Map params);

	@Select( "<script>"
			+ "select e.CAR_NUMBER as cph,count(e.eid) as num"
			+ " from sc_etcptjd e"
			+ " where  e.ENTER_TIME >= '${qssj}' and e.ENTER_TIME &lt;= '${jssj}'"
			+ " AND jingdu BETWEEN ${jingd} - ${count} * 0.01 AND ${jingd} + ${count} * 0.01 "
			+ " AND weidu BETWEEN ${weid} - ${count} * 0.01 AND ${weid} + ${count} * 0.01 "
			+ " group by e.CAR_NUMBER "
			+ " having num >= ${paihuai} "
			+ " ORDER BY num desc "
			+ " LIMIT 500 OFFSET 0 "
			+ "</script>")
	List<ImportPlaceVo> importList(Map params);
	
	@Select("<script>"
			+ "select e.CAR_NUMBER as cph,count(e.ENTER_TIME) as num"
			+ " from sc_etcptjd e"
			+ " where e.ENTER_TIME >= '${qssj}' and e.ENTER_TIME &lt;= '${jssj}'"
			+ " AND jingdu BETWEEN ${jingd} - ${count} * 0.01 AND ${jingd} + ${count} * 0.01 "
			+ " AND weidu BETWEEN ${weid} - ${count} * 0.01 AND ${weid} + ${count} * 0.01 "
			+ " group by e.CAR_NUMBER  "
			+ " having num >= ${paihuai}"
			+ "</script>")
	List<ImportPlaceVo> importListTotal(Map params);
	
	
	

	/**
	 * 黑名单比
	 * 
	 * @return
	 */
	@Select("SELECT * FROM Sc_blacklist b" 
	        + " left join sc_etcptjd e on e.CAR_NUMBER=b.carnum"
			+ " WHERE NOT EXISTS (SELECT 1 FROM sc_alarminfo a where b.carnum = a.carnum)")
	List<ScBlacklistEntity> bList();

	@Select("<script>" 
	        + "SELECT count(ENTER_TIME) FROM sc_etcptjd" 
	        + " WHERE SJLY = 'k'"
	        + "<if test='infoKind!=null '>"
			+ " AND InfoKind = ${infoKind}"
			+ "</if>"
			+ "<if test='color!=null '>" 
			+ " AND VehicleColor = ${color}"
			+ "</if>" 
			+ "<if test='DeviceID!=null '>" 
			+ " AND DeviceID = ${DeviceID}"
			+ "</if>"
			+ "<if test='pclass!=null '>" 
			+ " AND PlateClass in (SELECT value FROM sc_palte_class_type WHERE type like '%${pclass}%')"
			+ "</if>"
			+ "<if test='paltecolor!=null '>" 
			+ " AND PlateColor = ${paltecolor}"
			+ "</if>"
			+ "<if test='safetyBelt!=null '>" 
			+ " AND SafetyBelt = ${safetyBelt}"
			+ "</if>"
			+ "<if test='calling!=null '>" 
			+ " AND Calling = ${calling}"
			+ "</if>"
			+ "<if test='speed!=null '>" 
			+ " AND Speed >= ${speed}"
			+ "</if>"
			+ "<if test='numOfPassenger!=null '>" 
			+ " AND NumOfPassenger >= ${numOfPassenger}"
			+ "</if>" 
			+ "<if test='model!=null '>"
			+ " AND VehicleModel in (SELECT value FROM sc_model WHERE model like '%${model}%')"
			+ "</if>" 
			+ "<if test='brand!=null '>"
			+ " and VehicleBrand in (SELECT value FROM sc_brand WHERE name like '%${brand}%')"
			+ "</if>"
			+ "<if test='qclass!=null '>" 
			+ " AND VehicleClass in (SELECT code FROM sc_vehicleclass WHERE info like '%${qclass}%')"
			+ "</if>" 
			+ "<if test='cph!=null '>"
			+ " AND CAR_NUMBER like '%${cph}%' "
			+ "</if>"
			+ " AND ENTER_TIME >= '${qssj}' and ENTER_TIME &lt;= '${jssj}'"
			+ "</script>")
	int total(Map<String, Object> params);

	@Select("<script>"
			 + "select " 
				+ "( SELECT C.color FROM sc_color C WHERE cast(C.VALUE as INTEGER) = aaa.VehicleColor ) AS VehicleColor,"
				+ "(SELECT b.contact FROM sc_brand b WHERE cast(b.VALUE as INTEGER)= aaa.VehicleBrand LIMIT 1) AS VehicleBrand,"
				+ "aaa.carNumber AS carNumber,"
				+ "aaa.enterTime AS enterTime,"
				+ "aaa.enterImg AS enterImg,"
				+ "aaa.exitImg AS exitImg,"
				+ "(SELECT v.info FROM sc_vehicleclass v WHERE v.code = aaa.VehicleClass LIMIT 1) AS VehicleClass,"
				+ "aaa.VehicleModel AS VehicleModel,"
				+ "aaa.adress AS adress,"
				+ "aaa.SourceID AS SourceID,"
				+ "(SELECT P.TYPE FROM sc_palte_class_type P WHERE cast(P.VALUE as VARCHAR) = aaa.PlateClass) AS PlateClass,"
				+ "( SELECT C.color FROM sc_color C WHERE cast(C.VALUE as INTEGER) = aaa.PlateColor) AS PlateColor,"
				+ "aaa.Sunvisor AS Sunvisor,"
				+ "aaa.SafetyBelt AS SafetyBelt,"
				+ "aaa.Calling AS Calling,"
				+ "aaa.Speed AS Speed,"
				+ "aaa.NumOfPassenger AS NumOfPassenger,"
				+ "aaa.InfoKind AS InfoKind,"
				+ "aaa.SJLY AS sjly"
				+ " from"
				+ "(SELECT e.VehicleColor,e.VehicleBrand ,e.CAR_NUMBER as carNumber,e.ENTER_TIME as enterTime,e.ENTER_IMG as enterImg,e.EXIT_IMG as exitImg,e.VehicleClass,e.VehicleModel as VehicleModel,e.adress as adress,e.SourceID as SourceID,e.PlateClass,e.PlateColor,e.Sunvisor as Sunvisor,e.SafetyBelt as SafetyBelt,e.Calling as Calling,e.Speed as Speed,e.NumOfPassenger as NumOfPassenger,e.InfoKind as InfoKind,e.SJLY as sjly  from sc_etcptjd e "
	            + " WHERE e.SJLY = 'k'"
            + "<if test='infoKind!=null '>"
			+ " AND e.InfoKind = ${infoKind}"
			+ "</if>"
			+ "<if test='color!=null '>" 
			+ " AND e.VehicleColor = ${color}"
			+ "</if>" 
			+ "<if test='DeviceID!=null '>" 
			+ " AND e.DeviceID = ${DeviceID}"
			+ "</if>" 
			+ "<if test='speed!=null '>" 
			+ " AND e.Speed >= ${speed}"
			+ "</if>"
			+ "<if test='numOfPassenger!=null '>" 
			+ " AND e.NumOfPassenger >= ${numOfPassenger}"
			+ "</if>" 
			+ "<if test='pclass!=null '>" 
			+ " AND e.PlateClass in (SELECT value FROM sc_palte_class_type WHERE type like '%${pclass}%')"
			+ "</if>"
			+ "<if test='paltecolor!=null '>" 
			+ " AND e.PlateColor = ${paltecolor}"
			+ "</if>"
			+ "<if test='safetyBelt!=null '>" 
			+ " AND e.SafetyBelt = ${safetyBelt}"
			+ "</if>"
			+ "<if test='calling!=null '>" 
			+ " AND e.Calling = ${calling}"
			+ "</if>"
			+ "<if test='model!=null '>"
			+ " AND e.VehicleModel in (SELECT value FROM sc_model WHERE model like '%${model}%')"
			+ "</if>" 
			+ "<if test='brand!=null '>"
			+ " and e.VehicleBrand in (SELECT value FROM sc_brand WHERE name like '%${brand}%')"
			+ "</if>"
			+ "<if test='qclass!=null '>" 
			+ " AND e.VehicleClass in (SELECT code FROM sc_vehicleclass WHERE info like '%${qclass}%')"
			+ "</if>" 
			+ "<if test='cph!=null '>"
			+ " and e.CAR_NUMBER like '%${cph}%' "
			+ "</if>"
			+ " AND e.ENTER_TIME >= '${qssj}' and e.ENTER_TIME &lt;= '${jssj}'"
			+ " ORDER by e.ENTER_TIME DESC "
            + " limit #{limit} offset #{offset} )aaa"  
			+ "</script>")
	List<ScEtcpEntity> moHuList(Map<String, Object> params);

	@Select("<script>"
            + "select " 
			+ "( SELECT C.color FROM sc_color C WHERE cast(C.VALUE as INTEGER) = aaa.VehicleColor ) AS VehicleColor,"
			+ "(SELECT b.contact FROM sc_brand b WHERE cast(b.VALUE as INTEGER)= aaa.VehicleBrand LIMIT 1) AS VehicleBrand,"
			+ "aaa.carNumber AS carNumber,"
			+ "aaa.enterTime AS enterTime,"
			+ "aaa.enterImg AS enterImg,"
			+ "aaa.exitImg AS exitImg,"
			+ "(SELECT v.info FROM sc_vehicleclass v WHERE v.code = aaa.VehicleClass LIMIT 1) AS VehicleClass,"
			+ "aaa.VehicleModel AS VehicleModel,"
			+ "aaa.adress AS adress,"
			+ "aaa.SourceID AS SourceID,"
			+ "(SELECT P.TYPE FROM sc_palte_class_type P WHERE cast(P.VALUE as VARCHAR) = aaa.PlateClass) AS PlateClass,"
			+ "( SELECT C.color FROM sc_color C WHERE cast(C.VALUE as INTEGER) = aaa.PlateColor) AS PlateColor,"
			+ "aaa.Sunvisor AS Sunvisor,"
			+ "aaa.SafetyBelt AS SafetyBelt,"
			+ "aaa.Calling AS Calling,"
			+ "aaa.Speed AS Speed,"
			+ "aaa.NumOfPassenger AS NumOfPassenger,"
			+ "aaa.InfoKind AS InfoKind,"
			+ "aaa.SJLY AS sjly"
			+ " from"
			+ "(SELECT e.VehicleColor,e.VehicleBrand ,e.CAR_NUMBER as carNumber,e.ENTER_TIME as enterTime,e.ENTER_IMG as enterImg,e.EXIT_IMG as exitImg,e.VehicleClass,e.VehicleModel as VehicleModel,e.adress as adress,e.SourceID as SourceID,e.PlateClass,e.PlateColor,e.Sunvisor as Sunvisor,e.SafetyBelt as SafetyBelt,e.Calling as Calling,e.Speed as Speed,e.NumOfPassenger as NumOfPassenger,e.InfoKind as InfoKind,e.SJLY as sjly  from sc_etcptjd e "
            + " WHERE e.SJLY != 'p' "
            + "<if test='sjly!=null '>"
			+ " AND e.SJLY = '${sjly}'"
			+ "</if>"
			+ "<if test='color!=null '>" 
			+ " AND e.VehicleColor = ${color}"
			+ "</if>"
			+ "<if test='pclass!=null '>" 
			+ " AND e.PlateClass = (SELECT value FROM sc_palte_class_type WHERE type = #{pclass})"
			+ "</if>"
			+ "<if test='paltecolor!=null '>" 
			+ " AND e.PlateColor = ${paltecolor}"
			+ "</if>"
			+ "<if test='safetyBelt!=null '>" 
			+ " AND e.SafetyBelt = ${safetyBelt}"
			+ "</if>"
			+ "<if test='calling!=null '>" 
			+ " AND e.Calling = ${calling}"
			+ "</if>"
			+ "<if test='speed!=null '>" 
			+ " AND e.Speed >= ${speed}"
			+ "</if>"
			+ "<if test='numOfPassenger!=null '>" 
			+ " AND e.NumOfPassenger >= ${numOfPassenger}"
			+ "</if>" 
			+ "<if test='model!=null '>" 
			+ " AND e.VehicleModel = (SELECT value FROM sc_model WHERE model = #{model})"
			+ "</if>" 
			+ "<if test='brand!=null '>"
			+ " and e.VehicleBrand = ${brand} " 
			+ "</if>"
			+ "<if test='qclass!=null '>" 
			+ " AND e.VehicleClass = (SELECT code FROM sc_vehicleclass WHERE info = #{qclass})"
			+ "</if>" 
			+ "<if test='cph!=null '>"
			+ " AND e.CAR_NUMBER = #{cph} and e.ENTER_TIME >= '${qssj}' and e.ENTER_TIME &lt;= '${jssj}'"
			+ "</if>"
			+ "<if test='cph==null '>"
			+ " AND e.ENTER_TIME >= '${qssj}' and e.ENTER_TIME &lt;= '${jssj}'"
			+ "</if>" 
			+ " ORDER BY e.ENTER_TIME DESC "
			+ " limit #{limit} offset #{offset} ) aaa"
			+ "</script>")
	List<ScEtcpEntity> yiSuoList(Map<String, Object> params);

	@Select("<script>" 
	        + "SELECT count(ENTER_TIME) FROM sc_etcptjd" 
	        + " WHERE SJLY != 'p'"
			+ "<if test='color!=null '>" 
			+ " AND VehicleColor = ${color}"
			+ "</if>"
			+ "<if test='pclass!=null '>" 
			+ " AND PlateClass = (SELECT value FROM sc_palte_class_type WHERE type = #{pclass})"
			+ "</if>"
			+ "<if test='paltecolor!=null '>" 
			+ " AND PlateColor = ${paltecolor}"
			+ "</if>"
			+ "<if test='safetyBelt!=null '>" 
			+ " AND SafetyBelt = ${safetyBelt}"
			+ "</if>"
			+ "<if test='calling!=null '>" 
			+ " AND Calling = ${calling}"
			+ "</if>"
			+ "<if test='speed!=null '>" 
			+ " AND Speed >= ${speed}"
			+ "</if>"
			+ "<if test='numOfPassenger!=null '>" 
			+ " AND NumOfPassenger >= ${numOfPassenger}"
			+ "</if>" 
			+ "<if test='model!=null '>" 
			+ " AND VehicleModel = (SELECT value FROM sc_model WHERE model = #{model})"
			+ "</if>"
			+ "<if test='brand!=null '>"
			+ " and VehicleBrand = (SELECT value FROM sc_brand WHERE name = #{brand})"
			+ "</if>"
			+ "<if test='qclass!=null '>" 
			+ " AND VehicleClass = (SELECT code FROM sc_vehicleclass WHERE info = #{qclass})"
			+ "</if>" 
			+ "<if test='sjly!=null '>"
			+ " AND sjly = '${sjly}'"
			+ "</if>"
			+ "<if test='cph!=null '>"
			+ " AND CAR_NUMBER = #{cph} and ENTER_TIME >= '${qssj}' and ENTER_TIME &lt;= '${jssj}'"
			+ "</if>"
			+ "<if test='cph==null '>"
			+ " AND ENTER_TIME >= '${qssj}' and ENTER_TIME &lt;= '${jssj}'"
			+ "</if>" 
			+ "</script>")
	int yiSuoListTotals(Map params);

	@Select("SELECT color FROM Sc_color WHERE value = #{colorNum}")
	String colorName(String colorNum);

	
    @Select("SELECT count(1) FROM sc_etcptjd WHERE CAR_NUMBER = #{plateNo} and ENTER_TIME = '${etime}'")
    int jiancha(@Param("plateNo") String plateNo,@Param("etime") String etime);
    
    
	@Select("SELECT contact FROM Sc_brand WHERE value = #{brandNum} limit 1")
	String brandName(String brandNum);

	@Insert("insert into sc_etcptjd (" 
	        + "EID," 
			+ "CAR_NUMBER," 
	        + "ENTER_TIME," 
			+ "ENTER_IMG," 
	        + "EXIT_IMG,"
			+ "VehicleColor," 
	        + "VehicleBrand," 
			+ "VehicleModel," 
	        + "VehicleClass," 
			+ "VehicleStyles," 
	        + "InfoKind,"
			+ "SourceID," 
	        + "LaneNo," 
			+ "PlateClass," 
	        + "PlateColor," 
			+ "Sunvisor," 
	        + "SafetyBelt," 
			+ "Calling,"
			+ "NumOfPassenger," 
			+ "Speed," 
			+ "DeviceID," 
			+ "adress," 
			+ "VehicleColorDepth," 
			+ "jingdu," 
			+ "weidu," 
			+ "SJLY"
			+ ") values (" 
			+ "#{eid}," 
			+ "#{carNumber}," 
			+ "#{enterTime}," 
			+ "#{enterImg}," 
			+ "#{exitImg},"
			+ "${VehicleColor}," 
			+ "#{VehicleBrand}," 
			+ "#{VehicleModel}," 
			+ "#{VehicleClass}," 
			+ "#{VehicleStyles},"
			+ "#{InfoKind}," 
			+ "#{SourceID}," 
			+ "#{LaneNo}," 
			+ "#{PlateClass}," 
			+ "${PlateColor}," 
			+ "#{Sunvisor},"
			+ "#{SafetyBelt}," 
			+ "#{Calling}," 
			+ "#{NumOfPassenger}," 
			+ "#{Speed}," 
			+ "#{DeviceID},"
			+ "#{adress},"
			+ "#{VehicleColorDepth}," 
			+ "#{jingdu}," 
			+ "#{weidu}," 
			+ "#{sjly}" 
			+ ")")
	void saveETCP(ScEtcpEntity scEtcpEntity);

	
	@Select("SELECT CAR_NUMBER FROM sc_etcptjd")
	List<ScEtcpEntity>  ziDongAll();
	
	
	@Select("SELECT count(1) from sc_kkxx where id = #{dd}")
	int kaKouRuKu(String dd);
	
	@Select("SELECT plateno FROM sc_ssjk where taskid = (SELECT id FROM sc_jkrw where mytype =2 and valid =1 and username= #{username})")
	String  moreCphs(String username);
	
	@Select( "SELECT * FROM sc_etcptjd "
		   + "WHERE car_number = #{plate} "
		   + "AND enter_time = ("
		   + "SELECT max(enter_time) AS enterTime FROM sc_etcptjd where car_number = #{plate} GROUP BY car_number) ")
	List<ScEtcpEntity>  rtpCph(@Param("plate") String plate );

	
	@Select("SELECT * FROM sc_jkrw where mytype =2 and valid =1 and username= #{username}")
	String sTime(String username);

	@Select("SELECT * from sc_kkxx where id = #{id}")
	ScKkxxEntity kakou(String id);
	
	@Select("SELECT id,mc from sc_kkxx ")
	List<ScKkxxEntity> kakouByName();
	
	@Insert("insert into sc_key_value (" 
			+ "id," 
	        + "skey," 
			+ "contents"
			+ ") values ("
			+ "#{uuid},"
			+ "#{skey}," 
			+ "#{subscribeID}" 
			+ ")")
	void saveSID(@Param("uuid") String uuid ,@Param("skey") String skey ,@Param("subscribeID") String subscribeID );
	
	@Select("SELECT contents from sc_key_value where skey = #{subscribeID}")
	String findSID(@Param("subscribeID") String subscribeID);
	
	@Update("update sc_key_value  set contents= #{subscribeID} where skey=#{skey}")
	void updateSID(@Param("skey")String skey,@Param("subscribeID") String subscribeID);
	
	
	@Select("<script>"
            + "select " 
			+ "( SELECT C.color FROM sc_color C WHERE cast(C.VALUE as INTEGER) = aaa.VehicleColor ) AS VehicleColor,"
			+ "(SELECT b.contact FROM sc_brand b WHERE cast(b.VALUE as INTEGER)= aaa.VehicleBrand LIMIT 1) AS VehicleBrand,"
			+ "aaa.carNumber AS carNumber,"
			+ "aaa.enterTime AS enterTime,"
			+ "aaa.enterImg AS enterImg,"
			+ "aaa.exitImg AS exitImg,"
			+ "(SELECT v.info FROM sc_vehicleclass v WHERE v.code = aaa.VehicleClass LIMIT 1) AS VehicleClass,"
			+ "aaa.VehicleModel AS VehicleModel,"
			+ "aaa.adress AS adress,"
			+ "aaa.SourceID AS SourceID,"
			+ "(SELECT P.TYPE FROM sc_palte_class_type P WHERE cast(P.VALUE as VARCHAR) = aaa.PlateClass) AS PlateClass,"
			+ "( SELECT C.color FROM sc_color C WHERE cast(C.VALUE as INTEGER) = aaa.PlateColor) AS PlateColor,"
			+ "aaa.Sunvisor AS Sunvisor,"
			+ "aaa.SafetyBelt AS SafetyBelt,"
			+ "aaa.Calling AS Calling,"
			+ "aaa.Speed AS Speed,"
			+ "aaa.NumOfPassenger AS NumOfPassenger,"
			+ "aaa.InfoKind AS InfoKind,"
			+ "aaa.SJLY AS sjly"
			+ " from"
			+ "(SELECT e.VehicleColor,e.VehicleBrand ,e.CAR_NUMBER as carNumber,e.ENTER_TIME as enterTime,e.ENTER_IMG as enterImg,e.EXIT_IMG as exitImg,e.VehicleClass,e.VehicleModel as VehicleModel,e.adress as adress,e.SourceID as SourceID,e.PlateClass,e.PlateColor,e.Sunvisor as Sunvisor,e.SafetyBelt as SafetyBelt,e.Calling as Calling,e.Speed as Speed,e.NumOfPassenger as NumOfPassenger,e.InfoKind as InfoKind,e.SJLY as sjly  from sc_etcptjd e "
            + " WHERE 1 = 1 "
            + "<if test='infoKind!=null '>"
			+ " AND e.InfoKind = ${infoKind}"
			+ "</if>" 
			+ "<if test='color!=null '>" 
			+ " AND e.VehicleColor = ${color}"
			+ "</if>"
			+ "<if test='pclass!=null '>" 
			+ " AND e.PlateClass = (SELECT value FROM sc_palte_class_type WHERE type = #{pclass})"
			+ "</if>"
			+ "<if test='paltecolor!=null '>" 
			+ " AND e.PlateColor = ${paltecolor}"
			+ "</if>"
			+ "<if test='safetyBelt!=null '>" 
			+ " AND e.SafetyBelt = ${safetyBelt}"
			+ "</if>"
			+ "<if test='calling!=null '>" 
			+ " AND e.Calling = ${calling}"
			+ "</if>"
			+ "<if test='speed!=null '>" 
			+ " AND e.Speed >= ${speed}"
			+ "</if>"
			+ "<if test='numOfPassenger!=null '>" 
			+ " AND e.NumOfPassenger >= ${numOfPassenger}"
			+ "</if>" 
			+ "<if test='model!=null '>" 
			+ " AND e.VehicleModel = (SELECT value FROM sc_model WHERE model = #{model})"
			+ "</if>" 
			+ "<if test='brand!=null '>"
			+ " and e.VehicleBrand = ${brand} " 
			+ "</if>"
			+ "<if test='qclass!=null '>" 
			+ " AND e.VehicleClass = (SELECT code FROM sc_vehicleclass WHERE info = #{qclass})"
			+ "</if>" 
			+ "<if test='cph!=null '>"
			+ " AND e.CAR_NUMBER = #{cph} and e.ENTER_TIME >= '${qssj}' and e.ENTER_TIME &lt;= '${jssj}'"
			+ "</if>"
			+ "<if test='cph==null '>"
			+ " AND e.ENTER_TIME >= '${qssj}' and e.ENTER_TIME &lt;= '${jssj}'"
			+ "</if>" 
			+ " ORDER BY e.ENTER_TIME DESC "
			+ " limit #{limit} offset #{offset} ) aaa"
			+ "</script>")
	List<ScEtcpEntity> fastList(Map<String, Object> params);
	
	@Select( "SELECT * FROM sc_etcptjd WHERE car_number = #{plate} ")
	List<ScEtcpEntity>  checkReds(@Param("plate") String plate );
	
	@Insert("insert into sc_redlist_data ("
	        + "EID,"
			+ "CAR_NUMBER,"
	        + "ENTER_TIME,"
			+ "ENTER_IMG,"
	        + "EXIT_IMG,"
			+ "VehicleColor,"
	        + "VehicleBrand,"
			+ "VehicleModel,"
	        + "VehicleClass,"
			+ "VehicleStyles,"
	        + "InfoKind,"
			+ "SourceID,"
	        + "LaneNo,"
			+ "PlateClass,"
	        + "PlateColor,"
			+ "Sunvisor,"
	        + "SafetyBelt,"
			+ "Calling,"
			+ "NumOfPassenger,"
			+ "Speed,"
			+ "DeviceID,"
			+ "adress,"
			+ "VehicleColorDepth,"
			+ "jingdu,"
			+ "weidu,"
			+ "SJLY"
			+ ") values ("
			+ "#{eid},"
			+ "#{carNumber},"
			+ "#{enterTime},"
			+ "#{enterImg},"
			+ "#{exitImg},"
			+ "${VehicleColor},"
			+ "#{VehicleBrand},"
			+ "#{VehicleModel},"
			+ "#{VehicleClass},"
			+ "#{VehicleStyles},"
			+ "#{InfoKind},"
			+ "#{SourceID},"
			+ "#{LaneNo},"
			+ "#{PlateClass},"
			+ "${PlateColor},"
			+ "#{Sunvisor},"
			+ "#{SafetyBelt},"
			+ "#{Calling},"
			+ "#{NumOfPassenger},"
			+ "#{Speed},"
			+ "#{DeviceID},"
			+ "#{adress},"
			+ "#{VehicleColorDepth},"
			+ "#{jingdu},"
			+ "#{weidu},"
			+ "#{sjly}"
			+ ")")
	void saveRedList(ScEtcpEntity scEtcpEntity);

	
	@Delete("delete from sc_etcptjd where CAR_NUMBER = #{carnum}")
	void deleteByPlate(@Param("carnum") String carnum);

	@Select("SELECT id,mc from sc_kkxx where mc=#{tags}")
	List<ScKkxxEntity> kakouByTags(@Param("tags")String tags);
}
