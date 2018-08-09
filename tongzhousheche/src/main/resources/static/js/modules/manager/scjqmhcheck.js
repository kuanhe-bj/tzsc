var ph="" ;
var ss=null;
var t1=getDate(0);
var t2=getBeforeDate(0);
var pa= sessionStorage.getItem("pag");
var qs= sessionStorage.getItem("qs");
var js= sessionStorage.getItem("js");
  $(function () {
	     ss=sessionStorage.getItem("cph");
		   if (ss!=null&&ss!=""&&ss!="null") {
			   pa=sessionStorage.getItem("pag");
					ph+=baseURL +"etcp/moHuList?cph="+ss;
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
					ph+=baseURL +"etcp/moHuList?qssj="+qs;
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
	  
	/*  ....
	   ss=sessionStorage.getItem("cph");
	   if (ss!=null&&ss!=""&&ss!="null") {
			ph+=baseURL +"etcp/moHuList?cph="+ss;
			document.getElementById('cph').value=ss;
	   }else if (pa!=null&&pa!=""&&pa!="null") {
		    ph+=baseURL +"etcp/moHuList";
	   }else {
			pa=1;
	   }
	   sessionStorage.removeItem("cph");
	   sessionStorage.removeItem("pag");*/
            $("#jqGrid").jqGrid({
                url: ph,
                page:pa,
                datatype: "json",
                colModel: [	
        			{ label: '车牌号码', name: 'carNumber', index: 'carNumber', width: 90 ,height:50},
        			{ label: '出现时间', name: 'enterTime', index: 'enterTime', width: 150 },
        			{ label: '近景照片', name: 'exitImg', index: 'EXIT_IMG', width: 90 
        				,formatter: function(value,row,index){
        					if(typeof(value)=="undefined"){
        						return '-';
        					}else{
        						return '<img height="40px" width="60px" src="'+value+'"/>';
        					}
        			}},
        			{ label: '远景照片', name: 'enterImg', index: 'ENTER_IMG', width: 90 
        				,formatter: function(value,row,index){
        					if(typeof(value)=="undefined"){
        						return '-';
        					}else{
        						return '<img height="40px" width="60px" src="'+value+'"/>';
        					}
        			}},
        			{ label: '道路名称', name: 'adress', index: 'adress', width: 190 },
        			{ label: '车辆颜色', name: 'vehicleColor', width: 90 },
        			{ label: '车辆品牌', name: 'vehicleBrand', width: 90 },
        			{ label: '行车轨迹', name: 'carNumber', width: 100 
        				,formatter: function(value,row,index){
        					return '<a class="btn btn-default"  style="background: #337ab7;color:#fff;" onclick="infor(\''+value+'\')">行车轨迹</a>';
        					
        			}},
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
//        			{ label: '车辆型号', name: 'vehicleModel', width: 90 },
        		    { label: '车辆类型', name: 'vehicleClass', width: 90 },
        		    { label: '速度(km/h)', name: 'speed', index: 'Speed', width: 90 }
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
//                	此事件在点击表格特定单元格时发生。
//                	rowid 为行ID；iCol 为列索引；
//                	cellcontent 为单元格中内容；
//                	e 点击事件对象。
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
var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			cph: ss,
			model:null,
			brand:null,
			pclass:null,
			speed:null,
			numOfPassenger:null,
			qclass:null
		},
		showList: true,
		title:null
		
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reload: function () {
			vm.showList = true;
			if (vm.q.cph!=null && vm.q.cph.length>10) {
				alert("车牌号输入有误！");
				return false;
			};
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
			if (this.q.cph!=null) {
				this.q.cph = this.q.cph.replace(/ /g,'');
			}
			if (vm.q.brand!=null) {
				vm.q.brand = vm.q.brand.replace(/ /g,'');
			}
			if (vm.q.qclass!=null) {
				vm.q.qclass = vm.q.qclass.replace(/ /g,'');
			}
			if (vm.q.model!=null) {
				vm.q.model = vm.q.model.replace(/ /g,'');
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
			var paltecolor =$("#paltecolor").val();
			var safetyBelt =$("#safetyBelt").val();
			var calling =$("#calling").val();
			var infoKind =$("#infoKind").val();
			var tags =$("#tags").val();
			var color =$("#color").val();
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			ph="etcp/moHuList";
			$("#jqGrid").jqGrid('setGridParam',{ 
				url:baseURL  +ph,
                postData:{'cph': cph,'numOfPassenger': vm.q.numOfPassenger,'tags':tags,'qssj':sTime,'jssj':eTime,'speed': vm.q.speed,'color':color,'model':vm.q.model,'brand':vm.q.brand,'qclass':vm.q.qclass,'infoKind':infoKind,'pclass':vm.q.pclass,'paltecolor':paltecolor,'safetyBelt':safetyBelt,'calling':calling},
                page:1
            }).trigger("reloadGrid");
		},
	}
});

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
function infor(cph){
	 var pag = $("#jqGrid").jqGrid('getGridParam','page');
	 var sTime=$("#startTime").val();
	 var eTime=$("#endTime").val();
	 sessionStorage.setItem("pag",pag);
	 sessionStorage.setItem("cph",cph);
	 sessionStorage.setItem("carn",$("#cph").val());
	 sessionStorage.setItem("qs",sTime);
	 sessionStorage.setItem("js",eTime);
	 sessionStorage.setItem("kk",1);
	 window.location.href=baseURL +'vasMap/views/ziguijitu.html';
}
//时间比较（yyyy-MM-dd）
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