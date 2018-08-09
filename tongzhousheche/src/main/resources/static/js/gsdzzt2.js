var gsd = {};

function created1(){
			var data = [
						{name : gsd[0].province,value : gsd[0].carnum,color:'#4572a7'},
						{name : gsd[1].province,value : gsd[1].carnum,color:'#4572a7'},
						{name : gsd[2].province,value : gsd[2].carnum,color:'#4572a7'},
						{name : gsd[3].province,value : gsd[3].carnum,color:'#4572a7'},
						{name : gsd[4].province,value : gsd[4].carnum,color:'#4572a7'},
						{name : gsd[5].province,value : gsd[5].carnum,color:'#4572a7'},
						{name : gsd[6].province,value : gsd[6].carnum,color:'#4572a7'},
						{name : gsd[7].province,value : gsd[7].carnum,color:'#4572a7'},
						{name : gsd[8].province,value : gsd[8].carnum,color:'#4572a7'},
						{name : gsd[9].province,value : gsd[9].carnum,color:'#4572a7'},
						{name : gsd[10].province,value : gsd[10].carnum,color:'#4572a7'},
						{name : gsd[11].province,value : gsd[11].carnum,color:'#4572a7'},
						{name : gsd[12].province,value : gsd[12].carnum,color:'#4572a7'},
						{name : gsd[13].province,value : gsd[13].carnum,color:'#4572a7'},
						{name : gsd[14].province,value : gsd[14].carnum,color:'#4572a7'},
						{name : gsd[15].province,value : gsd[15].carnum,color:'#4572a7'},
						{name : gsd[16].province,value : gsd[16].carnum,color:'#4572a7'},
						{name : gsd[17].province,value : gsd[17].carnum,color:'#4572a7'},
						{name : gsd[18].province,value : gsd[18].carnum,color:'#4572a7'},
						{name : gsd[19].province,value : gsd[19].carnum,color:'#4572a7'},
						{name : gsd[20].province,value : gsd[20].carnum,color:'#4572a7'},
						{name : gsd[21].province,value : gsd[21].carnum,color:'#4572a7'},
						{name : gsd[22].province,value : gsd[22].carnum,color:'#4572a7'},
						{name : gsd[23].province,value : gsd[23].carnum,color:'#4572a7'},
						{name : gsd[24].province,value : gsd[24].carnum,color:'#4572a7'},
						{name : gsd[25].province,value : gsd[25].carnum,color:'#4572a7'},
						{name : gsd[26].province,value : gsd[26].carnum,color:'#4572a7'},
						{name : gsd[27].province,value : gsd[27].carnum,color:'#4572a7'},
						{name : gsd[28].province,value : gsd[28].carnum,color:'#4572a7'},
						{name : gsd[29].province,value : gsd[29].carnum,color:'#4572a7'}
		        	];
			
			var chart = new iChart.Column2D({
				render : 'canvasDiv',
				data : data,
				title : {
					text : '车辆归属地各省数量柱状图',
					color : '#3e576f'
				},
				width : 450,
				height : 500,
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
					width : 390,
					height:400,
					axis : {
						color : '#c0d0e0',
						width : [0, 0, 1, 0]
					},
					scale : [{
						position : 'left',
						start_scale : 0,
						end_scale : 20000,
						scale_space : 2000,
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




$(function(){
	$.ajax({
		url:baseURL + "generator/vehiclegs/listvehicle",
		dataType:"json",
		type:"post",
		success:function(r){
			 gsd = r.list;
			created1();
			
		}
	})

})