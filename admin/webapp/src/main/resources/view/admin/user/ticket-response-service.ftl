<html>
	<head>
		<title><@s.text name="page.editprofile.title" /></title>
		<link rel="stylesheet" type="text/css" href="<@s.url value="/styles/datepicker.css" />" />
        <script type="text/javascript" src="<@s.url value="/scripts/bootstrap-datepicker.js" />"></script>
        <script type="text/javascript">
        $(function(){
            $('#teknisiLokasi').datepicker();
            $('#teknisiMulai').datepicker();
            $('#teknisiSelesai').datepicker();
        });
        </script>
		<meta name="header" content="<@s.text name="Ticket" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/admin-sidenav.ftl" />
			<div class="span10">
				<#if user??>
					<#assign pass = user.password! />
				</#if>
			
				<@s.form id="change-password" theme="bootstrap" cssClass="form-horizontal">
					<@s.text name="tooltip.changepassword.newpass" var="ttnewpass" />
					<@s.text name="tooltip.changepassword.confirm" var="ttconfirm" />
				
					<@s.actionerror theme="bootstrap" />
					<#if request.getParameter('success')??>
					<div class="alert alert-success">
						<a class="close" data-dismiss="alert" href="#">&times;</a>
						<@s.text name="message.profile.updatesuccess" />
					</div>
					</#if>
					
					<@s.hidden name="user.id" />
                    <h5>Problem Identification</h5>
					<@s.textarea key="R.M Anomalies" name="" cssClass="span4" />
					<@s.textarea key="Action Taken" name="" cssClass="span4" />
					<br>
			
                    <h5>Field Operation</h5>
                    <hr>
                    <h5>Day 1</h5>
					<div class="control-group ">
                        <label class="control-label">Tech. Arrive onsite </label>
                        <div class="controls">
                            <input id="teknisiLokasi" type="text" name="" value="" readonly class="span4" />
                        </div>
                    </div>
					<div class="control-group ">
                        <label class="control-label">Tech. Start Working </label>
                        <div class="controls">
                            <input id="teknisiMulai" type="text" name="" value="" readonly class="span4" />
                        </div>
                    </div>
					<div class="control-group ">
                        <label class="control-label">Tech. Life Site </label>
                        <div class="controls">
                            <input id="teknisiSelesai" type="text" name="" value="" readonly class="span4" />
                        </div>
                    </div>
                    <hr>
                    <h5>Day 2</h5>
                    <div class="control-group ">
                        <label class="control-label">Tech. Arrive onsite </label>
                        <div class="controls">
                            <input id="teknisiLokasi" type="text" name="" value="" readonly class="span4" />
                        </div>
                    </div>
					<div class="control-group ">
                        <label class="control-label">Tech. Start Working </label>
                        <div class="controls">
                            <input id="teknisiMulai" type="text" name="" value="" readonly class="span4" />
                        </div>
                    </div>
					<div class="control-group ">
                        <label class="control-label">Tech. Life Site </label>
                        <div class="controls">
                            <input id="teknisiSelesai" type="text" name="" value="" readonly class="span4" />
                        </div>
                    </div>
                    <br>
                    <h5>Brief of work carried out</h5>
					<@s.textarea key="Troubleshoot" name="" cssClass="span4" />
					<@s.textarea key="Action Taken" name="" cssClass="span4" />
					<@s.select key="Job Status" name="user.role" cssClass="span4" list=['Resolved'] />
					<div class="form-actions">
						<#if user??>
						<@s.submit key="button.update" cssClass="btn btn-primary" />
						<#else>
						<@s.submit key="button.save" cssClass="btn btn-primary" />
						</#if>
						<@s.reset key="button.reset" cssClass="btn" />
						<span id="alert" class="alert alert-error hide"><@s.text name="message.password.doesntmatch" /></span>
					</div>
				</@s.form>
			</div>
		</div>
	</body>
</html>