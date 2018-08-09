package io.renren.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.service.Sc_dtcldzdansService;
import io.renren.vo.DtcldzdansVo;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/generator/sc_dtcldzdans")
public class Sc_dtcldzdansController {
	
	@Autowired
	private Sc_dtcldzdansService dynamicVehicleEctronicFileService;
	
	/**
	 * 动态车辆电子档案
	 */
	 @RequestMapping("/find")
	 public R list(@RequestParam Map<String, Object> params){
		 //查询列表数据
		 log.info("进入到Sc_dtcldzdansController", "动态车辆电子档案");
	     Query query = new Query(params);
	     List<DtcldzdansVo> list = this.dynamicVehicleEctronicFileService.queryList(query);
	     int total = dynamicVehicleEctronicFileService.queryTotal(query);
	     PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
	     log.info("返回结果：{}", pageUtil);
	     return R.ok().put("page", pageUtil);
	}
	 

}
