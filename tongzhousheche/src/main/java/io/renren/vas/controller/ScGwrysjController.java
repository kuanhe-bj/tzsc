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
import io.renren.vas.entity.ScGwrysjEntity;
import io.renren.vas.service.ScGwrysjService;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:33
 */
@RestController
@RequestMapping("/generator/scgwrysj")
public class ScGwrysjController {
	@Autowired
	private ScGwrysjService scGwrysjService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")

	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScGwrysjEntity> scGwrysjList = scGwrysjService.queryList(query);
		int total = scGwrysjService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scGwrysjList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/find")
	public R find(@RequestParam Map<String, Object> params){
		
        Query query = new Query(params);

		List<ScGwrysjEntity> scGwrysjList = scGwrysjService.find(query);
		
		int total = scGwrysjService.total(query);
		
		PageUtils pageUtil = new PageUtils(scGwrysjList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{drybh}")

	public R info(@PathVariable("drybh") String drybh){
		
		ScGwrysjEntity scGwrysj = scGwrysjService.queryObject(drybh);
		
		return R.ok().put("scGwrysj", scGwrysj);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	
	public R save(@RequestBody ScGwrysjEntity scGwrysj){
		
		ScGwrysjEntity scGwrysjEntity = scGwrysjService.getDrybh();
		
		if(scGwrysjEntity.getDrybh() == null || scGwrysjEntity.getDrybh() == "") {
			scGwrysj.setDrybh("Z_10000");
		} else {
			Integer drybh = Integer.parseInt(scGwrysjEntity.getDrybh().split("_")[1]);
			scGwrysj.setDrybh("Z_" + (drybh + 1));
		}
		
		scGwrysjService.save(scGwrysj);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")

	public R update(@RequestBody ScGwrysjEntity scGwrysj){
		scGwrysjService.update(scGwrysj);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	
	public R delete(@RequestBody String[] drybh){
		
		scGwrysjService.delete(drybh[0]);
		
		return R.ok();
	}
	
}
