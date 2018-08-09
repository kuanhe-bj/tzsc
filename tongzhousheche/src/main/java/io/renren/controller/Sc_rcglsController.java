package io.renren.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.service.Sc_rcglsService;
import io.renren.vo.Sc_rcglsVo;
import lombok.extern.slf4j.Slf4j;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
@Slf4j
@RestController
@RequestMapping("/generator/sc_rcgls")
public class Sc_rcglsController {

	@Autowired
	private Sc_rcglsService sc_rcglsService;
	
	/**
	 * 人车关联
	 */
	@RequestMapping("/find")
	public R list(@RequestParam Map<String, Object> params){
		log.info("进入到Sc_rcglsController", "人车关联");
        Query query = new Query(params);
		List<Sc_rcglsVo> list = sc_rcglsService.queryList(query);
		int total = sc_rcglsService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		log.info("返回结果：{}", pageUtil);
		return R.ok().put("page", pageUtil);
	}
	
	
}
