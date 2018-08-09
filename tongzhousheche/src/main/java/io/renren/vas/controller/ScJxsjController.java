package io.renren.vas.controller;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScJxsjEntity;
import io.renren.vas.service.ScJxsjService;
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
@RequestMapping("/generator/scjxsj")
public class ScJxsjController {
	@Autowired
	private ScJxsjService scJxsjService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScJxsjEntity> scJxsjList = scJxsjService.queryList(query);
		int total = scJxsjService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scJxsjList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	//查询
	@RequestMapping("/cx")
	public R cx(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScJxsjEntity> scJxsjList = scJxsjService.cx(query);
		int total = scJxsjService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scJxsjList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 信息
	 */
	@RequestMapping("/info/{dataid}")
	public R info(@PathVariable("dataid") String dataid){
		ScJxsjEntity scJxsj = scJxsjService.queryObject(dataid);
		
		return R.ok().put("scJxsj", scJxsj);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody ScJxsjEntity scJxsj){
		scJxsjService.save(scJxsj);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody ScJxsjEntity scJxsj){
		scJxsjService.update(scJxsj);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] dataids){
		scJxsjService.deleteBatch(dataids);
		
		return R.ok();
	}
	
}
