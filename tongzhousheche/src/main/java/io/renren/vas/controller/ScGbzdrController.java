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
import io.renren.vas.entity.ScGbzdrEntity;
import io.renren.vas.service.ScGbzdrService;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:33
 */
@RestController
@RequestMapping("/generator/scgbzdr")
public class ScGbzdrController {
	@Autowired
	private ScGbzdrService scGbzdrService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScGbzdrEntity> scGbzdrList = scGbzdrService.queryList(query);
		
		int total = scGbzdrService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scGbzdrList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/find")
	public R find(@RequestParam Map<String, Object> params){
		
        Query query = new Query(params);

		List<ScGbzdrEntity> scGbzdrList = scGbzdrService.find(query);
		
		int total = scGbzdrService.total(query);
		
		PageUtils pageUtil = new PageUtils(scGbzdrList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{drybh}")

	public R info(@PathVariable("drybh") String drybh){
		ScGbzdrEntity scGbzdr = scGbzdrService.queryObject(drybh);
		
		return R.ok().put("scGbzdr", scGbzdr);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")

	public R save(@RequestBody ScGbzdrEntity scGbzdr){
		scGbzdrService.save(scGbzdr);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")

	public R update(@RequestBody ScGbzdrEntity scGbzdr){
		scGbzdrService.update(scGbzdr);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")

	public R delete(@RequestBody String[] drybhs){
		scGbzdrService.deleteBatch(drybhs);
		
		return R.ok();
	}
	
}
