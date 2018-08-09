package io.renren.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.vo.VehicleRecordVo;

/**
 * 
 * @author 张强
 *
 */


@RestController
@RequestMapping("/keyarea")
public class KeyAreaCheckController {
	
	
	public List<VehicleRecordVo> keyAreaCheck(Map<String, Object> map) {
		 List<VehicleRecordVo>  list = new ArrayList<>();
	     return list;
	}
}
