//边缘球的数量
var num=0;
//边球姓名
var bq= null;
var link = null;
var link1 = null;
var link2 = null;
var link3 = null;
var otherNode = null;
var otherNode1 = null;
var otherNode2 = null;
var otherNode3 = null;
var dianhua= null;
var wangluo= null;
var a = null;
var sfz3= null;
var sfz5= null;
var cp5= null;
var xm5= null;
var arr = null;
var returnArr= null;
var canvas = document.getElementById('relation');
var stage = new JTopo.Stage(canvas); // 创建一个舞台对象
var scene = new JTopo.Scene(stage); // 创建一个场景对象
var userNode = new JTopo.CircleNode(); // 创建一个圆形节点
var cp2=sessionStorage.getItem("cp2");
var co=sessionStorage.getItem("co");


function back(){
	
	sessionStorage.setItem("cp22", cp2);
	

	 var co=sessionStorage.getItem("co");
	 if(co==1){
		 window.location.href=''+baseURL +'modules/generator/sc_rcgls.html';
	 }else{
		 sessionStorage.setItem("co","1");
		 var sfz42=sessionStorage.getItem("sfz42");
		 window.location.href=''+baseURL +'modules/generator/sc_rcglt.html?sfz3='+sfz42; 
	 }
}
$(function() { 
	
	if(cp2){
		
		vm.query();
	}
	
})
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
var cp3 = data.cp3;
$(function(){
	if(cp3){
		
		vm.query();
	}
})

	function clear(scene){
		if(scene){
			scene.clear();
		}else{
			return;
		}
	}
	function create(){
	    
	    //初始化中心球的值
		cp5 =vm.q.cp;
		sessionStorage.setItem("cp52",cp5);
		if(cp5 == null){
			cp5 = "";
		}
	    userNode.text='车牌号:&-30&0$' +cp5 +'&-30&20';
	    userNode.setBound(560, 320, 170, 170); // 同时设置大小及位置
	    userNode.showSelected = false; // 不显示选中矩形
	    userNode.shadow = "true";
	    userNode.textPosition='Middle_Center';
	    userNode.fontColor = '255,255,255';
	    userNode.font = '15px 微软雅黑';
	    userNode.fillColor='156, 133, 166';
	    userNode.layout = {type: 'circle', radius: 260}	    
	    scene.add(userNode); // 放入到场景中
	}
	var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			cp: cp2 ==null?cp3:cp2
		},
		Sc_rcglsVo:{},
		showList: true,
		title:null,
		roleList:{}
		
	},
	methods: {

		query: function () {
			clear(scene);
			$.ajax({
				url:baseURL + "generator/sc_crglt/find",
				data:"cp="+ $("#cp").val().trim().replace(/\s/g,""),
				dataType:"json",
				type:"post",
				success:function(r){
					bq=r.list;
					num=r.list.length;
					create();
					a = Array(num);
					for(var i=0;i<num;i++){
						a[i] =bq[i].sfz;
					}
					a = a.toString();
					var otherNodes = [];
					for(var i = 0;i < num;i++){
							otherNode = new JTopo.CircleNode(); // 创建一个圆形节点
							otherNode.setBound(200, 50, 180, 180); // 同时设置大小及位置
							otherNode.showSelected = false; // 不显示选中矩形
							sfz5 = bq[i].sfz;
							if(sfz5 == null){
								sfz5 = "";
							}
							otherNode.text = '身份证号：&-25&0$'+sfz5+'&-80&20';//&后代表横纵坐标，$代表换行
							sessionStorage.setItem("sfz3",a);
							otherNode.textPosition='Middle_Center';
							otherNode.fontColor = '255,255,255';
							otherNode.font = '15px 微软雅黑';
							otherNode.fillColor='123, 133, 166';
							otherNodes[i] = otherNode;						
							scene.add(otherNode); // 放入到场景中
						    link = new JTopo.Link(userNode, otherNode); // 增加连线
							scene.add(link);	
							otherNode.click(function() {
								click1(this);
							}); 
				    }
					function click1(otherNode){ 
						var j =0;
						for (var i = 0; i < otherNodes.length; i++) {
							if(otherNodes[i]==otherNode){
								j=i;
							}
						}
						sfz3=sessionStorage.getItem("sfz3");
						sessionStorage.setItem("co","2");
						var c = sfz3.split(",");
						sessionStorage.setItem("sfz32",c[j]);
						window.location.href=''+baseURL +'modules/generator/sc_rcglt.html?sfz3='+c[j];
						}
					for(var i=0;i<num;i++){
						 
						otherNode1 = new JTopo.CircleNode(); // 创建一个圆形节点
						otherNode1.setBound(200, 50, 180, 180); // 同时设置大小及位置
						otherNode1.showSelected = false; // 不显示选中矩形
						xm5 = bq[i].xm;
						if(xm5 == null){
							xm5 = "";
						}
						otherNode1.text = '姓名:&-30&0$' + xm5 +'&-30&20';
						otherNode1.textPosition='Middle_Center';
						otherNode1.fontColor = '255,255,255';
						otherNode1.font = '15px 微软雅黑';
						otherNode1.fillColor='123, 133, 166';
						scene.add(otherNode1); // 放入到场景中
					    link1 = new JTopo.Link(userNode, otherNode1); // 增加连线
						scene.add(link1);
					}
					
					for(var i=0;i<num;i++){
						otherNode2 = new JTopo.CircleNode(); // 创建一个圆形节点
						otherNode2.setBound(200, 50, 180, 180); // 同时设置大小及位置
						otherNode2.showSelected = false; // 不显示选中矩形
						dianhua = bq[i].dianhua;
						if(dianhua == null){
							dianhua = "";
						}
						otherNode2.text ='电话手机:&-30&0$' +dianhua +'&-30&20';							
						otherNode2.textPosition='Middle_Center';
						otherNode2.fontColor = '255,255,255';
						otherNode2.font = '15px 微软雅黑';
						otherNode2.fillColor='123, 133, 166';
						scene.add(otherNode2); // 放入到场景中
					    link2 = new JTopo.Link(userNode, otherNode2); // 增加连线
						scene.add(link2);
					}
					
					for(var i=0;i<num;i++){
						 
						otherNode3 = new JTopo.CircleNode(); // 创建一个圆形节点
						otherNode3.setBound(200, 50, 180, 180); // 同时设置大小及位置
						otherNode3.showSelected = false; // 不显示选中矩形
						wangluo = bq[i].wangluo;
						if(wangluo == null){
							wangluo = "";
						}
						otherNode3.text = '网络身份:&-30&0$' +wangluo +'&-80&20';
						otherNode3.textPosition='Middle_Center';
						otherNode3.fontColor = '255,255,255';
						otherNode3.font = '15px 微软雅黑';
						otherNode3.fillColor='123, 133, 166';
						scene.add(otherNode3); // 放入到场景中
					    link3 = new JTopo.Link(userNode, otherNode3); // 增加连线
						scene.add(link3);
					}
					
					JTopo.layout.layoutNode(scene, userNode, true);	
				 }
			});
			
		}

	}       
});
	