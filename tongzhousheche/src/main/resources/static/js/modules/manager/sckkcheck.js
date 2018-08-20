var ss=null;
var ph="" ;
var t1=getDate(0);
var t2=getBeforeDate(0);brand
var qs= sessionStorage.getItem("qs");
var js= sessionStorage.getItem("js");
var pa= sessionStorage.getItem("pag");
$(function () {
	     
	     ss=sessionStorage.getItem("cph");
		   if (ss!=null&&ss!=""&&ss!="null") {
			   pa=sessionStorage.getItem("pag");
					ph+=baseURL +"etcp/yiSuoList?cph="+ss;
					   if (qs!=null&&qs!=""&&qs!="null") {
							ph+="&qssj="+qs;
							   if (js!=null&&js!=""&&js!="null") {
									ph+="&jssj="+js;
									document.getElementById('cph').value=ss;
									
							}	
					}
			}else if (pa!=null&&pa!=""&&pa!="null") {
				pa=sessionStorage.getItem("pag");
			   if (qs!=null&&qs!=""&&qs!="null") {
					ph+=baseURL +"etcp/yiSuoList?qssj="+qs;
					   if (js!=null&&js!=""&&js!="null") {
							ph+="&jssj="+js;
					}	
			}
		   }else {
			pa=1;
		  }
		   sessionStorage.removeItem("pag");
		   sessionStorage.removeItem("cph");
		   sessionStorage.removeItem("qs");
		   sessionStorage.removeItem("js");
		  
    $("#jqGrid").jqGrid({
        url: ph,
        datatype: "json",
		page:pa,
        colModel: [	
			{ label: '车牌号码', name: 'carNumber', index: 'carNumber', width: 90 },
			{ label: '出现时间', name: 'enterTime', index: 'enterTime', width: 150
				/*	,formatter: function(value,row,index){
					if(typeof(value)=="undefined"){
						return '-';
					}else{
						var mydate=value.slice(0,value.indexOf("."));
						return mydate;
					}
			}*/},
			{ label: '近景照片', name: 'exitImg', index: 'exitImg', width: 90 
				,formatter: function(value,row,index){
					if(typeof(value)=="undefined"){
						return '-';
					}else{
						return '<img height="40px" width="60px" src="'+value+'"/>';
					}
			}},
			{ label: '远景照片', name: 'enterImg', index: 'enterImg', width: 90 
				,formatter: function(value,row,index){
					if(typeof(value)=="undefined"){
						return '-';
					}else{
						return '<img height="40px" width="60px" src="'+value+'"/>';
					}
			}},
			{ label: '道路名称', name: 'adress', index: 'adress', width: 190 },
			{ label: '车辆颜色', name: 'vehicleColor', index: 'vehicleColor', width: 90},
			{ label: '车辆品牌', name: 'vehicleBrand', index: 'vehicleBrand', width: 90 },
			{ label: '行车轨迹', name: 'carNumber', width: 100 
				,formatter: function(value,row,index){
					return '<a class="btn btn-default"  style="background: #337ab7;color:#fff;" onclick="infor(\''+value+'\')">行车轨迹</a>';
				}
			},
			{ label: '车牌种类', name: 'plateClass', index: 'PlateClass', width: 110},
			{ label: '车牌颜色', name: 'plateColor', index: 'PlateColor', width: 90},
			{ label: '数据来源', name: 'sjly', index: 'sjly', width: 80 
				,formatter: function(value,row,index){
					if (value=="k") {
						return "微卡口";
					}else {
						return "其他";
					}
			}},
			{ label: '安全带状态', name: 'safetyBelt', index: 'SafetyBelt', width: 90 
				,formatter: function(value,row,index){
					if (value==1) {
						return "有系";
					}else {
						return "未系";
					}
			}},
			{ label: '打电话状态', name: 'calling', index: 'Calling', width: 90 
				,formatter: function(value,row,index){
					if (value==1) {
						return "打电话中";
					}else {
						return "未打电话";
					}
			}},
			{ label: '乘客人数', name: 'numOfPassenger', index: 'NumOfPassenger', width: 80 },
			{ label: '遮阳板状态', name: 'sunvisor', index: 'Sunvisor', width: 90 
				,formatter: function(value,row,index){
					if (value==1) {
						return "放下";
					}else {
						return "收起";
					}
			}},
			{ label: '信息类型', name: 'infoKind', index: 'InfoKind', width: 80 
					,formatter: function(value,row,index){
						if (value==1) {
							return "自动采集";
						}else if(value==2){
							return "人工采集";
						}else if(value==0){
							return "其他";
						}else {
							return "-";
						}	
			}},
			{ label: '车辆类型', name: 'vehicleClass', index: 'vehicleClass', width: 100 },
			{ label: '速度', name: 'speed', index: 'Speed', width: 90 }
//			{ label: '车辆型号', name: 'vehicleModel', index: 'vehicleModel', width: 90 },
//			{ label: '消失时间', name: 'exitTime', index: 'exitTime', width: 150
//				,formatter: function(value,row,index){
//					if(value==""||value==null){
//						return '-';
//					}else{
//						var mydate=value.slice(0,value.indexOf("."));
//						return mydate;
//					}
//			}},
        ],
		viewrecords: true,
        height: 588,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 50, 
        autowidth:true,
        shrinkToFit:false,
        onCellSelect: function(rowid,iCol,cellcontent,e){
//        	此事件在点击表格特定单元格时发生。
//        	rowid 为行ID；iCol 为列索引；
//        	cellcontent 为单元格中内容；
//        	e 点击事件对象。
            if (iCol =='3'||iCol =='4') {
            	openImgDialog(cellcontent);
			}
		},
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	var re_records = $("#jqGrid").jqGrid('getGridParam', 'records'); //获取数据总条数
    	    if (re_records==0) {
				alert("暂无数据!")
			};
        	// 隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "" }); 
        }
    });
    colors();
    brand();
});

function  colors(){
	var url = 'generator/sc_blacklist/color';
	$.ajax({
		type : "POST",
		url : baseURL + url,
		contentType : "application/json",
		success : function(obj) {
			for(var i = 0;i < obj.list.length; i++) {
				
				var opt = $("<option></option>");
				opt.attr("value",obj.list[i].value);
				opt.text(obj.list[i].color);
				$("#color").append(opt);
			}
		}
	});
}

function  brand(){
	var url = 'generator/sc_blacklist/brand';
	$.ajax({
		type : "POST",
		url : baseURL + url,
		contentType : "application/json",
		success : function(obj) {
			for(var i = 0;i < obj.list.length; i++) {
				var opt = $("<option></option>");
				opt.attr("value",obj.list[i].value);
				opt.text(obj.list[i].contact);
				$("#brand").append(opt);
			}
		}
	});
}


function getBeforeDate(n){  
    var n = n;  
    var d = new Date();  
    var year = d.getFullYear();  
    var mon=d.getMonth()+1;  
    var day=d.getDate();  
    if(day <= n){  
            if(mon>1) {  
               mon=mon-1;  
            }  
           else {  
             year = year-1;  
             mon = 12;  
             }  
           }  
          d.setDate(d.getDate()-n);  
          year = d.getFullYear();  
          mon=d.getMonth()+1;  
          day=d.getDate();
       var hour=d.getHours();
       var min=d.getMinutes();
       var sen=d.getSeconds();
     s = year+"-"+(mon<10?('0'+mon):mon)+"-"+(day<10?('0'+day):day)+" "+(hour<10?('0'+hour):hour)+":"+(min<10?('0'+min):min)+":"+(sen<10?('0'+sen):sen);  
     return s;  
  }  

function getDate(n){  
    var n = n;  
    var d = new Date();  
    var year = d.getFullYear();  
    var mon=d.getMonth()+1;  
    var day=d.getDate();  
    if(day <= n){  
            if(mon>1) {  
               mon=mon-1;  
            }  
           else {  
             year = year-1;  
             mon = 12;  
             }  
           }  
          d.setDate(d.getDate()-n);  
          year = d.getFullYear();  
          mon=d.getMonth()+1;  
          day=d.getDate();
     s = year+"-"+(mon<10?('0'+mon):mon)+"-"+(day<10?('0'+day):day)+" 00:00:00";  
     return s;  
  }


var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			cph: ss,
			model:null,
			pclass:null,
			speed:null,
			numOfPassenger:null,
			qclass:null
		},
		showList: true,
		title: null,
		scEtcptjd: {}
	},
	methods: {
		
		query: function () {
			if (vm.q.cph!=null && vm.q.cph.length>9) {
				alert("车牌号输入有误!");
				return false;
			}
			if (vm.q.cph!=null) {
				vm.q.cph = vm.q.cph.replace(/ /g,'');
			}
			var sTime=$("#startTime").val();
			var eTime=$("#endTime").val();
			
			if (sTime==null||sTime=="") {
				alert("请起始选择时间");
				return false;
			}
			if (eTime==null||eTime=="") {
				alert("请结束选择时间");
				return false;
			}
			if (!compareDate(sTime,eTime)) {
				return false;
			};	
		
			vm.reload();
		},
		reload: function (event) {
			vm.showList = true;
			var sTime=$("#startTime").val();
			var eTime=$("#endTime").val();
			var color=$("#color").val();
			var brand=$("#brand").val();
			if (vm.q.brand!=null) {
				vm.q.brand = vm.q.brand.replace(/ /g,'');
			}
			if (vm.q.qclass!=null) {
				vm.q.qclass = vm.q.qclass.replace(/ /g,'');
			}
			if (vm.q.model!=null) {
				vm.q.model = vm.q.model.replace(/ /g,'');
			}
			if (vm.q.pclass!=null) {
				vm.q.pclass = vm.q.pclass.replace(/ /g,'');
			}
			if (vm.q.speed!=null) {
				vm.q.speed = vm.q.speed.replace(/ /g,'');
			}
			if (vm.q.numOfPassenger!=null) {
				vm.q.numOfPassenger = vm.q.numOfPassenger.replace(/ /g,'');
			}
			var cph=$("#cph").val();
			if (cph!=null) {
				cph = cph.replace(/ /g,'');
				cph=cph.toUpperCase();
			}
			var sjly=$("#sjly").val();
			var paltecolor =$("#paltecolor").val();
			var safetyBelt =$("#safetyBelt").val();
			var calling =$("#calling").val();
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			ph="etcp/yiSuoList" ;
			$("#jqGrid").jqGrid('setGridParam',{ 
				url: baseURL + ph,
				postData:{'cph': cph,'numOfPassenger': vm.q.numOfPassenger,'speed': vm.q.speed,'pclass':vm.q.pclass,'qssj':sTime,'jssj':eTime,'color':color,'brand':brand,'qclass':vm.q.qclass,'model':vm.q.model,'sjly':sjly,'paltecolor':paltecolor,'safetyBelt':safetyBelt,'calling':calling},
                page:1
            }).trigger("reloadGrid");
		}
	}
});
function infor(cph){
	 var carNum=$(this).val("data");
//		 开始时间
	 var startTime=$("#startTime").val();
//		 结束时间
	 var eTime=$("#endTime").val();
	 if (!compareDate(startTime,eTime)) {
			return false;
		};
	 var pag = $("#jqGrid").jqGrid('getGridParam','page');
	 sessionStorage.setItem("cph",cph);
	 sessionStorage.setItem("carn",$("#cph").val());
	 sessionStorage.setItem("pag",pag);
	 sessionStorage.setItem("qs",startTime);
	 sessionStorage.setItem("js",eTime);
	 sessionStorage.setItem("kk",1);
	 window.location.href=baseURL +'vasMap/views/ziguijitu.html';
//	 parent.sayhello("vasMap/views/scguijitu.html","2-"+cph,startTime,eTime);

}
// 时间比较（yyyy-MM-dd）
function compareDate(startDate, endDate) {
  var startTime = new Date(startDate);
  var startTimes = startTime.getTime();
  var endTime = new Date(endDate);
  var endTimes = endTime.getTime();
  if (endTimes<startTimes) {
      alert("结束时间不能小于开始时间");
    return false;
  }else if ((endTimes-startTimes)>(90*24*60*60*1000)) {
	  alert("时间段最长不超过90天");
	return false;
}
  return true;
}
 
function openImgDialog(src){
    if(src != ''){
    	src=src.replace('40', '300');
    	src=src.replace('60', '400');
    layer.open({
    	  type: 1,
    	  title :'照片',
    	  skin: 'layui-layer-rim', //加上边框
    	  area: ['412px', '355px'], //宽高
    	  content: src
    	});
    }
}