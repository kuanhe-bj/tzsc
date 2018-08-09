package io.renren.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;
import io.renren.service.Sc_alarminfoService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 接收告警信息
 *
 * @author zhangyulong
 * @date 2018/4/27
 */
@Slf4j
@RestController
@RequestMapping("/VIID/notification")
public class NotificationController {

	@Autowired
	private Sc_alarminfoService sc_alarminfoService;

	@RequestMapping("/receive")
	public R receive(@RequestBody String param) {
		log.info("收到告警信息，发送警告！！！！！！！！param:{}", param);
		if (StringUtils.isBlank(param)) {
			return null;
		}
		Date date=new Date();
		Map data = JsonUtil.toMap(param);
		if (data == null || data.isEmpty()) {
			return null;
		}
		Map dispositionNotificationList = (Map) data.get("DispositionNotificationListObject");
		log.info("收到告警信息dispositionNotificationList:{}", dispositionNotificationList);
		List<Map> dispositionNotifications = (List<Map>) dispositionNotificationList.get("DispositionNotificationObject");
		if (dispositionNotifications == null || dispositionNotifications.isEmpty()) {
			return null;
		}
		log.info("收到告警信息dispositionNotifications:{}", dispositionNotifications);
		for (Map map : dispositionNotifications) {
			log.info("dispositionNotification:{}", JsonUtil.objectToJson(map));
			map.get("NotificationID");
			map.get("DispositionID");
			map.get("Title");
			map.get("TriggerTime");
			map.get("CntObjectID");
			map.get("PersonObject");// 人员信息对象。Map类型。因为我们是按照车辆布控，可能为空�
			map.get("MotorVehicleObject");// 该返回结果是机动车对象。Map类型
			Map MotorVehicleObject = (Map) map.get("MotorVehicleObject");
			if (dispositionNotificationList == null || dispositionNotificationList.isEmpty()) {
			
			
			} else {
				ScAlarminfoEntity scAlarminfoEntity = new ScAlarminfoEntity();
				scAlarminfoEntity.setMessage("监控到目标车辆出现");
				scAlarminfoEntity.setTriggertime(date);
				scAlarminfoEntity.setReceiver("admin1");
				scAlarminfoEntity.setDatasource("布控");
				scAlarminfoEntity.setDatasourceid((String)map.get("NotificationID"));
				scAlarminfoEntity.setCarnum((String) MotorVehicleObject.get("PlateNo"));
				sc_alarminfoService.insert(scAlarminfoEntity);
				log.info("收到告警信息scAlarminfoEntity:{}", scAlarminfoEntity);
			}

		}

		return R.ok();

	}

}
