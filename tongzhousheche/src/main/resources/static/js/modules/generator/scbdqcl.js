$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scbdqcl/list',
        datatype: "json",
        colModel: [			
			{ label: '流水号', name: 'id', index: 'ID', width: 120, key: true },
			{ label: '车牌号', name: 'cph', index: 'CPH', width: 100 },
			{ label: '车主姓名', name: 'czxm', index: 'CZXM', width: 80 },
			{ label: '车主地址(流水号)', name: 'czdzid', index: 'CZDZID', width: 320 },
			{ label: '单位名称', name: 'dwmc', index: 'DWMC', width:230 },
			{ label: '单位地址', name: 'dwdz', index: 'DWDZ', width: 240 },
			{ label: '车主详细地址', name: 'czxxzd', index: 'CZXXZD', width: 320 },
			{ label: '事主姓名', name: 'szxm', index: 'SZXM', width: 80 },
			{ label: '事主地址(流水号)', name: 'szdzid', index: 'SZDZID', width: 390 },
			{ label: '事主详址', name: 'szxz', index: 'SZXZ', width: 240 },
			{ label: '操作时间', name: 'operatetime', index: 'OPERATETIME', width: 90 },
			{ label: '案件流水号', name: 'ajid', index: 'AJID', width: 140 }
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
var cp=null;
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		scBdqcl: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		wjdr:function(){
			vm.zc();
			
		},
		cx:function(){
			
			c();

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
		             url: baseURL + "generator/wjcd/text_wjdr",
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
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scBdqcl = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.scBdqcl.id == null ? "generator/scbdqcl/save" : "generator/scbdqcl/update";
			vm.scBdqcl.operatetime = $('#operatetime').val();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scBdqcl),
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
				    url: baseURL + "generator/scbdqcl/delete",
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
			$.get(baseURL + "generator/scbdqcl/info/"+id, function(r){
                vm.scBdqcl = r.scBdqcl;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		re: function () {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				url: baseURL + 'generator/scbdqcl/cx',
				postData:{
					'cph':cp
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
function c(){
	cp = $("#cph").val().trim().replace(/\s/g,"");
	cp = cp.toUpperCase();
	if(cp != null && cp != ""){
		vm.re();
	} else {
		window.location.href= baseURL + 'modules/generator/scbdqcl.html';
	}	
}