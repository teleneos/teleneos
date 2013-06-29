	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title><#if title != ''>${title} - </#if><@s.text name="page.title" /></title>
		<@s.url value="" var="ctx" />
		
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/bootstrap/teleneos-bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/style.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/2.3.1/css/bootstrap-responsive.min.css" />
		
		<script type="text/javascript" src="${ctx}/static/jquery/1.9.1/jquery.min.js"></script>
		${head!}
	</head>