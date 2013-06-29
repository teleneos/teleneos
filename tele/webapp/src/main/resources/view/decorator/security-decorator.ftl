<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<#include "/view/decorator/basic/head.ftl" />
	<body>
		<#include "/view/decorator/basic/logo.ftl" />
		<#if currentUser.role == 'ADMINISTRATOR'>
		<#include "/view/decorator/nav/admin-topnav.ftl" />
		<#elseif currentUser.role == 'USER'>
		<#include "/view/decorator/nav/user-topnav.ftl" />
		</#if>
		
		<div class="container-fluid" id="content">
			<div class="row-fluid">
				<#include "/view/decorator/nav/profile-sidenav.ftl" />
				
				<#if page.getProperty('meta.header')??>
				<div class="span10 block">
				<div class="navbar navbar-inner block-header">
					<div class="muted pull-left">
						<strong>${page.getProperty('meta.header')}</strong> <small>${page.getProperty('meta.desc')!}</small>
					</div>
				</div>
				${body!}
				</div>
				<#else>
				${body!}
				</#if>
			</div>
		</div>
		
		<#include "/view/decorator/basic/footer.ftl" />
	</body>
</html>
