import {
    userLogin,
    userRegister
} from '../api/userApi.js'

var user = new Vue({
    el:"#user-box",
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
                    userRegister({
                        loginCode: that.loginCode,
                        username: that.username,
                        password: hex_md5(that.password+"zzx")
                    }).then(res=>{
                        if (res.status == 200) {
                            alert("注册成功");
                            window.location.replace("main.html");
                        } else {
                            alert(res.message);
                        }
                    }).catch(err=>{
                        alert("网络连接失败，请稍后重试。");
                    })
                }else{
                    alert("密码输入不一致")
                }
            }
        },
        login: function (){
            var that = this
            if (that.loginCode == "" || that.password == "") {
                alert("账号密码不可为空，请重新输入。");
            } else {
                userLogin({
                    loginCode: that.loginCode,
                    password: hex_md5(that.password+"zzx")
                }).then(res => {
                    console.log(res)
                    if (res.status == 200) {
                        alert("登录成功");
                        window.location.replace("main.html");
                    } else {
                        alert(res.message);
                    }
                }).catch(err => alert("网络连接失败，请稍后重试。"))
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
