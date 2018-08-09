$(function () {
	//window.location=window.location.href + "#rrapp";
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scscaj/list',
        datatype: "json",
        colModel: [			
			{ label: '流水号', name: 'id', index: 'ID', width: 100, key: true },
			{ label: '案件编号', name: 'ajbh', index: 'AJBH', width: 170 },
			{ label: '案件名称', name: 'ajmc', index: 'AJMC', width: 170 },
			{ label: '派出所管辖（流水号）', name: 'pcsgxid', index: 'PCSGXID', width: 170 },
			{ label: '警区', name: 'jq', index: 'JQ', width: 80 },
			{ label: '发案地点（流水号）', name: 'faddid', index: 'FADDID', width: 280 },
			{ label: '发案地点详址', name: 'faddxz', index: 'FADDXZ', width: 280 },
			{ label: '发案开始时间', name: 'fakssj', index: 'FAKSSJ', width: 170 },
			{ label: '涉案车牌号', name: 'cph', index: 'CPH', width: 100 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 85, 
        autowidth:true,
        shrinkToFit:false,
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
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "" }); 
        }
    });
});

function add(data){
	var url = baseURL + 'generator/sc_scaj/save';
	var cph = $("#cph").val();
	if(data.list == null || data.list == "") {
		alert("没有符合的信息！");
		return;
	} else {
		$.ajax({
			url:url,
			data:cph,
			type: "POST",
			contentType: "application/json",
			success: function(r) {
				update();
			}
		});
	}
}

function update() {
	var url = baseURL + 'generator/sc_scaj/update';
	var data = $("#cph").val();
	$.ajax({
		url:url,
		data:data,
		type: "POST",
		contentType: "application/json",
		success: function(r) {
		}
	});
}

function check() {
	var carNum = $("#carNum").val();
	if (carNum.length < 10 &&  carNum != null && carNum != "" ) {
		$("#msg").css("color", "green");
		$("#msg").html("输入正确！");
	} else if(carNum == null || carNum == "") {
		$("#msg").css("color", "red");
		$("#msg").html("请输入车牌号");
	} else if(carNum.length >= 10){
		$("#msg").css("color", "red");
		$("#msg").html("输入长度不超过10个字符！");
	} 
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		scScaj: {}
	},
	methods: {
		query: function () {
			var cph = $("#carNum").val().trim().replace(/\s/g,"");
			cph = cph.toUpperCase();
			if(cph == null || cph == "") {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scscaj/list',
	                page:1
	            }).trigger("reloadGrid");
			} else {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scscaj/find',
					postData:{'cph': cph},
					page:1
				}).trigger("reloadGrid");
			}
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scScaj = {};
			
		},
		wjdr:function(){
			vm.zc();
			
		},
		zc:function(){
			
		         var fileObj = document.getElementById("FileUpload").files[0]; // js 获取文件对象
		         if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
		             alert("请选择文件");
		             return;
		         }
		         var formFile = new FormData();	     
		         formFile.append("file", fileObj); //加入文件对象
		         var data = formFile;
		         $.ajax({
		             url: baseURL + "generator/wjcd/text_caj",
		             data: data,
		             type: "Post",
		             dataType: "json",
		             cache: false,//上传文件无需缓存
		             processData: false,//用于对data参数进行序列化处理 这里必须false
		             contentType: false, //必须
		             success: function (r) {
		            	 if(r.text==""||r.text==null){
								alert("文件数据导入失败 ,导入0条数据,请按正确格式安排数据");
							}else{
								text=r.text;
								var n=r.text.length-1;
								if(r.text.length<2){
									r.text[n]=1;
									r.text[n-1]=0;
								}
								alert("您一共提供"+r.text[n]+"条数据，成功导入"+r.text[n-1]+"条数据，未导入"+(r.text[n]-r.text[n-1])+"条数据！");
								vm.reload();
							}
		            	 console.log(r.text);
		             },
		         });
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
		check: function() {
			var url = baseURL + 'generator/sc_scaj/check';
			var data = $("#cph").val();
			$.ajax({
				url:url,
				data:data,
				type: "POST",
				contentType: "application/json",
				success: function(r) {
					//console.log(r.list);
					if(r.list != null) {
						update();
					}
					vm.saveOrUpdate();
				}
			})
		},
		saveOrUpdate: function (event) {
			var url = vm.scScaj.id == null ? "generator/scscaj/save" : "generator/scscaj/update";
			vm.scScaj.fakssj=$('#fakssj').val();
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
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "generator/scscaj/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "generator/scscaj/info/"+id, function(r){
                vm.scScaj = r.scScaj;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});