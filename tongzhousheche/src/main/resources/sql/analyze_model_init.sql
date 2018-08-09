/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  taocy
 * Created: 26/03/2018
 */

# 计算（昼伏）夜出模型数值，简单算法，只计算夜出的情况，不计算昼伏及夜归的情况
# 定义晚10点（含）至早5点（不含）之间出停车场的车辆信息，并做规范化
SET SQL_SAFE_UPDATES = 0;
update vas_b.sc_dtcldzdan dt set dt.nightOut=(
select atan(count(1))*200/3.14 FROM vas_b.sc_etcptjd t 
where t.PARK_NM='通州区' and (hour(exit_time)>21 or hour(exit_time)<5) and car_number=dt.plate );
SET SQL_SAFE_UPDATES = 1;

# 计算高危地区指数，简单算法，按照车牌归属地计算高危指数
SET SQL_SAFE_UPDATES = 0;
update vas_b.sc_dtcldzdan dt set dt.highrisk=0 where left(dt.plate,'1')='京';
update vas_b.sc_dtcldzdan dt set dt.highrisk=10 where left(dt.plate,'1')='沪';
update vas_b.sc_dtcldzdan dt set dt.highrisk=20 where left(dt.plate,'1')='津';
update vas_b.sc_dtcldzdan dt set dt.highrisk=30 where left(dt.plate,'1')='渝';
update vas_b.sc_dtcldzdan dt set dt.highrisk=30 where left(dt.plate,'1')='冀';
update vas_b.sc_dtcldzdan dt set dt.highrisk=30 where left(dt.plate,'1')='晋';
update vas_b.sc_dtcldzdan dt set dt.highrisk=40 where left(dt.plate,'1')='蒙';
update vas_b.sc_dtcldzdan dt set dt.highrisk=40 where left(dt.plate,'1')='辽';
update vas_b.sc_dtcldzdan dt set dt.highrisk=40 where left(dt.plate,'1')='吉';
update vas_b.sc_dtcldzdan dt set dt.highrisk=40 where left(dt.plate,'1')='黑';
update vas_b.sc_dtcldzdan dt set dt.highrisk=20 where left(dt.plate,'1')='苏';
update vas_b.sc_dtcldzdan dt set dt.highrisk=20 where left(dt.plate,'1')='浙';
update vas_b.sc_dtcldzdan dt set dt.highrisk=20 where left(dt.plate,'1')='皖';
update vas_b.sc_dtcldzdan dt set dt.highrisk=30 where left(dt.plate,'1')='闽';
update vas_b.sc_dtcldzdan dt set dt.highrisk=30 where left(dt.plate,'1')='赣';
update vas_b.sc_dtcldzdan dt set dt.highrisk=30 where left(dt.plate,'1')='鲁';
update vas_b.sc_dtcldzdan dt set dt.highrisk=40 where left(dt.plate,'1')='豫';
update vas_b.sc_dtcldzdan dt set dt.highrisk=30 where left(dt.plate,'1')='鄂';
update vas_b.sc_dtcldzdan dt set dt.highrisk=30 where left(dt.plate,'1')='湘';
update vas_b.sc_dtcldzdan dt set dt.highrisk=30 where left(dt.plate,'1')='粤';
update vas_b.sc_dtcldzdan dt set dt.highrisk=40 where left(dt.plate,'1')='桂';
update vas_b.sc_dtcldzdan dt set dt.highrisk=30 where left(dt.plate,'1')='琼';
update vas_b.sc_dtcldzdan dt set dt.highrisk=40 where left(dt.plate,'1')='川';
update vas_b.sc_dtcldzdan dt set dt.highrisk=50 where left(dt.plate,'1')='贵';
update vas_b.sc_dtcldzdan dt set dt.highrisk=80 where left(dt.plate,'1')='云';
update vas_b.sc_dtcldzdan dt set dt.highrisk=90 where left(dt.plate,'1')='藏';
update vas_b.sc_dtcldzdan dt set dt.highrisk=80 where left(dt.plate,'1')='陕';
update vas_b.sc_dtcldzdan dt set dt.highrisk=90 where left(dt.plate,'1')='甘';
update vas_b.sc_dtcldzdan dt set dt.highrisk=90 where left(dt.plate,'1')='青';
update vas_b.sc_dtcldzdan dt set dt.highrisk=90 where left(dt.plate,'1')='宁';
update vas_b.sc_dtcldzdan dt set dt.highrisk=90 where left(dt.plate,'1')='新';
SET SQL_SAFE_UPDATES = 1;

# 计算隐匿指数，简单算法，按照最后一次出现时间，与现在时间对比
SET SQL_SAFE_UPDATES = 0;
update vas_b.sc_dtcldzdan dt set dt.hidden=(
select atan(TIMESTAMPDIFF(HOUR,max(t.EXIT_TIME),SYSDATE())/240)*200/3.1415926 
FROM vas_b.sc_etcptjd t 
where t.PARK_NM='通州区' and car_number=dt.plate 
group by t.car_number 
);
SET SQL_SAFE_UPDATES = 1;

# 计算是否首次进城，简单算法，按照最早一次出现时间，小于10天前
SET SQL_SAFE_UPDATES = 0;
update vas_b.sc_dtcldzdan dt set dt.isFirstIn= false;
update vas_b.sc_dtcldzdan dt set dt.isFirstIn= true where left(dt.plate,1)<>'京' and
 (select TIMESTAMPDIFF(DAY,min(t.EXIT_TIME),SYSDATE()) from vas_b.sc_etcptjd t
where t.PARK_NM='通州区' and car_number=dt.plate 
)<10;
SET SQL_SAFE_UPDATES = 1;

# 计算有进无出，现在先按照如果有出停车场为空的情况，则为有进无出
SET SQL_SAFE_UPDATES = 0;
update vas_b.sc_dtcldzdan dt set dt.onlyEnter= false;
update vas_b.sc_dtcldzdan dt,vas_b.sc_etcptjd t set dt.onlyEnter= true where 
t.PARK_NM='通州区' and t.car_number=dt.plate and t.EXIT_TIME is null;
SET SQL_SAFE_UPDATES = 1;

# 更新停车场热力图数据，目前没有时间参数
SET SQL_SAFE_UPDATES = 0;
delete from  vas_b.e_tcc  where sjly=1;
insert into vas_b.e_tcc(e_id,e_jingdu,e_weidu,count,sjly)
(SELECT district_nm_id,jingdu,weidu,count(1),1
 FROM vas_b.sc_etcptjd where park_nm='通州区' group by district_nm_id);
 SET SQL_SAFE_UPDATES = 1;