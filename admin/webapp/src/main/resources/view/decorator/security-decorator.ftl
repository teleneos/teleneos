<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<#include "/view/decorator/basic/head.ftl" />
	<body>
		<#include "/view/decorator/nav/admin-topnav.ftl" />
		
		<div class="container" id="content">
			<#if page.getProperty('meta.header')??>
			<div class="row-fluid">
				<div class="span12">
					<h2>${page.getProperty('meta.header')} <small>${page.getProperty('meta.desc')!}</small></h2>
				</div>
			</div>
			</#if>
			<div class="row-fluid">
				<#include "/view/decorator/nav/profile-sidenav.ftl" />
				<div class="span10">
				${body!}
				</div>
			</div>
		</div>
		
		<#include "/view/decorator/basic/footer.ftl" />
	</body>
</html>
