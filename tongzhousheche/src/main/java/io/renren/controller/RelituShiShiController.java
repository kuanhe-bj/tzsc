package io.renren.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import io.renren.common.validator.ValidatorUtils;
import io.renren.service.RelituShiShiService;
import io.renren.vo.RelituShiShiVo;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/map")
public class RelituShiShiController {
	
	@Autowired
	private RelituShiShiService relituShiShiService;
	
	/**
	 * 查询
	 */
	@RequestMapping("/query")
	public R queryAll(@RequestParam Map<String, Object> params){
		log.info("进入");
		Query query = new Query(params);
		int total = relituShiShiService.count(query);
		List<RelituShiShiVo> list = relituShiShiService.queryAll(query);
		PageUtils pageUtil = new PageUtils(list,total, query.getLimit(), query.getPage());
		log.info("离开");
		return R.ok().put("page", pageUtil);
	}
	
	
	@RequestMapping("/info/{id}")
	public R queryById(@PathVariable("id") String id){
		log.info("进入");
		RelituShiShiVo map = relituShiShiService.queryById(id);
		log.info("离开");
		return R.ok().put("map", map);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody RelituShiShiVo relitu){
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = df.format(d);
		relitu.setId(id);
		relitu.setCreatetime(strDate);
		relitu.setValid(1);
		relituShiShiService.save(relitu);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody RelituShiShiVo relitu){
		System.out.println("relitu="+relitu.getId());
		ValidatorUtils.validateEntity(relitu);
		relituShiShiService.update(relitu);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] ids){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ids.length; i++) {
			sb = sb.append(ids[i]);
		}
		relituShiShiService.deleteBatch(sb.toString());
		
		return R.ok();
	}
	
}
