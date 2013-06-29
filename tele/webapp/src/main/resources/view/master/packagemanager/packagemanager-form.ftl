<html>
	<head>
		<title><@s.text name="page.packagemanager.title" /></title>
		<meta name="header" content="<@s.text name="page.packagemanager.header" />">
		<content tag="sidenav">/view/decorator/nav/master-sidenav.ftl</content>
		<link rel="stylesheet" type="text/css" href="<@s.url value="/styles/datepicker.css" />" />
		<script type="text/javascript" src="<@s.url value="/scripts/bootstrap-datepicker.js" />"></script>
	</head>
	<body>
		<#function byteString byte>
			<#if byte % (1024 * 1024 * 1024) == 0>
				<#return (byte / (1024 * 1024 * 1024))?string('0.#') />
			<#elseif (byte >= (1024 * 1024)) && (byte % (1024 * 1024) == 0)>
				<#return (byte / (1024 * 1024))?string('0.#') />
			<#elseif byte >= 1024>
				<#return (byte / 1024)?string('0.#') />
			<#else>
				<#return byte?string('0.#') />
			</#if>
		</#function>
		<#function byteVal byte>
			<#if (byte % (1024 * 1024 * 1024) == 0)>
				<#return ((1024 * 1024 * 1024))?string('0.#') />
			<#elseif (byte >= (1024 * 1024)) && (byte % (1024 * 1024) == 0)>
				<#return ((1024 * 1024))?string('0.#') />
			<#elseif (byte >= 1024)>
				<#return (1024)?string('0.#') />
			<#else>
				<#return byte?string('0.#') />
			</#if>
		</#function>
		<#function timeFormat time>
			<#if (time = (60 * 24 * 30))>
				<#return (time / (60 * 24 * 30))?string('#') />
			<#elseif (time = (60 * 24 * 7))>
				<#return (time / (60 * 24 * 7))?string('#') />
			<#elseif (time >= (60 * 24))>
				<#return (time / (60 * 24))?string('#') />
			<#elseif (time >= 60)>
				<#return (time / (60))?string('#') />
			<#else>
				<#return time?string('#') />
			</#if>
		</#function>
		<#function timeVal time>
			<#if (time = (60 * 24 * 30))>
				<#return ((60 * 24 * 30))?string('#') />
			<#elseif (time = (60 * 24 * 7))>
				<#return ((60 * 24 * 7))?string('#') />
			<#elseif (time >= (60 * 24))>
				<#return ((60 * 24))?string('#') />
			<#elseif (time >= 60)>
				<#return ((60))?string('#') />
			<#else>
				<#return time?string('#') />
			</#if>
		</#function>
		<div class="block-content collapse in">
			<@s.form id="packageManager" theme="bootstrap" cssClass="form-horizontal" method="post">
				<@s.actionerror />
			
				<@s.hidden name="internetPackage.id" />
				<@s.textfield key="label.master.packagemanager.code" required="true" name="internetPackage.code" cssClass="span4" readonly="${internetPackage.id?exists?string}" />
				<@s.textfield key="label.master.packagemanager.name" required="true" name="internetPackage.name" cssClass="span4" />
				<@s.select key="label.master.group.paymentmethod" name="internetPackage.paymentMethod" list={'PREPAID' : 'Prepaid', 'POSTPAID' : 'Postpaid'} listKey="key" listValue="value" />
				<@s.textfield key="label.master.packagemanager.price" required="true" name="internetPackage.price" cssClass="span4" />
				<div class="control-group">
					<label class="control-label"><span class="required">*</span> Time</label>
					<div class="controls">
						<input class="span2" name="internetPackage.time" value="${timeFormat(internetPackage.time)!0}" type="text">
						<@s.select name="qt" theme="simple" style="width: 70px;" value="${timeVal(internetPackage.time!0)!0}" listKey="key" listValue="value" list={"${(1)?string('#')}" : 'Min', "${(60)?string('#')}" : 'Hour', "${(60 * 24)?string('#')}" : 'Day', "${(60 * 24 * 7)?string('#')}" : 'Week', "${(60 * 24 * 30)?string('#')}" : 'Mon'} />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" ><span class="required">*</span> Bandwidth Quota </label>
					<div class="controls">
						<input class="span2" name="internetPackage.quota" value="${byteString(internetPackage.quota!0)}" type="text">
						<@s.select name="qb" theme="simple" style="width: 70px;" value="${byteVal(internetPackage.quota!0)}" listKey="key" listValue="value" list={"${(1024 * 1024)?string('#')}" : 'MB', "${(1024 * 1024 * 1024)?string('#')}" : 'GB'} />
						<i class="icon-info-sign s2b_tooltip" title="Value zero means unlimited"></i>
					</div>
				</div>
				<#assign kbmb><#if (internetPackage.speed >= 1024)>1024<#else>1</#if></#assign>
				<div class="control-group " >
					<label class="control-label"><span class="required">*</span> Speed</label>
					<div class="controls">
						<input class="span2" name="internetPackage.speed" value="<#if (internetPackage.speed >= 1024)>${(internetPackage.speed / 1024)?string('#')}<#else>${internetPackage.speed?string('#')}</#if>" type="text">
						<@s.select name="qs" theme="simple" style="width: 70px;" value="${kbmb}" listKey="key" listValue="value" list={"1" : 'Kbps', "1024" : 'Mbps'} />
					</div>
				</div>
				<#assign kbmb><#if (internetPackage.nextSpeed >= 1024)>1024<#else>1</#if></#assign>
				<div class="control-group " >
					<label class="control-label"><span class="required">*</span> Next Speed</label>
					<div class="controls">
						<input class="span2" name="internetPackage.nextSpeed" value="<#if (internetPackage.nextSpeed >= 1024)>${(internetPackage.nextSpeed / 1024)?string('#')}<#else>${internetPackage.nextSpeed?string('#')}</#if>" type="text">
						<@s.select name="qns" theme="simple" style="width: 70px;" value="${kbmb}" listKey="key" listValue="value" list={"1" : 'Kbps', "1024" : 'Mbps'} />
					</div>
				</div>
				<@s.select key="label.master.packagemanager.status" name="internetPackage.logInformation.statusFlag" list={'ACTIVE' : 'Active', 'INACTIVE' : 'Inactive'} listKey="key" listValue="value" />
				<div class="form-actions">
					<#if internetPackage.id??>
					<@s.submit key="button.update" cssClass="btn btn-primary" />
					<#else>
					<@s.submit key="button.save" cssClass="btn btn-primary" />
					</#if>
					<@s.reset key="button.reset" cssClass="btn" />
					<span id="alert" class="alert alert-error hide"><@s.text name="message.password.doesntmatch" /></span>
				</div>
			</@s.form>
		</div>
		<script type="text/javascript">
		$(function(){
			
		});
		</script>
	</body>
</html>