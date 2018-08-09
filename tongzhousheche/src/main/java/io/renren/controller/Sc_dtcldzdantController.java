package io.renren.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.renren.common.utils.R;
import io.renren.service.Sc_dtcldzdantService;
import io.renren.vo.DtcldzdansVo;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
@Slf4j
@RestController
@RequestMapping("/generator/sc_dtcldzdant")
public class Sc_dtcldzdantController {

	@Autowired
	private Sc_dtcldzdantService sc_dtcldzdantService;
	
	/**
	 * 动态车辆电子档案图
	 */
	@RequestMapping("/find")
	public R list(String plate){
		log.info("进入到Sc_dtcldzdantController", "动态车辆电子档案图");
		DtcldzdansVo dtcldzdansVo = sc_dtcldzdantService.queryList(plate);
		log.info("返回结果：{}", dtcldzdansVo);
		return R.ok().put("dtcldzdansVo", dtcldzdansVo);
	}
	
	
}
