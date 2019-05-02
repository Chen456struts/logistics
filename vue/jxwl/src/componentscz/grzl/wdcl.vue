<template>
	<div class="amap-page-container">
		<div class="rigtop" style="text-align: center;font-size: 18px;border: 0px;">
			<marquee style="width: 100%;">
				如信息有问题请拨打400-1000-0001电话联系客服进行修改信息，感谢您对我们工作的支持，谢谢！
			</marquee>
			</MenuItem>
		</div>
		<Form ref="formValidate" :model="vehicle" :label-width="80" style="position:relative;left: 110px;">
			<Row>
				<Col span="8">
				<FormItem label="车牌号" prop="license">
					<Input v-model="vehicle.license" style="" :disabled="yzs"  :maxlength=10 placeholder="请输入车牌号"></Input>
				</FormItem>
				</Col>
				<Col span="8">
				<FormItem label="品牌" prop="vType">
					<Input v-model="vehicle.vType" :disabled="yzs":maxlength=10 placeholder="请输入品牌"></Input>
				</FormItem>
				</Col>
			</Row>
			<Row>
				<Col span="8">
				<FormItem label="车颜色" prop="vColor">
					<Select v-model="vehicle.vColor" :disabled="yzs" filterable>
						<Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
					</Select>
				</FormItem>
				</Col>
				<Col span="8">
				<FormItem label="车辆类型" prop="tType">
					<Select v-model="vehicle.tType" :disabled="yzs" filterable >
						<Option v-for="item in vehicleType" :value="item.tId" :key="item.tId">{{ item.vName }}</Option>
					</Select>
				</FormItem>
				</Col>
			</Row>
			<Row>
				<Col span="16">
				<FormItem label="备注"  prop="remarks">
					<Input v-model="vehicle.remarks" :disabled="yzs" type='textarea' :autosize="{minRows: 2,maxRows: 6}" placeholder="备注"></Input>
				</FormItem>
				</Col>
			</Row>
			<FormItem>
				<a href="" @click="add()">
					<Button type="success" onclick="return false" :disabled="yzs"   style="width:650px;"> 设置车辆 </Button>
				</a>
			</FormItem>
		</Form>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				yzs:true,
				cityList: [{
						value: '红色',
						label: '红色'
					}, {
						value: '黑色',
						label: '黑色'
					},
					{
						value: '白色',
						label: '白色'
					},
					{
						value: '棕色',
						label: '棕色'
					}, {
						value: '绿色',
						label: '绿色'
					},
					{
						value: '蓝色',
						label: '蓝色'
					}
				],
				url: "http://localhost:8080",
				vehicleType: '',
				vehicle: {
					license: '',
					iId: '',
					vType: '',
					vColor: '',
					tType: '',
					dId: '',
					remarks: '',
				}
			}
		},
		methods: {
			add() {
				var th = this;
				this.vehicle.dId = localStorage.getItem("mUser");
				axios.post(th.url + '/vehicle/insert', th.vehicle, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 200) {
						th.$Message.success(res.data.message);
						
					} else {
						th.$Message.error(res.data.message);
					}
				})
			}
		},
		created() {
			var th = this;
			axios.get(th.url + '/vehicleType/selectGroup').then(function(res) {
				th.vehicleType = res.data.data;
			})
			axios.get(th.url + '/vehicle/selectById?id='+localStorage.getItem("mUser")).then(function(res) {
				console.log(res);
				if(res.data.code == 200){
					var data = res.data.data;
					console.log(data);
					th.vehicle.dId = data.dId;
					th.vehicle.license = data.license;
					th.vehicle.remarks = data.remarks;
					th.vehicle.tType = data.tType;
					th.vehicle.vColor = data.vColor;
					th.vehicle.vType = data.vType;
				}else{
					th.yzs = false;
				}
			})
		}
	}
</script>
