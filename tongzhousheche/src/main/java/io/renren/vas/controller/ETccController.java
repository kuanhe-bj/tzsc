package io.renren.vas.controller;


import java.util.List;
import java.util.Map;

import io.renren.vas.entity.ETccEntity;
import io.renren.vas.service.ETccService;
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




/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-24 11:48:37
 */
@RestController
@RequestMapping("/generator/etcc")
public class ETccController {
	@Autowired
	private ETccService eTccService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("generator:etcc:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ETccEntity> eTccList = eTccService.queryList(query);
		int total = eTccService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(eTccList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{eId}")
	@RequiresPermissions("generator:etcc:info")
	public R info(@PathVariable("eId") String eId){
		ETccEntity eTcc = eTccService.queryObject(eId);
		
		return R.ok().put("eTcc", eTcc);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("generator:etcc:save")
	public R save(@RequestBody ETccEntity eTcc){
		eTccService.save(eTcc);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("generator:etcc:update")
	public R update(@RequestBody ETccEntity eTcc){
		eTccService.update(eTcc);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("generator:etcc:delete")
	public R delete(@RequestBody String[] eIds){
		eTccService.deleteBatch(eIds);
		
		return R.ok();
	}
	
}
