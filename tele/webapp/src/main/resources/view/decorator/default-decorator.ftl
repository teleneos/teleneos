<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<#include "/view/decorator/basic/head.ftl" />
	<body>
		<#include "/view/decorator/basic/logo.ftl" />
		<#include "/view/decorator/nav/admin-topnav.ftl" />
		<div class="modal hide fade in" id="popup-dialog">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3 id="popup-header"></h3>
			</div>
			<div class="modal-body" style="height: 400px;">
				<form method="get" class="form-inline" id="popup-search" style="width: 100%">
					<div class="input-append" style="width: 100%">
						<input type="text" name="q" value="" placeholder="Search..." style="width: 80%;">
						<button value="Search" class="btn" style="min-width: 0; width: 15%;">
							&nbsp;<i class="icon-search"></i>&nbsp;
						</button>
					</div>
				</form>
				<div id="popup-data"></div>
			</div>
		</div>
		<div class="container-fluid" id="content">
			<div class="row-fluid">
				<div>
				<#if page.getProperty('page.sidenav')??>
				<#include page.getProperty('page.sidenav') />
				</#if>
				</div>
				
				<#if page.getProperty('meta.header')??>
				<div class="span10 block">
				<div class="navbar-inverse navbar-inner block-header">
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
