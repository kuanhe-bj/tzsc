$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scetcptjd/list',
        datatype: "json",
        colModel: [	
			{ label: '车牌号码', name: 'carNumber', index: 'CAR_NUMBER', width: 80 },
			{ label: '进入时间', name: 'enterTime', index: 'ENTER_TIME', width: 150 },
			{ label: '出去时间', name: 'exitTime', index: 'EXIT_TIME', width: 150 },
			{ label: '进入照片', name: 'enterImg', index: 'ENTER_IMG', width: 90 
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
			}},
			{ label: '停车场区划', name: 'parkNm', index: 'PARK_NM', width: 90 },
			{ label: '停车场地址', name: 'adress', index: 'ADRESS', width: 220 },
			{ label: '所属商圈', name: 'districtNm', index: 'DISTRICT_NM', width: 80 },
			{ label: '数据来源', name: 'sjly', index: 'SJLY', width: 80 },
			{ label: '行车轨迹', name: 'guiji', width: 80 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        shrinkToFit:false,
        onCellSelect: function(rowid,iCol,cellcontent,e){
            if (iCol =='4'||iCol =='5') {
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
        	// 隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			cph: null,
			startTime:null,
			endTime:null
		},
		showList: true,
		title: null,
		scEtcptjd: {}
	},
	methods: {
		query: function () {
			if (vm.q.cph==null ||vm.q.cph=="" ||vm.q.cph.length>9) {
				alert("车牌号输入有误!");
				return false;
			}
			var start=IsDate(vm.q.startTime);
			if (start==1) {
				alert("时间输入有误!正确格式为yyyy-mm-dd hh:MM:ss");
				return false;
			}
			var end=IsDate(vm.q.endTime);
			if (end==1) {
				alert("时间输入有误!正确格式为yyyy-mm-dd hh:MM:ss");
				return false;
			}
			if ((vm.q.startTime!=null&& vm.q.startTime!="")&&(vm.q.endTime!=null&& vm.q.endTime!="")) {
				compareDate(vm.q.startTime,vm.q.endTime);	
			}
			vm.reload();
		},
		add1(){
			vm.reload();
        },
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'cph': vm.q.cph,'qssj':vm.q.startTime,'jssj':vm.q.endTime},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
function fun_date(aa){
    var date1 = new Date(),
    time1=date1.getFullYear()+"-"+(date1.getMonth()+1)+"-"+date1.getDate();// time1表示当前时间
    var date2 = new Date(date1);
    date2.setDate(date1.getDate()+aa);
    var time2 = date2.getFullYear()+"-"+(date2.getMonth()+1)+"-"+date2.getDate();
}

function infor(cph){
	parent.sayhello("modules/generator/scjdcjbxx.html",cph);
}

// 时间比较（yyyy-MM-dd）
function compareDate(startDate, endDate) {
  var arrStart = startDate.split("-");
  var startTime = new Date(arrStart[0], arrStart[1], arrStart[2]);
  var startTimes = startTime.getTime();
  var arrEnd = endDate.split("-");
  var endTime = new Date(arrEnd[0], arrEnd[1], arrEnd[2]);
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
 
/**
 * 判断输入框中输入的日期格式为yyyy-mm-dd和正确的日期
 */
function IsDate(mystring) {
var reg = /^\d{4}-(0\d|1[0-2])-([0-2]\d|3[01])( ([01]\d|2[0-3])\:[0-5]\d\:[0-5]\d)$/;
var str = mystring;
var arr = reg.exec(str);
if (str=="") return 0;
if (!reg.test(str)&&RegExp.$2<=12&&RegExp.$3<=31){
return 1;
}
return 0;
}
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