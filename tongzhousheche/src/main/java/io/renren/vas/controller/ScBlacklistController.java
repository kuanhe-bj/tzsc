package io.renren.vas.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScBlacklistEntity;
import io.renren.vas.service.ScBlacklistService;
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
@RequestMapping("/generator/scblacklist")
public class ScBlacklistController {
	@Autowired
	private ScBlacklistService scBlacklistService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("generator:scblacklist:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScBlacklistEntity> scBlacklistList = scBlacklistService.queryList(query);
		int total = scBlacklistService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scBlacklistList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("generator:scblacklist:info")
	public R info(@PathVariable("id") String id){
		ScBlacklistEntity scBlacklist = scBlacklistService.queryObject(id);
		
		return R.ok().put("scBlacklist", scBlacklist);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody ScBlacklistEntity scBlacklist){
		
		scBlacklistService.save(scBlacklist);
		
		return R.ok().put("code", 0);
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("generator:scblacklist:update")
	public R update(@RequestBody ScBlacklistEntity scBlacklist){
		scBlacklistService.update(scBlacklist);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] ids){
		
		scBlacklistService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
