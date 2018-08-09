function GetRequest() {
	var urlstr = decodeURI(decodeURI(location.search)); // 获取url"符后的字
	
	var theRequest = new Object();
	if (urlstr.indexOf("?") != -1) {
		var str = urlstr.substr(1);
		
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}
	return theRequest;
}

var data = GetRequest();
var taskid =  data.id;

$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + '/generator/sc_result/find',
        postData:{'taskid':taskid},
        datatype: "json",
        colModel: [			
			{ label: '编号', 
				name: 'id',
				index: 'id',
				width: 150, 
				key: true
				},
			{ label: '任务id', name: 'taskid', width: 100 },
			{ label: '车牌号', name: 'plateno', width: 100 },
			{ label: '分析时间', name: 'createtime', width: 100 },
			{ label: '分析结果', name: 'result', width: 160 },
			{ label: '分析值', name: 'rvalue', width: 160,
				formatter:function (cellvalue, options, rowObject) {
					  if(cellvalue == 100){
					        return "是";
					    }
					
				}
			},
			
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
		
		ResultVo:{},
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
					url: baseURL + 'generator/sc_result/find',
					
	                page:page
	            }).trigger("reloadGrid");
			},
		}
        
	});
	
