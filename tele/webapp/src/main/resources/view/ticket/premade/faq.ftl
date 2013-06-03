<html>
	<head>
		<title><@s.text name="page.faq.title" /></title>
<!-- 		<meta name="header" content="<@s.text name="page.faq.header" />"> -->
	</head>
	<body>
		<div class="row-fluid">
			<div class="span2"></div>
			<div class="span10">
				<div class="row-fluid">
				<form class="form-inline span10" method="get">
					<div class="input-append pull-right">
						<input type="text" name="q" value="${q}" />
						<button class="btn">
							<i class="icon-search"></i>
							<@s.text name="button.search" />
						</button>
					</div>
				</form>
				</div>
				<div class="span10">
				<#list answers.entityList as s>
				<div class="media-body" style="padding-bottom: 30px;">
					<h4 class="media-heading">${s.title}</h4>
					<div class="media">
						${s.content}
					</div>
				</div>
				</#list>
				</div>
			</div>
		</div>
	</body>
</html>