package io.renren.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import io.renren.vas.entity.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.renren.base.CountConsts;
import io.renren.config.RedisSequenceFactory;
import io.renren.dao.ScETCPDao;
import io.renren.vas.entity.ScKkxxEntity;
import io.renren.vo.ImportPlaceVo;
import io.renren.vo.ScEtcpEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("ETCPService")
public class ETCPService {

    @Autowired
    private ScETCPDao scETCPDao;

    private ReentrantLock lock = new ReentrantLock();

    public List<ScEtcpEntity> etcpList(Map<String, Object> params) {
        List<ScEtcpEntity> scEtcpEntityList = scETCPDao.etcpList(params);

        return scEtcpEntityList;
    }

    public int queryTotal(Map<String, Object> params) {
        return scETCPDao.queryTotals(params);
    }

    public List<ScEtcpEntity> etcpDataList(Map<String, Object> params) {
        List<ScEtcpEntity> scEtcpEntityList = scETCPDao.etcpDataList(params);

        return scEtcpEntityList;
    }

    public List<ImportPlaceVo> importList(Map<String, Object> params) {
        return scETCPDao.importList(params);
    }

    public int total(Map<String, Object> params) {
        return scETCPDao.total(params);
    }

    public List<ScEtcpEntity> moHuList(Map<String, Object> params) {

        List<ScEtcpEntity> scEtcpEntityList = scETCPDao.moHuList(params);

        return scEtcpEntityList;
    }

    public int jiancha(String plateNo, String etime) {

        int co = scETCPDao.jiancha(plateNo, etime);

        return co;

    }

    public List<ScEtcpEntity> yiSuoList(Map<String, Object> params) {
        List<ScEtcpEntity> scEtcpEntityList = scETCPDao.yiSuoList(params);

        return scEtcpEntityList;
    }

    public int yiSuoListTotals(Map<String, Object> params) {
        return scETCPDao.yiSuoListTotals(params);
    }

    public String colorName(String colorNum) {
        return scETCPDao.colorName(colorNum);
    }

    public String brandName(String brandNum) {
        return scETCPDao.brandName(brandNum);
    }

    public void saveETCP(ScEtcpEntity scEtcpEntity) {
        scETCPDao.saveETCP(scEtcpEntity);

    }

    public int importListTotal(Map<String, Object> params) {
        List<ImportPlaceVo> imList = scETCPDao.importListTotal(params);
        if (imList != null) {
            return imList.size();
        }
        return 0;
    }

    public List<ScEtcpEntity> shijian(String cph) {
        return scETCPDao.shijian(cph);
    }

    public List<ScEtcpEntity> rtpCph(String ccc) {
        return scETCPDao.rtpCph(ccc);
    }

    public String moreCphs(String username) {
        return scETCPDao.moreCphs(username);
    }

    public String sTime(String username) {
        return scETCPDao.sTime(username);
    }

    @Cacheable(value = "ScKkxxEntity", keyGenerator = "wiselyKeyGenerator")
    public ScKkxxEntity kakou(String id) {
        return scETCPDao.kakou(id);
    }

    @Autowired
    RedisSequenceFactory redisSequenceFactory;

    /**
     * 统计数据
     * @param i
     */
    public void count(String type) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateStr = sf.format(date);
        dateStr=dateStr.substring(0,15);
        if (CountConsts.HAVE_PLATE_COUNT.equals(type)) {
            redisSequenceFactory.generate(CountConsts.HAVE_PLATE_COUNT + dateStr);
        } else if (CountConsts.NO_PLATE_COUNT.equals(type)) {
            redisSequenceFactory.generate(CountConsts.NO_PLATE_COUNT + dateStr);
        } else if (CountConsts.ALL_COUNT.equals(type)){
            redisSequenceFactory.generate(CountConsts.ALL_COUNT + dateStr);
        }else{
            log.info("不存在【{}】统计类型：",type);
        }
    }

	 public List<ScEtcpEntity> fastList(Map<String, Object> params) {
	        List<ScEtcpEntity> scEtcpEntityList = scETCPDao.fastList(params);

	        return scEtcpEntityList;
	 }

	public List<ScKkxxEntity> kakouByName() {
		
		return scETCPDao.kakouByName();
	}

	public List<ScKkxxEntity> kakouByTags(String tags) {

		return scETCPDao.kakouByTags(tags);
	}
    public List<Coordinate>SearchEtcpMc(){
        return scETCPDao.SearchEtcpMc();
    }
    public List<Coordinate>SearchEtcpAddress(String address){
        return scETCPDao.SearchEtcpAddress(address);
    }
}
