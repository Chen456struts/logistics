<template>
	<div>
		<Row>
			<Col span="6" style="text-align: center;">
			<div style="background-color: #2d8cf0;height: 120px;margin: 30px;border-radius: 5%; ">
				<Icon type="md-medal" size="100" />
				<div style="background-color: #f5f7f9;height: 25px;">
					<span style="font-size: 16px;position:relative;float:left;left: 10px;">全部订单</span>
					<span style="font-size: 16px;position:relative;float:right;right: 10px;">{{ywc+ysz+dys}}</span>
				</div>
			</div>
			</Col>
			<Col span="6" style="text-align: center;">
			<div style="background-color: #2d8cf0;height: 120px;margin: 30px;border-radius: 5%; ">
				<Icon type="md-checkmark-circle-outline" size="100" />
				<div style="background-color: #f5f7f9;height: 25px;">
					<span style="font-size: 16px;position:relative;float:left;left: 10px;">已完成</span>
					<span style="font-size: 16px;position:relative;float:right;right: 10px;">{{ywc}}</span>
				</div>
			</div>
			</Col>
			<Col span="6" style="text-align: center;">
			<div style="background-color: #2d8cf0;height: 120px;margin: 30px;border-radius: 5%; ">
				<Icon type="md-boat" size="100" />
				<div style="background-color: #f5f7f9;height: 25px;">
					<span style="font-size: 16px;position:relative;float:left;left: 10px;">运输中</span>
					<span style="font-size: 16px;position:relative;float:right;right: 10px;">{{ysz}}</span>
				</div>
			</div>
			</Col>
			<Col span="6" style="text-align: center;">
			<div style="background-color: #2d8cf0;height: 120px;margin: 30px;border-radius: 5%; ">
				<Icon type="logo-rss" size="100" />
				<div style="background-color: #f5f7f9;height: 25px;">
					<span style="font-size: 16px;position:relative;float:left;left: 10px;">待运输</span>
					<span style="font-size: 16px;position:relative;float:right;right: 10px;">{{dys}}</span>
				</div>
			</div>
			</Col>
		</Row>
		<Row style="margin: 30px;margin-top:0px ;">
			<iframe src="https://www.amap.com/" width="100%" height="360" frameborder="0" scrolling="auto">

			</iframe>
		</Row>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				ywc: 0,
				dys: 0,
				ysz: 0,
				url: "http://localhost:8080",
			}
		},
		methods: {

		},
		created() {
const th = this;
			axios.get(th.url + '/orderInformation/getCount', {
						params: {
							sId:localStorage.getItem("mUser")
						}
					})
				.then(function(res) {
					th.dys = Number(res.data.data.dys);
					th.ywc = Number(res.data.data.ywc);
					th.ysz = Number(res.data.data.ysz);
				});
		}
	}
</script>
