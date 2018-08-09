package io.renren.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.renren.common.utils.R;
import io.renren.service.Sc_crgltService;
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
@RequestMapping("/generator/sc_crglt")
public class Sc_crgltController {

	@Autowired
	private Sc_crgltService sc_crgltService;
	/**
	 * 车人关联图
	 */
	@RequestMapping("/find")
	public R list(String cp){
		log.info("进入到Sc_crgltController", "车人关联图");
		List<Sc_rcglsVo> list = sc_crgltService.queryList(cp);
		log.info("返回结果：{}", list);
		return R.ok().put("list", list);
	}
	
	
}
