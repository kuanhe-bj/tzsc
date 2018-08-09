package io.renren.vas.controller;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.service.ScDtcldzdanService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;




/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-22 10:55:46
 */
@RestController
@RequestMapping("/generator/zfye")
public class Sc_zfycController {
	@Autowired
	private ScDtcldzdanService scDtcldzdanService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/zf")
	@RequiresPermissions("generator:scdtcldzdan:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScDtcldzdanEntity> scDtcldzdanList = scDtcldzdanService.all();
		int total = scDtcldzdanService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scDtcldzdanList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	@RequestMapping("/list")
	public R cs(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScDtcldzdanEntity> scDtcldzdanList = scDtcldzdanService.queryList(query);
		int total = scDtcldzdanService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scDtcldzdanList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
}
