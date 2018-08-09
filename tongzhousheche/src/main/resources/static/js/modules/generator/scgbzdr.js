$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scgbzdr/list',
        datatype: "json",
        colModel: [			
			{ label: '重点人员编号', name: 'drybh', index: 'DRYBH', width: 100, key: true },
			{ label: '姓名', name: 'xm', index: 'XM', width: 80 },
			{ label: '性别', name: 'xb', index: 'XB', width: 80 },
			{ label: '出生日期', name: 'csrq', index: 'CSRQ', width: 130 },
			{ label: '身份证号', name: 'sfzh', index: 'SFZH', width: 170 },
			{ label: '户籍地区划', name: 'hjdqh', index: 'HJDQH', width: 280 },
			{ label: '户籍地详址', name: 'hjdxz', index: 'HJDXZ', width: 280 },
			{ label: '现住地区划', name: 'xzdqh', index: 'XZDQH', width: 280 },
			{ label: '现住地详址', name: 'xzdxz', index: 'XZDXZ', width: 280 }
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

function check() {
	var carNum = $("#sfzh").val();
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
		scGbzdr: {}
	},
	methods: {
		query: function () {
			var sfzh = $("#sfzh").val().trim().replace(/\s/g,"");
			if(sfzh == null || sfzh == "") {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scgbzdr/list',
	                page:1
	            }).trigger("reloadGrid");
			} else {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scgbzdr/find',
					postData:{'sfzh': sfzh},
					page:1
				}).trigger("reloadGrid");
			}
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scGbzdr = {};
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
		             url: baseURL + "generator/wjcd/text_zdr",
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
			var drybh = getSelectedRow();
			if(drybh == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(drybh)
		},
		saveOrUpdate: function (event) {
			var url = vm.scGbzdr.drybh == null ? "generator/scgbzdr/save" : "generator/scgbzdr/update";
			vm.scGbzdr.csrq = $('#csrq').val();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scGbzdr),
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
			var drybhs = getSelectedRows();
			if(drybhs == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "generator/scgbzdr/delete",
                    contentType: "application/json",
				    data: JSON.stringify(drybhs),
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
		getInfo: function(drybh){
			$.get(baseURL + "generator/scgbzdr/info/"+drybh, function(r){
                vm.scGbzdr = r.scGbzdr;
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