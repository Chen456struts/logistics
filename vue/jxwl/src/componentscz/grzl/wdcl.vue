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
					<Input v-model="vehicle.license" :maxlength=10 placeholder="请输入车牌号"></Input>
				</FormItem>
				</Col>
				<Col span="8">
				<FormItem label="品牌" prop="vType">
					<Input v-model="vehicle.vType" :maxlength=10 placeholder="请输入品牌"></Input>
				</FormItem>
				</Col>
			</Row>
			<Row>
				<Col span="8">
				<FormItem label="车颜色" prop="vColor">
					<Input v-model="vehicle.vColor" :maxlength=20 placeholder="请输入车颜色"></Input>
				</FormItem>
				</Col>
				<Col span="8">
				<FormItem label="车辆类型" prop="tType">
					<Select v-model="vehicle.tType" filterable>
						<Option v-for="item in vehicleType" :value="item.tId" :key="item.tId">{{ item.vName }}</Option>
					</Select>
				</FormItem>
				</Col>
			</Row>
			<Row>
				<Col span="16">
				<FormItem label="备注" prop="remarks">
					<Input v-model="vehicle.remarks" type='textarea' :autosize="{minRows: 2,maxRows: 6}" placeholder="请输入备注"></Input>
				</FormItem>
				</Col>
			</Row>
			<Row>
				<Button type="success" style="width: 70%;" onclick="return false" > 添加车辆 </Button>
			</Row>
		</Form>
	</div>
</template>
<script>
	export default {
		data() {
			return {
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

		},
		created() {
			var th = this;
			axios.get(th.url + '/vehicleType/selectGroup').then(function(res) {
				th.vehicleType = res.data.data;
			})
		}
	}
</script>
