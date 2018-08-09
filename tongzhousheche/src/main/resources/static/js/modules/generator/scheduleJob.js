var cph=sessionStorage.getItem("cph");
$(function(){
		if(cph){
			$("#plate").val(cph);
			sessionStorage.removeItem("cph");
		}
		
	})


var cph = null;
$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + '/generator/ScheduleJob/list',
        datatype: "json",
        colModel: [			
			{ label: '任务id', 
				name: 'jobId',
				index: 'jobId',
				width: 160, 
				key: true,
			},
			{ label: 'spring bean名称', name: 'beanName', width: 160 },
			{ label: '方法名', name: 'methodName', width: 160 },
			{ label: '参数', name: 'params', width: 160 },
			{ label: 'corn表达式', name: 'cronExpression', width: 160 },
			{ label: '任务状态', name: 'status', width: 160, 
				formatter:function (cellvalue, options, rowObject) {
					if(cellvalue == 0){
				        return "正常";
				    }else{
				        return "暂停";
				    }
				
				}
			},
			{ label: '备注', name: 'remark', width: 160 },
			{ label: '创建时间', name: 'createTime', width: 160 }
			
        ],
		viewrecords: true,
        height: 385,
        
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
		showList: true,
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reload: function () {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
				$("#jqGrid").jqGrid('setGridParam',{
	                page:page
	            }).trigger("reloadGrid");

		}
	}
	
});
