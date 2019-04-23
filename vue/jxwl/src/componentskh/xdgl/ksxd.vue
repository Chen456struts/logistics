<template>
	<div class="amap-page-container">
		<Form ref="formValidate" :model="orderInformation" :label-width="80" style="margin-top:10px">
			<Row>
				<Col span="8">
				<FormItem label="车辆类型" prop="oType">
					<Select v-model="orderInformation.oType" filterable>
						<Option v-for="item in vehicleType" :value="item.tId" :key="item.vName">{{ item.vName }}</Option>
					</Select>
				</FormItem>
				</Col>
				<Col span="8">
				<FormItem label="联系人电话" prop="contacts">
					<Input v-model="orderInformation.contacts" :maxlength=18 placeholder="请输入联系人电话"></Input>
				</FormItem>
				</Col>
				<Col span="8">
				<FormItem label="预约时间" prop="startDate">
					<DatePicker type="datetime" format="yyyy-MM-dd HH:mm:ss"  @on-change="datesa()" placeholder="请选择预约时间" v-model="orderInformation.startDate"
					 style="width: 200px"></DatePicker>
				</FormItem>
				</Col>
			</Row>
			<Row>
				<FormItem label="发货地址" prop="shippingAddress">
					<Col span="3">
					<Input v-model="fhcs" :maxlength=18 placeholder="请输入发货城市"></Input>
					</Col>
					<Col span="6">
					<Select v-model="fh" filterable remote :remote-method="remoteMethod1" :loading="loading1">
						<Option v-for="(option, index) in options1" :value="option.name" :key="option.index">{{option.name}}</Option>
					</Select>
					</Col>
					<Col span="2">
					<span>高德发货地址</span>
					</Col>
					<Col span="6">
					<Input v-model="orderInformation.shippingAddress" :maxlength=18 placeholder="发货地址"></Input>
					</Col>
				</FormItem>
			</Row>
			<FormItem label="收货地址" prop="receivingAddress">
				<Col span="3">
				<Input v-model="shcs" :maxlength=18 placeholder="请输入收货城市"></Input>
				</Col>
				<Col span="6">
				<Select v-model="sh" filterable remote :remote-method="remoteMethod2" :loading="loading1">
					<Option v-for="(option, index) in options2" :value="option.name" :key="option.index">{{option.name}}</Option>
				</Select>
				</Col>
				<Col span="2">
				<span>高德收货地址</span>
				</Col>
				<Col span="6">
				<Input v-model="orderInformation.receivingAddress" :maxlength=18 placeholder="收货地址"></Input>
				</Col>
			</FormItem>
			<Row>
				<Col span="8">
				<FormItem label="路程/米" prop="oType">
					<Input v-model="lc" disabled placeholder="自动计算"></Input>
				</FormItem>
				</Col>
				<Col span="8">
				<FormItem label="时间/分钟" prop="contacts">
					<Input v-model="sj" disabled placeholder="自动计算"></Input>
				</FormItem>
				</Col>
				<Col span="8">
				<FormItem label="订单价格" prop="price">
					<Input v-model="orderInformation.price" :maxlength=18 disabled placeholder="自动计算"></Input>
				</FormItem>
				</Col>
			</Row>

			<Row>
				<FormItem label="订单备注" prop="sId">
					<Input v-model="orderInformation.oRemarks" type="textarea" :autosize="{minRows: 3,maxRows: 5}" placeholder="备注"></Input>
				</FormItem>
			</Row>
			<Button type="success" style="width:100%" onclick="return false" @click="ljyy()">立即预约</Button>
		</Form>
	</div>
</template>


<script>
	export default {
		data: function() {
			return {
				lc: '',
				sj: '',
				url: "http://localhost:8080",
				vehicleType: "",
				collectionFee: '',
				model13: '',
				fhcs: '',
				collectionFees: {
					cId: 0,
					cDate: '',
					mileage: '',
					cStarting: 12,
					remarks: '',
					cType: ''
				},
				shcs: '',
				sh: '',
				fh: '',
				fhlocation: '',
				shlocation: '',
				loading1: false,
				options1: [],
				options2: [],
				orderInformation: {
					oId: 0,
					oType: "",
					contacts: "",
					oRemarks: "",
					startDate: new Date(),
					price: "",
					shippingAddress: "",
					receivingAddress: "",
					sId: 1,
					dId: 0,
					oState: "待运输",
					eId: 0,
				}
			};
		},

		methods: {
			remoteMethod1(query) {
				var th = this;
				if (th.fhcs.length == 0) {
					return;
				}
				if (query.length > 0) {
					axios.get(
						'https://restapi.amap.com/v3/assistant/inputtips?key=894fb9d68503edc13aabaf040605f538&keywords=' + query +
						'&type=&location=&city=' + th.fhcs + '&datatype=all'
					).then(function(res) {
						if (res.data.tips.length > 0) {
							th.loading1 = true;
							setTimeout(() => {
								th.loading1 = false;
								th.options1 = res.data.tips;
								th.orderInformation.shippingAddress = th.options1[0].district + query;
								th.fhlocation = th.options1[0].location;
								if (th.orderInformation.receivingAddress.length > 1 && th.orderInformation.shippingAddress.length > 1) {
									th.addss();
								}
							}, 50);
						}
					})
				}
			},
			remoteMethod2(query) {
				var th = this;
				if (th.shcs.length == 0) {
					return;
				}
				if (query.length > 0) {
					axios.get(
						'https://restapi.amap.com/v3/assistant/inputtips?key=894fb9d68503edc13aabaf040605f538&keywords=' + query +
						'&type=&location=&city=' + th.shcs + '&datatype=all'
					).then(function(res) {
						if (res.data.tips.length > 0) {
							th.loading1 = true;
							setTimeout(() => {
								th.loading1 = false;
								th.options2 = res.data.tips;
								th.orderInformation.receivingAddress = th.options2[0].district + query;
								th.shlocation = th.options2[0].location;
								th.datesa();
							}, 100);
						}
					})
				}
			},
			datesa() {
				if (this.orderInformation.receivingAddress.length > 1 && this.orderInformation.shippingAddress.length > 1) {
					this.addss();
				}
			},
			addss() {
				var th = this;
				axios.get(
					'//restapi.amap.com/v3/distance?key=894fb9d68503edc13aabaf040605f538&origins=' + th.fhlocation + '&destination=' +
					th.shlocation + '&type=1'
				).then(function(res) {
					th.setdates();
					//路径距离，单位：米 
					th.lc = res.data.results[0].distance / 1000;
					//预计行驶时间，单位：秒 
					th.sj = res.data.results[0].duration / 60;

				})

			},
			ljyy() {
				var th = this;
					axios.post(th.url + '/orderInformation/insert', th.orderInformation, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 200) {
						th.$Message.success(res.data.message);
					} else {
						th.$Message.error(res.data.message);
					}
				}) 
			},
			setdates() {
				var th = this;
				var now = this.orderInformation.startDate,
					hour = now.getHours();
				if (hour > 6 && hour < 22) {
					th.cDate = 1.2;
					th.mileage = 1.0;

				} else {
					th.cDate = 1.5;
					th.mileage = 1.2;
				}
				th.orderInformation.price = (th.lc * th.mileage) + (th.cDate * th.sj);
				var num1 = parseFloat(th.orderInformation.price);
				if (!isNaN(num1)) {
					num1 = Math.round(num1 * 100) / 100;
					console.log("将浮点数四舍五入，取小数点后2位：");
					th.orderInformation.price = num1;
					console.log(num1);

				}
				if (th.orderInformation.price < 12) {
					th.orderInformation.price = 12;
				}
			},
		},
		created() {
			var th = this;
			axios.get(th.url + '/vehicleType/selectGroup').then(function(res) {
				th.vehicleType = res.data.data;
			})
			axios.get(th.url + '/collectionFee/selectAll').then(function(res) {
				th.collectionFee = res.data.data;
			})
		}
	};
</script>
