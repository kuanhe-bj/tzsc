package io.renren.vas.controller;


import java.util.List;
import java.util.Map;

import io.renren.vas.entity.ScRcglEntity;
import io.renren.vas.service.ScRcglService;
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
 * VIEW
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-28 15:52:43
 */
@RestController
@RequestMapping("/generator/scrcgl")
public class ScRcglController {
	@Autowired
	private ScRcglService scRcglService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("generator:scrcgl:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScRcglEntity> scRcglList = scRcglService.queryList(query);
		int total = scRcglService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scRcglList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{cp}")
	@RequiresPermissions("generator:scrcgl:info")
	public R info(@PathVariable("cp") String cp){
		ScRcglEntity scRcgl = scRcglService.queryObject(cp);
		
		return R.ok().put("scRcgl", scRcgl);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("generator:scrcgl:save")
	public R save(@RequestBody ScRcglEntity scRcgl){
		scRcglService.save(scRcgl);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("generator:scrcgl:update")
	public R update(@RequestBody ScRcglEntity scRcgl){
		scRcglService.update(scRcgl);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("generator:scrcgl:delete")
	public R delete(@RequestBody String[] cps){
		scRcglService.deleteBatch(cps);
		
		return R.ok();
	}
	
}
