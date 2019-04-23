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
	<div style="height: 100%;background-image: url(http://47.100.245.30:8080/image/bj.jpg);background-size: 100% 100%;">
		<div style="height: 6%;"></div>
		<div class="login">
			<br />
			<br />
			<h1 style="color: white;">进入系统</h1>
			<br />
			<Form style="width: 350px;margin: auto;" :model="user">
				
				<FormItem>
					<a href="#/adminindex" @click="handleSubmit()">
						<Button type="success" onclick="return false" long> 进入管理员 </Button>
						<br />
					</a>
					<a href="#/index" @click="handleSubmithz()">
						<Button type="success" onclick="return false" long> 进入货主 </Button>
					<br />
					</a>
					<a href="#/index" @click="handleSubmitsj()">
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
			handleSubmit(){
				window.location.href = "#/adminindex";
				},
			handleSubmithz(){
				window.location.href = "#/khindex";
				},
				handleSubmitsj(){
					window.location.href = "#/czindex";
				}
		}
	}
</script>
