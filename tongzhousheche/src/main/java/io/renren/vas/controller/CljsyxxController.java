package io.renren.vas.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScJdcjbxxEntity;
import io.renren.vas.service.CljsyxxService;

@RestController
@RequestMapping("/generator/cljsyxx")
public class CljsyxxController {
	@Autowired
	private CljsyxxService cljsyxxService;

	@RequestMapping("/finj")
	public R finj(@RequestParam Map<String, Object> params) throws ParseException {
		// 查询列表数据
		Query query = new Query(params);
		List<ScJdcjbxxEntity> list = cljsyxxService.finj(query);
		PageUtils pageUtil = new PageUtils(list, list.size(), query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);

	}
	
}
