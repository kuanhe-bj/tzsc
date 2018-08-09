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
import io.renren.service.Sc_dtcldzdanService;
import io.renren.vas.entity.ScDtcldzdanEntity;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@RestController
@RequestMapping("/generator/sc_taopai")
public class Sc_taopaiController {
	@Autowired
	private Sc_dtcldzdanService sc_dtcldzdanService;
	
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScDtcldzdanEntity> scDtcldzdanList = sc_dtcldzdanService.queryList(query);
		
		int total = sc_dtcldzdanService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scDtcldzdanList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
}
