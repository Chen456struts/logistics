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
						<Checkbox label="费用类型" v-model="fylxs">费用类型</Checkbox>
						</Col>
						<Col span="16">
						<Input height="20" v-model="fylx" placeholder="模糊查询费用类型"></Input>
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
		<Modal v-model="modal14" :loading="modal14loading" width="750" scrollable :title="title" @on-ok="addok">
			<Form ref="formValidate" :model="collectionFee" :label-width="80">
				<Row>
					<Col span="8">
					<FormItem label="编号" prop="cId">
						<Input v-model="collectionFee.cId" disabled placeholder="自动识别"></Input>
					</FormItem>
					</Col>
					<Col span="8">
					<FormItem label="起步价" prop="cStarting">
						<Input v-model="collectionFee.cStarting" :maxlength=10 placeholder="请输入起步价"></Input>
					</FormItem>
					</Col>
					<Col span="8">
					<FormItem label="时间收费/分钟" prop="cDate">
						<Input v-model="collectionFee.cDate" :maxlength=20 placeholder="请输入时间收费/分钟"></Input>
					</FormItem>
					</Col>
				</Row>
				<Row>
					<Col span="8">
					<FormItem label="里程收费/分钟" prop="mileage">
						<Input v-model="collectionFee.mileage" :maxlength=20 placeholder="请输入里程收费/分钟"></Input>
					</FormItem>
					</Col>
					<Col span="8">
					<FormItem label="收费类型" prop="f_type">
						<Input v-model="collectionFee.cType" :maxlength=20 placeholder="请输入收费类型"></Input>
					</FormItem>
					</Col>
					<Col span="8">
					<FormItem label="备注" prop="remarks">
						<Input v-model="collectionFee.remarks" :maxlength=20 placeholder="请输入备注"></Input>
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
				fylx: "",
				fylxs: false,
				loading: true,
				modal14: false,
				loading: true,
				modal14loading: true,
				title:'',
				url: "http://localhost:8080",
				count: 10,
				columns7: [{
						title: '编号',
						key: 'cId',
						align: 'center',
						width: 100
					}, {
						title: '起步价',
						key: 'cStarting',
						align: 'center',
					},
					{
						title: '时间收费/分钟',
						key: 'cDate',
						align: 'center',
						tooltip: true
					},
					{
						title: '里程收费/分钟',
						key: 'mileage',
						align: 'center'
					},
					{
						title: '收取费用类型',
						key: 'cType',
						tooltip: true,
						align: 'center'
					}, {
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
											this.remove(params.row.cId)
										}
									}		
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				collectionFee: {
					cId:0,
					cDate:'',
					mileage:'',
					cStarting:'',
					remarks:'',
					cType:''
				}
			}
		},
		methods: {
			//单击添加
			add() {
				this.title = "添加收费";
				this.collectionFee.cType = '';
				this.modal14 = true;
			},
			//单击编辑
			show(data){
				this.title = '编辑收费';
				this.collectionFee.cId=data.cId;
				this.collectionFee.cDate=data.cDate;
				this.collectionFee.mileage=data.mileage;
				this.collectionFee.cStarting=data.cStarting;
				this.collectionFee.remarks=data.remarks;
				this.collectionFee.cType=data.cType;
				this.modal14 = true;
			},
			//弹出添加保存
			addok() {
				const th = this;
				var urls = "insert";
				if(this.title == "编辑收费"){
					urls = "updateByPrimaryKey";
				}
				axios.post(th.url + '/collectionFee/'+urls, th.collectionFee, {
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
						axios.get(th.url + '/collectionFee/deleteByPrimaryKey', {
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
				if (!th.fylxs) {
					th.fylx = '';
				}
				axios.get(th.url + '/collectionFee/selectPage', {
					params: {
						page: page,
						cType:th.fylx
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
