<template>
	<header class="main-header">
		<!-- Logo -->
		<a href="" class="logo">
			<!-- mini logo for sidebar mini 50x50 pixels -->
			<span class="logo-mini"><b>Elic</b></span>
			<!-- logo for regular state and mobile devices -->
			<span class="logo-lg"><b>ElicEnd</b></span>
		</a>

		<!-- Header Navbar: style can be found in header.less -->
		<nav class="navbar navbar-static-top">
			<!-- Sidebar toggle button-->
			<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
				<span class="sr-only">Toggle navigation</span>
			</a>

			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<!-- User Account: style can be found in dropdown.less -->
					<li class="dropdown user user-menu">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<img src="../../static/assets/img/1.jpg" class="user-image" alt="User Image">
							<span class="hidden-xs">{{username}}</span>
						</a>
						<ul class="dropdown-menu">
							<!-- User image -->
							<li class="user-header">
								<img src="../../static/assets/img/1.jpg" class="img-circle" alt="User Image">
								<p>
									{{username}}
									<!--                                    <small><formatDate value="${user.created}" pattern="yyyy-MM-dd HH:mm:ss"/> </small> -->
								</p>
							</li>
							<!-- Menu Footer-->
							<li class="user-footer">
								<div class="pull-left">
									<a href="#" class="btn btn-default btn-flat">个人信息</a>
								</div>
								<div class="pull-right">
									<a href="#" class="btn btn-default btn-flat">注销</a>
								</div>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</nav>
	</header>
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
