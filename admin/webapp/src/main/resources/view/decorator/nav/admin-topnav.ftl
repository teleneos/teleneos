		<div class="navbar navbar-fixed-top navbar-inverse">
			<div class="navbar-inner">
				<div class="container">
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</a>
					<a class="brand" href="${ctx}/"><@s.text name="page.header" /></a>
					<div class="nav-collapse">
						<#include "/view/decorator/menu.ftl" />
					</div>
				</div>
			</div>
		</div>