var register = new Vue({
    el:"#register-box",
    data:{
        loginCode:"",
        password:"",
        username:"",
        rePassword:""
    },
    methods: {
        register: function (){
            var that = this
            if (that.username == "" || that.password == "" || that.loginCode == "" || that.rePassword == "") {
                alert("信息不能为空，请重新输入");
            } else {
				if (that.checkPassword()) {
					$.ajax({
					    url: 'http://localhost:8080/user/register',
					    type: 'post',
					    dataType: 'json',
					    contentType: 'application/json; charset=UTF-8',
					    data: JSON.stringify({
					        loginCode: that.loginCode,
							username: that.username,
					        password: hex_md5(that.password+"zzx")
					    }),
					    success: function(res) {
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
				}else{
					alert("密码输入不一致")
				}
            }
        },
		checkPassword: function(){
			if (this.password==this.rePassword){
				return true
			}
			return false
		}
    }
})
