/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  taocy
 * Created: 27/03/2018
 */

# 从停车信息表里向动态电子档案表里补充车牌信息，如之前有这个车牌就跳过
INSERT IGNORE INTO vas_b.sc_dtcldzdan(plate) 
SELECT distinct(CAR_NUMBER) FROM vas_b.sc_etcptjd 
where PARK_NM='通州区';

# 随机生成动态车辆电子档案表的车主姓名
SET SQL_SAFE_UPDATES = 0;
update vas_b.sc_dtcldzdan set owner=concat(substring('赵钱孙李周吴郑王冯陈诸卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮齐康伍余元卜顾孟平黄和穆萧尹姚邵堪汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董粱杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘干解应宗丁宣贲邓郁单杭洪包诸左石崔吉钮龚',floor(1+190*rand()),1),substring('明国华建文平志伟东海强晓生光林小民永杰军金健一忠洪江福祥中正振勇耀春大宁亮宇兴宝少剑云学仁涛瑞飞鹏安亚泽世汉达卫利胜敏群波成荣新峰刚家龙德庆斌辉良玉俊立浩天宏子松克清长嘉红山贤阳乐锋智青跃元武广思雄锦威启昌铭维义宗英凯鸿森超坚旭政传康继翔栋仲权奇礼楠炜友年震鑫雷兵万星骏伦绍麟雨行才希彦兆贵源有景升惠臣慧开章润高佳虎根远力进泉茂毅富博霖顺信凡豪树和恩向道川彬柏磊敬书鸣芳培全炳基冠晖京欣廷哲保秋君劲轩帆若连勋祖锡吉崇钧田石奕发洲彪钢运伯满庭申湘皓承梓雪孟其潮冰怀鲁裕翰征谦航士尧标洁城寿枫革纯风化逸腾岳银鹤琳显焕来心凤睿勤延凌昊西羽百捷定琦圣佩麒虹如靖日咏会久昕黎桂玮燕可越彤雁孝宪萌颖艺夏桐月瑜沛诚夫声冬奎扬双坤镇楚水铁喜之迪泰方同滨邦先聪朝善非恒晋汝丹为晨乃秀岩辰洋然厚灿卓杨钰兰怡灵淇美琪亦晶舒菁真涵爽雅爱依静棋宜男蔚芝菲露娜珊雯淑曼萍珠诗璇琴素梅玲蕾艳紫珍丽仪梦倩伊茜妍碧芬儿岚婷菊妮媛莲娟一',floor(1+400*rand()),1),substring('明国华建文平志伟东海强晓生光林小民永杰军金健一忠洪江福祥中正振勇耀春大宁亮宇兴宝少剑云学仁涛瑞飞鹏安亚泽世汉达卫利胜敏群波成荣新峰刚家龙德庆斌辉良玉俊立浩天宏子松克清长嘉红山贤阳乐锋智青跃元武广思雄锦威启昌铭维义宗英凯鸿森超坚旭政传康继翔栋仲权奇礼楠炜友年震鑫雷兵万星骏伦绍麟雨行才希彦兆贵源有景升惠臣慧开章润高佳虎根远力进泉茂毅富博霖顺信凡豪树和恩向道川彬柏磊敬书鸣芳培全炳基冠晖京欣廷哲保秋君劲轩帆若连勋祖锡吉崇钧田石奕发洲彪钢运伯满庭申湘皓承梓雪孟其潮冰怀鲁裕翰征谦航士尧标洁城寿枫革纯风化逸腾岳银鹤琳显焕来心凤睿勤延凌昊西羽百捷定琦圣佩麒虹如靖日咏会久昕黎桂玮燕可越彤雁孝宪萌颖艺夏桐月瑜沛诚夫声冬奎扬双坤镇楚水铁喜之迪泰方同滨邦先聪朝善非恒晋汝丹为晨乃秀岩辰洋然厚灿卓杨钰兰怡灵淇美琪亦晶舒菁真涵爽雅爱依静棋宜男蔚芝菲露娜珊雯淑曼萍珠诗璇琴素梅玲蕾艳紫珍丽仪梦倩伊茜妍碧芬儿岚婷菊妮媛莲娟一',floor(1+400*rand()),1));

# 车身颜色
update vas_b.sc_dtcldzdan set color = substr('黑白灰红蓝黄橙棕绿紫青粉',floor(1+12*rand()),1);

# 车辆品牌
update vas_b.sc_dtcldzdan set brand = substr('大众别克宝马本田标致丰田福特日产奥迪现代起亚荣威吉利东风长城雷诺路虎',1+floor(17*rand())*2,2);

# 车辆型号
update vas_b.sc_dtcldzdan set model = substr('载货越野自卸牵引专用客车轿车挂车',1+floor(8*rand())*2,2);

# 出行、违章、昼伏夜出、高危、事故、隐匿等
update vas_b.sc_dtcldzdan set abnormal=truncate(100*rand(),2),violation=truncate(100*rand(),2),
nightOut=truncate(100*rand(),2),highrisk=truncate(100*rand(),2),accident=truncate(100*rand(),2),
hidden=truncate(100*rand(),2),limits=truncate(100*rand(),2),summary=truncate(100*rand(),2),
overSpeed=truncate(100*rand(),2),ContViolation=truncate(100*rand(),2),abTravel=truncate(100*rand(),2),
wander=truncate(100*rand(),2),efence=truncate(100*rand(),2),multiPlate=truncate(100*rand(),2);

# 是否假牌、存疑、涉案等
update vas_b.sc_dtcldzdan set isfake=floor(rand()+0.5),isInDoubt=floor(rand()+0.5),isInvolved=floor(rand()+0.5),
isSuspects=floor(rand()+0.5),isFirstIn=floor(rand()+0.5),times=floor(rand()+0.5),onlyEnter=floor(rand()+0.5);


SET SQL_SAFE_UPDATES = 1;


