$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'map/query',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'id', index: "id", width: 45, key: true },
			{ label: '用户名', name: 'username', index: "username", width: 75 },
			{ label: '热力图名称', name: 'mapname', width: 100 },
			{ label: '刷新间隔时间', name: 'refreshtime', width: 100 },
			{ label: '创建时间', name: 'createTime', index: "createTime", width: 150},
			{ label: '操作', name: 'ditu', index: 'ditu', width: 45, align: 'center',
	            formatter: function (cellvalue, options, rowObject) {
	            	var mapname = rowObject.mapname;
	            	var refreshtime = rowObject.refreshtime;
	                return '<button class="btn btn-default"  style="background: #337ab7;color:#fff;" onclick= "map(\''+mapname+'\',\''+refreshtime+'\')">'
					+ '地图' + '</button>';}}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
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
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});
	
var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			mapname: null
		},
		showList: true,
		title:null,
		map:{},
		items:[{text:'请选择',value:'-1'}
		    ,{text:'停车场热力图',value:'停车场热力图'}
			,{text:'卡口热力图',value:'卡口热力图'}
			,{text:'拥堵点热力图',value:'拥堵点热力图'}
			,{text:'大车流热力图',value:'大车流热力图'}
			,{text:'113卡口热力图',value:'113卡口热力图'}
			,{text:'检查站热力图',value:'检查站热力图'}
			,{text:'核录热力图',value:'核录热力图'}],  
        selected:'-1' 
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.map = {};
		},
		update: function () {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "修改";
            vm.getRole(id);
		},
		del: function () {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "map/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getRole: function(id){
            $.get(baseURL + "map/info/"+id, function(r){
            	vm.map = r.map;
                
    		});
		},
		saveOrUpdate: function () {
			
            if(vm.validator()){
                return ;
            }
            if(vm.selected=='-1'){
                alert("热力图名不能为空");
                return;
            }
            vm.map.mapname = vm.selected;
            vm.selected = '-1';
			var url = vm.map.id == null ? "map/save" : "map/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.map),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
	    reload: function () {
	    	vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'roleName': trim(vm.q.mapname)},
                page:page
            }).trigger("reloadGrid");
		},
        validator: function () {
            if(isBlank(vm.map.username)){
                alert("角色名不能为空");
                return true;
            }
        },
        valimap: function () {
            if(vm.map.selected==-1){
                alert("热力图名不能为空");
                return true;
            }
        }
	}
});

function map(mapname,refreshtime) {
	if(mapname=="停车场热力图"){
		var url = encodeURI(baseURL + "tzsc/public_html/resources/views/shishi1.html"
				+"?refreshtime=" + refreshtime+"&tab=rw" );
		location.href = url;
	}
	if(mapname=="卡口热力图"){
		var url = encodeURI(baseURL + "tzsc/public_html/resources/views/shishi2.html"
				+"?refreshtime=" + refreshtime+"&tab=rw" );
		location.href = url;
	}
	if(mapname=="拥堵点热力图"){
		var url = encodeURI(baseURL + "tzsc/public_html/resources/views/shishi3.html"
				+"?refreshtime=" + refreshtime+"&tab=rw" );
		location.href = url;
		}
	if(mapname=="大车流热力图"){
		var url = encodeURI(baseURL + "tzsc/public_html/resources/views/shishi4.html"
				+"?refreshtime=" + refreshtime+"&tab=rw" );
		location.href = url;
	}
	if(mapname=="113卡口热力图"){
		var url = encodeURI(baseURL + "tzsc/public_html/resources/views/shishi5.html"
				+"?refreshtime=" + refreshtime+"&tab=rw" );
		location.href = url;
	}
	if(mapname=="检查站热力图"){
		var url = encodeURI(baseURL + "tzsc/public_html/resources/views/shishi6.html"
				+"?refreshtime=" + refreshtime+"&tab=rw" );
		location.href = url;
	}
	if(mapname=="核录热力图"){
		var url = encodeURI(baseURL + "tzsc/public_html/resources/views/shishi7.html"
				+"?refreshtime=" + refreshtime+"&tab=rw" );
		location.href = url;
	}
		
}
function trim(str){
	return str.replace(/\s|\xA0/g,"");   
}
