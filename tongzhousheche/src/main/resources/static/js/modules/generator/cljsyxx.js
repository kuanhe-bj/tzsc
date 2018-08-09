function GetRequest() {  
    var url = location.search; //获取url中"?"符后的字串   
    var theRequest = new Object();  
    if (url.indexOf("?") != -1) {  
        var str = url.substr(1); 
        strs = str.split("&");  
        for (var i = 0; i < strs.length; i++) {  
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);  
        } 
        
    }  
    return theRequest;  
    
}  
var address=GetRequest();

$(function() {
	$("#jqGrid")
			.jqGrid(
					{
						url : baseURL + 'generator/cljsyxx/finj',
						datatype : "json",
						colModel : [
									{
										label : '机动车所有人',
										name : 'syr',
										index : "syr",
										width : 45,
										key : true
									},
									{
										label : '身份证明号码',
										name : 'sfzmhm',
										index : "sfzmhm",
										width : 75
									},
									{
										label : '初次登记日期',
										name : 'ccdjrq',
										index : "ccdjrq",
										width : 75
									},
									{
										label : '住所详细地址',
										name : 'zsxxdz',
										index : "zsxxdz",
										width : 75
									},
									{
										label : '车辆型号' ,
										name : 'clxh',
										index : "clxh",
										width : 75
									},
									{
										label : '车身颜色代码',
										name : 'csys',
										index : "csys",
										width : 75
									} ],
						viewrecords : true,
						height : 385,
						rowNum : 10,
						rowList : [ 10, 30, 50 ],
						rownumbers : true,
						rownumWidth : 55,
						autowidth : true,
						multiselect : true,
						pager : "#jqGridPager",
						jsonReader : {
							root : "page.list",
							page : "page.currPage",
							total : "page.totalPage",
							records : "page.totalCount"
						},
						prmNames : {
							page : "page",
							rows : "limit",
							order : "order"
						},
						gridComplete : function() {
							// 隐藏grid底部滚动条
							$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
								"overflow-x" : "hidden"
							});
						},
						dd : function() {
							window.location.href=baseURL+'modules/generator/dtwj.html'
						}
					});
});

var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		title : null,
		role : {}
	},
	methods : {
		reload : function() {
			$.ajax({
				url : baseURL + 'generator/czz/cz',
				dataType:"json",
				type:"post",
				data:{'licensePlate':address.a},
				success:function(obj){
				
				   var  syr=obj.list[0].vehiOwner;
				   sessionStorage.setItem("syr",syr);
					
				}
			});
			vm.showList = true;
			var s=sessionStorage.getItem("syr");
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					//车主
					'syr' : s
				},
			}).trigger("reloadGrid");
			
		},
	}
});

$(window).load(function(){
	vm.reload();
});