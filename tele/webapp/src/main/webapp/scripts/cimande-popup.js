$(function() {
$('#popup-dialog').on('show', function() {
	$('#popup-search').submit(function() {
		var obj = $(this).attr('object-name').split('|');
		var target = $(this).attr('field-target').split('|');
		var fire = $(this).attr('fire');
		jQuery.ajax({
			type : 'GET',
			dataType : 'json',
			accepts : 'application/json',
			url : $(this).attr('action') + ".json",
			data : $(this).serialize(),
			success : function(data) {
				$('#popup-data').empty();
			
				var table = $('<table class="table table-striped"></table>');
				$('#popup-data').append(table);
				
				function addRow(data1, data2) {
					var tr = $('<tr></tr>').appendTo(table);
					
					for (d in data1) {
						d = data1[d];
						if($.isNumeric(d) && new String(d).length==13){
							 var date=new Date(d);
							 var month=new Array();
							 month[0]="January";
							 month[1]="February";
							 month[2]="March";
							 month[3]="April";
							 month[4]="May";
							 month[5]="June";
							 month[6]="July";
							 month[7]="August";
							 month[8]="September";
							 month[9]="October";
							 month[10]="November";
							 month[11]="December";
							 var n = month[date.getMonth()]; 
							 d = date.getDate()+" "+n+" "+date.getFullYear();
						}
						var td1 = $('<td></td>').text(d);
						tr.append(td1);
					}
					
					var td2 = $('<td></td>');
					tr.append(td2);
					var btn = $('<a title="Set" href="#" data-dismiss="modal"></a>').addClass('btn');
					btn.addClass('btn-primary');
					btn.addClass('popup-btn');
					btn.addClass('pull-right');
					btn.attr('data-id', data2);
					btn.attr('data-name', data1);
					btn.append('&nbsp;<i class="icon-pencil icon-white"></i>&nbsp;');
					td2.append(btn);
				}
				
				/*addRow('None', '');*/
				
				for(d in data[obj[0]].entityList) {
					d = data[obj[0]].entityList[d];
					
					var dts = new Array();
					
					var datas = obj[1].split(',');
					for (dd in datas) {
						dd = datas[dd];
						
						var dt = d;
						
						var objs = dd.split('.');
						for (o in objs) {
							o = objs[o];
							
							dt = dt[o];
						}
						
						dts.push(dt);
					}
					if(target[0]=='noid'){
						addRow(dts, d.content);
					}else{
						addRow(dts, d.id);
					}
				}
				
				$('.popup-btn').click(function() {
					if(target[0]=='noid'){
						$('#' + target[1]).val($(this).attr('data-id'));
						$('#' + target[0]).val($(this).attr('data-name').split(',')[0]);
					}else{
						$('#' + target[0]).val($(this).attr('data-id'));
						$('#' + target[1]).val($(this).attr('data-name').split(',')[0]);
					}
					if(fire!=''){
						eval(fire);
					}
				});
			},
			error : function() {
				
			}
		});
	
		return false;
	}).submit();
});

$('#popup-dialog').on('hide', function() {
	$('#popup-search').unbind('submit');
	$('.popup-btn').unbind('click');
	$('#popup-data').empty();
	$('#popup-search').removeAttr('action');
	$('#popup-search').removeAttr('object-name');
	$('#popup-search').removeAttr('field-target');
	$('#popup-header').empty();
});

$('.openpopup').click(function() {
	$('#popup-search').attr('action', $(this).attr('href'));
	$('#popup-search').attr('object-name', $(this).attr('object-name'));
	$('#popup-search').attr('field-target', $(this).attr('field-target'));
	$('#popup-search').attr('fire', $(this).attr('fire'));
	$('#popup-header').text($(this).attr('title'));
	$('#popup-dialog').modal('show');
	
	return false;
});

$('.confirm').click(function(e) {
	var c = $(this);
	
	e.preventDefault();
	
	bootbox.confirm(c.attr('data-message'), function(result) {
		if (result) {
			var form = $('<form></form>');
			form.appendTo($(document.body));
			form.attr('action', c.attr('href'));
			form.attr('method', 'POST');
			
			form.submit();
		}
	});
	
});
});