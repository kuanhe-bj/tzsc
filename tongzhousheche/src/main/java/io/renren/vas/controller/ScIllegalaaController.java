package io.renren.vas.controller;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScIllegalaaEntity;
import io.renren.vas.service.ScIllegalaaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:39:58
 */
@RestController
@RequestMapping("/generator/scillegalaa")
public class ScIllegalaaController {
	@Autowired
	private ScIllegalaaService scIllegalaaService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("generator:scillegalaa:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScIllegalaaEntity> scIllegalaaList = scIllegalaaService.queryList(query);
		int total = scIllegalaaService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scIllegalaaList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("generator:scillegalaa:info")
	public R info(@PathVariable("id") String id){
		ScIllegalaaEntity scIllegalaa = scIllegalaaService.queryObject(id);
		
		return R.ok().put("scIllegalaa", scIllegalaa);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("generator:scillegalaa:save")
	public R save(@RequestBody ScIllegalaaEntity scIllegalaa){
		scIllegalaaService.save(scIllegalaa);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("generator:scillegalaa:update")
	public R update(@RequestBody ScIllegalaaEntity scIllegalaa){
		scIllegalaaService.update(scIllegalaa);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("generator:scillegalaa:delete")
	public R delete(@RequestBody String[] ids){
		scIllegalaaService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
