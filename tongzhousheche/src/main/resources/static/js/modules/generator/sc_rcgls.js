var a = null;
var b= null;
var c= null;
var d = null;
var e = null;

var cph=sessionStorage.getItem("cph");

var sfz32=sessionStorage.getItem("sfz32");

var cp32=sessionStorage.getItem("cp32");

var cp22=sessionStorage.getItem("cp22");

var sfz22=sessionStorage.getItem("sfz22");


var ph='/generator/sc_rcgls/find' ;

$(function(){
	$("#back").hide();
	var data = GetRequest();
	$("#sfz").val(data.sfzh);
	if(cp32 != null && cp32 != "" && cp32 != undefined){
		$("#cp").val(cp32);
		sessionStorage.removeItem("cp32");
	}else if(cp22 != null && cp22 != "" && cp22 != undefined){
		$("#cp").val(cp22);
		sessionStorage.removeItem("cp22");
	}
	
	 if(sfz32 != null && sfz32 != "" && sfz32 != undefined){
		$("#sfz").val(sfz32);		
		sessionStorage.removeItem("sfz32");
	}else if(sfz22 != null && sfz22 != "" && sfz22 != undefined){	
		$("#sfz").val(sfz22);
		sessionStorage.removeItem("sfz22");
	}
	
	})

$(function(){
	sessionStorage.clear();	
	
})

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

$(function () {
	if(cph){
		$("#cp").val(cph);
		ph+="?cp="+cph;
		sessionStorage.removeItem("cph");
	}
    $("#jqGrid").jqGrid({
        url: baseURL + ph,
        datatype: "json",
        postData:{'cp': $("#cp").val().trim().replace(/\s/g,""),'xm':$("#xm").val().trim().replace(/\s/g,""),'sfz':$("#sfz").val().trim().replace(/\s/g,"")},
        colModel: [			
			{ label: '车牌号', 
				name: 'cp',
				index: 'cp',
				width: 120, 
				key: true,
			formatter : function(cellvalue, options,
					rowObject) {
				if(cellvalue == null){
					return '<a class="btn btn-primary"  style="background: #337ab7;color:#fff;width:100px;height:36px">'
			    	+ "无牌" + '</a>';
			    }else{
			    	return '<a class="btn btn-primary"  style="background: #337ab7;color:#fff;width:100px;" onclick="cpp(\''
			    	+ cellvalue
			    	+ '\')">'
			    	+ cellvalue + '</a>';
			       
			    }
			}
			},
			{ label: '看图', 
				name: 'cp',
				width: 100, 
				formatter : function(cellvalue, options,
						rowObject) {
					if(cellvalue == null){
						return '<a style="text-decoration:underline;color:blue">'
						+ '车人图' + '</a>'; 
					}else{
						return '<a  href="../../modules/generator/sc_crglt.html" style="text-decoration:underline;color:blue" onclick="cpp2(\''
						+ cellvalue
						+ '\')">'
						+ '车人图' + '</a>';	
					}
					
				}
			
			},
			{ label: '车主', name: 'owner', width: 120 },
			{ label: '车的品牌', name: 'brand', width: 120 },
			{ label: '车的颜色', name: 'color', width: 120 },
			{ label: '姓名', name: 'xm', width: 120, 
				formatter : function(cellvalue, options,
						rowObject) {
				
					if(cellvalue == null){
						return '<a class="btn btn-primary"  style="background: #337ab7;color:#fff;width:68px;height:36px">'
				    	+ "无" + '</a>';
				    }else{
				        
				    	return '<a class="btn btn-primary"  style="background: #337ab7;color:#fff;" onclick="xm1(\''
				    	+ cellvalue
				    	+ '\')">'
				    	+ cellvalue + '</a>';
				    }
				}
			},
			
			{ label: '身份证', name: 'sfz', width: 180,
				formatter : function(cellvalue, options,
						rowObject) {
				
					if(cellvalue == null){
						return '<a class="btn btn-primary"  style="background: #337ab7;color:#fff;width:165px;height:36px">'
				    	+ "无" + '</a>';
				    }else{
				        
				    	return '<a class="btn btn-primary"  style="background: #337ab7;color:#fff;" onclick="sfz1(\''
				    	+ cellvalue
				    	+ '\')">'
				    	+ cellvalue + '</a>';
				    }
				}
			},
			{ label: '看图', 
				name: 'sfz', 
				width: 100,
				formatter : function(cellvalue, options,
						rowObject) {
					if(cellvalue == null){
						return '<a style="text-decoration:underline;color:blue">'
						+ '人车图' + '</a>';
					}else{
						return '<a  href="../../modules/generator/sc_rcglt.html" style="text-decoration:underline;color:blue" onclick="sfz2(\''
						+ cellvalue
						+ '\')">'
						+ '人车图' + '</a>';
					}	
				}
			},
			{ label: '电话手机', name: 'dianhua', width: 170 },
			{ label: '网络身份', name: 'wangluo', width: 160 },
			{ label: '数据来源', name: 'sjly', width: 120 },
			{ label: '车辆轨迹', width: 120,
				name: 'cp',
				index: 'cp',
				formatter : function(cellvalue, options,
						rowObject) {
					return '<a class="btn btn-primary"  style="background: #337ab7;color:#fff;" onclick="infor(\''
							+ cellvalue
							+ '\')">'
							+ '车辆轨迹' + '</a>';
				}
			},
        ],
		viewrecords: true,
        height: 530,
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
        },
       
    });
});
function cpp(cp){
	a=cp;
	$("#cp").val(a);
	vm.query();
}
function cpp2(cp){
	d=cp;
	sessionStorage.setItem("co", "1");
	sessionStorage.setItem("cp2", d);

}
function xm1(xm){
	b=xm;
	$("#xm").val(b);
	vm.query();
}
function sfz2(sfz){
	e=sfz;
	sessionStorage.setItem("co", "1");
	sessionStorage.setItem("sfz2", e);
}
function sfz1(sfz){
	c=sfz;
	$("#sfz").val(c);
	vm.query();
}


	var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			cp: null,
			xm: null,
			sfz: null
		},
		Sc_rcglsVo:{},
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
			var carNum = $("#cp").val().trim().replace(/\s/g,"");
			carNum = carNum.toUpperCase();
			var xm = $("#xm").val().trim().replace(/\s/g,"");
			var sfz = $("#sfz").val().trim().replace(/\s/g,"");
			if(carNum != null || carNum != "" || xm != null || xm != "" || sfz != null || sfz !="") {
				$("#jqGrid").jqGrid('setGridParam',{
					url: baseURL + 'generator/sc_rcgls/find',
					postData:{'cp': carNum, 'xm': xm, 'sfz': sfz},
					page:1
				}).trigger("reloadGrid");
			} else {
				$("#jqGrid").jqGrid('setGridParam',{
					url: baseURL + 'generator/sc_rcgls/find',
					page:page
				}).trigger("reloadGrid");
			}
			
		}
	
	}
	
        
});

	function infor(cph){
		//跳页面传递车牌数据
		 parent.sayhello( "vasMap/views/scguijitu.html",'2-'+cph);
	}
	
