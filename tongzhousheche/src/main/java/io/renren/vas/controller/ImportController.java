package io.renren.vas.controller;

import io.renren.vas.dao.ExcelDao;
import io.renren.vas.entity.ScBlacklistEntity;
import io.renren.vas.service.IimportService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("")
public class ImportController {

    @Autowired
    private IimportService iimportService;
    @Autowired
    private ExcelDao excelDao;

    //  Excel导入数据到数据库
//    @PostMapping("/importExcel")
    @RequestMapping("/importExcel")
    @ResponseBody
    public String importExcel(@RequestParam("myfile")MultipartFile myFile){
        ModelAndView modelAndView = new ModelAndView();
        Integer nums = iimportService.importExcel(myFile);
        modelAndView.addObject("msg","导入数成功");
        return "导入成功";


    }
}
