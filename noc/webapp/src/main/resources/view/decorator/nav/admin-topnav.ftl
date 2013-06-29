		<div class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container-fluid">
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</a>
					<a class="brand" href="${ctx}/">
						<#-- <@s.text name="page.header" /> -->
						<img src="<@s.url value="/images/teleneos.png" />" style="height: 20px;" />
					</a>
					<div class="nav-collapse">
						<#include "/view/decorator/menu.ftl" />
					</div>
				</div>
			</div>
		</div>