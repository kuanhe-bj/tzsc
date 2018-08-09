package io.renren.vas.controller;


import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScViwEntity;
import io.renren.vas.service.ScViwService;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:39:58
 */
@RestController
@RequestMapping("/generator/scviw")
public class ScViwController {
	@Autowired
	private ScViwService scViwService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	//@RequiresPermissions("generator:scviw:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScViwEntity> scViwList = scViwService.queryList(query);
		int total = scViwService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scViwList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/find")
	//@RequiresPermissions("generator:scviw:list")
	public R find(@RequestParam Map<String, Object> params){
		
        Query query = new Query(params);

		List<ScViwEntity> scViwList = scViwService.find(query);

		int total = scViwService.total(query);
		
		PageUtils pageUtil = new PageUtils(scViwList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	//@RequiresPermissions("generator:scviw:info")
	public R info(@PathVariable("id") String id){
		ScViwEntity scViw = scViwService.queryObject(id);
		
		return R.ok().put("scViw", scViw);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	//@RequiresPermissions("generator:scviw:save")
	public R save(@RequestBody ScViwEntity scViw){
		String uuid = UUID.randomUUID().toString();
		
		scViw.setId(uuid);
		
		scViw.setReadstate("1");
		
		scViw.setPushstate("未读取");
		
		scViwService.save(scViw);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	//@RequiresPermissions("generator:scviw:update")
	public R update(@RequestBody ScViwEntity scViw){
		scViwService.update(scViw);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	//@RequiresPermissions("generator:scviw:delete")
	public R delete(@RequestBody String[] ids){
		scViwService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
