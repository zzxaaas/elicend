var login = new Vue({
	el:"#login-box",
	data:{
		loginCode:"",
		password:""
	},
	methods: {
		login: function (){
			var that = this
			if (that.loginCode == "" || that.password == "") {
				alert("账号密码不可为空，请重新输入。");
			} else {
				$.ajax({
					url: 'http://localhost:8080/user/login',
					type: 'post',
					dataType: 'json',
					contentType: 'application/json; charset=UTF-8',
					data: JSON.stringify({
						loginCode: that.loginCode,
						password: hex_md5(that.password+"zzx")
					}),
					success: function(res) {
						console.log(res)
						if (res.status == 200) {
							window.location.replace("main.html");
						} else {
							alert(res.message);
						}
					},
					error: function(err) {
						alert("网络连接失败，请稍后重试。");
					}
				})
			}
		}
	}
})
