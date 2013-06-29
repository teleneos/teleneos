<html>
	<head>
		<title><@s.text name="Ticket" /></title>
		<meta name="header" content="<@s.text name="New Ticket" />">
		<content tag="sidenav">/view/decorator/nav/ticket-sidenav.ftl</content>
		<link rel="stylesheet" type="text/css" href="<@s.url value="/styles/datepicker.css" />" />
		<script type="text/javascript" src="<@s.url value="/scripts/bootstrap-datepicker.js" />"></script>
		<script type="text/javascript">
		$(function(){
			$('#periodfrom').datepicker();
		});
		</script>
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/ticket-sidenav.ftl" />
			<div class="span10">
				<@s.form id="change-password" theme="bootstrap" cssClass="form-horizontal">
					<div class="control-group ">
						<label class="control-label">Unit Information</label>
						<div class="controls">
							<textarea name="" cols="" rows="" id="" class="span4"></textarea><br>
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label">Valid</label>
						<div class="controls">
							<input type="checkbox" name="valid" value="">Yes
							&nbsp;&nbsp;
							<input type="checkbox" name="valid" value="">No
						</div>
					</div>
					<@s.textarea key="Fault Symptoms" name="" cssClass="span4" />
					<@s.textfield key="Caller" name="" cssClass="span4" />
					<@s.textfield key="Receiver" name="" cssClass="span4" />
					<div class="control-group">
						<label class="control-label">Date</label>
						<div class="controls">
							<input id="periodfrom" type="text" name="" value="" readonly class="span4" />
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label" for="add_transactionDetail_item_id">Engineer's Code</label>
						<div class="controls">
							<input type="text" id="item-name" readonly="true" class="span4">
							<button class="btn openpopup"  type="button" title="<@s.text name="page.item.title" />" object-name="items|name" field-target="item-id|item-name" href="<@s.url value="/pos/item" />">Choose</button>
						</div>
					</div>
					<div class="form-actions">
						<@s.submit key="button.next" cssClass="btn btn-primary" />
						<span id="alert" class="alert alert-error hide"><@s.text name="message.password.doesntmatch" /></span>
					</div>
				</@s.form>
			</div>
		</div>
		<script type="text/javascript">
		$(function() {
			$('#newpass1, #newpass2').keyup(function() {
				if (!($('#newpass1').val() === $('#newpass2').val())) {
					$('#alert').fadeIn();
				} else {
					$('#alert').fadeOut();
				}
			});
			$('.password').val('${pass!}');
		});
		</script>
	</body>
</html>