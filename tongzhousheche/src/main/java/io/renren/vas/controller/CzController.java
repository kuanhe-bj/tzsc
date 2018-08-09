package io.renren.vas.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;
import io.renren.vas.entity.ScJxsjEntity;
import io.renren.vas.service.CzService;

@RestController
@RequestMapping("/generator/czz")
public class CzController {
	@Autowired
	private CzService czService;

	@RequestMapping("/cz")
	public R finj(@RequestBody String licensePlate) throws ParseException {
		// 查询列表数据
		
		String[] str=licensePlate.split("=");
		List<ScJxsjEntity> list = czService.cz(str[1]);
		
		return R.ok().put("list", list);

	}
	
}
