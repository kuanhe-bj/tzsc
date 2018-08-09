$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/schlsj/list',
        datatype: "json",
        colModel: [			
			{ label: '核查单号', name: 'checkinfoid', index: 'CHECKINFOID', width: 140, key: true },
			{ label: '警察编号', name: 'policemanid', index: 'POLICEMANID', width: 150 },
			{ label: '核查时间', name: 'checktime', index: 'CHECKTIME', width: 140 },
			{ label: '核查年份', name: 'checkyear', index: 'CHECKYEAR', width: 90 },
			{ label: '核查月份', name: 'checkmonth', index: 'CHECKMONTH', width: 90 },
			{ label: '核查天数', name: 'checkday', index: 'CHECKDAY', width: 90 },
			{ label: '核查地点', name: 'checkaddress', index: 'CHECKADDRESS', width: 270 },
			{ label: '核查区', name: 'checkqu', index: 'CHECKQU', width: 150 },
			{ label: '核查街道', name: 'checkjiedao', index: 'CHECKJIEDAO', width: 251 },
			{ label: '核查区名称', name: 'checkquname', index: 'CHECKQUNAME', width: 251 },
			{ label: '核查街道名称', name: 'checkjiedaoname', index: 'CHECKJIEDAONAME', width: 251 },
			{ label: '身份证号', name: 'checkpersoncardnumber', index: 'CHECKPERSONCARDNUMBER', width: 160 },
			{ label: '核查人姓名', name: 'checkpersonname', index: 'CHECKPERSONNAME', width: 90 },
			{ label: '车号码', name: 'checkcarnumber', index: 'CHECKCARNUMBER', width: 90 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 85, 
        autowidth:true,
        multiselect: true,
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
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "" }); 
        }
    });
});

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
		scHlsj: {}
	},
	methods: {
		query: function () {
			var cph = $("#carNum").val().trim().replace(/\s/g,"");
			cph = cph.toUpperCase();
			if(cph == null || cph == "") {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/schlsj/list',
	                page:1
	            }).trigger("reloadGrid");
			} else {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/schlsj/find',
					postData:{'cph': cph},
					page:1
				}).trigger("reloadGrid");
			}
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scHlsj = {};
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
		             url: baseURL + "generator/wjcd/text_lsj",
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
			var checkinfoid = getSelectedRow();
			if(checkinfoid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(checkinfoid)
		},
		saveOrUpdate: function (event) {
			var url = vm.scHlsj.checkinfoid == null ? "generator/schlsj/save" : "generator/schlsj/update";
			vm.scHlsj.checktime = $('#checktime').val();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scHlsj),
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
			var checkinfoids = getSelectedRows();
			if(checkinfoids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "generator/schlsj/delete",
                    contentType: "application/json",
				    data: JSON.stringify(checkinfoids),
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
		getInfo: function(checkinfoid){
			$.get(baseURL + "generator/schlsj/info/"+checkinfoid, function(r){
                vm.scHlsj = r.scHlsj;
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