package io.renren.vas.controller;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScTravelaaEntity;
import io.renren.vas.service.ScTravelaaService;
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
 * @date 2018-02-26 11:56:26
 */
@RestController
@RequestMapping("/generator/sctravelaa")
public class ScTravelaaController {
	@Autowired
	private ScTravelaaService scTravelaaService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("generator:sctravelaa:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScTravelaaEntity> scTravelaaList = scTravelaaService.queryList(query);
		int total = scTravelaaService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scTravelaaList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("generator:sctravelaa:info")
	public R info(@PathVariable("id") String id){
		ScTravelaaEntity scTravelaa = scTravelaaService.queryObject(id);
		
		return R.ok().put("scTravelaa", scTravelaa);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("generator:sctravelaa:save")
	public R save(@RequestBody ScTravelaaEntity scTravelaa){
		scTravelaaService.save(scTravelaa);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("generator:sctravelaa:update")
	public R update(@RequestBody ScTravelaaEntity scTravelaa){
		scTravelaaService.update(scTravelaa);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("generator:sctravelaa:delete")
	public R delete(@RequestBody String[] ids){
		scTravelaaService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
