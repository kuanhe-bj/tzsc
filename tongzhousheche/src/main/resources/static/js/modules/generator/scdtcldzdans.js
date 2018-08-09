var cp6=sessionStorage.getItem("cp6");
var ph='/generator/sc_dtcldzdans/find';
var cph = sessionStorage.getItem("cph");
var pag = sessionStorage.getItem("pag");
sessionStorage.removeItem("pag");
$(function(){
	if(cph){
		$("#plate").val(cph);
		ph += "?plate=" + cph;
		sessionStorage.removeItem("cph");
	}
	if (pag != null && pag != "" && pag != "null") {
		
	}else {
		pag = 1;
	}
	if(cp6){
		$("#plate").val(cp6);
		ph+="?plate="+cp6;
		sessionStorage.removeItem("cp6");
	}	
});


var pv = null;
var province = null;
var plate = null;
$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + ph,
        datatype: "json",
        page: pag,
        postData:{
        	'province': province,
        	'plate' : plate
        },
        colModel: [			
			{ label: '车牌号', 
				name: 'plate',
				index: 'plate',
				width: 160, 
				key: true,
				formatter:function(cellvalue, options, rowObject){
					 
					 return '<a class="btn btn-primary"  style="background: #337ab7;color:#fff;width:100px;" onclick="infor(\''+cellvalue+'\')">'+cellvalue+'</a>';
					 }},
			{ label: '车主姓名', name: 'owner', width: 160 },
			{ label: '车辆颜色', name: 'color', width: 160 },
			{ label: '车辆品牌', name: 'brand', width: 160 },
			{ label: '车辆型号', name: 'model', width: 160 },
			{ label: '出行异常指数', name: 'abnormal', hidden: true ,width: 100 },
			{ label: '违章异常指数', name: 'violation', hidden: true ,width: 100 },
			{ label: '昼伏夜出指数', name: 'nightOut',hidden: true , width: 100 },
			{ label: '高危地区指数', name: 'highrisk',hidden: true , width: 100 },
			{ label: '事故异常指数', name: 'accident',hidden: true , width: 100 },
			{ label: '隐匿车辆指数', name: 'hidden',hidden: true , width: 100 },
			{ label: '假牌盗牌', name: 'isFake',hidden: true , width: 80 },
			{ label: '存疑车辆', name: 'isInDoubt',hidden: true , width: 80 },
			{ label: '是否涉案', name: 'isInvolved',hidden: true , width: 80 },
			{ label: '车主是否重点人', name: 'isSuspects',hidden: true , width: 120 },
			{ label: '是否首次进城', name: 'isFirstIn',hidden: true , width: 100 },
			{ label: '次数异常分析', name: 'times',hidden: true , width: 100 },
			{ label: '限行分析指数', name: 'limit',hidden: true , width: 100 },
			{ label: '综合异常指数', name: 'summary', width: 160 },
			{ label: '车辆有进无出', name: 'onlyEnter',hidden: true , width: 100 },
			{ label: '频繁超速指数', name: 'overSpeed',hidden: true , width: 100 },
			{ label: '查看详细报告',
				name: 'plate', 
				width: 100 ,
				formatter:function(cellvalue, options, rowObject){
					 
					 return '<a class="btn btn-primary"  style="background: #337ab7;color:#fff;" onclick="infor1(\''+cellvalue+'\')">'+'详细报告'+'</a>';
				}	
			
			}
			
        ],
		viewrecords: true,
        height: 530,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 85, 
        autowidth:true,
        shrinkToFit:false,  
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
            plate:"plate", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "" }); 
        },
		loadComplete: function(xhr) { 
	        var re_records = $("#jqGrid").jqGrid('getGridParam', 'records'); //获取数据总条数
            if(re_records==0){
            	alert("没有符合条件的记录！"); 
            }
	    }
    });
    if(tab == '1'){
    	$("#jqGrid").jqGrid('clearGridData');  //清空表格
	}	
});
	var tab = '';
	var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			plate: null
		},
		dtcldzdansVo:{},
		showList: true,
		title:null,
		
		
	},
	methods: {
		query: function () {
			var carNum = $("#plate").val().trim().replace(/\s/g,"");
			carNum = carNum.toUpperCase();	
			$("#jqGrid").jqGrid('setGridParam',{
				url: baseURL + '/generator/sc_dtcldzdans/find',
				postData:{'plate': carNum},
				page:1
			}).trigger("reloadGrid");
		},
		reload: function () {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				url: baseURL + '/generator/sc_dtcldzdans/find',
	            page:page
			}).trigger("reloadGrid");
			
		}

		
	}     
});
	
	function infor(cph){
		var t1 = getBeforeDate(7);
		var t2 = getBeforeDate(0);
		var pag = $("#jqGrid").jqGrid('getGridParam','page');
		sessionStorage.setItem("cph", cph);
		sessionStorage.setItem("carn",$("#plate").val());
		sessionStorage.setItem("pag", pag);
		sessionStorage.setItem("js", t2);
		sessionStorage.setItem("qs", t1);
		window.location.href = baseURL + 'vasMap/views/ziguijitu.html';
	}

	function getBeforeDate(n) {
		var n = n;
		var d = new Date();
		var year = d.getFullYear();
		var mon = d.getMonth() + 1;
		var day = d.getDate();
		if (day <= n) {
			if (mon > 1) {
				mon = mon - 1;
			} else {
				year = year - 1;
				mon = 12;
			}
		}
		d.setDate(d.getDate() - n);
		year = d.getFullYear();
		mon = d.getMonth() + 1;
		day = d.getDate();
		var hour = d.getHours();
		var min = d.getMinutes();
		var sen = d.getSeconds();
		s = year + "-" + (mon < 10 ? ('0' + mon) : mon) + "-"
				+ (day < 10 ? ('0' + day) : day) + " "
				+ (hour < 10 ? ('0' + hour) : hour) + ":"
				+ (min < 10 ? ('0' + min) : min) + ":"
				+ (sen < 10 ? ('0' + sen) : sen);
		return s;
	}
	
	function infor1(plate){
		var searchUrl =encodeURI(baseURL + 'modules/generator/sc_dtcldzdant.html?plate='+plate);

		location.href=searchUrl;
		
	}
	
	//对url参数进行解析
	function GetRequest() {
		var urlstr = decodeURI(decodeURI(location.search)); // 获取url?符后的字符串
		var theRequest = new Object();
		if (urlstr.indexOf("?") != -1) {
			var str = urlstr.substr(1);
			strs = str.split("&");
			for (var i = 0; i < strs.length; i++) {
				theRequest[i] = unescape(strs[i].split("=")[1]);
			}
		}
		return theRequest;
	}
	var param = GetRequest();
	tab = param[1];
	if(tab=='1'){
		plate = param[0];
		$("#plate").val(param[0]);
	}else{
		province = param[0];
		$("#plate").val("");
	}
	
	function trim(str){
		return str.replace(/\s|\xA0/g,"");   
	}

	
	