var ph="";
var ss="";
var t1=getDate(0);
var t2=getBeforeDate(0);
var qs= sessionStorage.getItem("qs");
var js= sessionStorage.getItem("js");
var pa= sessionStorage.getItem("pag");
$(function () {
	ss=sessionStorage.getItem("cph");
	   if (ss!=null&&ss!="") {
			ph+=baseURL +"etcp/etcpList?cph="+ss;
			   if (qs!=null&&qs!="") {
					ph+="&qssj="+qs;
					   if (js!=null&&js!="") {
							ph+="&jssj="+js;
							document.getElementById('cph').value=ss;
							
					}	
			}
	   }else if (pa!=null&&pa!=""&&pa!="null") {
		pa=sessionStorage.getItem("pag");
		   if (qs!=null&&qs!="") {
				ph+=baseURL +"etcp/etcpList?qssj="+qs;
				   if (js!=null&&js!="") {
						ph+="&jssj="+js;
				}	
		}
	    }else {
		   pa=1;
	    }
	       sessionStorage.removeItem("pa");
		   sessionStorage.removeItem("cph");
		   sessionStorage.removeItem("qs");
		   sessionStorage.removeItem("js");
	    $("#jqGrid").jqGrid({
	        url: ph , 
	        page:pa,
	        datatype: "json",
	        colModel: [	
				{ label: '车牌号码', name: 'carNumber', index: 'CAR_NUMBER', width: 80 },
				{ label: '进入时间', name: 'enterTime', index: 'ENTER_TIME', width: 150 
				/*	,formatter: function(value,row,index){
 					if(typeof(value)=="undefined"){
 						return '-';
 					}else{
 						var mydate=value.slice(0,value.indexOf("."));
 						return mydate;
 					}
 			}*/},
				{ label: '出去时间', name: 'exitTime', index: 'EXIT_TIME', width: 150 
 				,formatter: function(value,row,index){
 					if(value=="1970-01-01 00:00:00"){
 						return '';
 					}else if(value==null){
 						return '';
 					}else{
 						return value;
 					}
 			}},
			/*	{ label: '进入照片', name: 'enterImg', index: 'ENTER_IMG', width: 90 
					,formatter: function(value,row,index){
						if(typeof(value)=="undefined"){
							return '-';
						}else{
							return '<img height="40px" src="'+value+'"/>';
						}
				}},
				{ label: '出去照片', name: 'exitImg', index: 'EXIT_IMG', width: 90 
					,formatter: function(value,row,index){
						if(typeof(value)=="undefined"){
							return '-';
						}else{
							return '<img height="40px" src="'+value+'"/>';
						}
				}},*/
				{ label: '停车场区划', name: 'parkNm', index: 'PARK_NM', width: 90 },
				{ label: '停车场地址', name: 'adress', index: 'ADRESS', width: 230 },
				{ label: '所属商圈', name: 'districtNm', index: 'DISTRICT_NM', width: 170 },
				{ label: '数据来源', name: 'sjly', index: 'SJLY', width: 80 
					,formatter: function(value,row,index){
						  return 'ETCP';
							
					}},
				{ label: '行车轨迹', name: 'carNumber', width: 100 
					,formatter: function(value,row,index){
						
					  return '<a class="btn btn-default"  style="background: #337ab7;color:#fff;" onclick="infor(\''+value+'\')">行车轨迹</a>';
						
				}}
	        ],
			viewrecords: true,
	        height: 620,
	        rowNum: 10,
			rowList : [10,30,50],
	        rownumbers: true, 
	        rownumWidth: 40, 
	        autowidth:true,
	        shrinkToFit:false,
/*	        onCellSelect: function(rowid,iCol,cellcontent,e){
//	        	此事件在点击表格特定单元格时发生。
//	        	rowid 为行ID；iCol 为列索引；
//	        	cellcontent 为单元格中内容；
//	        	e 点击事件对象。
	            if (iCol =='4'||iCol =='5') {
	            	openImgDialog(cellcontent);
				}
			},*/
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

	    
});
function  ceshikakou(){
	var url = 'etcp/tollgate';
	$.ajax({
		type : "POST",
		url : baseURL + url,
		contentType : "application/json",
		success : function(obj) {
		
		}
	});
}
var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			cph:null,
			park:null,
			address:null
			
		},
		showList: true,
		title: null,
		scEtcptjd: {}
	},
	
	methods: {
		jk: function () {
			var sTime=$("#startTime").val();
			var eTime=$("#endTime").val();
			
			if (this.q.cph!=null) {
				this.q.cph = this.q.cph.replace(/ /g,'');
			}
			if (this.q.cph!=null&& this.q.cph.length>9) {
				alert("车牌号输入有误!");
				return false;
			}
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
		
		   this.reload();
		},
		reload: function () {
			this.showList = true;
			var sTime=$("#startTime").val();
			var eTime=$("#endTime").val();
			var cph=$("#cph").val();
			if (cph!=null) {
				cph = cph.replace(/ /g,'');
				cph=cph.toUpperCase();
			}
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				url: baseURL +"etcp/etcpList" , 
				postData:{'cph': cph,'qssj':sTime,'jssj':eTime,'park':this.q.park,'address':this.q.address},
                page:1
            }).trigger("reloadGrid");
		}
	}
});


function infor(cph){
	 var carNum=$(this).val("data");
//	 开始时间
	 var startTime=$("#startTime").val()
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
//   放大图片
function openImgDialog(src){
    if(src != ''){
    	src=src.replace('40', '300');
    layer.open({
    	  type: 1,
    	  title :'照片',
    	  skin: 'layui-layer-rim', //加上边框
    	  area: ['545px', '420px'], //宽高
    	  content: src
    	});
    }
}
