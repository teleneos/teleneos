<html>
	<head>
		<title><@s.text name="page.faq.title" /></title>
<!-- 		<meta name="header" content="<@s.text name="page.faq.header" />"> -->
	</head>
	<body>
		<div class="row-fluid">
			<div class="span10 offset1">
				<div class="row-fluid" style="padding-bottom: 35px;">
					<div class="span2"><h2>FAQ</h2></div>
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
				<div class="row-fluid">
				<#assign no = 1 + ((page - 1) * max) />
				<#list answers.entityList as s>
				<div class="media">
				 	<h4 class="pull-left" style="margin: 0; margin-right:10px;">
						${no}
					</h4>
					<div class="media-body" style="padding-bottom: 10px;">
						<h4 class="media-heading">${s.title}</h4>
						<div class="media">
							${s.content}
						</div>
					</div>
				</div>
				<#assign no = no + 1 />
				</#list>
				</div>
				<div id="pagination" class="pull-right"></div>
			</div>
		</div>
		<script type="text/javascript" src="<@s.url value="/scripts/jq/pagination.js" />"></script>
		<script type="text/javascript">
		$(function() {
			$('#pagination').pagination({
				items: ${answers.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(answers.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>