var gsd = {};
var v = null;

$(function(){
	$.ajax({
		url:baseURL + "generator/vehiclegs/getcarnum",
		dataType:"json",
		type:"post",
		success:function(r){
			 v = r.vehiclegsVo;
			
			
			
		}
	})

})

function create(){
			var data = zztlist
			
			var chart = new iChart.Column2D({
				render : 'canvasDiv',
				data : data,
				title : {
					text : '车辆归属地各省数量柱状图',
					color : '#3e576f'
				},
				width : 800,
				height : 600,
				label : {
					fontsize:11,
					textAlign:'right',
					textBaseline:'middle',
					rotate:-45,
					color : '#666666'
				},
				tip:{
					enable:true,
					listeners:{
						 //tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
						parseText:function(tip,name,value,text,i){
							//将数字进行千位格式化
							var f = new String(value);
							f = f.split("").reverse().join("").replace(/(\d{3})/g,"$1,").split("").reverse();
							if(f[0]==','){
								f.shift();
							}	
							f = f.join("");
							
							return name+"车辆:<br/>"+f+"辆<br/>占全国比重:<br/>"+(value/this.get('total') * 100).toFixed(2)+ '%';
						}
					}
				},
				shadow : true,
				shadow_blur : 2,
				shadow_color : '#aaaaaa',
				shadow_offsetx : 1,
				shadow_offsety : 0,
				column_width : 62,
				sub_option : {
					label : false,
					border : {
						width : 2,
						color : '#ffffff'
					}
				},
				coordinate : {
					background_color : null,
					grid_color : '#c0c0c0',
					width : 550,
					height:400,
					axis : {
						color : '#c0d0e0',
						width : [0, 0, 1, 0]
					},
					scale : [{
						position : 'left',
						start_scale : 0,
						end_scale : 1000000,
						scale_space : 50000,
						scale_enable : true,
						label : {
							fontsize:11,
							color : '#666666'
						},
						listeners:{
							parseText:function(t,x,y){
								return {text:t/10000}
							}
						 }
					}]
				}
			});
			
			//利用自定义组件构造左侧说明文本
			chart.plugin(new iChart.Custom({
					drawFn:function(){
						//计算位置
						var coo = chart.getCoordinate(),
							x = coo.get('originx'),
							y = coo.get('originy');
						//在左上侧的位置，渲染一个单位的文字
						chart.target.textAlign('start')
						.textBaseline('bottom')
						.textFont('600 11px Verdana')
						.fillText('车辆数(万辆)',x-40,y-10,false,'#6d869f');
						
					}
			}));
			
			chart.draw();

}	





$(function () {
	
    $("#jqGrid").jqGrid({
        url: baseURL + '/generator/vehiclegs/carnumList',
        datatype: "json",
        
        colModel: [			
			
			{ label: '省、直辖市', name: 'province', width: 80 },
			{ label: '所占比例', name: 'percent', width: 80 },
			{ label: '车辆数量', name: 'carnum', width: 80 },
			
        ],
		viewrecords: true,
        height: 420,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 55, 
        autowidth:true,
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
		province:null,
		entertime:null,
		exittime:null,
		showList: true,
		title:null,
		roleList:{},
		VehiclegsVo:{}
	},
	filters:{
		keepTwoNum:function(value){
			value = Number();
			return value.toFixed(2);
		}
	},
	methods: {
		query: function () {
			vm.showList = true;
			var province = $("#province").val().trim().replace(/\s/g,"");
			var entertime = $("#entertime").val();
			var exittime = $("#exittime").val();
			if (entertime!=null&&entertime!=""&&exittime!=null&&exittime!="") {
				if (!compareDate(entertime, exittime)) {
					return false;
				};
			}
				
			$("#jqGrid").jqGrid('setGridParam',{
					 url: baseURL + 'generator/vehiclegs/dList',
					 postData:{ 'province': province,
						 		'entertime': entertime,
						 		'exittime': exittime
					 },
					 page:1
				}).trigger("reloadGrid");
				
		},
		reload: function () {
			
				$("#jqGrid").jqGrid('setGridParam',{
					url: baseURL + 'generator/vehiclegs/carnumList',
	                page:page
	            }).trigger("reloadGrid");
			
		},
		
	}
	
        
});
	
	$(function(){
		$.ajax({
			url:baseURL + "generator/vehiclegs/listzzt",
			dataType:"json",
			type:"post",
			success:function(r){
				 zztlist = r.list;
				create();
				
			}
		})

	})
	
	$(function(){
		$.ajax({
			url:baseURL + "generator/vehiclegs/listbzt",
			dataType:"json",
			type:"post",
			success:function(r){
				 bztlist = r.list;
				
				create1();
			}
		})

	})
// 时间比较（yyyy-MM-dd）
function compareDate(startDate, endDate) {
  var startTime = new Date(startDate);
  var startTimes = startTime.getTime();
  var endTime = new Date(endDate);
  var endTimes = endTime.getTime();
  if (endTimes<startTimes) {
      alert("结束时间不能小于开始时间");
    return false;
  }else if ((endTimes-startTimes)>(7*24*60*60*1000)) {
	  alert("时间段最长不超过7天");
	return false;
}
  return true;
}	
	
	
function create1(){
				var data = bztlist
				new iChart.Pie2D({
					render : 'canvasDiv1',
					data: data,
					title : '车辆归属地各省所占百分比饼状图',
					subtitle:"北京"+v.carnum+"辆",
					legend : {
						enable : true
					},
					showpercent:true,
					decimalsnum:2,
					width : 800,
					height : 800,
					radius:120
				}).draw();
	
}