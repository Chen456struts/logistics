<style>
	.login{
	height: 300px;
    width: 450px;
    top: 100px;
    position: relative;
	border-radius: 30px;
    text-align: center;
	background: rgba(0,50,100,0.8);
	margin: auto;
	}
</style>
<template>
	<div style="height: 100%;background-image: url(http://localhost:8080/image/login.png);background-size: 100% 100%;">
		<div style="height: 6%;"></div>
		<div class="login">
			<br />
			<br />
			<h1 style="color: white;">锦轩物流系统</h1>
			<br />
			<Form style="width: 350px;margin: auto;" :model="user">

				<FormItem>
					<a href="#/admin" @click="handleSubmit()">
						<Button type="success" onclick="return false" long> 进入管理员 </Button>
						<br />
					</a><br />
					<a href="#/hz" @click="handleSubmithz()">
						<Button type="success" onclick="return false" long> 进入货主 </Button>
						<br />
					</a><br />
					<a href="#/sj" @click="handleSubmitsj()">
						<Button type="success" onclick="return false" long> 进入司机 </Button>
						<br />
					</a>
				</FormItem>
			</Form>
		</div>
		<div style="height:6%;"></div>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				btn: "用短信验证码登录",
				yzm: "display: none;",
				yhm: "display: block;",
				btnhqyzm: "获取验证码",
				user: {
					name: '',
					password: '',
					phone: '',
					yzm: '',
					btn1: false
				},
				code: 1024,
				url: "http://localhost:8080"
			}
		},
		methods: {
			handleSubmit() {
				window.location.href = "#/admin";
			},
			handleSubmithz() {
				window.location.href = "#/hz";
			},
			handleSubmitsj() {
				window.location.href = "#/sj";
			}
		}
	}
</script>
