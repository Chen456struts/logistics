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
						<Checkbox label="">姓名</Checkbox>
						</Col>
						<Col span="16">
						<Input height="20" placeholder="模糊查询姓名"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
						<Checkbox label="">手机号码</Checkbox>
						</Col>
						<Col span="16">
						<Input height="20" placeholder="模糊查询手机号码"></Input>
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
					<Button @click="select(1)">
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
		<Modal v-model="modal13" draggable scrollable title="学生会信息" @on-ok="ok">
			<Form ref="formValidate" :model="evaluationInformation" :label-width="80">
				<FormItem label="学生会名称" prop="fraction">
					<Input v-model="evaluationInformation.fraction" placeholder="请输入学生会名称"></Input>
				</FormItem>
				<FormItem label="学生会备注" prop="eContent">
					<Input v-model="evaluationInformation.eContent" type='textarea' :autosize="{minRows: 5,maxRows: 6}" placeholder="请输入学生会备注"></Input>
				</FormItem>
				<FormItem label="排序" prop="eSort">
					<InputNumber :max="100" :min="1" v-model="evaluationInformation.eSort"></InputNumber>
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
				baDate: [],
				loading: true,
				modal13: false,
				url: "http://localhost:8080",
				count: 10,
				evaluationInformation: {
					eId: 0,
					dId: 0,
					sId: 0,
					fraction: "",
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
						key: 'dName',
						align: 'center',
						tooltip: true
					},
					{
						title: '司机名字',
						key: 'sName',
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

											this.remove(params.row.mId, params.index)
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
			//导出数据
			exportData() {
				this.$refs.table.exportCsv({
					filename: '司机信息'
				});
			},
			//查询
			changePage(page) {
				const th = this;
				console.log(th.url);
				axios.get(th.url + '/evaluationInformation/selectPage', {
					params: {
						page: page
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				th.loading = false;
			},
			//添加
			add() {
				this.modal13 = true;
			}

		},
		created() {
			this.changePage(1);
		}
	}
</script>
