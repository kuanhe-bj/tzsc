var dan = null;
var canvas = document.getElementById('canvas');
var stage = new JTopo.Stage(canvas); // 创建一个舞台对象
var scene = new JTopo.Scene(stage); // 创建一个场景对象
var userNode = new JTopo.CircleNode(); // 创建一个节点
var otherNode = null;

function backup() {
	 window.history.back(-1);
	 sessionStorage.setItem("cp6", plate);
}
function createCircle(){
    
    //初始化中心球的值
    userNode.text='车牌号:&-30&0$'+plate+'&-30&20';
    userNode.setBound(540, 310, 180, 180); // 同时设置大小及位置
    userNode.showSelected = false; // 不显示选中矩形
    userNode.shadow = "true";
    userNode.textPosition='Middle_Center';
    userNode.fontColor = '255,255,255';
    userNode.font = '15px 微软雅黑';
    userNode.fillColor='156, 133, 166';
    userNode.layout = {type: 'circle', radius: 240}	    
    scene.add(userNode); // 放入到场景中
}

function clear(scene){
	if(scene){
		scene.clear();
	}else{
		return;
	}
}

function GetRequest() {
	var urlstr = decodeURI(decodeURI(location.search)); // 获取url"符后的字
	var theRequest = new Object();
	if (urlstr.indexOf("?") != -1) {
		var str = urlstr.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}
	return theRequest;
}

var data = GetRequest();
var plate = data.plate;
$(function() { 	
	if(plate){
		vm.query();
	}
})

function create() {

		 new iChart.Bar2D({
			render : 'canvasDiv',
			background_color : '#EEEEEE',
			data : [
				{name : '综合异常指数',value : summary,color:'#4572a7'},
				{name : '出行异常指数',value : abnormal,color:'#4572a7'},
				{name : '违章异常指数',value : violation,color:'#4572a7'},
			    {name : '昼伏夜出指数',value : nightOut,color:'#4572a7'},
				{name : '高危地区指数',value : highrisk,color:'#4572a7'},
				{name : '事故频率分析指数',value : accident,color:'#4572a7'},
				{name : '隐匿车辆',value : hidden,color:'#4572a7'},
				{name : '假牌盗牌',value : isFake,color:'#4572a7'},
			    {name : '存疑车辆',value : isInDoubt,color:'#4572a7'},
				{name : '涉案',value : isInvolved,color:'#4572a7'},
				{name : '车主是否重点人',value : isSuspects,color:'#4572a7'},
				{name : '首次进城',value : isFirstIn,color:'#4572a7'},
				{name : '次数异常',value : times,color:'#4572a7'},
				{name : '限行分析指数',value : limits,color:'#4572a7'},
				{name : '车辆有进无出',value : onlyEnter,color:'#4572a7'},
				{name : '频繁超速分析指数',value : overSpeed,color:'#4572a7'},
				{name : '连续违章指数',value : ContViolation,color:'#4572a7'},
				{name : '异常轨迹指数',value : abTravel,color:'#4572a7'},
				{name : '重点区域徘徊指数',value : wander,color:'#4572a7'},
				{name : '电子围栏高危地区指数',value : efence,color:'#4572a7'},
				{name : '套牌',value : multiPlate,color:'#4572a7'},
				{name : '敏感地区指数',value : sensitive,color:'#4572a7'},
				{name : '盗抢车辆指数',value : robbery,color:'#4572a7'},
				{name : '遮挡号牌',value : obscured,color:'#4572a7'},
				{name : '遮挡面部',value : faceCover,color:'#4572a7'},
				{name : '无牌',value : noPlate,color:'#4572a7'},
				{name : '前科记录',value : records,color:'#4572a7'},
				{name : '涉毒',value : drug,color:'#4572a7'},
				{name : '在逃',value : atLarge,color:'#4572a7'},
				{name : '亲戚等关联人',value : relative,color:'#4572a7'},
				{name : '临晨出现指数',value : morning,color:'#4572a7'},
				{name : '地点异常',value : address,color:'#4572a7'},
				{name : '时间异常',value : timeAbnormal,color:'#4572a7'},
				{name : '长期留京',value : longStay,color:'#4572a7'},
				{name : '重大事故',value : hugeAccident,color:'#4572a7'},
				{name : '犯罪倾向',value : tendency,color:'#4572a7'},
				{name : '黑车指数',value : black,color:'#4572a7'}
			    ],

			title : '动态车辆电子档案图',
			width : 850,
			height : 550,
			coordinate : {
				width : 530,
				height : 450,
				axis : {
					width : [0, 0, 1, 1]
				},
				scale : [{
					position : 'bottom',
					start_scale : 0,
					end_scale : 100,
					scale_space : 10
				}]
			},
			animation : true,
			sub_option : {
				listeners : {
					parseText : function(r, t) {
						return t;
					},
					click:function(r,e,m){
						
						if(r.get('name')=='综合异常指数'){
							window.location.href=''+baseURL +'modules/generator/sc_dtcldzdans.html';
						}else if(r.get('name')=='出行异常指数'){
							window.location.href=''+baseURL +'modules/generator/sc_cxyc.html';
						}else if(r.get('name')=='违章异常指数'){
							window.location.href=''+baseURL +'modules/generator/sc_wzyc.html';
						}else if(r.get('name')=='昼伏夜出指数'){
							window.location.href=''+baseURL +'modules/manager/sc_zfyc.html';
						}else if(r.get('name')=='高危地区指数'){
							window.location.href=''+baseURL +'modules/generator/sc_gwdq.html';
						}else if(r.get('name')=='事故频率分析指数'){
							window.location.href=''+baseURL +'modules/generator/sc_sgyc.html';
						}else if(r.get('name')=='隐匿车辆'){
							window.location.href=''+baseURL +'modules/generator/sc_yncl.html';
						}else if(r.get('name')=='假牌盗牌'){
							window.location.href=''+baseURL +'modules/generator/sc_jpdpfx.html';
						}else if(r.get('name')=='存疑车辆'){
							window.location.href=''+baseURL +'modules/generator/sc_cyclfx.html';
						}else if(r.get('name')=='涉案'){
							window.location.href=''+baseURL +'modules/generator/sc_clanjglfx.html';
						}else if(r.get('name')=='车主是否重点人'){
							window.location.href=''+baseURL +'modules/generator/sc_weizuyinxing.html';
						}else if(r.get('name')=='首次进城'){
							window.location.href=''+baseURL +'modules/generator/sc_scrc.html';
						}else if(r.get('name')=='次数异常'){
							window.location.href=''+baseURL +'modules/generator/sc_csyc.html';
						}else if(r.get('name')=='限行分析指数'){
							window.location.href=''+baseURL +'modules/generator/sc_xxfx.html';
						}else if(r.get('name')=='车辆有进无出'){
							window.location.href=''+baseURL +'modules/generator/sc_clyjwc.html';
						}else if(r.get('name')=='频繁超速分析指数'){
							window.location.href=''+baseURL +'modules/generator/sc_pfcsyc.html';
						}else if(r.get('name')=='连续违章指数'){
							window.location.href=''+baseURL +'modules/generator/sc_lxwz.html';
						}else if(r.get('name')=='异常轨迹指数'){
							window.location.href=''+baseURL +'modules/generator/sc_ycgj.html';
						}else if(r.get('name')=='重点区域徘徊指数'){
							window.location.href=''+baseURL +'modules/generator/sc_zdqyphfx.html';
						}else if(r.get('name')=='电子围栏高危地区指数'){
							window.location.href=''+baseURL +'modules/generator/sc_dzwlgwdq.html';
						}else if(r.get('name')=='套牌'){
							window.location.href=''+baseURL +'modules/generator/sc_tpfx.html';
						}else if(r.get('name')=='盗抢车辆指数'){
							window.location.href=''+baseURL +'modules/generator/sc_dqcljfmxq.html';
						}else if(r.get('name')=='遮挡号牌'){
							window.location.href=''+baseURL +'modules/generator/sc_zdhmjfmx.html';
						}else if(r.get('name')=='遮挡面部'){
							window.location.href=''+baseURL +'modules/generator/sc_zdmbjfmx.html';
						}else if(r.get('name')=='无牌'){
							window.location.href=''+baseURL +'modules/generator/sc_wpcljfmx.html';
						}else if(r.get('name')=='前科记录'){
							window.location.href=''+baseURL +'modules/generator/sc_qkryjfmx.html';
						}else if(r.get('name')=='涉毒'){
							window.location.href=''+baseURL +'modules/generator/sc_sdryjfmx.html';
						}else if(r.get('name')=='在逃'){
							window.location.href=''+baseURL +'modules/generator/sc_ztryjfmx.html';
						}else if(r.get('name')=='亲戚等关联人'){
							window.location.href=''+baseURL +'modules/generator/sc_gxryjfmx.html';
						}else if(r.get('name')=='临晨出现指数'){
							window.location.href=''+baseURL +'modules/generator/sc_lccxjfmx.html';
						}
					}
			       
				}
			},
			legend : {
				enable : false
			}
		}).draw();	
}
	
	var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			plate:plate
		},
		dtcldzdansVo:{},
		showList: true,
		title:null,
		roleList:{}	
	},
	methods: {
		
		query: function () {
			clear(scene);
			$.ajax({
				url:baseURL + "generator/sc_dtcldzdant/find",
				data:"plate="+$("#plate").val().trim().replace(/\s/g,""),
				dataType:"json",
				type:"post",
				success:function(r){
					dan=r.dtcldzdansVo;
					if(dan != null){
						
						abnormal = Math.round(dan.abnormal);
						violation = Math.round(dan.violation);
						nightOut = Math.round(dan.nightOut);
						highrisk = Math.round(dan.highrisk);
						accident = Math.round(dan.accident);
						hidden = Math.round( dan.hidden);
						isFake = Math.round( dan.isFake);
						isInDoubt = Math.round( dan.isInDoubt);
						isInvolved = Math.round( dan.isInvolved);
						isSuspects = Math.round( dan.isSuspects);
						isFirstIn = Math.round( dan.isFirstIn);
						times = Math.round(dan.times);
						limits = Math.round(dan.limits);
						summary = Math.round(dan.summary);
						onlyEnter = Math.round(dan.onlyEnter);
						overSpeed = Math.round(dan.overSpeed);
						ContViolation = Math.round(dan.contViolation);
						abTravel = Math.round(dan.abTravel);
						wander = Math.round(dan.wander);
						efence = Math.round(dan.efence);
						multiPlate = Math.round(dan.multiPlate);
						sensitive = Math.round(dan.sensitive);
						robbery = Math.round(dan.robbery);
						obscured = Math.round(dan.obscured);
						faceCover = Math.round(dan.faceCover);
						noPlate = Math.round(dan.noPlate);
						records = Math.round(dan.records);
						drug = Math.round(dan.drug);
						atLarge = Math.round(dan.atLarge);
						relative = Math.round(dan.relative);
						morning = Math.round(dan.morning);
						address = Math.round(dan.address);
						timeAbnormal = Math.round(dan.timeAbnormal);
						longStay = Math.round(dan.longStay);
						hugeAccident = Math.round(dan.hugeAccident);
						tendency = Math.round(dan.tendency);
						black = Math.round(dan.black);
						plate = dan.plate;
						createCircle();
						
						if(hidden == 1){
							hidden = 100;
						}else{
							hidden = 0;
						}
						if(isFake == 1){
							isFake = 100;
						}else{
							isFake = 0;
						}
						if(isInDoubt == 1){
							isInDoubt = 100;
						}else{
							isInDoubt = 0;
						}
						
						if(isInvolved == 1){
							isInvolved = 100;
						}else{
							isInvolved = 0;
						}
						
						if(isSuspects == 1){
							isSuspects = 100;
						}else{
							isSuspects = 0;
						}
						
						if(isFirstIn == 1){
							isFirstIn = 100;
						}else{
							isFirstIn = 0;
						}
						
						if(times == 1){
							times = 100;
						}else{
							times = 0;
						}
						if(onlyEnter == 1){
							onlyEnter = 100;
						}else{
							onlyEnter = 0;
						}
						if(multiPlate == 1){
							multiPlate = 100;
						}else{
							multiPlate = 0;
						}
						if(obscured == 1){
							obscured = 100;
						}else{
							obscured = 0;
						}
						if(faceCover == 1){
							faceCover = 100;
						}else{
							faceCover = 0;
						}	
						if(noPlate == 1){
							noPlate = 100;
						}else{
							noPlate = 0;
						}
						
						if(records == 1){
							records = 100;
						}else{
							records = 0;
						}
						
						if(drug == 1){
							drug = 100;
						}else{
							drug = 0;
						}
						if(atLarge == 1){
							atLarge = 100;
						}else{
							atLarge = 0;
						}
						if(relative == 1){
							relative = 100;
						}else{
							relative = 0;
						}
						
						
						if(address == 1){
							address = 100;
						}else{
							address = 0;
						}
						if(timeAbnormal == 1){
							timeAbnormal = 100;
						}else{
							timeAbnormal = 0;
						}	
						if(longStay == 1){
							longStay = 100;
						}else{
							longStay = 0;
						}
						
						if(hugeAccident == 1){
							hugeAccident = 100;
						}else{
							hugeAccident = 0;
						}
						
						if(tendency == 1){
							tendency = 100;
						}else{
							tendency = 0;
						}
						create();	
						sfz = dan.sfz;
						if(sfz == null){
							sfz = "";
						}
						
						
						
						otherNode1 = new JTopo.CircleNode(); // 创建一个圆形节点
						otherNode1.setBound(200, 50, 170, 170); // 同时设置大小及位置
						otherNode1.showSelected = false; // 不显示选中矩形
						otherNode1.text = '车主姓名:&-30&0$'+dan.owner+'&-30&20';//&后代表横纵坐标，$代表换行
						otherNode1.textPosition='Middle_Center';
						otherNode1.fontColor = '255,255,255';
						otherNode1.font = '15px 微软雅黑';
						otherNode1.fillColor='123, 133, 166';
						scene.add(otherNode1); // 放入到场景中
						link = new JTopo.Link(userNode, otherNode1); // 增加连线
						scene.add(link);
						
						otherNode2 = new JTopo.CircleNode(); // 创建一个圆形节点
						otherNode2.setBound(200, 50, 170, 170); // 同时设置大小及位置
						otherNode2.showSelected = false; // 不显示选中矩形
						otherNode2.text = "车辆颜色：&-30&0$"+dan.color+'&-30&20';
						otherNode2.textPosition='Middle_Center';
						otherNode2.fontColor = '255,255,255';
						otherNode2.font = '15px 微软雅黑';
						otherNode2.fillColor='123, 133, 166';	
						scene.add(otherNode2); // 放入到场景中
						link = new JTopo.Link(userNode, otherNode2); // 增加连线
						scene.add(link);
						
						otherNode3 = new JTopo.CircleNode(); // 创建一个圆形节点
						otherNode3.setBound(200, 50, 170, 170); // 同时设置大小及位置
						otherNode3.showSelected = false; // 不显示选中矩形
						otherNode3.text = "车辆品牌：&-30&0$"+dan.brand+'&-30&20';
						otherNode3.textPosition='Middle_Center';
						otherNode3.fontColor = '255,255,255';
						otherNode3.font = '15px 微软雅黑';
						otherNode3.fillColor='123, 133, 166';	
						scene.add(otherNode3); // 放入到场景中
						link = new JTopo.Link(userNode, otherNode3); // 增加连线
						//link.strokeColor = JTopo.util.randomColor(); // 线条颜色随机
						scene.add(link);
						
						otherNode4 = new JTopo.CircleNode(); // 创建一个圆形节点
						otherNode4.setBound(200, 50, 170, 170); // 同时设置大小及位置
						otherNode4.showSelected = false; // 不显示选中矩形
						otherNode4.text = "车辆型号：&-30&0$"+dan.model+'&-30&20';
						otherNode4.textPosition='Middle_Center';
						otherNode4.fontColor = '255,255,255';
						otherNode4.font = '15px 微软雅黑';
						otherNode4.fillColor='123, 133, 166';	
						scene.add(otherNode4); // 放入到场景中
						link = new JTopo.Link(userNode, otherNode4); // 增加连线
						scene.add(link);
						
						otherNode5 = new JTopo.CircleNode(); // 创建一个圆形节点
						otherNode5.setBound(200, 50, 170, 170); // 同时设置大小及位置
						otherNode5.showSelected = false; // 不显示选中矩形
						otherNode5.text = "身份证：&-25&0$"+sfz+'&-77&20';
						otherNode5.textPosition='Middle_Center';
						otherNode5.fontColor = '255,255,255';
						otherNode5.font = '15px 微软雅黑';
						otherNode5.fillColor='123, 133, 166';	
						scene.add(otherNode5); // 放入到场景中
						link = new JTopo.Link(userNode, otherNode5); // 增加连线
						scene.add(link);
					}else{
						abnormal = 0;
						violation = 0;
						nightOut = 0;
						highrisk = 0;
						accident = 0;
						hidden = 0;
						isFake = 0;
						isInDoubt = 0;
						isInvolved = 0;
						isSuspects = 0;
						isFirstIn = 0;
						times = 0;
						limits = 0;
						summary = 0;
						onlyEnter = 0;
						overSpeed = 0;
						ContViolation = 0;
						abTravel = 0;
						wander = 0;
						efence = 0;
						multiPlate = 0;
						sensitive = 0;
						robbery = 0;
						obscured = 0;
						faceCover = 0;
						noPlate = 0;
						records = 0;
						drug = 0;
						atLarge = 0;
						relative = 0;
						morning = 0;
						address = 0;
						timeAbnormal = 0;
						longStay = 0;
						hugeAccident = 0;
						tendency = 0;
						black = 0;
						plate = $("#plate").val();
						createCircle();
						create();	
						sfz = dan.sfz;
						if(sfz == null){
							sfz = "";
						}
						
						
						otherNode1 = new JTopo.CircleNode(); // 创建一个圆形节点
						otherNode1.setBound(200, 50, 170, 170); // 同时设置大小及位置
						otherNode1.showSelected = false; // 不显示选中矩形
						otherNode1.text = '车主姓名:&-30&0$'+dan.owner+'&-30&20';//&后代表横纵坐标，$代表换行
						otherNode1.textPosition='Middle_Center';
						otherNode1.fontColor = '255,255,255';
						otherNode1.font = '15px 微软雅黑';
						otherNode1.fillColor='123, 133, 166';
						scene.add(otherNode1); // 放入到场景中
						link = new JTopo.Link(userNode, otherNode1); // 增加连线
						scene.add(link);
						
						otherNode2 = new JTopo.CircleNode(); // 创建一个圆形节点
						otherNode2.setBound(200, 50, 170, 170); // 同时设置大小及位置
						otherNode2.showSelected = false; // 不显示选中矩形
						otherNode2.text = "车辆颜色：&-30&0$"+dan.color+'&-30&20';
						otherNode2.textPosition='Middle_Center';
						otherNode2.fontColor = '255,255,255';
						otherNode2.font = '15px 微软雅黑';
						otherNode2.fillColor='123, 133, 166';	
						scene.add(otherNode2); // 放入到场景中
						link = new JTopo.Link(userNode, otherNode2); // 增加连线
						scene.add(link);
						
						otherNode3 = new JTopo.CircleNode(); // 创建一个圆形节点
						otherNode3.setBound(200, 50, 170, 170); // 同时设置大小及位置
						otherNode3.showSelected = false; // 不显示选中矩形
						otherNode3.text = "车辆品牌：&-30&0$"+dan.brand+'&-30&20';
						otherNode3.textPosition='Middle_Center';
						otherNode3.fontColor = '255,255,255';
						otherNode3.font = '15px 微软雅黑';
						otherNode3.fillColor='123, 133, 166';	
						scene.add(otherNode3); // 放入到场景中
						link = new JTopo.Link(userNode, otherNode3); // 增加连线
						scene.add(link);
						
						otherNode4 = new JTopo.CircleNode(); // 创建一个圆形节点
						otherNode4.setBound(200, 50, 170, 170); // 同时设置大小及位置
						otherNode4.showSelected = false; // 不显示选中矩形
						otherNode4.text = "车辆型号：&-30&0$"+dan.model+'&-30&20';
						otherNode4.textPosition='Middle_Center';
						otherNode4.fontColor = '255,255,255';
						otherNode4.font = '15px 微软雅黑';
						otherNode4.fillColor='123, 133, 166';	
						scene.add(otherNode4); // 放入到场景中
						link = new JTopo.Link(userNode, otherNode4); // 增加连线
						scene.add(link);
						
						otherNode5 = new JTopo.CircleNode(); // 创建一个圆形节点
						otherNode5.setBound(200, 50, 170, 170); // 同时设置大小及位置
						otherNode5.showSelected = false; // 不显示选中矩形
						otherNode5.text = "身份证：&-25&0$"+sfz+'&-77&20';
						otherNode5.textPosition='Middle_Center';
						otherNode5.fontColor = '255,255,255';
						otherNode5.font = '15px 微软雅黑';
						otherNode5.fillColor='123, 133, 166';	
						scene.add(otherNode5); // 放入到场景中
						link = new JTopo.Link(userNode, otherNode5); // 增加连线
						//link.strokeColor = JTopo.util.randomColor(); // 线条颜色随机
						scene.add(link);
					}
					
					JTopo.layout.layoutNode(scene, userNode, true);	
				}
			});				
		}
		
	}       
});
	