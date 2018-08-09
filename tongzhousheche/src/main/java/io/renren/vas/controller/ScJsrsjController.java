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
import io.renren.vas.entity.ScJsrsjEntity;
import io.renren.vas.service.ScJsrsjService;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:33
 */
@RestController
@RequestMapping("/generator/scjsrsj")
public class ScJsrsjController {
	@Autowired
	private ScJsrsjService scJsrsjService;
	
	/**
	 * 条件查询
	 */
	@RequestMapping("/queryBy")
	
	public R listBy(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScJsrsjEntity> scJsrsjList = scJsrsjService.queryBy(query);
		int total = scJsrsjService.sfzTotal(query);
		
		PageUtils pageUtil = new PageUtils(scJsrsjList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScJsrsjEntity> scJsrsjList = scJsrsjService.queryList(query);
		int total = scJsrsjService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scJsrsjList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{dataid}")
	
	public R info(@PathVariable("dataid") String dataid){
		ScJsrsjEntity scJsrsj = scJsrsjService.queryObject(dataid);
		
		return R.ok().put("scJsrsj", scJsrsj);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	
	public R save(@RequestBody ScJsrsjEntity scJsrsj){
		scJsrsjService.save(scJsrsj);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")

	public R update(@RequestBody ScJsrsjEntity scJsrsj){
		scJsrsjService.update(scJsrsj);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")

	public R delete(@RequestBody String[] dataids){
		scJsrsjService.deleteBatch(dataids);
		
		return R.ok();
	}
	
}
