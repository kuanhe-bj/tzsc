package io.renren.vas.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScBdqclEntity;
import io.renren.vas.service.ScBdqclService;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@RestController
@RequestMapping("/generator/scbdqcl")
public class ScBdqclController {
	@Autowired
	private ScBdqclService scBdqclService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScBdqclEntity> scBdqclList = scBdqclService.queryList(query);
		int total = scBdqclService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scBdqclList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	
	public R info(@PathVariable("id") String id){
		ScBdqclEntity scBdqcl = scBdqclService.queryObject(id);
		
		return R.ok().put("scBdqcl", scBdqcl);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	
	public R save(@RequestBody ScBdqclEntity scBdqcl){
		
		ScBdqclEntity scBdqclEntity = scBdqclService.getId();
		
		if(scBdqclEntity == null) {
			scBdqcl.setId(1);
		} else {
			int id = scBdqclEntity.getId(); 
			scBdqcl.setId(id + 1);
		}
		
		scBdqclService.save(scBdqcl);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	
	public R update(@RequestBody ScBdqclEntity scBdqcl){
		scBdqclService.update(scBdqcl);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	
	public R delete(@RequestBody String[] ids){
		scBdqclService.deleteBatch(ids);
		
		return R.ok();
	}
	//查询
	@RequestMapping("/cx")
	
	public R cx(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScBdqclEntity> scBdqclList = scBdqclService.cx(query);
		int total = scBdqclService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scBdqclList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
}
