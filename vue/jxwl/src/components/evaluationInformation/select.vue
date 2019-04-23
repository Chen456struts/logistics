<style>
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
</style>
<template>
	<div>
		<div class="rigtop">
			<Form ref="evaluationInformation" inline>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
						<Checkbox label="" v-model="sName">货主姓名</Checkbox>
						</Col>
						<Col span="16">
						<Input height="20" placeholder="模糊查询货主姓名"  v-model="evaluationInformation.sName"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
						<Checkbox label="" v-model="dName">司机姓名</Checkbox>
						</Col>
						<Col span="16">
						<Input height="20" placeholder="模糊查询司机姓名" v-model="evaluationInformation.dName"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
						<Checkbox label="" v-model="plfss">评论分数</Checkbox>
						</Col>
						<Col span="16">
						<Select v-model="fsid" filterable>
							<Option v-for="item in fs" :value="item.id" :key="item.id">{{ item.value}}</Option>
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
			<Form ref="formValidate" :model="evaluationInformation" :label-width="80">
				<FormItem label="货主名字" prop="sName">
					<Row>
						<Col span="13">
						<Select v-model="evaluationInformation.sId" @on-change="sNameadd($event)" filterable>
							<Option v-for="item in shipperInformation" :value="item.sId" :key="item.sName">{{ item.sName }}</Option>
						</Select>
						</Col>
						<Col span="4">货主编号：</Col>
						<Col span="7">
						<Input disabled="disabled" :value="evaluationInformation.sId" />
						</Col>
					</Row>
				</FormItem>
				<FormItem label="司机名字" prop="dName">
					<Row>
						<Col span="13">
						<Select v-model="evaluationInformation.dId" @on-change="dNameadd($event)" filterable>
							<Option v-for="item in driverInformation" :value="item.dId" :key="item.dName">{{ item.dName }}</Option>
						</Select>
						</Col>
						<Col span="4">司机编号：</Col>
						<Col span="7">
						<Input disabled="disabled" :value="evaluationInformation.dId" />
						</Col>
					</Row>
				</FormItem>
				<FormItem label="评价分数" prop="fraction">
					<Rate show-text allow-half v-model="evaluationInformation.fraction">
						<span style="color: #f5a623">{{ evaluationInformation.fraction }}</span>
					</Rate>
				</FormItem>
				<FormItem label="评价内容" prop="eContent">
					<Input v-model="evaluationInformation.eContent" type="textarea" :autosize="{minRows: 6,maxRows: 8}" placeholder="内容"></Input>
				</FormItem>
			</Form>
		</Modal>

	</div>
</template>
<script>
	export default {
		data() {
			return {
				fsid: 5,
				modal14: false,
				loading: true,
				sName: false,
				plfss: false,
				dName:false,
				title: '',
				modal14loading: true,
				fs: [{
						id: 1,
						value: "0-1分"
					},
					{
						id: 2,
						value: "1-2分"
					},
					{
						id: 3,
						value: "2-3分"
					},
					{
						id: 4,
						value: "3-4分"
					},
					{
						id: 5,
						value: "5分"
					}
				],
				url: "http://localhost:8080",
				count: 10,
				driverInformation: '',
				shipperInformation: '',
				evaluationInformation: {
					eId: 0,
					dId: 0,
					sId: 0,
					fraction: 5,
					eContent: "",
					sName: "",
					dName: ""
				},
				columns7: [{
						title: '编号',
						key: 'eId',
						align: 'center',
						width: 100
					},
					{
						title: '货主名字',
						key: 'sName',
						align: 'center',
						tooltip: true
					},
					{
						title: '司机名字',
						key: 'dName',
						align: 'center'
					},
					{
						title: '评价分数',
						key: 'fraction',
						align: 'center',
					}, {
						title: '评价内容',
						key: 'eContent',
						tooltip: true,
						align: 'center'
					}, {
						title: '货主编号',
						key: 'dId',
						tooltip: true,
						align: 'center'
					}, {
						title: '司机编号',
						key: 'sId',
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

											this.remove(params.row.eId)
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
			dNameadd(e) {
				for (var i = 0; i < this.driverInformation.length; i++) {
					if ((this.driverInformation[i].dId) == e) {
						this.evaluationInformation.dName = this.driverInformation[i].dName;
					}
				}
			},
			sNameadd(e) {
				for (var i = 0; i < this.shipperInformation.length; i++) {
					if ((this.shipperInformation[i].sId) == e) {
						this.evaluationInformation.sName = this.shipperInformation[i].sName;
					}
				}
			},
			//单击添加
			add() {
				this.title = "添加评价信息";
				this.evaluationInformation.fraction = 5;
				this.evaluationInformation.eContent = "";
				this.modal14 = true;
			},
			//单击编辑
			show(data) {
				this.title = '编辑评价信息'
				console.log(data);
				this.evaluationInformation.eId = data.eId;
				this.evaluationInformation.dId = data.dId;
				this.evaluationInformation.sId = data.sId;
				this.evaluationInformation.fraction = data.fraction;
				this.evaluationInformation.eContent = data.eContent;
				this.evaluationInformation.sName = data.sName;
				this.evaluationInformation.dName = data.dName;
				this.modal14 = true;
			},
			//弹出添加保存
			addok() {
				const th = this;
				var urls = "insert";
				if (this.title == "编辑评价信息") {
					urls = "updateByPrimaryKey";
				}
				axios.post(th.url + '/evaluationInformation/' + urls, th.evaluationInformation, {
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
						axios.get(th.url + '/evaluationInformation/deleteByPrimaryKey', {
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
				const th = this;
				if(!th.plfss){
					this.fsid = 0;
				}
				if(!th.sName){
					this.evaluationInformation.sName = '';
				}
				if(!th.dName){
					this.evaluationInformation.dName = '';
				}
				axios.get(th.url + '/evaluationInformation/selectPage', {
					params: {
						page: page,
						fsid:th.fsid,
						sName:th.evaluationInformation.sName,
						dName:th.evaluationInformation.dName
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
			axios.get(th.url + '/driverInformation/selectAll').then(function(res) {
				th.driverInformation = res.data.data;
			})
			axios.get(th.url + '/shipperInformation/selectAll').then(function(res) {
				th.shipperInformation = res.data.data;
			})
			this.changePage(1);
		}
	}
</script>
