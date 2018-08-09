var gsd = {};

function created1(){
			var data = zztlist;
			
			var chart = new iChart.Column2D({
				render : 'canvasDiv',
				data : data,
				background_color : '#0f3054',
				title : {
					text : '车辆归属地',
					color : '#ffffff'
				},
				width : 620,
				height : 530,
				border:{
					color : '#0d2f65'
				},
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
				shadow_color : '#0f3054',
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
					background_color : '#0f3054',
					grid_color : '#c0c0c0',
					width : 450,
					height:400,
					axis : {
						color : '#c0d0e0',
						width : [0, 0, 1, 0]
					},
					scale : [{
						position : 'left',
						start_scale : 0,
						end_scale : 400000,
						scale_space : 30000,
						scale_enable : true,
						label : {
							fontsize:11,
							color : '#ffffff'
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
						.fillText('车辆数(万辆)',x-40,y-10,false,'#ffffff');
						
					}
			}));
			
			chart.draw();

}	




$(function(){
	$.ajax({
		url:baseURL + "generator/vehiclegs/listzzt",
		dataType:"json",
		type:"post",
		success:function(r){
			zztlist = r.list;
			created1();
			
		}
	})

})