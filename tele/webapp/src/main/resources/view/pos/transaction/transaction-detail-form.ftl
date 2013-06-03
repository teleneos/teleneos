<html>
	<head>
		<title><@s.text name="page.tdetail.title" /></title>
		<meta name="header" content="<@s.text name="page.tdetail.header" />">
		<script type="text/javascript" src="<@s.url value="/scripts/cimande-popup.js" />"></script>
		<script type="text/javascript">
			printDivCSS = new String ('<link href="myprintstyle.css" rel="stylesheet" type="text/css">')
			function printDiv(divId) {
				var mywindow = window.open('', 'my div', '');
				mywindow.document.body.innerHTML=printDivCSS + document.getElementById(divId).innerHTML
				mywindow.window.focus()
			    mywindow.window.print()
			    
			}
			
			$(function() {
				$('#package').hide();
				$('#uom-id').attr('name', 'transactionDetail.uom.id');
				$('input[name="change"]').change(function() {
					if ($(this).val() == 'true') {
						$('#package').hide();
						$('#uom').fadeIn();
						$('#uom-id').attr('name', 'transactionDetail.uom.id');
						$('#item').fadeIn();
						$('input[name="transactionDetail.quantity"]').parents('.control-group').fadeIn();
						$('#postpaid').hide();
					} else if ($(this).val() == 'false'){
						$('#item').hide();
						$('input[name="transactionDetail.quantity"]').parents('.control-group').hide();
						$('#package').fadeIn();
						$('#uom').hide();
						$('#uom-id').attr('name', '');
						$('#postpaid').hide();
					} else {
						$('#package').hide();
						$('#postpaid').fadeIn();
						$('#item').hide();
						$('input[name="transactionDetail.quantity"]').parents('.control-group').hide();
						$('#uom').hide();
						$('#uom-id').attr('name', '');
					}
				});
				
				$('input[name="change"]:checked').change();
				
			});
			function populateOptions(){
				$.getJSON("/pos/conversion/from.json?q="+$('#item-id').val(), function(result) {
				    var options = $("#conversion");
				    $(options).empty();
				    $.each(result.conversions.entityList, function() {
				        options.append($("<option />").val(this.uomFrom.id).text(this.uomFrom.name));
				    });
				});					
			}
		</script>
	</head>
	<body>
		<#function noInvoice counter>
			<#if counter &gt;= 100000>
				<#return counter?string('#')/>
			<#elseif counter &gt;= 10000>
				<#return '0' + counter?string('#')/>
			<#elseif counter &gt;= 1000>
				<#return '00' + counter?string('#')/>
			<#elseif counter &gt;= 100>
				<#return '000' + counter?string('#') />
			<#elseif counter &gt;= 10>
				<#return '0000' + counter?string('#') />
			<#else>
				<#return '00000' + counter?string('#') />
			</#if>
		</#function>
		<#function timeFormat time>
			<#if (time = (60 * 24 * 7))>
				<#return (time / (60 * 24 * 7))?string('#') + ' Weeks' />
			<#elseif (time >= (60 * 24))>
				<#return (time / (60 * 24))?string('#') + ' Days' />
			<#elseif (time >= 60)>
				<#return (time / (60))?string('#') + ' Hours' />
			<#elseif (time >= 1)>
				<#return (time)?string('#') + ' Min' />
			<#elseif (time == 0)>
				<#return '-' />
			</#if>
		</#function>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<@s.form action="/pos/transaction/detail/%{transactionHeader.id}" theme="bootstrap" cssClass="form-horizontal">
					<@s.hidden name="transactionHeader.id" />
					<#if transactionHeader.id??>
					<@s.textfield key="label.admin.theader.noinvoice" readonly="true" value="${noInvoice(transactionHeader.counter)}" cssClass="span4" />
					<@s.textfield key="label.admin.theader.date" readonly="true" value="${transactionHeader.logInformation.createDate?string('dd-MM-yyyy')!} ${transactionHeader.logInformation.createDate?string('HH:mm')!}" cssClass="span4" />
					<@s.textfield key="label.admin.theader.user" readonly="true" value="${transactionHeader.username!}" cssClass="span4" />
					<@s.textfield key="label.admin.theader.admin" readonly="true" value="${currentUser.username!}" cssClass="span4" />
					<#else>
					<div class="control-group ">
						<label class="control-label" for="add_id"><span class="required">*</span> <@s.text name="menu.admin.user" /></label>
						<div class="controls">
							<@s.hidden name="id" id="user-id" />
							<input type="text" id="user-name" readonly="true" class="span4">
							<button class="btn openpopup" type="button" title="<@s.text name="page.user.title" />" object-name="users|user" field-target="user-id|user-name" href="<@s.url value="/admin/user/list" />">Choose</button>
						</div>
					</div>
					</#if>
					<#if !transactionHeader.cash??>
					<@s.radio name="change" list={'true' : 'Item', 'false' : 'Internet Package', 'postpaid' : 'Postpaid'} listKey="key" listValue="value" value="true" />
					<div class="control-group <#if fieldErrors.containsKey('transactionDetail.item.name')>error</#if>" id="item">
						<label class="control-label" for="add_transactionDetail_item_id"> <span class="required">*</span> <@s.text name="page.item.title" /></label>
						<div class="controls">
							<@s.hidden name="transactionDetail.item.id" id="item-id" />
							<input type="text" id="item-name" readonly="true" class="span4" value="<#if transactionDetail.item??> ${transactionDetail.item.name!} </#if>" name="transactionDetail.item.name">
							<button class="btn openpopup"  type="button" title="<@s.text name="page.item.title" />" object-name="items|name" field-target="item-id|item-name" fire="populateOptions();" href="<@s.url value="/pos/item" />">Choose</button>
							<#if fieldErrors.containsKey('transactionDetail.item.name')>
								<span class="help-inline">${fieldErrors.get('transactionDetail.item.name').get(0)?string}</span>
							</#if>
						</div>
					</div>
					<div class="control-group " id="package">
						<label class="control-label" for="add_id">Package<span class="required">*</span></label>
						<div class="controls">
							<@s.hidden name="transactionDetail.internetPackage.id" id="package-id" />
							<input type="text" id="package-name" readonly="true" class="span4">
							<button class="btn openpopup" type="button" title="Package" object-name="internetPackages|code,name" field-target="package-id|package-name" href="/master/packages">Choose</button>
						</div>
					</div>
					<div class="control-group " id="postpaid">
						<label class="control-label" for="add_id">Postpaid<span class="required">*</span></label>
						<div class="controls">
							<@s.hidden name="transactionDetail.userPackage.id" id="user-package-id" />
							<input type="text" id="user-package-name" readonly="true" class="span4">
							<button class="btn openpopup" type="button" title="Postpaid" object-name="userPackages|username,internetPackage.name" field-target="user-package-id|user-package-name" href="/admin/user/postpaid">Choose</button>
						</div>
					</div>
					<@s.textfield key="label.admin.tdetail.quantity" required="true"  name="transactionDetail.quantity" cssClass="span4" />
					<div class="control-group <#if erroruom?string=='true' | fieldErrors.containsKey('transactionDetail.item.name')>error</#if>" id="uom">
						<label class="control-label" for="add_item_category_id"><span class="required">*</span><@s.text name="label.admin.item.uom" /></label>
							<div class="controls">
							<#--
								<@s.hidden id="uom-id" name="transactionDetail.uom.id" />
								<input type="text" readonly="true" value="<#if transactionDetail.uom??> ${transactionDetail.uom.name!} </#if>" id="uom-name" class="span4" name="transactionDetail.uom.name">
								<button class="btn openpopup" type="button" title="<@s.text name="page.uom.title" />" object-name="uoms|name" field-target="uom-id|uom-name" href="<@s.url value="/pos/uom" />">Choose</button>
							-->
							<select name="transactionDetail.uom.id" id="conversion">
								
							</select>
							<#if erroruom?string=='true'>
								<span class="help-inline">Conversion for this uom is not available</span>
							</#if>
							<#if fieldErrors.containsKey('transactionDetail.uom.name')>
								<span class="help-inline">${fieldErrors.get('transactionDetail.uom.name').get(0)?string}</span>
							</#if>
							</div>
					</div>
					<div class="form-actions">
						<@s.submit key="button.add" cssClass="btn btn-primary" />
					</div>
					</#if>
				</@s.form>
				<ul id="myTab" class="nav nav-tabs">
					<li class="active"><a href="#home" data-toggle="tab">Item</a></li>
					<li><a href="#profile" data-toggle="tab">Internet</a></li>
				</ul>
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in active" id="home">
					
						<#if transactionHeader.id??>
						<table class="table table-striped table-condensed">
							<thead>
								<tr>
									<th>#</th>
									<th><@s.text name="label.admin.tdetail.item" /></th>
									<th style="text-align:center;"><@s.text name="label.admin.tdetail.quantity" /></th>
									<th style="text-align: right;"><@s.text name="label.admin.tdetail.price" /></th>
									<th style="text-align: right;"><@s.text name="label.admin.tdetail.subtotal" /></th>
									<#if !transactionHeader.cash??>
									<th style="text-align: right;"><@s.text name="label.admin.tdetail.action" /></th>
									</#if>
								</tr>
							</thead>
							<tbody>
								<#assign no = 1 + ((page - 1) * max) />
								<#assign totalPrice = 0 />
								<#assign totalQnty = 0 />
								<@s.url value="/pos/transaction/edit/" var="editUrl" />
								<#list transactionDetails.entityList as s>
								<#if s.item??>
									<#if s.conversion?? >
									<#assign price = (s.quantity * s.conversion.multiplyRate!0) * s.price />
									<#else>
									<#assign price = s.quantity * s.price />
									</#if> 
									<tr>
										<td>${no}</td>
										<td><#if s.item??>${s.item.name!}</#if></td>
										<td style="text-align: center;"><#if s.item??>${s.quantity?string('#')!} ${s.uom.name!}<#else>-</#if></td>
										<td style="text-align: right;">${s.price!}</td>
										<td style="text-align: right;">${price}</td>
										<#if !transactionHeader.cash??>
										<td style="text-align: right;"><a href="/pos/transaction/remove/${transactionHeader.id}?transactionDetail.id=${s.id}"><i class="icon-remove"></i></a></td>
										</#if>
									</tr>
									<#assign no = no + 1 />
									<#assign totalPrice = totalPrice + price!0 />
									<#assign totalQnty = totalQnty + s.quantity />
								</#if>
								</#list>
								<#assign no = no - 1 />
								
								<tr>
									<td></td>
									<td><strong><@s.text name="label.admin.tdetail.total" /></strong></td>
									<td style="text-align: center;"><#--<strong>${totalQnty}</strong>--></td>
									<td style="text-align: right;" colspan="2"><strong>${totalPrice}</strong> </td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="tab-pane fade" id="profile">
						<table class="table table-striped table-condensed">
							<thead>
								<tr>
									<th>#</th>
									<th><@s.text name="label.admin.tdetail.item" /></th>
									<th style="text-align: right;"><@s.text name="label.admin.tdetail.price" /></th>
									<#if !transactionHeader.cash??>
									<th style="text-align: right;"><@s.text name="label.admin.tdetail.action" /></th>
									</#if>
								</tr>
							</thead>
							<tbody>
								<#assign nox = 1 + ((page - 1) * max) />
								<#assign totalPriceInternet = 0 />
								<@s.url value="/pos/transaction/edit/" var="editUrl" />
								<#list transactionDetails.entityList as s>
								<#if s.internetPackage?? || s.userPackage??>
								<#if s.userPackage??>
									<#assign minuteUsage=(((s.userPackage.endDate?long - s.userPackage.logInformation.createDate?long)*0.001)/60)>
									<#assign priceTotal = minuteUsage/s.userPackage.internetPackage.time*s.userPackage.internetPackage.price>
								</#if>
									<tr>
										<td>${nox}</td>
										<td><#if s.internetPackage??>${s.internetPackage.name!}<#else>${s.userPackage.internetPackage.name!} ${timeFormat(minuteUsage)} @ ${s.userPackage.internetPackage.price} </#if></td>
										<td style="text-align: right;"><#if s.internetPackage??> ${s.internetPackage.price!} <#if s.internetPackage.paymentMethod == 'POSTPAID' > @ ${timeFormat(s.internetPackage.time!0)} </#if> <#elseif s.userPackage??> ${priceTotal} </#if> </td>
										<#if !transactionHeader.cash??>
										<td style="text-align: right;"><a href="/pos/transaction/remove/${transactionHeader.id}?transactionDetail.id=${s.id}"><i class="icon-remove"></i></a></td>
										</#if>
									</tr>
									<#assign no = no + 1 />
									<#if s.internetPackage??>
										<#if s.internetPackage.paymentMethod != 'POSTPAID' >
											<#assign totalPriceInternet = totalPriceInternet + s.internetPackage.price!0 />
										</#if>
									<#elseif s.userPackage??>
										<#assign totalPriceInternet = totalPriceInternet + priceTotal!0 />
									</#if>
								</#if>
								</#list>
								<#assign nox = nox - 1 />
								<tr>
									<td></td>
									<td><strong><@s.text name="label.admin.tdetail.total" /></strong></td>
									<td style="text-align: right;"><strong>${totalPriceInternet}</strong> </td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
					<#if transactionHeader.cash??>
					<#assign cashBack = transactionHeader.cash -  (totalPriceInternet+totalPrice) />
					<table class="table">
					<tr>
						<td colspan="2"><strong><@s.text name="label.admin.tdetail.cash" /></strong></td>
						<#if !transactionHeader.cash??>
						<td style="text-align: right;" colspan="3"><strong>${transactionHeader.cash!}</strong></td>
						<#else>
						<td style="text-align: right;" colspan="2"><strong>${transactionHeader.cash!}</strong></td>
						</#if>
					</tr>
					<tr>
						<td colspan="2"><strong><@s.text name="label.admin.tdetail.change" /></strong></td>
						<td style="text-align: right;" colspan="2"><strong>${cashBack!}</strong></td>
					</tr>
					</table>
					</#if>
					<#if !transactionHeader.cash??>
						<@s.form theme="bootstrap" action="/pos/transaction/cash" cssClass="form-horizontal">
						<@s.hidden name="transactionHeader.id" />
						<h3 class="pull-right">Grand Total ${(totalPriceInternet+totalPrice!0)?string('0.###')}</h3>
						<br/>
						<@s.textfield key="label.admin.tdetail.cash" required="true"  name="transactionHeader.cash" cssClass="span4" />
						<div class="form-actions">
							<@s.hidden name="grandtotal" value=(totalPriceInternet+totalPrice!0)?string('#')/>
							<@s.submit key="button.save" cssClass="btn btn-primary" onclick="open_win()" />
						</div>
						</@s.form>
						<#else>
						<form theme="bootstrap" class="form-horizontal">
						<div class="form-actions">
							<input type="button" value="Print" onclick="javascript:printDiv('print')" class="btn btn-primary"/>
						</div>
						</form>
					</#if>
				</#if>
			</div>
		</div>
		<div id="print" style="visibility: hidden;">
		<#include "/view/pos/invoice/invoice-print.ftl" />
		</div>
	</body>
</html>