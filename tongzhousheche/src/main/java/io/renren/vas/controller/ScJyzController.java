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
import io.renren.vas.entity.ScJyzEntity;
import io.renren.vas.service.ScJyzService;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@RestController
@RequestMapping("/generator/scjyz")
public class ScJyzController {
	@Autowired
	private ScJyzService scJyzService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScJyzEntity> scJyzList = scJyzService.queryList(query);
		int total = scJyzService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scJyzList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 根据车牌号查询
	 */
	@RequestMapping("/queryBycph")
	public R queryBycph(@RequestParam Map<String, Object> params){
		System.out.println("params="+params);
		//查询列表数据
        Query query = new Query(params);

		List<ScJyzEntity> scJyzList = scJyzService.queryBycph(params);
		int total = scJyzService.cphTotal(query);
		
		PageUtils pageUtil = new PageUtils(scJyzList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") String id){
		ScJyzEntity scJyz = scJyzService.queryObject(id);
		
		return R.ok().put("scJyz", scJyz);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody ScJyzEntity scJyz){
	    String uuid = UUID.randomUUID().toString();
		scJyz.setId(uuid);
		scJyzService.save(scJyz);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody ScJyzEntity scJyz){
		scJyzService.update(scJyz);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] ids){
		scJyzService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
