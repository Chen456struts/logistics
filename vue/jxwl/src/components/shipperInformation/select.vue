<style>
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
</style>
<template>
	<div>
		<div class="rigtop">
			<Form ref="shipperInformation"  inline>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
						<Checkbox label="">姓名</Checkbox>
						</Col>
						<Col span="16">
						<Input  height="20" placeholder="模糊查询姓名"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
						<Checkbox label="">手机号码</Checkbox>
						</Col>
						<Col span="16">
						<Input  height="20" placeholder="模糊查询手机号码"></Input>
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
						<Button  @click="add()">
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
		
	</div>
</template>
<script>
	export default {
		data() {
			return {
				sex: "",
				baDate: [],
				loading: true,
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
						width:200,
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
						width: 100,
						tooltip: true,
						align: 'center'
					},
					{
						title: '余额',
						key: 'sBalance',
						width: 200,
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
					filename: '货主信息'
				});
			},
			//查询
			changePage(page) {
				const th = this;
				console.log(th.url);
				axios.get(th.url + '/shipperInformation/selectPage', {
					params: {
						pageNum: page
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
