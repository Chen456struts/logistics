<style>
	.rigtop { width: 100%; left: 20; height: 45px;border: 1px solid #dcdee2;padding: 5px;line-height: 45px;margin:5px 0;}
	.rigtop form{
		height: 35px;
	}
	.rigtop .ivu-select-selection,.ivu-select-input, .rigtop .ivu-input{
		height: 28px;
	}
	.rigtop .ivu-btn {
		padding: 4px 5px 4px;
	}
</style>
<style scoped>
	.ivu-layout-header{
		z-index: 999;
}
.ivu-menu-horizontal,.layout-logo,.ivu-layout-header{
	height: 50px;
	line-height:50px; 
	top:0px;
}
.ivu-menu-vertical{
	height: 607px;
}
.layout{
    background: #f5f7f9;
    position: relative;
    overflow: hidden;
}
.layout-logo{
    width: 100px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
}
.layout-nav{
   right: 50px;
    position: absolute;
}
</style>
<template>
	<div class="layout">
		<Layout>
			<!--头部-->
			<Header>
				<Menu mode="horizontal" :theme="theme" active-name="6" :style="{position: 'fixed', width: '100%'}">
					<div class="layout-logo" style="left:-50px;top:0px;width:50%;text-align: center; ">
						<Menu mode="horizontal" @on-select="topfu" :accordion="true" :theme="theme" active-name="1">
							<div class="layout-logo" style="left:0;top:0;width: 200px;;text-align: center;padding-left: 6px;">
								<router-Link to="/index/">
									<MenuItem name="控制台">
									<h3>锦轩物流管理系统</h3>
									</MenuItem>
								</router-Link>
							</div>
							
							
							<router-Link to="/index/">
								<MenuItem name="控制台">
								<Icon type="ios-paper" />
								控制台
								</MenuItem>
							</router-Link>
							
							<router-Link to="/index/noticSelect">
								<MenuItem name="工作安排">
								<Icon type="ios-people" />
								工作安排
								</MenuItem>
							</router-Link>
							<router-Link to="/index/workArrangementSelect">
								<MenuItem name="公告管理">
								<Icon type="ios-people" />
								公告管理
								</MenuItem>
							</router-Link>
						</Menu>
					</div>
					
					<div class="layout-nav">
						<MenuItem name="5">
						<Avatar src="https://i.loli.net/2017/08/21/599a521472424.jpg" />
						<Dropdown>
							{{mName}}
							<Icon type="ios-arrow-down"></Icon>
					
							<DropdownMenu slot="list">
								<DropdownItem  @click.native="updatepassword">修改密码</DropdownItem>
							</DropdownMenu>
						</Dropdown>
						<Modal v-model="modal13" draggable scrollable title="修改密码" @on-ok="ok">
							<Form ref="formValidate" :model="user" :label-width="80">
								<FormItem label="旧密码" prop="password">
									<Input type="password" v-model="user.password" placeholder="请输入旧密码"></Input>
								</FormItem>
								<FormItem  label="新密码" prop="passwords">
									<Input type="password" v-model="user.passwords" placeholder="请输入新密码"></Input>
								</FormItem>
								<FormItem  label="确认密码" prop="passwordss">
									<Input type="password" v-model="user.passwordss" placeholder="请输入确认密码"></Input>
								</FormItem>
							</Form>
						</Modal>
						</MenuItem>
						<Submenu name="6">
							<template slot="title">
								<Icon type="ios-shirt-outline" />
								更换主题
							</template>
							<div style="background-color: #1b315e">
								<RadioGroup v-model="theme">
									<Radio label="dark">
										<Icon type="social-apple"></Icon>
										<span>黑色主题</span>
									</Radio>
									<Radio label="light">
										<Icon type="social-android"></Icon>
										<span>白色主题</span>
									</Radio>
									<Radio label="primary">
										<Icon type="social-windows"></Icon>
										<span>飘色主题</span>
									</Radio>
								</RadioGroup>
							</div>
					
						</Submenu>
							<MenuItem name="3" @click.native="logout">
							<Icon type="ios-exit-outline" />
							<span>注销</span>
							</MenuItem>
					</div>
					
				</Menu>
			</Header>
			<Layout>
				<!--左边-->
				<Sider hide-trigger :style="{background: '#fff'}">
					<Menu @on-open-change="fu" @on-select="ze" :accordion="true" active-name="1-2" :theme="theme" width="auto"
					 :open-names="['1']">
						<Submenu name="前台用户管理">
							<template slot="title">
								<Icon type="logo-yen" />
								前台用户管理
							</template>
							<router-Link to="/index/financialManagementSelect">
								<MenuItem name="货主管理">货主管理</MenuItem>
							</router-Link>
							<router-Link to="/index/financialManagementSelect">
								<MenuItem name="司机管理">司机管理</MenuItem>
							</router-Link>
							<router-Link to="/index/financialManagementSelect">
								<MenuItem name="司机载重管理">司机载重管理</MenuItem>
							</router-Link>
								<router-Link to="/index/financialManagementSelect">
								<MenuItem name="司机车辆管理">司机车辆管理</MenuItem>
							</router-Link>
							</router-Link>
								<router-Link to="/index/financialManagementSelect">
								<MenuItem name="评论管理">评论管理</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="货源管理">
							<template slot="title">
								<Icon type="ios-paper-outline" />
								货源管理
							</template>
							<router-Link to="/index/minutesOfTheMeetingSelect">
								<MenuItem name="货源列表">货源列表</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="订单管理">
							<template slot="title">
								<Icon type="md-done-all"></Icon>
								订单管理
							</template>
							<router-Link to="/index/attendanceSelect">
								<MenuItem name="全部订单">全部订单</MenuItem>
							</router-Link>
							<router-Link to="/index/attendanceSelect">
								<MenuItem name="待运输订单">待运输订单</MenuItem>
							</router-Link>
							<router-Link to="/index/attendanceSelect">
								<MenuItem name="运输中订单">运输中订单</MenuItem>
							</router-Link>
							<router-Link to="/index/attendanceSelect">
								<MenuItem name="已完成订单">已完成订单</MenuItem>
							</router-Link>
							<router-Link to="/index/attendanceSelect">
								<MenuItem name="司机轨迹查看">司机轨迹查看</MenuItem>
							</router-Link>
							<router-Link to="/index/attendanceSelect">
								<MenuItem name="订单回单上传确认">订单回单上传确认</MenuItem>
							</router-Link>
							<router-Link to="/index/attendanceSelect">
								<MenuItem name="订单评价管理">订单评价管理</MenuItem>
							</router-Link>	
							<router-Link to="/index/attendanceSelect">
								<MenuItem name="运输合同管理">运输合同管理</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="数据报表">
							<template slot="title">
								<Icon type="ios-ribbon" />
								数据报表
							</template>
							<router-Link to="/index/personalMeritRecordSelect">
								<MenuItem name="货源发布统计">货源发布统计</MenuItem>
							</router-Link>
							<router-Link to="/index/departmentalExcellenceRecordSelect">
								<MenuItem name="订单统计">订单统计</MenuItem>
							</router-Link>
							<router-Link to="/index/departmentalExcellenceRecordSelect">
								<MenuItem name="货运部发货冬季月报表">货运部发货冬季月报表</MenuItem>
							</router-Link>
							<router-Link to="/index/departmentalExcellenceRecordSelect">
								<MenuItem name="司机注册统计">司机注册统计</MenuItem>
							</router-Link>
							<router-Link to="/index/departmentalExcellenceRecordSelect">
								<MenuItem name="货主注册统计">货主注册统计</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="财务管理">
							<template slot="title">
								<Icon type="ios-people"></Icon>
								财务管理
							</template>
							<router-Link to="/index/memberInformationSelect">
								<MenuItem name="订单打款管理">订单打款管理</MenuItem>
							</router-Link>
							<router-Link to="/index/memberInformationselectEcharts">
								<MenuItem name="用户充值管理">用户充值管理</MenuItem>
							</router-Link>
								<router-Link to="/index/memberInformationselectEcharts">
								<MenuItem name="用户提现管理">用户提现管理</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="平台内容管理">
							<template slot="title">
								<Icon type="ios-keypad"></Icon>
								平台内容管理
							</template>
							<router-Link to="/index/jurisdictionSelect">
								<MenuItem name="系统轮播管理">系统轮播管理</MenuItem>
							</router-Link>
							<router-Link to="/index/classTableSelect">
								<MenuItem name="系统反馈管理">系统反馈管理</MenuItem>
							</router-Link>
							<router-Link to="/index/typeOfMeetingSelect">
								<MenuItem name="系统通知管理">系统通知管理</MenuItem>
							</router-Link>
							<router-Link to="/index/exchangeTableSelect">
								<MenuItem name="系统内容展示管理">系统内容展示管理</MenuItem>
							</router-Link>
							<router-Link to="/index/departmentTypeSelect">
								<MenuItem name="新闻管理">新闻管理</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="系统参数">
							<template slot="title">
								<Icon type="ios-people"></Icon>
								系统参数
							</template>
							<router-Link to="/index/memberInformationSelect">
								<MenuItem name="系统参数设置">系统参数设置</MenuItem>
							</router-Link>
						</Submenu>
					</Menu>
				</Sider>
				<!--右边-->
				<Layout>
					<!--导航栏-->
					<Breadcrumb :style="{margin: '5px'}">
						<span>
							<Icon type="ios-pin-outline" />
							当前位置：</span>
						<span>{{ parentTag }}</span>
						<span>{{ childTag }}</span>
						<span style="float: right; margin-right: 20px;">{{years}}</span>
					</Breadcrumb>
					<!--内容-->
					<Content :style="{padding: '0 25px', minHeight: '280px', background: '#fff'}">
						<router-view>
				
						</router-view>
					</Content>
				</Layout>
			</Layout>
		</Layout>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				url: "http://47.100.245.30:8080",
				years: "",
				mName: "小邦哥1",
				theme: "primary",
				parentTag: "控制台",
				modal13: false,
				childTag: "",
				user: {
					password: "",
					passwords: "",
					passwordss: ""
				}
			}
		},
		methods: {
			logout() {
				var th = this;
				axios.get(th.url + '/login/logout').then(function(res) {
					if (res.data.code == 1028) {
						th.$Message.success(res.data.message);
						localStorage.setItem("accessToken", null);
						setTimeout(function() {
							window.location.href = "/";
						}, 900);

					}
				});

			},
			ok() {
				var th = this;
				if (th.user.password.length < 6) {
					th.$Message.warning("密码最少为6位");
					return;
				}
				if (th.user.passwords != th.user.passwordss) {
					th.$Message.warning("两次密码不一致");
					return;
				}
				th.user.mUser = localStorage.getItem("mUser");
				axios.get(th.url + '/memberInformation/upassword', {
					params: {
						mUser: th.user.mUser,
						password: th.user.password,
						passwords: th.user.passwords
					}
				}).then(function(res) {
					if (res.data.code == 1028) {
						th.$Message.success(res.data.message);
						localStorage.setItem("accessToken", null);
						axios.get(th.url + '/login/logout');
						setTimeout(function() {
							window.location.href = "/";
						}, 500);
					} else {
						th.$Message.error(res.data.message);
					}
				})
			},
			updatepassword() {
				this.modal13 = true;
			},
			fu(e) {
				this.parentTag = e[0];
				this.childTag = "";
			},
			ze(e) {
				this.childTag = " > " + e;
			},
			topfu(e) {
				this.parentTag = e;
				this.childTag = "";
			},
		},
		created() {
			this.mName = localStorage.getItem("mName");
			var data = new Date();
			this.years = data.getFullYear() + "年" + (data.getMonth() + 1) + "月" + data.getDate() + "日";
		}
	}
</script>
