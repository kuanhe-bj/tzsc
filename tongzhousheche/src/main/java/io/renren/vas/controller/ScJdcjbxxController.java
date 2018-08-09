package io.renren.vas.controller;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScJdcjbxxEntity;
import io.renren.vas.service.ScJdcjbxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@RestController
@RequestMapping("/generator/scjdcjbxx")
public class ScJdcjbxxController {
	@Autowired
	private ScJdcjbxxService scJdcjbxxService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")

	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScJdcjbxxEntity> scJdcjbxxList = scJdcjbxxService.queryList(query);
		int total = scJdcjbxxService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scJdcjbxxList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/find")

	public R find(@RequestParam Map<String, Object> params){

		Query query = new Query(params);

		List<ScJdcjbxxEntity> scJdcjbxxList = scJdcjbxxService.find(query);
		
		int total = scJdcjbxxService.total(query);
		
		PageUtils pageUtil = new PageUtils(scJdcjbxxList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{dataid}")
	
	public R info(@PathVariable("dataid") String dataid){
		ScJdcjbxxEntity scJdcjbxx = scJdcjbxxService.queryObject(dataid);
		
		return R.ok().put("scJdcjbxx", scJdcjbxx);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")

	public R save(@RequestBody ScJdcjbxxEntity scJdcjbxx){
		scJdcjbxxService.save(scJdcjbxx);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	
	public R update(@RequestBody ScJdcjbxxEntity scJdcjbxx){
		scJdcjbxxService.update(scJdcjbxx);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")

	public R delete(@RequestBody String[] dataids){
		scJdcjbxxService.deleteBatch(dataids);
		
		return R.ok();
	}
	
}
