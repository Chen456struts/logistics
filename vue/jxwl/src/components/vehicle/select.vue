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
						类型名称
						</Col>
						<Col span="16">
						<Input height="20" v-model="vName" placeholder="模糊查询类型名称"></Input>
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
			<Form ref="formValidate" :model="vehicleType" :label-width="80">
				<FormItem label="类型名称" prop="vName">
					<Input v-model="vehicleType.vName" :maxlength=10 placeholder="请输入类型名称"></Input>
				</FormItem>
				<FormItem label="载重" prop="vLoad">
					<Input v-model="vehicleType.vLoad" :maxlength=10 placeholder="请输入载重"></Input>
				</FormItem>
				<FormItem label="长 × 宽 × 高" prop="vZgk">
					<Input v-model="vehicleType.vZgk" :maxlength=20 placeholder="请输入车辆长宽高"></Input>
				</FormItem>
				<FormItem label="载货体积" prop="vS">
					<Input v-model="vehicleType.vS" :maxlength=20 placeholder="请输入载货体积"></Input>
				</FormItem>
			</Form>
		</Modal>

	</div>
</template>
<script>
	export default {
		data() {
			return {
				modal14loading: true,
				loading: true,
				modal14: false,
				vName: '',
				title: '',
				url: "http://localhost:8080",
				count: 10,
				vehicleType: {
					tId: 0,
					vName: "",
					vLoad: "",
					vZgk: "",
					vS: "",
				},
				columns7: [{
						title: '编号',
						key: 'tId',
						align: 'center',
						width: 100
					},
					{
						title: '类型名称',
						key: 'vName',
						align: 'center',
						tooltip: true
					},
					{
						title: '载重',
						key: 'vLoad',
						align: 'center'
					},
					{
						title: '长 × 宽 × 高',
						key: 'vZgk',
						align: 'center',
					}, {
						title: '载货体积',
						key: 'vS',
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
											this.remove(params.row.tId)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				typeofMeeting: []
			}
		},
		methods: {
			//单击添加
			add() {
				this.title = "添加车辆类型";
				this.vehicleType.vName = "";
				this.vehicleType.vLoad = "";
				this.vehicleType.vZgk = "";
				this.vehicleType.vS = "";
				this.modal14 = true;
			},
			//单击编辑
			show(data) {
				this.title = '编辑车辆类型'
				this.vehicleType.tId = data.tId;
				this.vehicleType.vName = data.vName;
				this.vehicleType.vLoad =  data.vLoad;
				this.vehicleType.vZgk =  data.vZgk;
				this.vehicleType.vS =  data.vS;
				this.modal14 = true;
			},
			//弹出添加保存
			addok(){
			if (this.vehicleType.vName.length < 1) {
				this.$Message.warning('请输入类型名称!');
				this.modal14show();
				return;
			}
			if (this.vehicleType.vLoad.length < 1) {
				this.$Message.warning('请输入载重!');
				this.modal14show();
				return;
			}
			if (this.vehicleType.vZgk.length < 1) {
				this.$Message.warning('请输入长宽高');
				this.modal14show();
				return;
			}
			if (this.vehicleType.vS.length < 1) {
				this.$Message.warning('请输入载货体积');
				this.modal14show();
				return;
			}
			const th = this;
			var urls = "insert";
			if (this.title == "编辑车辆类型") {
				urls = "updateByPrimaryKey";
			}
			axios.post(th.url + '/vehicleType/' + urls, th.vehicleType, {
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
					axios.get(th.url + '/vehicleType/deleteByPrimaryKey', {
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
				filename: '车辆类型信息'
			});
		},
		//查询
		changePage(page) {
			const th = this;
			axios.get(th.url + '/vehicleType/selectPage', {
				params: {
					page: page,
					vName:th.vName
				}
			}).then(function(res) {
				th.data6 = res.data.data;
				th.count = res.data.count;
			})
			th.loading = false;
		},

	},
	created() {
		this.changePage(1);
	}
	}
</script>
