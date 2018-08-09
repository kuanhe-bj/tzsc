$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scjqcheck/mohucheck',
        datatype: "json",
        colModel: [			
			{ label: '车牌号', name: 'cph',width: 80 },
			{ label: '车主姓名', name: 'czxm', width: 80 },
			{ label: '车辆颜色', name: 'clys', width: 80 },
			{ label: '车辆型号', name: 'clxh', width: 80 },
			{ label: '事故异常指数', name: 'accident', width: 80 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
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
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		scTravelaa: {}
	},
	methods: {
		find:function() {
			vm.reload();
		},
		reload: function (event) {
			vm.showList = true;
			var carNum=$("#carNum").val();
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'plate':carNum},
                page:page
            }).trigger("reloadGrid");
		},
	}
});