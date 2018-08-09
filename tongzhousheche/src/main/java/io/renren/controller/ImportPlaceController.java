package io.renren.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.service.ETCPService;
import io.renren.service.Sc_blacklistService;
import io.renren.vo.ImportPlaceVo;
import lombok.extern.slf4j.Slf4j;

/**
*
*
* @author zq
* @email 256096868@qq.com
* @date 2018-03-27 15:19:34
*/
@Slf4j
@RestController
@RequestMapping("/importplace")
public class ImportPlaceController {
	  
	@Autowired
	private ETCPService scETCPService;

	@Autowired
	private Sc_blacklistService sc_blacklistService;
	
	   /**
     * 重点区域查询
     */
    @RequestMapping("/list")
    public R importList(@RequestBody Map<String, Object> params) {
    	log.info("进入到这里了", "重点区域徘徊");
    	Integer paihuai = Integer.parseInt(params.get("paihuai").toString());
    	Double count = Double.parseDouble(params.get("count").toString());
    	double jingd = Double.parseDouble(params.get("jingd").toString());
    	double weid = Double.parseDouble(params.get("weid").toString());
    	params.put("count", count);
    	params.put("paihuai", paihuai);
    	params.put("jingd", jingd);
    	params.put("weid", weid);
        Query query = new Query(params);
        List<ImportPlaceVo> vehicleRecordVoList = scETCPService.importList(query);
//        int total = scETCPService.importListTotal(query);
//		PageUtils pageUtil = new PageUtils(vehicleRecordVoList, total, query.getLimit(), query.getPage());
		
        return R.ok().put("page", vehicleRecordVoList);
    }
    //向黑名单推送
    @RequestMapping("/tuisong")
    public R tshmd(@RequestBody Map<String, Object> params) {
    	
    	sc_blacklistService.insertQB(params);
		log.info("离开");
        return R.ok();
    }
}
