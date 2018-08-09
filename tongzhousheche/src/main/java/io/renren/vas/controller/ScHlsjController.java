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
import io.renren.vas.entity.ScHlsjEntity;
import io.renren.vas.service.ScHlsjService;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@RestController
@RequestMapping("/generator/schlsj")
public class ScHlsjController {
	@Autowired
	private ScHlsjService scHlsjService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScHlsjEntity> scHlsjList = scHlsjService.queryList(query);
		int total = scHlsjService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scHlsjList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/find")
	public R find(@RequestParam Map<String, Object> params){
		
        Query query = new Query(params);

		List<ScHlsjEntity> scHlsjList = scHlsjService.find(query);
		
		int total = scHlsjService.total(query);
		
		PageUtils pageUtil = new PageUtils(scHlsjList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{checkinfoid}")
	public R info(@PathVariable("checkinfoid") String checkinfoid){
		ScHlsjEntity scHlsj = scHlsjService.queryObject(checkinfoid);
		
		return R.ok().put("scHlsj", scHlsj);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody ScHlsjEntity scHlsj){
		String uuid = UUID.randomUUID().toString();
		scHlsj.setCheckinfoid(uuid);
		scHlsjService.save(scHlsj);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody ScHlsjEntity scHlsj){
		scHlsjService.update(scHlsj);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] checkinfoids){
		scHlsjService.deleteBatch(checkinfoids);
		
		return R.ok();
	}
	
}
