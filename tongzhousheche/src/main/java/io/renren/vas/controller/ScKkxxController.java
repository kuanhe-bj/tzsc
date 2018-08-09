package io.renren.vas.controller;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScKkxxEntity;
import io.renren.vas.service.ScKkxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@RestController
@RequestMapping(value={"/generator/sckkxx","/interface/Tollgates"})
public class ScKkxxController {
	@Autowired
	private ScKkxxService scKkxxService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")

	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScKkxxEntity> scKkxxList = scKkxxService.queryList(query);
		int total = scKkxxService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scKkxxList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	//查询
	@RequestMapping("/cx")

	public R cx(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScKkxxEntity> scKkxxList = scKkxxService.cx(query);
		
		int total = scKkxxService.total(query);
		
		PageUtils pageUtil = new PageUtils(scKkxxList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")

	public R info(@PathVariable("id") String id){
		ScKkxxEntity scKkxx = scKkxxService.queryObject(id);
		
		return R.ok().put("scKkxx", scKkxx);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")

	public R save(@RequestBody ScKkxxEntity scKkxx){
		
		String id=UUID.randomUUID().toString();
		scKkxx.setId(id);
		scKkxxService.save(scKkxx);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")

	public R update(@RequestBody ScKkxxEntity scKkxx){
		scKkxxService.update(scKkxx);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")

	public R delete(@RequestBody String[] ids){
		scKkxxService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
