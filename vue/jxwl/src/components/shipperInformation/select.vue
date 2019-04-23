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
							<Checkbox label="姓名" v-model="sNames" >姓名</Checkbox>
							</Col>
							<Col span="16">
							<Input height="20" placeholder="模糊查询姓名" v-model="sName" ></Input>
							</Col>
						</Row>
					</FormItem>
					<FormItem>
						<Row>
							<Col span="8" style="text-align: center;">
							<Checkbox label="手机号码" v-model="sPhones">手机号码</Checkbox>
							</Col>
							<Col span="16">
							<Input height="20" placeholder="模糊查询手机号码"  v-model="sPhone"></Input>
							</Col>
						</Row>
					</FormItem>
					<FormItem>
						性别 :
						<RadioGroup v-model="sex">
							<Radio label="">
								<Icon type="ios-eye" />
								<span>全部</span>
							</Radio>
							<Radio label="男">
								<Icon type="ios-eye" />
								<span>男</span>
							</Radio>
							<Radio label="女">
								<Icon type="ios-eye-off" />
								<span>女</span>
							</Radio>
						</RadioGroup>
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
			<Form ref="formValidate" :model="shipperInformation" :label-width="80">
				<FormItem label="姓名" prop="sName">
					<Input v-model="shipperInformation.sName" :maxlength=6 placeholder="请输入姓名"></Input>
				</FormItem>
				<FormItem label="身份证号码" prop="sUuid">
					<Input v-model="shipperInformation.sUuid" :maxlength=18 placeholder="请输入身份证号码"></Input>
				</FormItem>
				<FormItem label="手机号码" prop="sPhone">
					<Input v-model="shipperInformation.sPhone" :maxlength=11 placeholder="请输入手机号码"></Input>
				</FormItem>
				<FormItem label="密码" prop="sPassword">
					<Input type="password" v-model="shipperInformation.sPassword" :maxlength=16 placeholder="请输入密码"></Input>
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
				sPhone:'',
				sName:'',
				sPhones:false,
				sNames:false,
				modal14: false,
				loading: true,
				modal14loading: true,
				title:'添加货主',
				url: "http://localhost:8080",
				count: 10,
				columns7: [{
						title: '编号',
						key: 'sId',
						align: 'center',
						width: 100
					},
					{
						title: '货主姓名',
						key: 'sName',
						align: 'center',
						tooltip:true
					},
					{
						title: '性别',
						key: 'sSex',
						align: 'center'
					},
					{
						title: '手机号码',
						key: 'sPhone',
						align: 'center',
					}, {
						title: '身份证',
						key: 'sUuid',
						tooltip: true,
						align: 'center'
					},{
						title: '注册时间',
						key: 'registerDate',
						align: 'center',
						tooltip:true
					},
					{
						title: '余额',
						key: 'sBalance',
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
											this.remove(params.row.sId)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				shipperInformation: {
					sId: 0,
					sName: '',
					sSex: '',
					sUuid: '',
					sPhone: '',
					sPassword: '',
					dBalance: 0,
					registerDate: ''
				},
			}
		},
		methods: {
			//单击添加
			add() {
				this.title = "添加货主";
				this.shipperInformation.sPhone = "";
				this.shipperInformation.sUuid = "";
				this.shipperInformation.sName = "";
				this.shipperInformation.sPassword = "";
				this.modal14 = true;
			},
			//单击编辑
			show(data){
				this.title = '编辑货主'
				this.modal14 = true;
				this.shipperInformation.sId = data.sId;
				this.shipperInformation.sPhone = data.sPhone;
				this.shipperInformation.sUuid = data.sUuid;
				this.shipperInformation.sName = data.sName;
				this.shipperInformation.sPassword = data.sPassword;
			},
			//弹出添加保存
			addok() {
				if (this.shipperInformation.sName.length < 1) {
					this.$Message.warning('请输入名字!');
					this.modal14show();
					return;
				}
				if (this.shipperInformation.sPhone.length < 1 || this.shipperInformation.sPhone.length != 11) {
					this.$Message.warning('电话号码不正确，需要11位手机号码!');
					this.modal14show();
					return;
				}
				if (this.shipperInformation.sPassword.length < 6 ) {
					this.$Message.warning('密码不正确,需要6位以上!');
					this.modal14show();
					return;
				}
				var text = this.shipperInformation.sUuid;
				text = text.toUpperCase();
				var aa = ["7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"];
				var bb = ["1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"];
				var sum = 0;
				for (var i = 0; i < 17; i++) {
					sum += text.substring(i, 1 + i) * aa[i];
				}
				if (bb[sum % 11] != text.substring(17, 18) || text.length != 18) {
					this.$Message.error('身份证有误!');
					this.modal14show();
					return;
				}
				this.shipperInformation.sSex = text.substring(16, 17) % 2 == 0 ? "女" : "男";
				const th = this;
				var urls = "insert";
				if(this.title == "编辑货主"){
					urls = "updateByPrimaryKey";
				}
				axios.post(th.url + '/shipperInformation/'+urls, th.shipperInformation, {
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
			remove(id){
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/shipperInformation/deleteByPrimaryKey', {
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
					filename: '货主信息'
				});
			},
			changePage(page) {
				const th = this;
				th.loading = true;
				if(!th.sPhones){
					th.sPhone = '';
				}
				if(!th.sNames){
					th.sName='';
				}
				axios.get(th.url + '/shipperInformation/selectPage', {
					params: {
						page: page,
						sPhone: th.sPhone,
						sName: th.sName,
						sSex: th.sex
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				th.loading=false;
			},
		},
		created() {
			this.changePage(1);
		}
	}
</script>
