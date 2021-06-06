<template>
	<aside class="main-sidebar">
		<!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel" style="height: 70px">
				<div class="pull-left image">
					<img src="../../static/assets/img/1.jpg" class="img-circle" alt="User Image">
				</div>
				<div class="pull-left info">
					<p>{{username}}</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>

			<!-- sidebar menu: : style can be found in sidebar.less -->
			<ul class="sidebar-menu" data-widget="tree">
				<li class="header">功能菜单</li>
				<li class="active treeview">
					<a href="#">
						<i class="fa fa-users"></i> <span>项目管理</span>
						<span class="pull-right-container">
							<i class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
					<ul class="treeview-menu">
						<li><a href="./main.html"><i class="fa fa-circle-o"></i> 项目列表</a></li>
						<li><a href="./project_form.html"><i class="fa fa-circle-o"></i> 新增项目</a></li>
					</ul>
				</li>
				<li class="active treeview">
					<a href="#">
						<i class="fa fa-book"></i> <span>构建管理</span>
						<span class="pull-right-container">
							<i class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
					<ul class="treeview-menu">
						<li><a href="./container.html"><i class="fa fa-circle-o"></i> 容器列表</a></li>
					</ul>
				</li>
			</ul>
		</section>
	</aside>
</template>

<script>
	module.exports = {
		props: {
			username: {
				type: String,
				default: "zzxaaas"
			}
		}
	}
</script>

<style>
</style>
