<template>
	<div id="myChart" :style="{width: '1150px', height: '550px'}"></div>
</template>

<script>
	import echarts from 'echarts'
	export default {
		data() {
			return {
				ywc:335,
				dys:325,
				ysz:0,
				url: "http://localhost:8080",
			}
		},
		mounted() {
			const th = this;
				axios.get(th.url + '/orderInformation/getCount')
				.then(function(res) {
					th.dys = Number(res.data.data.dys);
					th.ywc = Number(res.data.data.ywc);
					th.ysz = Number(res.data.data.ysz);
					th.drawLine();
				})
				
		},
		methods: {
			drawLine() {
				const th = this;
				let myChart = this.echarts.init(document.getElementById('myChart'))
				myChart.setOption({
					title: {
						text: '锦轩物流系统数据',
						subtext: '来源真实订单数据',
						x: 'center'
					},
					tooltip: {
						trigger: 'item',
						formatter: "{a} <br/>{b} : {c} ({d}%)"
					},
					legend: {
						orient: 'vertical',
						left: 'left',
						data: ['已完成', '待运输', '运输中', '全部订单']
					},
					series: [{
						name: '访问来源',
						type: 'pie',
						radius: '70%',
						center: ['50%', '55%'],
						data: [{
								value: th.ywc,
								name: '已完成'
							},
							{
								value: th.dys,
								name: '待运输'
							},
							{
								value: th.ysz,
								name: '运输中'
							},
							{
								value: (th.ywc+th.dys+th.ysz),
								name: '全部订单'
							}
						],
						itemStyle: {
							emphasis: {
								shadowBlur: 10,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0, 0, 0, 0.5)'
							}
						}
					}]
				})

			}
		}
	}
</script>
