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
import io.renren.service.Sc_clanjglfxService;
import io.renren.vo.DtcldzdansVo;
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
@RequestMapping("/generator/sc_clanjglfx")
public class Sc_clanjglfxController {
	@Autowired
	private Sc_clanjglfxService sc_clanjglfxService;
	
	/**
	 * 车辆案件关联分析
	 */
	@RequestMapping("/find")
	public R checkList(@RequestParam Map<String, Object> params){
		//查询列表数据
		log.info("进入到Sc_clanjglfxController", "车辆案件关联分析");
        Query query = new Query(params);
		List<DtcldzdansVo> list = sc_clanjglfxService.queryList(query);
		int total = sc_clanjglfxService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		log.info("返回结果：{}", pageUtil);
		return R.ok().put("page", pageUtil);
	}
	

	
}
