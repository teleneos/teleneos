<html>
	<head>
		<title><@s.text name="page.home" /></title>
		<script type="text/javascript" src="<@s.url value="/scripts/leaflet/leaflet.js" />"></script>
		<link rel="stylesheet" type="text/css" href="<@s.url value="/scripts/leaflet/leaflet.css" />" />
		<!--[if lte IE 8]>
			<link rel="stylesheet" type="text/css" href="<@s.url value="/scripts/leaflet/leaflet.ie.css" />" />
		<![endif]-->
	</head>
	<body>
		<div class="row-fluid">
			<div class="span12 well" id="map" style="height: 500px;">
			</div>
		</div>
		<script type="text/javascript">
		$(function() {
			var map = new L.Map('map');
	
			var markerGreen = L.icon({
				iconUrl: '<@s.url value="/images/map/marker-comp-green.png" />',
				iconAnchor:   [16, 37],
				popupAnchor: [0, -25]
			});
			
			var markerRed = L.icon({
				iconUrl: '<@s.url value="/images/map/marker-comp-red.png" />',
				iconAnchor:   [16, 37],
				popupAnchor: [0, -25]
			});

			var markers = new L.LayerGroup();
			map.addLayer(markers);
	
			// Alternatives http://www.osmosa.net/osm_tiles3/{z}/{x}/{y}.png
			var tile = new L.TileLayer('http://tile.openstreetmap.org/{z}/{x}/{y}.png', {
				attribution: 'Tiles CC-BY-SA <a target="_blank" href="http://osmosa.net/">Osmosa</a>, data &copy; <a target="_blank" href="http://openstreetmap.org/">OpenStreetMap</a> contributors.',
				maxZoom: 18, minZoom: 3
			});
	
			map.addLayer(tile);
			
			<#--
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(p) {
					latLng = new L.LatLng(p.coords.latitude, p.coords.longitude);

					map.setView(latLng, 13);
					map.setZoom(13);

					var myLocMarker = new L.marker(latLng);
					myLocMarker.bindPopup('My Location');
					markers.addLayer(myLocMarker);
					
				});
			}
			-->
			
			<#--
			<#list telecentres.entityList as t>
				var tele = new L.marker([<#if t.latitude??>${t.latitude?string("0.0000000000")!}</#if>, <#if t.longitude??>${t.longitude?string("0.0000000000")!}</#if>],{icon : markerGreen});
				var client = '<p><strong>${t.name!}</strong><p><table class="table table-condensed table-stripped"><tr><th>Client</th><th>IP</th><th>Status</th></tr>';
				<#list t.telecentreComputers as tc>
					client = client +'<tr><td>${tc.name!}</td><td>${tc.host!}</td><td>';
					if(${tc.available!} == 1){
						client = client  + '<img src="<@s.url value="/images/map/marker-comp-green.png" />" /></td></tr>';
					}else{
						client = client  + '<img src="<@s.url value="/images/map/marker-comp-red.png" />" /></td></tr>';
					}
				</#list>
				client = client + '</table>';	
				tele.bindPopup(client);
				markers.addLayer(tele);
			</#list>
			-->
			
			$.getJSON('<@s.url value="/monitoring/availability.json" />', function(data) {
				var centerView;
			
				for (d in data.availabilities.entityList) {
					d = data.availabilities.entityList[d];
					
					var pingTime = d[1];
					var currentTime = +new Date();
					var tele = d[2];
					
					var marker;
					if ((currentTime - 60000) < pingTime) {
						marker = new L.marker([tele.lat, tele.lng], {icon : markerGreen});
					} else {
						marker = new L.marker([tele.lat, tele.lng], {icon : markerRed});
					}
					
					var container = $('<div />');
					$('<strong />').text(tele.name).appendTo(container);
					var a = $('<a/>').attr('href', '<@s.url value="/tele/edit/" />' + tele.id);
					a.text('(' + tele.id + ')');
					container.append(' ').append(a);
					
					a = $('<a/>').attr('href', 'mailto:' + tele.email);
					a.text(tele.email);
					$('<div />').append(a).appendTo(container);
					
					var p = $('<p />').appendTo(container);
					$('<div />').text(tele.address.street1).appendTo(p);
					$('<div />').text(tele.address.zip).append(' (').append(tele.phone).append(')').appendTo(p);
					
					p = $('<p />').appendTo(container);
					a = $('<a/>').attr('href', '<@s.url value="/monitoring/availability/clients/" />' + tele.id);
					a.attr('target', '_blank');
					a.text('view client status');
					p.append(a);					
					
					var htmlString = $('<div/>').append(container.clone()).html();
					
					marker.bindPopup(htmlString);
					
					markers.addLayer(marker);
					centerView = [tele.lat, tele.lng];
				}
				
				map.setView(centerView, 13);
				map.setZoom(13);
			});
			
			map.on('click', function(e) {
			
			});
		});
		</script>
	</body>
</html>