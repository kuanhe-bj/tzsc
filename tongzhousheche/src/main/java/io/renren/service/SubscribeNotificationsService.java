package io.renren.service;

import com.tceasy.common.utils.json.JsonUtil;
import io.renren.base.CountConsts;
import io.renren.vas.entity.ScKkxxEntity;
import io.renren.vo.ScEtcpEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangyulong
 * @date 2018/6/22
 */
@Slf4j
@Service
public class SubscribeNotificationsService {

    @Autowired
    private ETCPService scETCPService;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 处理一所传过来的数据
     *
     * @param param
     */
    @Async("asyncServiceExecutor")
    public void processor(@RequestBody String param) {
        log.info("异步处理数据开始");
        Map resultData = JsonUtil.toMap(param);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map subscribeNotificationListObject = (Map) resultData.get("SubscribeNotificationListObject");
        if (subscribeNotificationListObject == null) {
            log.info("get.subscribeNotificationListObject is null");
            return;
        }
        for (int i = 0; i < ((List) subscribeNotificationListObject.get("SubscribeNotificationObject")).size(); i++) {
            Map subscribeNotificationObject = (Map) ((List) subscribeNotificationListObject.get("SubscribeNotificationObject")).get(i);
            if (subscribeNotificationObject == null) {
                log.info("get.SubscribeNotificationObject is null");
                return;
            }
            Map motorVehicleObjects = (Map) subscribeNotificationObject.get("MotorVehicleObjectList");
            if (motorVehicleObjects == null) {
                log.info("get.motorVehicleObjects is null");
                return;
            }
            log.info("motorVehicleObjects:{}", motorVehicleObjects.size());
            for (int j = 0; j < ((List) motorVehicleObjects.get("MotorVehicleObject")).size(); j++) {
                try {
                    Map motorVehicleObject = (Map) ((List) motorVehicleObjects.get("MotorVehicleObject")).get(i);
                    if (motorVehicleObject == null) {
                        log.info("get.motorVehicleObject is null");
                        return;
                    }
                    String plateNo = (String) motorVehicleObject.get("PlateNo");
                    log.info("PlateNo:{}", plateNo);
                    if (StringUtils.isBlank(plateNo) || plateNo.length() < 6) {
                        scETCPService.count(CountConsts.NO_PLATE_COUNT);
                        return;
                    }
                    log.info("PassTime:{}", (String) motorVehicleObject.get("PassTime"));
                    String etime = sdfm.format(sdf.parse((String) motorVehicleObject.get("PassTime")));
                    String value = (String) redisTemplate.opsForValue().get(plateNo + etime);//去redis中取数据
                    log.info("value:{}", value);
                    if (StringUtils.isBlank(value)) {
                        this.save(i, motorVehicleObject, plateNo, etime);
                        redisTemplate.opsForValue().set(plateNo + etime, "true", 300, TimeUnit.SECONDS);
                    }
                    scETCPService.count(CountConsts.HAVE_PLATE_COUNT);
                } catch (Exception e) {
                    log.error("{}", e);
                }
            }
        }
    }

    /**
     * 组装数据并保存到数据库
     *
     * @param i
     * @param motorVehicleObject
     * @param plateNo
     * @param etime
     */
    private void save(int i, Map motorVehicleObject, String plateNo, String etime) {
        ScEtcpEntity scEtcpEntity = new ScEtcpEntity();
        scEtcpEntity.setEnterTime((String) motorVehicleObject.get("PassTime"));
        UUID uuid = UUID.randomUUID();
        scEtcpEntity.setEid(uuid.toString().replace("-", ""));
        scEtcpEntity.setCarNumber(plateNo);
        scEtcpEntity.setEnterTime(etime);
        scEtcpEntity.setInfoKind((Integer) motorVehicleObject.get("InfoKind"));
        scEtcpEntity.setLaneNo((Integer) motorVehicleObject.get("LaneNo"));
        scEtcpEntity.setSourceID((String) motorVehicleObject.get("SourceID"));
        scEtcpEntity.setVehicleBrand((String) motorVehicleObject.get("VehicleBrand"));
        scEtcpEntity.setVehicleClass((String) motorVehicleObject.get("VehicleClass"));
        scEtcpEntity.setVehicleModel((String) motorVehicleObject.get("VehicleModel"));
        scEtcpEntity.setVehicleColor((String) motorVehicleObject.get("VehicleColor"));
        scEtcpEntity.setVehicleStyles((String) motorVehicleObject.get("VehicleStyles"));
        Map subimage = (Map) motorVehicleObject.get("SubImageList");
        if (subimage == null) {
            log.info("get.SubImageList is null ");
            return;
        }
        for (int k = 0; k < ((List) subimage.get("SubImageInfoObject")).size(); k++) {
            Map SubimageObject = (Map) ((List) subimage.get("SubImageInfoObject")).get(i);
            if (k == 0) {
                scEtcpEntity.setEnterImg((String) SubimageObject.get("StoragePath"));
                log.info("照片存储111:{}", (String) SubimageObject.get("StoragePath"));
            } else {
                scEtcpEntity.setExitImg((String) SubimageObject.get("StoragePath"));
                log.info("照片存储222:{}", (String) SubimageObject.get("StoragePath"));
            }
        }
        scEtcpEntity.setEnterImg((String) motorVehicleObject.get("StorageUrl1"));
        scEtcpEntity.setExitImg((String) motorVehicleObject.get("StorageUrl2"));
        scEtcpEntity.setCalling((Integer) motorVehicleObject.get("Calling"));
        scEtcpEntity.setDeviceID((String) motorVehicleObject.get("DeviceID"));
        if (motorVehicleObject.get("DeviceID") != null && motorVehicleObject.get("DeviceID") != "") {
            String id = (String) motorVehicleObject.get("DeviceID");
            ScKkxxEntity scKkxxEntity = scETCPService.kakou(id);
            if (scKkxxEntity != null) {
                log.info("PlateNo:{}", plateNo);
                scEtcpEntity.setJingdu(scKkxxEntity.getJd());
                scEtcpEntity.setWeidu(scKkxxEntity.getWd());
                scEtcpEntity.setAdress(scKkxxEntity.getMc());
                log.info("经纬度成功存储:{}", scEtcpEntity.getJingdu());
            }
        }
        scEtcpEntity.setSafetyBelt((Integer) motorVehicleObject.get("SafetyBelt"));
        scEtcpEntity.setSunvisor((Integer) motorVehicleObject.get("Sunvisor"));
        if (motorVehicleObject.get("PlateColor") != null && motorVehicleObject.get("PlateColor") != "") {
            scEtcpEntity.setPlateColor((String) motorVehicleObject.get("PlateColor"));
        }
        scEtcpEntity.setPlateClass((String) motorVehicleObject.get("PlateClass"));
        scEtcpEntity.setNumOfPassenger((Integer) motorVehicleObject.get("NumOfPassenger"));
        if (motorVehicleObject.get("VehicleColorDepth") != null && motorVehicleObject.get("VehicleColorDepth") != "") {
            scEtcpEntity.setVehicleColorDepth(Integer.parseInt((String) motorVehicleObject.get("VehicleColorDepth")));
        }
        scEtcpEntity.setSpeed((Integer) motorVehicleObject.get("Speed"));
        scEtcpEntity.setSjly("k");
        scEtcpEntity.setDirection(Integer.parseInt((String) motorVehicleObject.get("Direction")));
        scETCPService.saveETCP(scEtcpEntity);
    }
}
