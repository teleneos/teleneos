<html>
	<head>
		<title><@s.text name="Log Management" /></title>
		<meta name="header" content="<@s.text name="Log Management" />">
		<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="<@s.url value="/static/explorercanvas/r3/excanvas.min.js" />"></script><![endif]-->
		<script type="text/javascript" src="<@s.url value="/static/flot/0.7/jquery.flot.min.js" />"></script>
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/network-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.textfield label="Log File Name" cssClass="span4" />
					<@s.textfield label="Log Format" cssClass="span4" />
					<@s.textfield label="Keep History (in days)" cssClass="span2" />
					<@s.select label="Status" list={'ACTIVE' : 'Active', 'INACTIVE' : 'Inactive'} listKey="key" listValue="value" />
					
					<div class="form-actions">
						<input type="button" value="Save" class="btn btn-primary">
					</div>
				</@s.form>
			</div>
		</div>
		<script type="text/javascript">
		$(function() {
			
		});
		</script>
	</body>
</html>