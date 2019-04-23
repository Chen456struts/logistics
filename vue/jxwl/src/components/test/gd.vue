<template>
	<div>
		<div id="container"></div>
		<div class="info" style='min-width:10rem;'>
			<img src="//a.amap.com/lbs/static/img/js-drivingonapp.png" style='width:120px;height:120px'>
			<div style='text-align: center'>手机扫码打开示例</div>
		</div>
		<input type='button' id='callApp' class="btn" value='点击调起高德地图' />
	</div>
</template>

<script>
	import VueAMap from "vue-amap"
	let amapManager = new VueAMap.AMapManager();
	export default {
		data: function() {
			return {

			};
		},

		methods: {
			add() {
				var map = new AMap.Map("container", {
					resizeEnable: true,
					zoom: 20 //这里设置没有用，create()函数里面new maker时会把zoom改成默认值
				})
				AMap.plugin(["AMap.Driving"], function() {
					var drivingOption = {
						policy: AMap.DrivingPolicy.LEAST_TIME,
						map: map
					};
					var driving = new AMap.Driving(drivingOption); //构造驾车导航类
					//根据起终点坐标规划驾车路线
					driving.search([{
						keyword: '广东省珠海市南油大酒店'
					}, {
						keyword: '北京大学'
					}], function(status, result) {
						console.log(result);
						driving.searchOnAMAP({
							origin: result.origin,
							destination: result.destination
						});

					});

				});
				map.addControl(new AMap.ToolBar());

			}
		},
		created: {
			/*  
			   AMap.plugin(["AMap.Driving"], function() {
			       var drivingOption = {
			           policy:AMap.DrivingPolicy.LEAST_TIME,
			           map:map
			       };
			       var driving = new AMap.Driving(drivingOption); //构造驾车导航类
			       //根据起终点坐标规划驾车路线
			       driving.search([{keyword:'北京站'},{keyword:'北京大学'}],function(status,result){
			           button.onclick = function(){
			               driving.searchOnAMAP({
			                   origin:result.origin,
			                   destination:result.destination
			               });
			           } 
			       });
			       
			   });
			   map.addControl(new AMap.ToolBar());
			   var button = document.getElementById('callApp');
			   if(AMap.UA.mobile){
			       document.getElementsByClassName('info')[0].style.display='none';
			       button.style.fontSize = '50px';
			   }else{
			       button.style.marginRight = '10px';
			   } */
		}
	};
</script>
