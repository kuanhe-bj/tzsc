var cph=sessionStorage.getItem("cph");
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
			vm.reload();
		},
		reload: function () {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				url:baseURL + '/generator/sc_dtcldzdans/find',
				postData:{'plate': vm.q.plate},
				page:page
			}).trigger("reloadGrid");
		}
	}
});

if(cph!=null||cph!=""){
	vm.q.plate=cph;
	url=baseURL + '/generator/sc_dtcldzdans/find?plate='+cph;
}
$(function () {

    $("#jqGrid").jqGrid({
        url: url,
        datatype: "json",
        colModel: [			
			{ label: '车牌号', 
				name: 'plate',
				index: 'plate',
				width: 160, 
				key: true,
				formatter:function(cellvalue, options, rowObject){
					 return '<a class="btn btn-default"  style="background: #337ab7;color:#fff;" onclick="infor(\''+cellvalue+'\')">'+cellvalue+'</a>';
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
			{ label: '频繁超速指数', name: 'overSpeed',hidden: true , width: 100 }
        ],
		viewrecords: true,
        height: 385,
        
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
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
        }
    });
});
	function infor(cph){

		 parent.sayhello("modules/manager/scparkcheck.html",cph);

	}