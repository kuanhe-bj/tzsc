package io.renren.vas.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScJqEntity;
import io.renren.vas.service.ScJqService;

/**
 *
 *
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@RestController
@RequestMapping("/generator/scjq")
public class ScJqController {
    @Autowired
    private ScJqService scJqService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ScJqEntity> scJqList = scJqService.queryList(query);
        int total = scJqService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(scJqList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }
    
    @RequestMapping("/find")
    public R find(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        

        List<ScJqEntity> scJqList = scJqService.find(query);
        
        int total = scJqService.total(query);

        PageUtils pageUtil = new PageUtils(scJqList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id) {
        ScJqEntity scJq = scJqService.queryObject(id);

        return R.ok().put("scJq", scJq);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ScJqEntity scJq) {
    	String uuid = UUID.randomUUID().toString();
    	scJq.setId(uuid);
        scJqService.save(scJq);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ScJqEntity scJq) {
        scJqService.update(scJq);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids) {
        scJqService.deleteBatch(ids);

        return R.ok();
    }
}
