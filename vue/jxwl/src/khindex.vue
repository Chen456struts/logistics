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
								<router-Link to="/khindex/">
									<MenuItem name="控制台">
									<h3>锦轩物流货主系统</h3>
									</MenuItem>
								</router-Link>
							</div>
							<router-Link to="/khindex">
								<MenuItem name="控制台1">
								<Icon type="ios-paper" />
								控制台
								</MenuItem>
							</router-Link>
							<MenuItem >
							<marquee style="width: 400px;position: absolute;">
								{{gk}}
								</marquee>
							</MenuItem>
								
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
						<Submenu name="下单管理">
							<template slot="title">
								<Icon type="logo-yen" />
								下单管理
							</template>
							<router-Link to="/khindex/ksxd">
								<MenuItem name="快速下单">快速下单</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="订单管理">
							<template slot="title">
								<Icon type="md-done-all"></Icon>
								订单管理
							</template>
							
							<router-Link to="/khindex/ddglSelect">
								<MenuItem name="全部订单">全部订单</MenuItem>
							</router-Link>
							<router-Link to="/khindex/ddglSelectdys">
								<MenuItem name="待运输订单">待运输订单</MenuItem>
							</router-Link>
							<router-Link to="/khindex/ddglSelectysz">
								<MenuItem name="运输中订单">运输中订单</MenuItem>
							</router-Link>
							<router-Link to="/khindex/ddglSelectywc">
								<MenuItem name="已完成订单">已完成订单</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="个人资料">
							<template slot="title">
								<Icon type="ios-ribbon" />
								个人资料
							</template>
							<router-Link to="/grzl/wdxx">
								<MenuItem name="我的信息">我的信息</MenuItem>
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
				url: "http://localhost:8080",
				years: "",
				mName: "小邦哥1",
				theme: "primary",
				gk:"长江上海航道处2019年空调和厨房油烟机维保服务采购项目成交公告",
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
						th.$Message.success(res.data.message);
						localStorage.setItem("accessToken", null);
						setTimeout(function() {
							window.location.href = "/";
						}, 900);
				}); 

			},
			ok() {
				var th = this;
				if (th.user.passwords != th.user.passwordss) {
					th.$Message.warning("两次密码不一致");
					th.modal13show();
					return;
				}
				th.user.mUser = localStorage.getItem("mUser");
				axios.get(th.url + '/shipperInformation/updatePassword', {
					params: {
						id: th.user.mUser,
						old: th.user.password,
						password: th.user.passwords
					}
				}).then(function(res) {
					if (res.data.code == 200) {
						th.$Message.success(res.data.message);
						localStorage.setItem("accessToken", null);
						axios.get(th.url + '/login/logout');
						setTimeout(function() {
							window.location.href = "/adminindex";
						}, 500);
					} else {
						th.modal13show();
						th.$Message.error(res.data.message);
					}
				})
			},
			modal13show() {
				this.modal13 = false;
				setTimeout(() => {
					this.modal13 = true;
				}, 1000);
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
			var th = this;
			var data = new Date();
			this.years = data.getFullYear() + "年" + (data.getMonth() + 1) + "月" + data.getDate() + "日";
			axios.get(th.url + '/notice/selectDirection', {
					params: {
						direction: '货主'
					}
				})
			.then(function(res) {
				th.gk = res.data.data;
			})
		}
	}
</script>
