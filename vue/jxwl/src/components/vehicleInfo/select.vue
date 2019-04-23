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
						<Checkbox label="车牌号" v-model="cbh">车牌号</Checkbox>
						</Col>
						<Col span="16">
						<Input height="10" placeholder="模糊查询车牌号" v-model="license" ></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
						<Checkbox label="车辆类型"v-model="types" >车辆类型</Checkbox>
						</Col>
						<Col span="16">
						<Select v-model="iId"   filterable>
							<Option v-for="item in vehicleType" :value="item.tId" :key="item.vName">{{ item.vName }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem style="position: relative;left: 10px">
					<Button @click="changePage(1)">
						<Icon type="ios-sync" />快速查询
					</Button>
				</FormItem>
				<FormItem style="position: absolute;right: 30px">
					<FormItem>
						<Button @click="add()">
							<Icon type="ios-add-circle-outline" />添加记录
						</Button>
					</FormItem>
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

		<Modal v-model="modal14" :loading="modal14loading" scrollable :title="title" @on-ok="addok">
			<Form ref="formValidate" :model="vehicle" :label-width="80">
				<FormItem label="车牌号" prop="license">
					<Input v-model="vehicle.license" :maxlength=10 placeholder="请输入车牌号"></Input>
				</FormItem>
				<FormItem label="品牌" prop="vType">
					<Input v-model="vehicle.vType" :maxlength=10 placeholder="请输入品牌"></Input>
				</FormItem>
				<FormItem label="车颜色" prop="vColor">
					<Input v-model="vehicle.vColor" :maxlength=20 placeholder="请输入车颜色"></Input>
				</FormItem>
				
				<FormItem label="车辆类型" prop="tType">
				<Select v-model="vehicle.tType"   filterable>
					<Option v-for="item in vehicleType" :value="item.tId" :key="item.tId">{{ item.vName }}</Option>
				</Select>
				</FormItem>
				<FormItem label="司机姓名" prop="dId">
					<Row>
						<Col span="13" >
						<Select v-model="vehicle.dId"  filterable>
								<Option v-for="item in driverInformation" :value="item.dId" :key="item.dId">{{ item.dName }}</Option>
							</Select>
						</Col>
						<Col span="4" >司机编号：</Col>
						<Col span="7" >
							<Input  disabled="disabled" :value="vehicle.dId"/>
						</Col>
					</Row>
				</FormItem>
				<FormItem label="备注" prop="remarks">
					<Input v-model="vehicle.remarks" type='textarea' :autosize="{minRows: 2,maxRows: 6}" placeholder="请输入备注"></Input>
				</FormItem>
			</Form>
		</Modal>

	</div>
</template>
<script>
	export default {
		data() {
			return {
				sex: "",
				title:'',
				cbh:false,
				types:false,
				modal14loading: true,
				loading: true,
				modal14: false,
				license:'',
				baDate: [],
				loading: true,
				url: "http://localhost:8080",
				count: 10,
				iId:1,
				columns7: [{
						title: '编号',
						key: 'vId',
						align: 'center',
						width: 100
					},
					{
						title: '车牌号',
						key: 'license',
						align: 'center',
						tooltip: true
					},
					{
						title: '品牌',
						key: 'vType',
						align: 'center'
					},
					{
						title: '车颜色',
						key: 'vColor',
						align: 'center',
					}, {
						title: '车辆类型',
						key: 'vName',
						tooltip: true,
						align: 'center'
					}, {
						title: '载重',
						key: 'vLoad',
						tooltip: true,
						align: 'center'
					}, {
						title: '长×宽×高',
						key: 'vZgk',
						tooltip: true,
						align: 'center'
					}, {
						title: '载货体积',
						key: 'vS',
						tooltip: true,
						align: 'center'
					}, {
						title: '司机编号',
						key: 'dId',
						tooltip: true,
						align: 'center'
					}, {
						title: '备注',
						key: 'remarks',
						width: 100,
						align: 'center',
						tooltip: true
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
											this.show(params.row)
										}
									}
								}, '编辑'),
								h('Button', {
									props: {
										type: 'error',
										size: 'small'
									},
									on: {
										click: () => {
											this.remove(params.row.vId)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				vehicleType:"",
				vehicle:{
					license:'',
					iId:'',
					vType:'',
					vColor:'',
					tType:'',
					dId:'',
					remarks:'',
				},
				driverInformation:''
			}
		},
		methods: {
			//单击添加
				add() {
					this.title = "添加车辆类型";
					this.vehicle.license = "";
					this.vehicle.vColor = "";
					this.vehicle.tType = "";
					this.vehicle.remarks =  "";
					this.modal14 = true;
				},
				//单击编辑
				show(data) {
					this.title = '编辑车辆类型'
					this.vehicle.license = data.license;
					this.vehicle.vId = data.vId;
					this.vehicle.vType =  data.vType;
					this.vehicle.vColor =  data.vColor;
					this.vehicle.tType =  data.tType;
					this.vehicle.dId =  data.dId;
					this.vehicle.remarks =  data.remarks;
					this.modal14 = true;
				},
				//弹出添加保存
				addok(){
				const th = this;
				var urls = "insert";
				if (this.title == "编辑车辆类型") {
					urls = "updateByPrimaryKey";
				}
				axios.post(th.url + '/vehicle/' + urls, th.vehicle, {
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
			modal14show() {
				this.modal14 = false;
				setTimeout(() => {
					this.modal14 = true;
				}, 0);
			},
			//删除操作
			remove(id) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/vehicle/deleteByPrimaryKey', {
							params: {
								id: id
							}
						}).then(function(res) {
							if (res.data.code === 200) {
								th.$Message.success(res.data.message);
								th.changePage(1);
							} else {
								th.$Message.error(res.data.message);
							}
						})
					}
				});
			},
			//导出数据
			exportData() {
				this.$refs.table.exportCsv({
					filename: '司机信息'
				});
			},
			//查询
			changePage(page) {
				if(!this.cbh){
					this.license = '';
				}
				if(!this.types){
					this.iId = '';
				}
				const th = this;
				axios.get(th.url + '/vehicle/selectPage', {
					params: {
						page: page,
						license:th.license,
						iId:th.iId
					}
				}).then(function(res) {
					if(res.data.data.length>0){
						var arrayMap = res.data.data.map(e => {
							e.vName = e.vehicleType.vName;
							e.vLoad = e.vehicleType.vLoad;
							e.vZgk = e.vehicleType.vZgk;
							e.vS = e.vehicleType.vS;
							return e;
						})
						th.data6 =arrayMap;
					}else{
						th.data6 = [];
					}
					th.count = res.data.count;
				})
				th.loading = false;
			}
		},
		created() {
			var th = this;
			axios.get(th.url + '/vehicleType/selectGroup').then(function(res) {
				th.vehicleType = res.data.data;
			})
			axios.get(th.url + '/driverInformation/selectAll').then(function(res) {
				th.driverInformation = res.data.data;
			})
			this.changePage(1);
			
			
		}
	}
</script>
