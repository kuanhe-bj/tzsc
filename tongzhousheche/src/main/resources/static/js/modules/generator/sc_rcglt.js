//边缘球的数量
var num=0;
var num2=0;
//边球姓名
var bq=[];
var link = null;
var link1 = null;
var otherNode = null;
var otherNode1 = null;
var canvas = document.getElementById('relation');
var stage = new JTopo.Stage(canvas); // 创建一个舞台对象
var scene = new JTopo.Scene(stage); // 创建一个场景对象
var userNode = new JTopo.CircleNode(); // 创建一个节点
var linkNode = null;
var cp4 = null;
var xm4 = null;
var sfz4 = null;
var sfz2=sessionStorage.getItem("sfz2");
var co=sessionStorage.getItem("co");

function back(){
	sessionStorage.setItem("sfz22", sfz2);
	
	var co=sessionStorage.getItem("co");
	 if(co==1){
		 window.location.href=''+baseURL +'modules/generator/sc_rcgls.html';
	 }else{
		 sessionStorage.setItem("co","1");
		 var cp52=sessionStorage.getItem("cp52");
		 window.location.href=''+baseURL +'modules/generator/sc_crglt.html?cp3='+cp52;
	 }
	
	
}


var a = null;
var b = null;
var d = null;
var num1=[];
$(function(){
	if(sfz2){
		
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
var sfz3 = data.sfz3;

$(function() { 	
	if(sfz3){
		
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
		sfz4 = vm.q.sfz;
		sessionStorage.setItem("sfz42",sfz4);
		if(sfz4 == null){
			sfz4 = "";
		}
	    userNode.text= '身份证号：&-25&0$'+sfz4+'&-80&20';//&后代表横纵坐标，$代表换行
	    userNode.setBound(560, 320, 170, 170); // 同时设置大小及位置
	    userNode.showSelected = false; // 不显示选中矩形
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
				sfz: sfz2 ==null?sfz3:sfz2
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
					url:baseURL + "generator/sc_rcglt/find",
					data:"sfz="+$("#sfz").val().trim().replace(/\s/g,""),
					dataType:"json",
					async:false,
					type:"post",
					success:function(r){
						bq=r.list;
						num=r.list.length;
						create();
						a = Array(num);
						
						
						for(var i=0;i<num;i++){
							a[i] =bq[i].cp;
						}
						a = a.toString();
						var otherNodes = [];
						for(var i=0;i<num;i++){
								
								otherNode = new JTopo.CircleNode(); // 创建一个圆形节点
								otherNode.setBound(200, 50, 180, 180); // 同时设置大小及位置
								otherNode.showSelected = false; // 不显示选中矩形
								cp4 = bq[i].cp;
								if(cp4 == null){
									cp4 = "";
								}
								otherNode.text = '车牌号:&-30&0$' +cp4 +'&-30&20';
								sessionStorage.setItem("cp3", a);
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
							 cp3=sessionStorage.getItem("cp3");
							 sessionStorage.setItem("co","2");
							 var c = cp3.split(",");
							 var searchUrl =encodeURI(baseURL +'modules/generator/sc_crglt.html?cp3='+c[j]);
							 sessionStorage.setItem("cp32",c[j]);
							 window.location.href=searchUrl;
							}
						
						

						for(var i=0;i<1;i++){
							 
							otherNode1 = new JTopo.CircleNode(); // 创建一个圆形节点
							otherNode1.setBound(200, 50, 180, 180); // 同时设置大小及位置
							xm4 = bq[i].xm;
							if(xm4 == null){
								xm4 = "";
							}
							otherNode1.text = '姓名:&-30&0$' + xm4 +'&-30&20';
							otherNode1.showSelected = false; // 不显示选中矩形
							otherNode1.textPosition='Middle_Center';
							otherNode1.fontColor = '255,255,255';
							otherNode1.font = '15px 微软雅黑';
							otherNode1.fillColor='123, 133, 166';
							scene.add(otherNode1); // 放入到场景中
						    link1 = new JTopo.Link(userNode, otherNode1); // 增加连线
							scene.add(link1);
						}
						JTopo.layout.layoutNode(scene, userNode, true);
					}
				});
			}

		}       
	});
			       
	   

	
	

	