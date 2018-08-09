package io.renren.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;
import io.renren.service.Sc_blacklistService;
import io.renren.service.Sc_dtcldzdanService;
import io.renren.vas.entity.ScBlacklistEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/generator/sc_gwrysj")
public class Sc_gwrysjController {

	@Autowired
	private Sc_dtcldzdanService sc_dtcldzdanService;

	@Autowired
	private Sc_blacklistService sc_blacklistService;

	/**
	 * 高危人员分析
	 */
	@RequestMapping("/check")
	public R check(@RequestBody String sfz) {

		log.info("进入到Sc_gwrysjController check", "高危人员分析");

		List<ScDtcldzdanEntity> list = sc_dtcldzdanService.sfzByList(sfz);

		return R.ok().put("list", list);
	}

	@RequestMapping("/save")
	public R save(@RequestBody String carNum) {

		log.info("-------------------添加功能開始-------------------");

		ScBlacklistEntity scBlacklistEntity = new ScBlacklistEntity();

		scBlacklistEntity.setCarnum(carNum);

		scBlacklistEntity.setCreateuser("admin1");

		sc_blacklistService.save(scBlacklistEntity);

		log.info("-------------------添加功能結束-------------------");

		return R.ok().put("num", 0);
	}

	@RequestMapping("/update")
	public R update(@RequestBody String sfz) {

		log.info("-------------------修改功能開始-------------------");

		List<ScDtcldzdanEntity> list = sc_dtcldzdanService.sfzByList(sfz);

		if (list.size() != 0) {
			ScBlacklistEntity scBlacklistEntity = new ScBlacklistEntity();
			scBlacklistEntity.setCarnum(list.get(0).getPlate());
			scBlacklistEntity.setCreatereason("高危重点人");
			scBlacklistEntity.setCreateuser("admin1");
			sc_dtcldzdanService.sfzUpdate(sfz);
		}

		log.info("-------------------修改功能結束-------------------");

		return R.ok().put("num", 0);
	}
}
