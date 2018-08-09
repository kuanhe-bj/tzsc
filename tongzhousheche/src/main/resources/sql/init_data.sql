/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  taocy
 * Created: 24/03/2018
 */


# 重新生成归属地统计表，从动态电子档案表里取车牌分组，保存省份为简称
truncate table vas_b.sc_gsd;
select @allplate:= count(*) from vas_b.sc_dtcldzdan;
select @rownum:=0;
insert into vas_b.sc_gsd(id,province,percent,carnum)
(select @rownum:=@rownum+1 as id,left(plate,1) as province, count(*)/@allplate*100 as percent,
 count(*) as carnum from vas_b.sc_dtcldzdan as t group by left(plate,1));
# 替换车牌归属地中间的简称为全称
SET SQL_SAFE_UPDATES = 0;
update vas_b.sc_gsd set province='北京' where province='京';
update vas_b.sc_gsd set province='上海' where province='沪';
update vas_b.sc_gsd set province='天津' where province='津';
update vas_b.sc_gsd set province='重庆' where province='渝';
update vas_b.sc_gsd set province='河北' where province='冀';
update vas_b.sc_gsd set province='山西' where province='晋';
update vas_b.sc_gsd set province='内蒙古' where province='蒙';
update vas_b.sc_gsd set province='辽宁' where province='辽';
update vas_b.sc_gsd set province='吉林' where province='吉';
update vas_b.sc_gsd set province='黑龙江' where province='黑';
update vas_b.sc_gsd set province='江苏' where province='苏';
update vas_b.sc_gsd set province='浙江' where province='浙';
update vas_b.sc_gsd set province='安徽' where province='皖';
update vas_b.sc_gsd set province='福建' where province='闽';
update vas_b.sc_gsd set province='江西' where province='赣';
update vas_b.sc_gsd set province='山东' where province='鲁';
update vas_b.sc_gsd set province='河南' where province='豫';
update vas_b.sc_gsd set province='湖北' where province='鄂';
update vas_b.sc_gsd set province='湖南' where province='湘';
update vas_b.sc_gsd set province='广东' where province='粤';
update vas_b.sc_gsd set province='广西' where province='桂';
update vas_b.sc_gsd set province='海南' where province='琼';
update vas_b.sc_gsd set province='四川' where province='川';
update vas_b.sc_gsd set province='贵州' where province='贵';
update vas_b.sc_gsd set province='云南' where province='云';
update vas_b.sc_gsd set province='西藏' where province='藏';
update vas_b.sc_gsd set province='陕西' where province='陕';
update vas_b.sc_gsd set province='甘肃' where province='甘';
update vas_b.sc_gsd set province='青海' where province='青';
update vas_b.sc_gsd set province='宁夏' where province='宁';
update vas_b.sc_gsd set province='新疆' where province='新';
SET SQL_SAFE_UPDATES = 1;