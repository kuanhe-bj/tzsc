var fanwei = null;
var time = null;
var cph;
var c = "";
var fakssj;
var nu = sessionStorage.getItem("nu");
var na = sessionStorage.getItem("na");
var ti = sessionStorage.getItem("ti");
var jh = sessionStorage.getItem("jh");
var a = 0;
var vm = new Vue(
		{
			el : '#rrapp',
			data : {
				q : {
					number : null,
					name : null,
					qssj : null,
					jssj : null,
					ladw : null,
					time : null,
					fanwei : null
				},
				showList : true,
				title : null,
				role : {}
			},
			methods : {
				query : function() {

					if (vm.q.number == null && vm.q.name == null
							&& vm.q.qssj == null && vm.q.jssj == null
							&& vm.q.ladw == null) {

						alert("请输入数据");

					}
					vm.rel();
				},
				x : function() {
					vm.reload();
				},
				// 清空数据
				qk : function() {
					vm.q.number = null;
					vm.q.name = null;
					vm.q.qssj = null;
					vm.q.jssj = null;
					vm.q.ladw = null;
					vm.q.time = null;
					vm.q.fanwei = null;

				},
				reload : function() {
					vm.showList = true;
					var a = null;
					var b = null;
					if (vm.q.number != null && vm.q.number != "") {
						qkg(vm.q.number);
						a = c;
						c = "";
					}
					if (vm.q.name != null && vm.q.name != "") {
						qkg(vm.q.name);
						b = c;
						c = "";

					}
					var page = $("#jqGrid").jqGrid('getGridParam', 'page');
					$("#jqGrid").jqGrid('setGridParam', {
						postData : {
							'number' : a,
							'name' : b
						},
					}).trigger("reloadGrid");

				},
				rel : function() {
					vm.showList = true;
					var a = null;
					var b = null;
					if (vm.q.number != null && vm.q.number != "") {
						qkg(vm.q.number);
						a = c;
						c = "";

					}
					if (vm.q.name != null && vm.q.name != "") {
						qkg(vm.q.name);
						b = c;
						c = "";

					}
					var page = $("#jqGrid").jqGrid('getGridParam', 'page');
					if ((vm.q.number != null && vm.q.number != "" && (vm.q.name == null || vm.q.name == ""))) {
						$("#jqGrid").jqGrid('setGridParam', {
							url : baseURL + 'generator/dsj/page',
							postData : {
								'number' : a
							},
							page : 1
						}).trigger("reloadGrid");
					} else if ((vm.q.name != null && vm.q.name != "" && (vm.q.number == null || vm.q.number == ""))) {
						$("#jqGrid").jqGrid('setGridParam', {
							url : baseURL + 'generator/dsj/page',
							postData : {
								'name' : b
							},
							page : 1
						}).trigger("reloadGrid");
					} else if (vm.q.number != null && vm.q.name != null
							&& vm.q.number != "" && vm.q.name != "") {
						$("#jqGrid").jqGrid('setGridParam', {
							url : baseURL + 'generator/dsj/fin_dsall',
							postData : {
								'number' : a,
								'name' : b
							},
							page : 1
						}).trigger("reloadGrid");
					} else if (vm.q.number == null && vm.q.name == "") {
						window.location.href = baseURL
								+ 'modules/generator/scsmajor.html';
					} else if (vm.q.number == "" && vm.q.name == null) {
						window.location.href = baseURL
								+ 'modules/generator/scsmajor.html';
					} else if (vm.q.number == null && vm.q.name == null) {
						window.location.href = baseURL
								+ 'modules/generator/scsmajor.html';
					} else if (vm.q.number == "" && vm.q.name == "") {
						window.location.href = baseURL
								+ 'modules/generator/scsmajor.html';
					}
				},

			}
		});
var ur = null;
if (nu.length != 0 && na.length == 0) {
	ur = baseURL + 'generator/dsj/list?number=' + nu;
}
if (nu.length == 0 && na.length != 0) {
	ur = baseURL + 'generator/dsj/list?name=' + na;
}
if (nu.length != 0 && na.length != 0) {
	ur = baseURL + 'generator/dsj/list?number=' + nu + '&name=' + na;
}
if (nu.length == 0 && na.length == 0) {
	ur = baseURL + 'generator/dsj/list';
}
$(function() {
	$("#jqGrid")
			.jqGrid(
					{

						url : ur,

						datatype : "json",
						colModel : [
								{
									label : '案件编号',
									name : 'ajbh',
									index : "ajbh",
									width : 55,
									key : true
								},
								{
									label : '车牌号',
									name : 'cph',
									index : 'cph',
									width : 45,
								},
								{
									label : '案件名称',
									name : 'ajmc',
									index : "ajmc",
									width : 75
								},
								{
									label : '发案开始时间',
									name : 'fakssj',
									index : "fakssj",
									width : 75
								},
								{
									label : '立案单位',
									name : 'pcsgxid',
									index : "pcsgxid",
									width : 75
								},
								{
									label : '发案地点',
									name : 'faddid',
									index : "faddid",
									width : 75,
									formatter : function(cellvalue, options,
											rowObject) {

										cph = rowObject.cph;
										fakssj = rowObject.fakssj;
										sessionStorage
												.setItem("fakssj", fakssj);
										sessionStorage.setItem("cph", cph);

										fanwei = vm.q.fanwei;
										if (vm.q.time == null) {
											time = 1;
										}
										if (vm.q.fanwei == null) {
											fanwei = 1;
										}
										sessionStorage.setItem("number",
												vm.q.number);
										sessionStorage.setItem("name",
												vm.q.name);
										sessionStorage.setItem("time", time);
										sessionStorage
												.setItem("fanwei", fanwei);

										// 车辆数 半径
										return "<a href=" + baseURL
												+ "vasMap/views/dtwj.html?a="
												+ cellvalue + " color:blue>"
												+ cellvalue + "</a>";
									}
								},
								{
									label : '发案地点详址',
									name : 'faddxz',
									index : "faddxz",
									width : 75,
									formatter : function(cellvalue, options,
											rowObject) {
										cph = rowObject.cph;
										fakssj = rowObject.fakssj;
										sessionStorage
												.setItem("fakssj", fakssj);
										sessionStorage.setItem("cph", cph);
										time = vm.q.time;
										fanwei = vm.q.fanwei;
										if (vm.q.time == null) {
											time = 1;
										}
										if (vm.q.fanwei == null) {
											fanwei = 1;
										}
										sessionStorage.setItem("number",
												vm.q.number);
										sessionStorage.setItem("name",
												vm.q.name);
										sessionStorage.setItem("time", time);
										sessionStorage
												.setItem("fanwei", fanwei);

										return "<a href=" + baseURL
												+ "vasMap/views/dtwj.html?a="
												+ cellvalue + " color:blue>"
												+ cellvalue + "</a>";
									}
								}, {
									label : '简要案情',
									name : 'jyaq',
									index : "jyaq",
									width : 75
								} ],
						sortable : true,
						sortorder : 'asc',
						viewrecords : true,
						height : 385,
						rowNum : 10,
						rowList : [ 10, 30, 50 ],
						rownumbers : true,
						rownumWidth : 85,
						autowidth : true,
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

var jingdu = null;
var weidu = null;
var faddxz = null;
var allcar = {};
var a1 = null;
var b1 = null;

// 输入去空格
function qkg(a) {
	var b = a.trim().split(" ");
	for (var i = 0; i < b.length; i++) {
		if (b[i] != null) {
			c = c + b[i];
		}

	}
}

$(window).load(function() {
	console.log("111111");

	if (nu != null) {

		vm.q.number = nu;
	} else {
		vm.q.number = "";
	}
	if (na != null) {

		vm.q.name = na;
	} else {
		vm.q.name = "";
	}
	if (ti != null) {

		vm.q.time = ti;
	} else {
		vm.q.time = "";
	}
	if (jh != null) {

		vm.q.fanwei = jh;
	} else {
		vm.q.fanwei = "";
	}
	if (nu != null || na != null || ti != null || jh != null) {
		vm.query();
		sessionStorage.clear();
	}
})
