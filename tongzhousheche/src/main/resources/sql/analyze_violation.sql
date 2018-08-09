/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  taocy
 * Created: 08/05/2018
 */

# 违章分析的各项参数处理

# 违章信息均记载于sc_violation表中，理论上该表的信息来自于交管
# 如无交管信息，则可以模拟测试数据

# 本SQL脚本的主要目的是，从sc_violation表中统计各项违章指标，
# 然后存入到sc_dtcldzdan表中，作为车辆的该项指标属性


# 违章异常指数,计算过去3个月的违章指数
SET SQL_SAFE_UPDATES = 0;
update sc_dtcldzdan set violation=0;
update sc_dtcldzdan dt set dt.violation=(
select atan(count(1)/10)*200/3.1415926 FROM sc_violation t 
where t.plateno=dt.plate and t.vtime>date_sub(curdate(), interval 3 month));
SET SQL_SAFE_UPDATES = 1;

# 连续违章指数，计算过去1一个月时间的违章指数，
SET SQL_SAFE_UPDATES = 0;
update sc_dtcldzdan set contViolation=0;
update sc_dtcldzdan dt set dt.contViolation=(
select atan(count(1))*200/3.14 FROM sc_violation t 
where t.plateno=dt.plate and t.vtime>date_sub(curdate(), interval 1 month));
SET SQL_SAFE_UPDATES = 1;

# 限行分析指数，计算过去3个月的限行违章
SET SQL_SAFE_UPDATES = 0;
update sc_dtcldzdan set limits=0;
update sc_dtcldzdan dt set dt.limits=(
select atan(count(1)/10)*200/3.14 FROM sc_violation t 
where t.plateno=dt.plate and t.vtime>date_sub(curdate(), interval 3 month) and t.islimitout=1);
SET SQL_SAFE_UPDATES = 1;

# 假牌分析， 如果从交管数据获取到假牌，则设置假牌标志
SET SQL_SAFE_UPDATES = 0;
update sc_dtcldzdan set isFake=0;
update sc_dtcldzdan dt set dt.isfake=1 where
(select count(1) from sc_violation t
where t.plateno=dt.plate and t.isfake=1
)>0;
SET SQL_SAFE_UPDATES = 1;

# 套牌分析，由套牌分析程序来做

# 遮挡号牌，如果从交管数据获取到遮挡号牌违章，则设置遮挡号牌违章标志
SET SQL_SAFE_UPDATES = 0;
update sc_dtcldzdan set obscured=0;
update sc_dtcldzdan dt set dt.obscured=1 where
(select count(1) from sc_violation t
where t.plateno=dt.plate and t.iscovered=1
)>0;
SET SQL_SAFE_UPDATES = 1;

# 遮挡面部，如果从交管数据获取到遮挡面部违章，则设置遮挡面部违章标志
SET SQL_SAFE_UPDATES = 0;
update sc_dtcldzdan set faceCover=0;
update sc_dtcldzdan dt set dt.faceCover=1 where
(select count(1) from sc_violation t
where t.plateno=dt.plate and t.ismask=1
)>0;
SET SQL_SAFE_UPDATES = 1;

# 无牌车辆积分模型，如果从交管数据获取到无牌违章，则设置无牌违章标志
SET SQL_SAFE_UPDATES = 0;
update sc_dtcldzdan set noPlate=0;
update sc_dtcldzdan dt set dt.noPlate=1 where
(select count(1) from sc_violation t
where t.plateno=dt.plate and t.noplate=1
)>0;
SET SQL_SAFE_UPDATES = 1;

# 频繁超速异常分析，从交管数据中获取超速违章，计算超速异常指数
SET SQL_SAFE_UPDATES = 0;
update sc_dtcldzdan set overSpeed=0;
update sc_dtcldzdan dt set dt.overSpeed=(
select atan(count(1)/10)*200/3.14 FROM sc_violation t 
where t.plateno=dt.plate and t.vtime>date_sub(curdate(), interval 3 month) and t.isoverspeed=1);
SET SQL_SAFE_UPDATES = 1;

# 事故频率分析，从交管数据中获取事故违章，计算事故频率指数
SET SQL_SAFE_UPDATES = 0;
update sc_dtcldzdan set accident=0;
update sc_dtcldzdan dt set dt.accident=(
select atan(count(1)/10)*200/3.14 FROM sc_violation t 
where t.plateno=dt.plate and t.vtime>date_sub(curdate(), interval 3 month) and t.isaccident=1);
SET SQL_SAFE_UPDATES = 1;

# 重大事故分析，从交管数据中获取重大事故违章，计算重大事故频率指数
SET SQL_SAFE_UPDATES = 0;
update sc_dtcldzdan set hugeAccident=0;
update sc_dtcldzdan dt set dt.hugeAccident=(
select atan(count(1)/10)*200/3.14 FROM sc_violation t 
where t.plateno=dt.plate and t.vtime>date_sub(curdate(), interval 3 month) and t.isbigaccident=1);
SET SQL_SAFE_UPDATES = 1;