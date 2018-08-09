package io.renren.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.service.Sc_scrcService;
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
@Configuration
@EnableScheduling
@Component  
@RestController
@RequestMapping("/generator/sc_scrc")
public class Sc_scrcController {
	@Autowired
	private Sc_scrcService sc_scrcService;
	/**
	 * 首次进城分析
	 */
	@RequestMapping("/find")
	public R checkList(@RequestParam Map<String, Object> params){
		//查询列表数据
		log.info("进入到Sc_scrcController", "首次进城分析");
        Query query = new Query(params);
		List<DtcldzdansVo> list = sc_scrcService.queryList(query);
		int total = sc_scrcService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		log.info("返回结果：{}", pageUtil);
		return R.ok().put("page", pageUtil);
	}
	

	@RequestMapping("/list_dan")
	public R list_dan(){
		//查询列表数据
		List<DtcldzdansVo> list = sc_scrcService.list_dan();
		log.info("首次进城筛选isfrist值为1的list方法执行");
		return R.ok().put("list", list);
	}
	
}


