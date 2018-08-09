package io.renren.vas.controller;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScRedlistEntity;
import io.renren.vas.service.ScRedlistService;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:39:58
 */
@RestController
@RequestMapping("/generator/scredlist")
public class ScRedlistController {
	@Autowired
	private ScRedlistService scRedlistService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScRedlistEntity> scRedlistList = scRedlistService.queryList(query);
		
		int total = scRedlistService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scRedlistList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("generator:scredlist:info")
	public R info(@PathVariable("id") String id){
		ScRedlistEntity scRedlist = scRedlistService.queryObject(id);
		
		return R.ok().put("scRedlist", scRedlist);
	}
	
	/**
	 * 保存
	 * @throws ParseException 
	 */
	@RequestMapping("/save")
	public R save(@RequestBody ScRedlistEntity scRedlist) {
		
		scRedlistService.save(scRedlist);
		
		return R.ok().put("code", 0);
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("generator:scredlist:update")
	public R update(@RequestBody ScRedlistEntity scRedlist){
		scRedlistService.update(scRedlist);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] ids){
		scRedlistService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
