<style>
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
</style>
<template>
	<div>
		<div class="rigtop">
			<Form ref="shipperInformation" inline>
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

		<Modal v-model="modal14" :loading="modal14loading" scrollable :title="title" @on-ok="addok">
			<Form ref="formValidate" :model="notice" :label-width="80">
				<FormItem label="公告方向" prop="vColor">
					<Input v-model="notice.direction" disabled :maxlength=20 placeholder="请输入公告方向"></Input>
				</FormItem>
				<FormItem label="公告内容" prop="remarks">
					<Input v-model="notice.nContent" type='textarea' :autosize="{minRows: 5,maxRows: 6}" placeholder="请输入公告内容"></Input>
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
				url: "http://localhost:8080",
				count: 10,
				columns7: [{
						title: '编号',
						key: 'infroId',
						align: 'center',
						width: 100
					},{
						title: '公告内容',
						key: 'nContent',
						align: 'center',
					},
					{
						title: '公告方向',
						key: 'direction',
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
								}, '修改内容')
							]);
						}
					}
				],
				data6: [],
				notice:{
					infroId:0,
					nContent:'',
					direction:'',
				}
			}
		},
		methods: {
			//单击编辑
			show(data) {
				this.title = '编辑公告'
				this.notice.infroId = data.infroId;
				this.notice.nContent = data.nContent;
				this.notice.direction =  data.direction;
				this.modal14 = true;
			},
			//弹出添加保存
			addok(){
			const th = this;
			axios.post(th.url + '/notice/updateByPrimaryKey', th.notice, {
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
				axios.get(th.url + '/notice/selectPage', {
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
