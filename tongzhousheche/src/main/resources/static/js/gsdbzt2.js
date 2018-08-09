	$(function(){
		$.ajax({
			url:baseURL + "generator/vehiclegs/listvehicle",
			dataType:"json",
			type:"post",
			success:function(r){
				gsd = r.list;
				created2();
			}
		})
	})
	
	
	
	function created2(){
		var data = [
			{name : gsd[0].province,value : gsd[0].percent,color:'#9d4a4a'},
			{name : gsd[1].province,value : gsd[1].percent,color:'#FF0000'},
			{name : gsd[2].province,value : gsd[2].percent,color:'#5d7f97'},
			{name : gsd[3].province,value : gsd[3].percent,color:'#4572a7'},
			{name : gsd[4].province,value : gsd[4].percent,color:'#00FF00'},
			{name : gsd[5].province,value : gsd[5].percent,color:'#FF0000'},
			{name : gsd[6].province,value : gsd[6].percent,color:'#778088'},
			{name : gsd[7].province,value : gsd[7].percent,color:'#871F78'},
			{name : gsd[8].province,value : gsd[8].percent,color:'#9d4a4a'},
			{name : gsd[9].province,value : gsd[9].percent,color:'#00FF00'},
			{name : gsd[10].province,value : gsd[10].percent,color:'#4572a7'},
			{name : gsd[11].province,value : gsd[11].percent,color:'#871F78'},
			{name : gsd[12].province,value : gsd[12].percent,color:'#4572a7'},
			{name : gsd[13].province,value : gsd[13].percent,color:'#97b3bc'},
			{name : gsd[14].province,value : gsd[14].percent,color:'#4572a7'},
			{name : gsd[15].province,value : gsd[15].percent,color:'#a5aaaa'},
			{name : gsd[16].province,value : gsd[16].percent,color:'#FF0000'},
			{name : gsd[17].province,value : gsd[17].percent,color:'#6f83a5'},
			{name : gsd[18].province,value : gsd[18].percent,color:'#97b3bc'},
			{name : gsd[19].province,value : gsd[19].percent,color:'#778088'},
			{name : gsd[20].province,value : gsd[20].percent,color:'#00FF00'},
			{name : gsd[21].province,value : gsd[21].percent,color:'#6f83a5'},
			{name : gsd[22].province,value : gsd[22].percent,color:'#97b3bc'},
			{name : gsd[23].province,value : gsd[23].percent,color:'#871F78'},
			{name : gsd[24].province,value : gsd[24].percent,color:'#4572a7'},
			{name : gsd[25].province,value : gsd[25].percent,color:'#97b3bc'},
			{name : gsd[26].province,value : gsd[26].percent,color:'#6f83a5'},
			{name : gsd[27].province,value : gsd[27].percent,color:'#4572a7'},
			{name : gsd[28].province,value : gsd[28].percent,color:'#6f83a5'},
			{name : gsd[29].province,value : gsd[29].percent,color:'#5d7f97'}
		];
	        	
		new iChart.Pie2D({
			render : 'canvasDiv1',
			data: data,
			title : '车辆归属地各省所占百分比饼状图',
			legend : {
				enable : true
			},
			showpercent:true,
			decimalsnum:2,
			width : 500,
			height : 530,
			radius:50
		}).draw();
	
}