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
import io.renren.service.Sc_jpdpfxService;
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
@RequestMapping("/generator/sc_jpdpfx")
public class Sc_jpdpfxController {
	@Autowired
	private Sc_jpdpfxService sc_jpdpfxService;
	
	/**假牌盗牌分析
	 */
	@RequestMapping("/find")
	public R checkList(@RequestParam Map<String, Object> params){

		log.info("进入到Sc_jpdpfxController", "假牌盗牌分析");
        
		Query query = new Query(params);
        
        List<DtcldzdansVo> list = sc_jpdpfxService.queryList(query);
		
		int total = sc_jpdpfxService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		log.info("返回结果：{}", pageUtil);
		return R.ok().put("page", pageUtil);
	}
	

	
}
