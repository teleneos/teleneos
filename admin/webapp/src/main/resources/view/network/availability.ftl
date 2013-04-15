<html>
	<head>
		<title><@s.text name="Network Availability" /></title>
		<meta name="header" content="<@s.text name="Network and Service Availability" />">
	</head>
	<body>
<#function rand min max>
  <#local now = .now?long?c />
  <#local randomNum = _rand +
    ("0." + now?substring(now?length-1) + now?substring(now?length-2))?number />
  <#if (randomNum > 1)>
    <#assign _rand = randomNum % 1 />
  <#else>
    <#assign _rand = randomNum />
  </#if>
  <#return (min + ((max - min) * _rand))?round />
</#function>
<#assign _rand = 0.36 />
		<div class="row-fluid">
			<#include "/view/decorator/nav/network-sidenav.ftl" />
			<div class="span10">
				
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th>Name</th>
							<th>Applications</th>
							<#--
							<th>Items</th>
							<th>Triggers</th>
							<th>Graph</th>
							<th>Discovery</th>
							-->
							<th>Interface</th>
							<th>Templates</th>
							<th>Status</th>
							<th>Availability</th>
						</tr>
					</thead>
					<tbody>
						<#list 1..10 as i>
						<tr>
							<td><a>Computer ${rand(i, 100)}</a></td>
							<td><a>Applications</a></td>
							<#--
							<td><a>Items</a></td>
							<td><a>Triggers</a></td>
							<td><a>Graph</a></td>
							<td><a>Discovery</a></td>
							-->
							<td>192.168.2.${rand(i + 3, 100)}:10050</td>
							<td>Template OS Linux</td>
							<td>
								<#if rand(1, 2) = 1>
								<span class="label label-success">Monitored</span>
								<#else>
								<span class="label label-important">Inactive</span>
								</#if>
							</td>
							<td><i class="icon-ok"></i></td>
						</tr>
						</#list>
					</tbody>
				</table>
			</div>
		</div>
		<script type="text/javascript">
		$(function() {
		});
		</script>
	</body>
</html>