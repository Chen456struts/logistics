<style>
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
</style>
<template>
	<div>
		<div class="rigtop">
			<Form ref="shipperInformation" inline>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
						<Checkbox v-model="ddbhs">订单编号</Checkbox>
						</Col>
						<Col span="16">
						<Input height="20" v-model="orderInformation.oId" placeholder="模糊查询订单编号"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
						<Checkbox v-model="lxhms">联系号码</Checkbox>
						</Col>
						<Col span="16">
						<Input height="20" v-model="orderInformation.contacts" placeholder="模糊查询联系人手机号码"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem style="position: relative;left: 10px">
					<Button @click="changePage(1)">
						<Icon type="ios-sync" />快速查询
					</Button>
				</FormItem>
				<FormItem style="position: absolute;right: 30px">
					<Button @click="exportData()">
						<Icon type="ios-download-outline" />数据导出
					</Button>
				</FormItem>
			</Form>
		</div>

		<Table border :columns="columns7" :data="data6" height="450" :loading="loading" stripe size='default' ref="table"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>

		<Modal v-model="modal14" :loading="modal14loading" fullscreen :title="title" @on-ok="addok">
			<Form ref="formValidate" :model="orderInformation" :label-width="80">
				<Row>
					<Col span="8">
					<FormItem label="订单编号" prop="oId">
						<Input v-model="orderInformation.oId" :maxlength=18 placeholder="自动录入"></Input>
					</FormItem>
					</Col>
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
				</Row>
				<Row>
					<Col span="8">
					<FormItem label="发货地址" prop="shippingAddress">
						<Input v-model="orderInformation.shippingAddress" placeholder="请输入发货地址"></Input>
					</FormItem>
					</Col>
					<Col span="8">
					<FormItem label="收货地址" prop="receivingAddress">
						<Input v-model="orderInformation.receivingAddress" placeholder="请输入收货地址"></Input>
					</FormItem>
					</Col>
					<Col span="8">
					<FormItem label="订单价格" prop="price">
						<Input v-model="orderInformation.price" :maxlength=18 placeholder="请输入订单价格"></Input>
					</FormItem>
					</Col>
				</Row>
				<Row>
					<Col span="8">
					<FormItem label="运输车牌号" prop="dId">
						<Select v-model="orderInformation.dId" filterable>
							<Option v-for="item in vehicle" :value="item.iId" :key="item.iId">{{ item.license }}</Option>
						</Select>
					</FormItem>
					</Col>
					<Col span="8">
					<FormItem label="货主名字" prop="sId">
						<Select v-model="orderInformation.sId" filterable>
							<Option v-for="item in shipperInformation" :value="item.sId" :key="item.sId">{{ item.sName }}</Option>
						</Select>
					</FormItem>
					</Col>
					<Col span="8">
					<FormItem label="评价id" prop="eId">
						<Input v-model="orderInformation.eId" :maxlength=18 placeholder="请输入订单状态"></Input>
					</FormItem>
					</Col>
				</Row>

				<Row>
					<Col span="8">
					<FormItem label="预约时间">
						<DatePicker type="date" placeholder="预约时间" @on-change="getStartTime" v-model="orderInformation.startDate"></DatePicker>
					</FormItem>
					</Col>
					<Col span="8">
					<FormItem label="完成时间">
						<DatePicker type="date" placeholder="请选择完成时间" @on-change="getStartTimeend" v-model="orderInformation.endDate"></DatePicker>
					</FormItem>
					</Col>
					<Col span="8">
					<FormItem label="状态">
						<RadioGroup v-model="orderInformation.oState">
							<Radio label="待运输">
								<Icon type="ios-eye" />
								<span>待运输</span>
							</Radio>
							<Radio label="运输中">
								<Icon type="ios-eye-off" />
								<span>运输中</span>
							</Radio>
							<Radio label="已完成">
								<Icon type="ios-football-outline" />
								<span>已完成</span>
							</Radio>
						</RadioGroup>
					</FormItem>
					</Col>
				</Row>
				<Row>
					<Col span="24">
					<FormItem label="订单备注" prop="sId">
						<Input v-model="orderInformation.oRemarks" type="textarea" :autosize="{minRows: 3,maxRows: 5}" placeholder="备注"></Input>
					</FormItem>
					</Col>
				</Row>
			</Form>
		</Modal>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				modal14: false,
				loading: true,
				modal14loading: true,
				title: '添加订单',
				vehicleType: "",
				vehicle: "",
				ddbhs: false,
				lxhms: false,
				url: "http://localhost:8080",
				count: 10,
				shipperInformation: "",
				columns7: [{
						title: '订单编号',
						key: 'oId',
						align: 'center',
						width: 100
					},
					{
						title: '联系人手机',
						key: 'contacts',
						align: 'center',
						tooltip: true
					},
					{
						title: '预约时间',
						key: 'startDate',
						align: 'center'
					},
					{
						title: '完成时间',
						key: 'endDate',
						align: 'center',
					}, {
						title: '订单价格',
						key: 'price',
						tooltip: true,
						align: 'center'
					}, {
						title: '发货地址',
						key: 'shippingAddress',
						tooltip: true,
						align: 'center'
					}, {
						title: '收货地址',
						key: 'receivingAddress',
						tooltip: true,
						align: 'center'
					}, {
						title: '货主编号',
						key: 'sId',
						tooltip: true,
						align: 'center'
					}, {
						title: '订单状态',
						key: 'oState',
						tooltip: true,
						align: 'center'
					},
					{
						title: '操作',
						key: 'action',
						width: 150,
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Button', {
									props: {
										type: 'primary',
										size: 'small'
									},
									style: {
										marginRight: '5px'
									},
									on: {
										click: () => {
											this.show(params.row.oId)
										}
									}
								}, '完成订单')
							]);

						}
					}
				],
				data6: [],
				orderInformation: {
					oId: 0,
					oType: "",
					contacts: "",
					oRemarks: "",
					startDate: "",
					endDate: "",
					price: "",
					shippingAddress: "",
					receivingAddress: "",
					sId: 0,
					dId: 0,
					oState: "待运输",
					eId: 0,
				}
			}
		},
		methods: {
			//时间
			getStartTime(starTime) {
				this.orderInformation.startDate = starTime;
			},
			//时间
			getStartTimeend(starTime) {
				this.orderInformation.endDate = starTime;
			},
			//单击添加
			add() {
				this.title = "添加订单";
				this.modal14 = true;
			},
			//弹出添加保存
			addok() {
				const th = this;
				var urls = "insert";
				if (this.title == "编辑车辆类型") {
					urls = "updateByPrimaryKey";
				}
				axios.post(th.url + '/orderInformation/' + urls, th.orderInformation, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 200) {
						th.$Message.success(res.data.message);
						th.modal14 = false;
						th.changePage(1);
					} else {
						th.modal14show();
						th.$Message.error(res.data.message);
					}
				})

			},
			//单击编辑
			show(oId) {
				var th = this;
				axios.get(th.url + '/orderInformation/updateSet', {
					params: {
						oId: oId,
						oState: "已完成",
						endDate:1
					}
				}).then(function(res) {
					if (res.data.code === 200) {
						th.$Message.success("操作成功!");
						th.changePage(1);
					}else{
						th.$Message.success("操作失败!");
					}
				})
			},
			//导出数据
			exportData() {
				this.$refs.table.exportCsv({
					filename: '待运输信息表'
				});
			},
			//查询
			changePage(page) {
				const th = this;
				if (!th.ddbhs) {
					th.orderInformation.oId = '';
				}
				if (!th.lxhms) {
					th.orderInformation.contacts = '';
				}
				axios.get(th.url + '/orderInformation/selectStart', {
					params: {
						page: page,
						oId: th.orderInformation.oId,
						oStart: '运输中',
						contacts: th.orderInformation.contacts,
						dId: localStorage.getItem("mUser")
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				th.loading = false;
			},
		},
		created() {
			var th = this;
			axios.get(th.url + '/shipperInformation/selectAll').then(function(res) {
				th.shipperInformation = res.data.data;
			})
			axios.get(th.url + '/vehicleType/selectGroup').then(function(res) {
				th.vehicleType = res.data.data;
			})
			axios.get(th.url + '/vehicle/selectAll').then(function(res) {
				th.vehicle = res.data.data;
			})
			this.changePage(1);
		}
	}
</script>
