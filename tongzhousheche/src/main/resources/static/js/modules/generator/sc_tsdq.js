$(function() {
	$("#jqGrid").jqGrid({
						url : baseURL + 'generator/tcc/xq',
						datatype : "json",
						colModel : [
								{
									label : '停车场编号',
									name : 'districtNmId',
									index : "q.districtNmId",
									width : 45,
									key : true,
									formatter : function(cellvalue, options,
											rowObject) {
										return "<a href="
												+ baseURL
												+ "modules/generator/tccxq.html?districtNmId="
												+ cellvalue + " color:blue>"
												+ cellvalue + "</a>";
									}
								}, {
									label : '停车场名称',
									name : 'parkNm',
									index : "q.parkNm",
									width : 75
								}, {
									label : '车辆数',
									name : 'count',
									index : "q.count",
									width : 75
								} ],
						viewrecords : true,
						height : 385,
						rowNum : 10,
						rowList : [ 10, 30, 50 ],
						rownumbers : true,
						rownumWidth : 25,
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
						}
					});
});
var vm = new Vue({
	el : "#rrapp",
	data : {
		q : {
			districtNmId : null,
			parkNm : null,
			count:null
		},
		showList : true
	},
	methods : {
		query : function() {
			if (vm.q.districtNmId == null && vm.q.parkNm == null) {

				alert("请输入数据");
				
			}else{
			vm.reload();
			}
		},
		qk:function() {
			if (vm.q.districtNmId == null && vm.q.parkNm == null) {

				alert("请输入数据");
				
			}else{
			//经纬度  车辆数  链接热力图
			location.href=baseURL+"vasMap/views/tcc_screlitu.html";
			}
		},
		reload : function() {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {	
				postData : {
					'districtNmId' : vm.q.districtNmId,
					'parkNm' : vm.q.parkNm
				},
				page : page
			}).trigger("reloadGrid");
		}
	}
});
