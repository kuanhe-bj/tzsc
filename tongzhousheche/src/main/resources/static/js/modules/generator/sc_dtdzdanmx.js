$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + '/generator/sc_dtdzdanmx/find',
        datatype: "json",
        colModel: [			
			{ label: '姓名', name: 'xm',width: 160, key: true,},
			{ label: '出生日期', name: 'csrq', width: 160, 
				formatter : function(cellvalue, options, rowObject) {
					if(cellvalue != null) {
						cellvalue = cellvalue.slice(0,cellvalue.indexOf(" "));
					} else {
						cellvalue = "";
					}
					return cellvalue;	
				}
			},
			{ label: '年龄', name: 'age', width: 160 },
			{ label: '民族', name: 'mz', width: 160 },
			{ label: '车牌号', name: 'cp', width: 160 },
			{ label: '简要案情', name: 'jyaq', width: 160 },	
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
			search: null
		},
		GwrysjVo:{},
		showList: true,
		title:null,
		roleList:{}
		
	},
	methods: {
		query: function () {
			if(vm.q.search == null || vm.q.search == "") {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url:baseURL + '/generator/sc_dtdzdanmx/find',
					page:1
				}).trigger("reloadGrid");
			} else {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url:baseURL + '/generator/sc_dtdzdanmx/query',
					postData:{'search': $.trim(vm.q.search).replace(/\s/g,"")},
					page:1
				}).trigger("reloadGrid");
			}
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
	