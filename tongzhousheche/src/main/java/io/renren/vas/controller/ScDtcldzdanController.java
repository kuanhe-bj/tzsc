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
@RequestMapping("/generator/scdtcldzdan")
public class ScDtcldzdanController {
	@Autowired
	private ScDtcldzdanService scDtcldzdanService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("generator:scdtcldzdan:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScDtcldzdanEntity> scDtcldzdanList = scDtcldzdanService.queryList(query);
		
		int total = scDtcldzdanService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scDtcldzdanList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("generator:scdtcldzdan:info")
	public R info(@PathVariable("id") Integer id){
		ScDtcldzdanEntity scDtcldzdan = scDtcldzdanService.queryObject(id);
		
		return R.ok().put("scDtcldzdan", scDtcldzdan);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("generator:scdtcldzdan:save")
	public R save(@RequestBody ScDtcldzdanEntity scDtcldzdan){
		scDtcldzdanService.save(scDtcldzdan);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("generator:scdtcldzdan:update")
	public R update(@RequestBody ScDtcldzdanEntity scDtcldzdan){
		scDtcldzdanService.update(scDtcldzdan);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("generator:scdtcldzdan:delete")
	public R delete(@RequestBody Integer[] ids){
		scDtcldzdanService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
