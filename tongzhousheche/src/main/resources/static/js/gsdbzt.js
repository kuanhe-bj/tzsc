	$(function(){
		$.ajax({
			url:baseURL + "generator/vehiclegs/listbzt",
			dataType:"json",
			type:"post",
			success:function(r){
				bztlist = r.list;
				created2();
			}
		})

	})
	
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
	
	function created2(){
				var data = bztlist;
	        	
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
					width : 528,
					height : 530,
					radius:70,
				    offset_angle:278,
				    offsety:100
				}).draw();
	
}