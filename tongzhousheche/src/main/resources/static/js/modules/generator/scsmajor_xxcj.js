var id_all = null;
var c="";
$(function() {
	$("#jqGrid")
			.jqGrid(
					{
						url : baseURL + 'generator/dsj_xxcj/list',
						datatype : "json",
						colModel : [
								{
									label : '案件编号',
									name : 'ajbh',
									index : "ajbh"
								},
								{
									label : '案件名称',
									name : 'ajmc',
									index : "ajmc"
								},
								{
									label : '派出所管辖',
									name : 'pcsgxid',
									index : "pcsgxid"
								},
								{
									label : '警区',
									name : 'jq',
									index : "jq"
								},
								{
									label : '发案地点',
									name : 'faddid',
									index : "faddid"
								},
								{
									label : '发案地点详址',
									name : 'faddxz',
									index : "faddxz"
								},
								{
									label : '发案开始时间',
									name : 'fakssj',
									index : "fakssj"
								},
								{
									label : '涉案车牌号',
									name : 'cph',
									index : "cph"
								},
								{
									label : '经度',
									name : 'jindu',
									index:'jindu'
								},
								{
									label : '纬度',
									name : 'weidu',
									index:'weidu'
								},
								{
									label : '简要案情',
									name : 'jyaq',
									index : "jyaq"
								}],
						autowidth : true,
						shrinkToFit : false,
						multiselect: true,
						viewrecords : true,
						height : 385,
						rowNum : 10,
						rowList : [ 10, 30, 50 ],
						rownumbers : true,
						rownumWidth : 85,
						pager : "#jqGridPager",
						ExpandColumn : "name",
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
								"overflow-x" : ""
							});
						}
					});
});
clkBool="false";
var vm = new Vue({
	el : '#rrapp',
	data : {
		q : {
			ajbh : null,
			ajmc : null,
			pcsgx : null,
			jq : null,
			fadd : null,
			faddxz : null,
			fakssj : null,
			sacph : null,
			jd : null,
			wd : null,
			jyaq : null
		},
		scScaj : {},
		showList : true,
		title : null,
		role : {}
	},
	methods : {
		cx:function(){
			var ajbh = $("#ajbh").val().trim().replace(/\s/g,"");
			var ajmc = $("#ajmc").val().trim().replace(/\s/g,"")
			if((ajbh == null || ajbh == "") && (ajmc == null || ajmc == "")) {
				$("#jqGrid").jqGrid('setGridParam', {
					url : baseURL + 'generator/dsj_xxcj/list',
					page: 1
				}).trigger("reloadGrid");
			} else {
				$("#jqGrid").jqGrid('setGridParam', {
					url : baseURL + 'generator/dsj_xxcj/cx',
					postData:{
						'ajbh' : ajbh,
						'ajmc' : ajmc
					},
					page: 1
				}).trigger("reloadGrid");
			}
		},
		add : function() {
			vm.showList = false;
			vm.title = '新增';
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.getInfo(id);
		},
		getInfo: function(id){
			$.get(baseURL + "generator/scscaj/info/"+id, function(r){
                vm.scScaj = r.scScaj;
            });
		},
		saveOrUpdate : function(event) {
			var url = vm.scScaj.id == null ? "generator/sc_scaj/saveScaj" : "generator/sc_scaj/updateScaj";
			vm.scScaj.fakssj = $('#fakssj').val();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scScaj),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		reload : function() {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {}).trigger("reloadGrid");

		},
	}
});
function plate(cph) {
	var cph;
	var c = null;
	$.ajax({
		url : baseURL + 'generator/dsj_xxcj/list_dan',
		data : "plate=" + cph,
		dataType : "json",
		type : "post",
		async : false,
		success : function(r) {
			c = r.list;

		}
	});
	if (c != null) {
		$.ajax({
			url : baseURL + 'generator/dsj_xxcj/update_dan',
			data : "plate=" + cph,
			dataType : "json",
			type : "post",
			async : false,
			success : function(r) {
				var c = r.count;
				if (c == 1) {
					black(cph);
				}

			}
		});
	} else {
		return;
	}
}
function black(cph) {
	var params = {};
	params["cph"] = cph;
	params["user"] = "admin1";
	params["createReason"] = "重大事件信息采集";
	$.ajax({
		url : baseURL + 'generator/dsj_xxcj/insert_blick',
		dataType : "json",
		type : "post",
		data : params,
		async : false,
		success : function(r) {
			if (r.count == 1) {
			}
		}
	});
}
function xg(id) {
	id_all = id;
	$.ajax({
		url : baseURL + 'generator/dsj_xxcj/select_ID',
		data : "id=" + id,
		dataType : "json",
		type : "post",
		async : false,
		success : function(r) {
			vm.q.ajbh = r.list[0].ajbh;
			vm.q.ajmc = r.list[0].ajmc;
			vm.q.pcsgx = r.list[0].pcsgxid;
			vm.q.jq = r.list[0].jq;
			vm.q.fadd = r.list[0].faddid;
			vm.q.faddxz = r.list[0].faddxz;
			vm.q.fakssj = r.list[0].fakssj;
			vm.q.sacph = r.list[0].cph;
			vm.q.jd = r.list[0].jindu;
			vm.q.wd = r.list[0].weidu;
			vm.q.jyaq = r.list[0].jyaq;
			alert("请在上面表格修改");
			clkBool=true;
		}
	});
}
//输入去空格
function qkg(a){
	 var b=a.trim().split(" ");
	 for(var i=0;i<b.length;i++){
		 if(b[i]!=null){
			 c=c+b[i];
		 }
			
	}
}