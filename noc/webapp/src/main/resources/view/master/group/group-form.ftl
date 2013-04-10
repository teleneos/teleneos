<html>
	<head>
		<title><@s.text name="page.group.title" /></title>
		<meta name="header" content="<@s.text name="page.group.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/master-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal" method="post">
					<@s.hidden name="group.id" />
					<@s.textfield key="label.master.group.code" required="true" name="group.code" cssClass="span4" />
					<@s.textfield key="label.master.group.name" required="true" name="group.name" cssClass="span4" />
					<#--
					<div class="control-group ">
						<label class="control-label">
							<@s.text name="label.master.group.club" />
						</label>
						<div class="controls">
							<label class="radio inline">
								<input type="radio" name="group.club" value="true" checked>
								Yes
							</label>
							<label class="radio inline">
								<input type="radio" name="group.club" value="false">
								No
							</label>
						</div>
					</div>
					-->
					<@s.radio key="label.master.group.foc" name="group.freeOfCharge" list={'true' : 'Yes', 'false' : 'No'} listKey="key" listValue="value" />
					<@s.select key="label.master.group.paymentmethod" name="group.paymentMethod" list={'PREPAID' : 'Prepaid', 'POSTPAID' : 'Postpaid'} listKey="key" listValue="value" />
					<div class="control-group ">
						<label class="control-label">
							<@s.text name="label.master.group.package" />
						</label>
						<div class="controls">
							<div class="row-fluid">
								<div class="span4">Select package</div>
								<div class="span4 offset1">Package being selected</div>
							</div>
							<div class="row-fluid">
							<div class="span4">
								<select id="packages" class="span12" multiple>
									
								</select>
							</div>
							<div class="span1">
								<a class="btn" id="moveToRight">
									<i class="icon-arrow-right"></i>
								</a>
								<br><br>
								<a class="btn" id="moveToLeft">
									<i class="icon-arrow-left"></i>
								</a>
							</div>
							<div class="span4">
								<select name="packageIds" class="span12" multiple>
									<#list group.groupPackages as g>
										<option value="${g.internetPackage.id!}" pgval="${g.id!}">${g.internetPackage.name!}</option>
									</#list>
								</select>
							</div>
							</div>
						</div>
					</div>
					<@s.select key="label.master.group.status" required="true" name="group.logInformation.statusFlag" list={'ACTIVE' : 'Enable', 'INACTIVE' : 'Disable'} listKey="key" listValue="value" />
					<div class="form-actions">
						<#if group.id??>
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
		<script type="text/javascript">
		$(function() {
			var groupId = ''
		
			var loadPackage = function(free, method) {
				jQuery.ajax({
					url : '<@s.url value="/master/packages/list/" />' + free + '/' + method + '/${group.id!'nogroup'}.json',
					accepts : 'application/json',
					dataType : 'json',
					success : function(data) {
						$('#packages').empty();
					
						for (i in data.internetPackages.entityList) {
							i = data.internetPackages.entityList[i];
							var op = $('<option></option>');
							op.val(i.id);
							op.text(i.name);
							
							$('#packages').append(op);
						}
					}
				});
			}
			
			// loadPackage('free', 'POSTPAID');
			// $('select[name="group.paymentMethod"]').parents('.control-group').hide();
			
			$('input[name="group.freeOfCharge"]').change(function() {
				if ($(this).val() == 'true') {
					loadPackage('free', 'POSTPAID');
					$('select[name="group.paymentMethod"]').parents('.control-group').fadeOut();
				} else {
					loadPackage('paid', 'POSTPAID');
					$('select[name="group.paymentMethod"]').parents('.control-group').fadeIn();
				}
			});
			
			$('input[name="group.freeOfCharge"]:checked').change();
			
			$('#moveToRight').click(function() {
				$('#packages').find(':selected').appendTo($('select[name="packageIds"]'));
			});
			
			$('#moveToLeft').click(function() {
				$('select[name="packageIds"]').find(':selected').appendTo($('#packages'));
			});
			
			$('select[name="group.paymentMethod"]').change(function() {
				if ($(this).val() == 'POSTPAID') {
					var cont = $('<span id="var"></span>');
					var txtfld = $('<input class="span1" type="text" name="group.paymentPeriod" value="${group.paymentPeriod!}" />');
					var cmb = $('<select class="span2" name="q"></select>');
					cmb.append('<option value="1">Minutes</option>');
					cmb.append('<option value="60">Hours</option>');
					cmb.append('<option value="${(60 * 24)?string('#')}">Days</option>');
					cmb.append('<option value="${(60 * 24 * 7)?string('#')}">Weeks</option>');
					cmb.append('<option value="${(60 * 24 * 30)?string('#')}">Months</option>');
					
					cont.append('&nbsp;').append(txtfld).append('&nbsp;').append(cmb).append('&nbsp;once');
					$(this).after(cont);
				} else {
					$('#var').remove();
				}
			}).change();
		});
		</script>
	</body>
</html>