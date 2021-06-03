"use strict"
window.onload = function() {
	if(document.cookie==null)
		window.location.replace("login.html");
	operation.GetAllStu();
	selectUp();
}

function selectUp() {

	var i //下拉框的箭头
	var div //下拉框的input的父元素
	var input = $('#search') //下拉框的input
	var dropdown = $(".el-select-dropdown") //下拉框内容的body
	var s = $(".el-select") //下拉框的body 
	$("#select-dropdown").click(function() {
		var t = $(this);
		i = t.children('.el-input__suffix').children('.el-input__suffix-inner').children('.el-icon-arrow-up')
		div = i.parent().parent().parent(".el-input")
		// display: none; min-width: 90px; transform-origin: center top 0px; z-index: 2123; position: absolute; top: 65px; left: 28px;"
		// 根据el-select的长宽以及坐标设置下拉框出现的位置
		dropdown.css({
			"min-width": parseInt(s.css("width")) + 'px',
			"top": parseInt(s.css("top")) + s.offset().top + s.height() + 'px',
			"left": parseInt(s.css("left")) + s.offset().left + 'px'
		})

		if (i.hasClass('is-reverse') == false) {
			i.addClass('is-reverse')
			div.addClass('is-focus')
			dropdown.slideDown("slow");
		} else {
			i.removeClass('is-reverse')
			div.removeClass('is-focus')
			dropdown.slideUp("slow");
		}
	});

	$(".el-select-dropdown__list li").click(function() {
		var index = $(this).index()
		$(this).siblings('li').removeClass('selected');
		$(this).addClass('selected')
		$(".el-input--suffix").children(".el-input__inner").val($(this).text())
		i.removeClass('is-reverse')
		div.removeClass('is-focus')
		dropdown.slideUp("slow")
		input.attr('key', $(this).attr('key'))
		// if (input.attr('key') == 'sdatebirth') {
		// 	$("#search_text").attr("type", "date")
		// }
	})
	$(".el-select-dropdown__list li").hover(function() {
		$(this).siblings('li').removeClass('hover')
		$(this).addClass('hover')
	})
}

function choiceOpera() {

	if (operation.opera == "添加") {
		operation.SaveStu();
	} else {
		operation.UpadateStu();
	}
	operation.closeDialog()

}

function searchStudent() {

	if ($('#search').attr('key') == "StuNo") {
		operation.getStuByNo()
	} else if ($('#search').attr('key') == "StuName") {
		operation.getStuByName()
	}

}

var operation = new Vue({

	el: "#operation",
	data: {
		stu: '',
		stuNo: '',
		stuName: '',
		sex: '',
		stuClass: '',
		age: '',
		admissionDate: '',
		job: '',
		opera: '添加',
		stuInfo: [],
	},
	methods: {
		onSubmit() {
			console.log('submit!');
		},
		setMassage(value) {
			this.stu = value.stu;
		},
		close() {
			this.check = false;
		},
		openDialog(opera, value) {

			if ($(".el-dialog__wrapper").css("display") == "none") {
				// $(".el-dialog__title").text(opera)
				operation.opera = opera
				$(".el-dialog__wrapper").show()
				$(".v-modal").show()
			}

			if (value != null) {

				this.id = value.id
				this.stuNo = value.stuNo
				this.stuName = value.stuName
				this.sex = value.sex
				this.stuClass = value.stuClass
				this.age = value.age
				this.admissionDate = value.admissionDate
				this.job = value.job

			}
		},
		closeDialog() {
			//隐藏对话框
			$(".el-dialog__wrapper").hide()
			$(".v-modal").hide()
			//清空输入框
			this.stuNo = '',
				this.id = ''
			this.stuNo = ''
			this.stuName = ''
			this.sex = ''
			this.stuClass = ''
			this.age = ''
			this.admissionDate = ''
			this.job = ''

		},
		logout() {
			var that = this
			$.ajax({
				url: 'http://localhost:8181/user/logout',
				type: 'get',
				async: true,
				timeout: 5000,
				data: {
					id:document.cookie,
				},
				dataType: 'json',
				beforeSend: function() {
		
				}, //再发送请求前调用的方法
				success: function(msg) {
					if (msg.status == 1) {
						window.location.replace('login.html');
					} else {
						alert(msg.error)
					}
				},
				error: function() {
					
				},
				complete: function() {
		
				}
			});
		},
		getStuByNo() {
			var that = this
			$.ajax({
				url: 'http://localhost:8181/stu/getstu/no/' + this.stu,
				type: 'get',
				async: true,
				timeout: 5000,
				data: {
					cookie:document.cookie,
				},
				dataType: 'json',
				beforeSend: function() {

				}, //再发送请求前调用的方法
				success: function(msg) {
					if (msg.status == 1) {
						that.stuInfo = []
						that.stuInfo.push(msg.data)
					} else if (msg.status == -1) {
						window.location.replace('login.html');
						window.history.back(-1);
					} else {
						alert(msg.error)
					}
				},
				error: function() {
					alert("查询失败！")

				},
				complete: function() {

				}
			});
		},
		getStuByName() {
			var that = this
			$.ajax({
				url: 'http://localhost:8181/stu/getstu/name/' + this.stu,
				type: 'get',
				async: true,
				timeout: 5000,
				data: {
					cookie:document.cookie,
				},
				dataType: 'json',
				beforeSend: function() {

				}, //再发送请求前调用的方法
				success: function(msg) {
					if (msg.status == 1) {
						that.stuInfo = []
						that.stuInfo.push(msg.data)
					} else if (msg.status == -1) {
						window.location.replace('login.html');
						window.history.back(-1);
					} else {
						alert(msg.error)
					}
				},
				error: function() {
					alert("查询失败！")

				},
				complete: function() {

				}
			});
		},
		GetAllStu() {
			var that = this
			$.ajax({
				url: 'http://localhost:8181/stu/getstu/all',
				type: 'get',
				async: true,
				timeout: 5000,
				crossDomain: true,
				data: {
					cookie:document.cookie,
					method: 'GetAllStu',
				},
				dataType: 'json',
				beforeSend: function() {

				},
				success: function(json) {
					if (json.status == -1) {
						window.location.replace('login.html');
						window.history.back(-1);
					}
					that.stuInfo = json.data
				},
				error: function(err) {
					console.log(err)
					alert("数据库读取失败")
				},
				complete: function() {

				}
			});
		},
		SaveStu() {
			var that = this
			$.ajax({
				url: 'http://localhost:8181/stu/save?cookie='+document.cookie,
				type: 'POST',
				async: true,
				timeout: 5000,
				contentType: 'application/json; charset=UTF-8',
				data: JSON.stringify({
					stuNo: that.stuNo,
					stuName: that.stuName,
					sex: that.sex,
					stuClass: that.stuClass,
					age: that.age,
					admissionDate: that.admissionDate,
					job: that.job
				}),
				dataType: 'json',
				beforeSend: function() {

				},
				success: function(msg) {
					console.log(msg)
					if (msg.status == -1) {
						window.location.replace('login.html');
						window.history.back(-1);
					}
					that.GetAllStu()
				},
				error: function() {
					alert("添加失败！")

				},
				complete: function() {

				} //回调方法 无论success或者error都会执行
			});
		},
		UpadateStu() {
			var that = this
			console.log(that.id)
			$.ajax({
				url: 'http://localhost:8181/stu/update?cookie='+document.cookie,
				type: 'post',
				async: true,
				timeout: 5000,
				contentType: 'application/json; charset=UTF-8',
				data: JSON.stringify({
					method: 'UpadateStu',
					id: parseInt(that.id),
					stuNo: that.stuNo,
					stuName: that.stuName,
					sex: that.sex,
					stuClass: that.stuClass,
					age: that.age,
					admissionDate: that.admissionDate,
					job: that.job
				}),
				beforeSend: function() {

				},
				success: function(msg) {
					if (msg.status == -1) {
						window.location.replace('login.html');
						window.history.back(-1);
					}
					console.log(msg)
					that.GetAllStu()
				},
				error: function() {
					alert("修改失败")

				}, //请求发生错误时调用方法
				complete: function() {

				} //回调方法 无论success或者error都会执行
			});
		},
		DeleteStu(id) {
			console.log(id)
			var that = this
			$.ajax({
				url: 'http://localhost:8181/stu/delete/' + id, //要请求的url地址
				type: 'get', //请求方法 GET or POST
				async: true, //是否使用异步请求的方式
				timeout: 5000, //请求超时的时间，以毫秒计
				data: {
					cookie:document.cookie,
					id: id
				}, 
				beforeSend: function() {

				}, //再发送请求前调用的方法
				success: function(msg) {
					if (msg.status == -1) {
						window.location.replace('login.html');
						window.history.back(-1);
					}
					that.GetAllStu()
				}, //请求成功时回调方法，data为服务器返回的数据
				error: function() {
					alert("删除失败！")

				}, //请求发生错误时调用方法
				complete: function() {

				} //回调方法 无论success或者error都会执行
			});
		}
	}
})
