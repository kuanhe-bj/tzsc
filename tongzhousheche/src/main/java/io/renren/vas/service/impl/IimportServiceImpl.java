package io.renren.vas.service.impl;

import com.alibaba.fastjson.JSON;
import io.renren.vas.dao.ExcelDao;
import io.renren.vas.entity.ScBlacklistEntity;
import io.renren.vas.service.IimportService;
import io.renren.vas.util.ExcelTool;
import io.renren.vas.util.ImportException;
import io.renren.vas.util.ResultEnum;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("IimportService")
public class IimportServiceImpl implements IimportService {
    private final static String XLS = "xls";
    public static final String XLSX = "xlsx";

    private final static Logger logger = LoggerFactory.getLogger(IimportServiceImpl.class);

    @Autowired
    private ExcelDao excelDao;

    ExcelTool Excel = new ExcelTool();

    @Override
    public Integer importExcel(MultipartFile myFile) {
        //1.  使用HSSFWorkbook 打开或者创建 “Excel对象”
        //2.  用HSSFWorkbook返回对象或者创建sheet对象
        //3.  用sheet返回行对象，用行对象得到Cell对象
        //4.  对Cell对象进行读写
        List<ScBlacklistEntity> blacklistEntities = new ArrayList<>();
        Workbook workbook = null;
        String fileName = myFile.getOriginalFilename();//  获取文件名
        logger.info("【fileName】{}",fileName);
        if (fileName.endsWith(XLS))
        {
            try {
                workbook = new HSSFWorkbook(myFile.getInputStream());//  2003版本

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(fileName.endsWith(XLSX)){
            try {
                workbook = new XSSFWorkbook(myFile.getInputStream());//  2007版本
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            throw new ImportException(ResultEnum.FILE_IS_NOT_EXCEL); // 文件不是Excel文件
        }
        Sheet sheet = workbook.getSheet("sheet1");
        int rows = sheet.getLastRowNum();
        logger.info("【rows】{}",rows);
        if(rows == 0){
            throw  new ImportException(ResultEnum.DATA_IS_NULL);// 数据为空 请填写数据
        }
        long startTime = System.currentTimeMillis();
        for(int i = 1;i<= rows+1;i++){
            Row row = sheet.getRow(i);
            if(row !=null){
                ScBlacklistEntity scBlacklistEntity = new ScBlacklistEntity();
                //  车牌号
                String carnum = Excel.getCellValue(row.getCell(0));
                if(!StringUtils.isEmpty(carnum)){
                    scBlacklistEntity.setCarnum(carnum);
                }else {
                    scBlacklistEntity.setCarnum(null);
                }
                //  添加时间
                scBlacklistEntity.setCreatetime(new Date());
                //  添加人
                String  createuser = Excel.getCellValue(row.getCell(1));
                if(!StringUtils.isEmpty(createuser)){
                    scBlacklistEntity.setCreateuser(createuser);
                }else {
                    scBlacklistEntity.setCreateuser(null);
                }
                // 添加原因
                String createreason = Excel.getCellValue(row.getCell(2));
                if(!StringUtils.isEmpty(createreason)){
                    scBlacklistEntity.setCreatereason(createreason);
                }else {
                    scBlacklistEntity.setCreatereason(null);
                }
                //System.out.println(JSON.toJSON(scBlacklistEntity));
                //tbagentMapper.insert(scBlacklistEntity);
                blacklistEntities.add(scBlacklistEntity);
                //logger.info("插入数据完成");
            }
        }
        excelDao.batchInsert(blacklistEntities);  //  批量插入 五秒完成
        long endTime = System.currentTimeMillis();
        long totaltime = endTime - startTime;
        logger.info("【消耗时间为】{}",totaltime+"ms");  //  将近两万条数据 3秒解析完成
        logger.info("【第一条数据为】{}",JSON.toJSON(blacklistEntities.get(0)));
        return rows;
    }

}
