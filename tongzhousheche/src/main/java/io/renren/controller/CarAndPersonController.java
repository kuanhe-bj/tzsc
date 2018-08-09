package io.renren.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScJdcjbxxEntity;
import io.renren.vas.service.ScJdcjbxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zhangqiang
 * @email256096868@qq.com
 * @date 2018-03-15 15:00:34
 */
@RestController
@RequestMapping("/carperinfor")
public class CarAndPersonController {

    @Autowired
    private ScJdcjbxxService scJdcjbxxService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ScJdcjbxxEntity> scJdcjbxxList = scJdcjbxxService.queryList(query);

        int total = scJdcjbxxService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(scJdcjbxxList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

}
