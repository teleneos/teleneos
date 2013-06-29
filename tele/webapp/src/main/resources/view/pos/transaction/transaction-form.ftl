<html>
	<head>
		<title><@s.text name="page.tdetail.title" /></title>
		<meta name="header" content="<@s.text name="page.tdetail.header" />">
		<content tag="sidenav">/view/decorator/nav/pos-sidenav.ftl</content>
		<script type="text/javascript" src="<@s.url value="/scripts/cimande-popup.js" />"></script>
	</head>
	<body>
		<div class="block-content collapse in">
			<@s.form theme="bootstrap" cssClass="form-horizontal">
				<div class="control-group ">
					<label class="control-label" for="add_id">User <span class="required">*</span></label>
					<div class="controls">
						<@s.hidden name="id" id="user-id" />
						<input type="text" id="user-name" readonly="true" class="span4">
						<button class="btn openpopup" type="button" title="<@s.text name="page.user.title" />" object-name="users|user.username" field-target="user-id|user-name" href="<@s.url value="/admin/user/list/nodetail" />">Choose</button>
					</div>
				</div>
				<div class="form-actions">
					<@s.submit key="button.next" cssClass="btn btn-primary" />
				</div>
			</@s.form>
		</div>		
	</body>
</html>