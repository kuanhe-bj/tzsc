package io.renren.vas.controller;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScGsdEntity;
import io.renren.vas.service.ScGsdService;
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
@RequestMapping("/generator/scgsd")
public class ScGsdController {
	@Autowired
	private ScGsdService scGsdService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("generator:scgsd:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScGsdEntity> scGsdList = scGsdService.queryList(query);
		int total = scGsdService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scGsdList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("generator:scgsd:info")
	public R info(@PathVariable("id") Integer id){
		ScGsdEntity scGsd = scGsdService.queryObject(id);
		
		return R.ok().put("scGsd", scGsd);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("generator:scgsd:save")
	public R save(@RequestBody ScGsdEntity scGsd){
		scGsdService.save(scGsd);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("generator:scgsd:update")
	public R update(@RequestBody ScGsdEntity scGsd){
		scGsdService.update(scGsd);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("generator:scgsd:delete")
	public R delete(@RequestBody Integer[] ids){
		scGsdService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
