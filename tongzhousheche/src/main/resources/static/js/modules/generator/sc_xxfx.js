var cph=sessionStorage.getItem("cph");
var ss=null;
var ph="/generator/sc_xxfx/find";
var pag=sessionStorage.getItem("pag");
sessionStorage.removeItem("pag");
$(function () {
	if(cph){
		$("#plate").val(cph);
		
		ph+="?plate="+cph;
		sessionStorage.removeItem("cph");
	}
    if (pag!=null&&pag!=""&&pag!="null") {
		
	}else {
		pag=1;
	}
    $("#jqGrid").jqGrid({
        url: baseURL + ph,
        page:pag,
        datatype: "json",
        colModel: [			
			{ label: '车牌号', 
				name: 'plate',
				index: 'plate',
				width: 160, 
				key: true,
				formatter:function(cellvalue, options, rowObject){
					 return '<a class="btn btn-primary"  style="background: #337ab7;color:#fff;width:100px;" onclick="infor(\''+cellvalue+'\')">'+cellvalue+'</a>';
					 }
			},
			{ label: '车主姓名', name: 'owner', width: 160 },
			{ label: '车辆颜色', name: 'color', width: 160 },
			{ label: '车辆品牌', name: 'brand', width: 160 },
			{ label: '车辆型号', name: 'model', width: 160 },
			{ label: '限行分析指数', name: 'limits', width: 160},	
        ],
		viewrecords: true,
        height: 512,
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
            cph:"cph", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "" }); 
        }
    });
});
	var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			
			plate: null
		},
		dtcldzdansVo:{},
		showList: true,
		title:null,
		roleList:{}
		
	},
	methods: {
		query: function () {
			var carNum = $("#plate").val().trim().replace(/\s/g,"");
			carNum = carNum.toUpperCase();
			$("#jqGrid").jqGrid('setGridParam',{
				url: baseURL + '/generator/sc_xxfx/find',
				postData:{'plate': carNum},
				page:1
			}).trigger("reloadGrid");
		},
		reload: function () {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				url: baseURL + '/generator/sc_xxfx/find',
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
		window.location.href=baseURL +'vasMap/views/ziguijitu.html';
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

	