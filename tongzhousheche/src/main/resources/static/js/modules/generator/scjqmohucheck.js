$(function () {

    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scjqcheck/mohucheck',
        datatype: "json",
        colModel: [			
			{ label: '车牌号', name: 'plate', width: 45, key: true, 
				formatter:function(cellvalue, options, rowObject){
				    return "<span onclick='jqchck("+cellvalue+")'>"+cellvalue+"</span>";
		    }},
			{ label: '车主名字', name: 'onwer', width: 75 },
			{ label: '车辆颜色', name: 'color', width: 90 },
			{ label: '车辆品牌', name: 'brand', width: 90 },
			{ label: '车辆型号', name: 'model', width: 90 }
			
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
            cph:"cph", 
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
		q:{
			plate: null,
			color:null,
			type:null,
			brand:null
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
			if ( vm.q.plate.length>10) {
				alert("车牌号输入有误！");
				return false;
			};
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'plate': vm.q.plate,'color':vm.q.color,'model':vm.q.model,'brand':vm.q.brand},
                page:page
            }).trigger("reloadGrid");
		},
	}
});
function jqchck(carNum){
	 parent.sayhello("modules/generator/scjqcheck.html",carNum);
}