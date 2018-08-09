$(function() {
	vm.query();
})
var vm = new Vue({
		el : '#rrapp',
		data : {
			zfyc : '',
			tpxy : '',
			ymcl : '',
			scjc : '',
			tcw : '',
			tcsl:'',
			jrrc:'',
			jrcc:'',
			bkzl:'',
			jrbk:'',
			ljbj:'',
			jrbj:''
		},
		methods : {
			query : function() {				
				$.ajax({
					type : "POST",
					url : baseURL + "main/query",
					contentType : "application/json",
					data : {},
					success : function(r) {
						vm.zfyc = r.page.zfyc;
					}
				});

			}
		}
	});