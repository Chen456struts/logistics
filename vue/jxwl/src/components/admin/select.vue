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
					<Row >
						<Col span="8" style="text-align: center;">
						<label >查询账号</label>
						</Col>
						<Col span="16">
						<Input height="20" placeholder="模糊查询账号"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem style="position: relative;left: 10px">
					<Button @click="changePage()(1)">
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
			<Form ref="formValidate" :model="admin" :label-width="80">
				<FormItem label="账号" prop="aAccount">
					<Input v-model="admin.aAccount" :maxlength=20 placeholder="请输入账号"></Input>
				</FormItem>
				<FormItem label="密码" prop="aPassword">
					<Input type="password" v-model="admin.aPassword" :maxlength=20 placeholder="请输入密码"></Input>
				</FormItem>
				<FormItem label="手机号码" prop="aPhone">
					<Input type="password" v-model="admin.aPhone" :maxlength=11 placeholder="请输入手机号码"></Input>
				</FormItem>
				<FormItem label="权限" prop="aRank">
				<Select v-model="admin.aRank" style="width: 150px;">
					<Option v-for="item in qx" :value="item.value" :key="item.value">{{ item.label }}</Option>
				</Select>
				</FormItem>
				<FormItem label="备注" prop="remarks">
					<Input v-model="admin.remarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}" placeholder="备注"></Input>
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
				loading: true,
				qx: [{
						value: '高级管理员',
						label: '高级管理员'
					},
					{
						value: '普通管理员',
						label: '普通管理员'
					}
				],
				url: "http://localhost:8080",
				count: 10,
				columns7: [{
						title: '编号',
						key: 'aId',
						align: 'center',
						width: 100
					},{
						title: '账号',
						key: 'aAccount',
						align: 'center',
					},{
						title: '手机号码',
						key: 'aPhone',
						align: 'center',
					},
					{
						title: '权限',
						key: 'aRank',
						align: 'center',
						tooltip: true
					},{
						title: '备注',
						key: 'remarks',
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

											this.remove(params.row.mId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				admin:{
					aId:0,
					aAccount:'',
					aPassword:'',
					aPhone:'',
					aRank:'',
					remarks:''
				}
			}
		},
		methods: {
			//单击添加
				add() {
					this.title = "添加管理员信息";
					this.admin.aAccount = "";
					this.admin.aPassword = "";
					this.admin.aPhone = "";
					this.admin.aRank = "";
					this.admin.remarks = "";
					this.modal14 = true;
				},
				//单击编辑
				show(data) {
					this.title = '编辑管理员信息'
					this.admin.aId = data.aId;
					this.admin.aAccount = data.aAccount;
					this.admin.aPassword = data.aPassword;
					this.admin.aPhone = data.aPhone;
					this.admin.aRank = data.aRank;
					this.admin.remarks = data.remarks;
					this.modal14 = true;
				},
				//弹出添加保存
				addok(){
				const th = this;
				var urls = "insert";
				if (this.title == "编辑管理员信息") {
					urls = "updateByPrimaryKey";
				}
				axios.post(th.url + '/admin/' + urls, th.admin, {
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
						axios.get(th.url + '/admin/deleteByPrimaryKey', {
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
					filename: '管理员信息'
				});
			},
			//查询
			changePage(page) {
				const th = this;
				console.log(th.url);
				axios.get(th.url + '/admin/selectPage', {
					params: {
						page: page
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
