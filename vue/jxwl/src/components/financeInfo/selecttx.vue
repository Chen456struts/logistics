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
						<Checkbox label="" v-model="jyrs">交易人</Checkbox>
						</Col>
						<Col span="16">
						<Input height="20" v-model="jyr" placeholder="模糊查询交易人"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					交易身份 :
					<RadioGroup v-model="types">
						<Radio label="">
							<Icon type="ios-eye" />
							<span>全部</span>
						</Radio>
						<Radio label="司机">
							<Icon type="ios-eye" />
							<span>司机</span>
						</Radio>
						<Radio label="货主">
							<Icon type="ios-eye-off" />
							<span>货主</span>
						</Radio>
					</RadioGroup>
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

		<Modal v-model="modal14" :loading="modal14loading" width="750" scrollable :title="title" @on-ok="addok">
			<Form ref="formValidate" :model="financeInfo" :label-width="80">
				<Row>
					<Col span="8">
					<FormItem label="编号" prop="f_id">
						<Input v-model="financeInfo.fId" placeholder="自动识别"></Input>
					</FormItem>
					</Col>
					<Col span="8">
					<FormItem label="交易前余额" prop="befor_money">
						<Input v-model="financeInfo.beforMoney" :maxlength=10 placeholder="请输入交易前余额"></Input>
					</FormItem>
					</Col>
					<Col span="8">
					<FormItem label="交易后余额" prop="after_money">
						<Input v-model="financeInfo.afterMoney" :maxlength=20 placeholder="请输入交易后余额"></Input>
					</FormItem>
					</Col>
				</Row>
				<Row>
					<Col span="8">
					<FormItem label="交易余额" prop="f_money">
						<Input v-model="financeInfo.fMoney" :maxlength=20 placeholder="请输入交易余额"></Input>
					</FormItem>
					</Col>
					<Col span="8">
					<FormItem label="交易类型" prop="f_type">
						<Input v-model="financeInfo.fType" :maxlength=20 placeholder="请输入交易类型"></Input>
					</FormItem>
					</Col>
					<Col span="8">
					<FormItem label="交易身份" prop="f_name_type">
						<Input v-model="financeInfo.fNameType" :maxlength=20 placeholder="请输入交易身份"></Input>
					</FormItem>
					</Col>
				</Row>
				<Row>
					<Col span="8">
					<FormItem label="交易人id" prop="f_ids">
						<Input v-model="financeInfo.fIds" :maxlength=20 placeholder="请输入交易人id"></Input>
					</FormItem>
					</Col>
					<Col span="8">
					<FormItem label="交易人名字" prop="f_name">
						<Input v-model="financeInfo.fName" :maxlength=20 placeholder="请输入交易人名字"></Input>
					</FormItem>
					</Col>
					<Col span="8">
					<FormItem label="交易时间" prop="f_date">
						<Input v-model="financeInfo.fDate" :maxlength=20 placeholder="请输入交易时间"></Input>
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
				modal14loading: true,
				loading: true,
				modal14: false,
				title: '',
				cityList: [{
						value: '充值',
						label: '充值'
					},
					{
						value: '支付',
						label: '支付'
					},
					{
						value: '提现',
						label: '提现'
					}
				],
				types: '',
				loading: true,
				url: "http://localhost:8080",
				count: 10,
				columns7: [{
						title: '编号',
						key: 'fId',
						align: 'center',
						width: 100
					},
					{
						title: '交易前余额',
						key: 'beforMoney',
						align: 'center',
						tooltip: true
					}, {
						title: '交易余额',
						key: 'fMoney',
						align: 'center',
					},
					{
						title: '交易后余额',
						key: 'afterMoney',
						align: 'center'
					},
					{
						title: '交易类型',
						key: 'fType',
						tooltip: true,
						align: 'center'
					}, {
						title: '交易身份',
						key: 'fNameType',
						tooltip: true,
						align: 'center'
					}, {
						title: '交易人名字',
						key: 'fName',
						tooltip: true,
						align: 'center'
					}, {
						title: '交易时间',
						key: 'fDate',
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
											this.remove(params.row.fId);
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				jylx: '',
				jyr: '',
				jyrs: false,
				jylxs: false,
				financeInfo: {
					fId: 0,
					beforMoney: 0,
					afterMoney: 0,
					fMoney: 0,
					fType: '',
					fNameType: '',
					fIds: 0,
					fName: '',
					fDate: ''
				}
			}
		},
		methods: {
			//单击添加
			add() {
				this.title = "添加车辆类型";
				this.financeInfo.fName = "";
				this.financeInfo.fNameType = "";
				this.modal14 = true;
			},
			//单击编辑
			show(data) {
				this.title = '编辑车辆类型'
				this.financeInfo.fId = data.fId;
				this.financeInfo.beforMoney = data.beforMoney;
				this.financeInfo.afterMoney = data.afterMoney;
				this.financeInfo.fMoney = data.fMoney;
				this.financeInfo.fType = data.fType;
				this.financeInfo.fNameType = data.fNameType;
				this.financeInfo.fIds = data.fIds;
				this.financeInfo.fName = data.fName;
				this.financeInfo.fDate = data.fDate;
				this.modal14 = true;
			},
			//弹出添加保存
			addok() {
				const th = this;
				var urls = "insert";
				if (this.title == "编辑车辆类型") {
					urls = "updateByPrimaryKey";
				}
				axios.post(th.url + '/financeInfo/' + urls, th.financeInfo, {
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
						axios.get(th.url + '/financeInfo/deleteByPrimaryKey', {
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
				if (!this.jylxs) {
					this.jylx = '';
				}
				if (!this.jyrs) {
					this.jyr = '';
				}
				const th = this;
				axios.get(th.url + '/financeInfo/selectType', {
					params: {
						page: page,
						fName: th.jyr,
						fNameType: th.types,
						fType:'提现'
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
