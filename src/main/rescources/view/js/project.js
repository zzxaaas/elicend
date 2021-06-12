import {
	saveProject,
	getAllProject
} from '../api/projectApi.js'

var project = new Vue({
	el: "#project-box",
	data: {
		userId: 12,
		language: "",
		languageVersion: "",
		gitRepoUri: "",
		port:0,
		bindPort:0,
		localRepo: "",
		projectName: "",
		projectVersion: "",
		command: "",
		db: "",
		dbVersion: "",
		container: "",
		state: 0,
		needDB: false,
		projectList: []
	},
	components: {
		// 将组建加入组建库
		'my-header': httpVueLoader('./app/header.vue'),
		'my-aside': httpVueLoader('./app/aside.vue'),
		'my-footer': httpVueLoader('./app/footer.vue')
	},
	methods: {
		save: function() {
			var that = this
			if (this.checkValue()) {
				that.userId = 12
				saveProject({
					userId: that.userId,
					language: that.language,
					languageVersion: that.languageVersion,
					port:that.port,
					gitRepoUri: that.gitRepoUri,
					projectName: that.projectName,
					projectVersion: that.projectVersion,
					command: that.command,
					db: that.db,
					dbVersion: that.dbVersion,
				}).then(res => {
					console.log(res)
					if (res.status == 200) {
						alert("保存成功");
						window.location.replace("main.html");
					} else {
						alert(res.message);
					}
				}).catch(err => alert("网络连接失败，请稍后重试。"))
			}
		},
		getAll: function() {
			var that = this
			getAllProject({
				userId: that.userId,
			}).then(res => {
				console.log(res)
				if (res.status == 200) {
					that.projectList = res.data
					console.log(that.projectList)
				} else {
					alert(res.message);
				}
			}).catch(err => alert("网络连接失败，请稍后重试。"))
		},
		checkValue: function() {
			if (!this.projectName || !this.projectVersion || !this.gitRepoUri || !this.language || !this
				.languageVersion) {
				return false
			}
			return true
		}

	},

})