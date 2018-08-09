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
import io.renren.service.Sc_resultService;
import io.renren.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/generator/sc_result")
public class Sc_resultController {
	
	@Autowired
	private Sc_resultService sc_resultService;
	
	/**
	 * 查看分析结果
	 */
	@RequestMapping("/find")
	public R checkList(@RequestParam Map<String, Object> params){
		//查询列表数据
		log.info("进入到Sc_resultController", "查看分析结果");
        Query query = new Query(params);
		List<ResultVo> list = sc_resultService.queryList(query);
		int total = sc_resultService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		log.info("返回结果：{}", pageUtil);
		return R.ok().put("page", pageUtil);
	}
}
