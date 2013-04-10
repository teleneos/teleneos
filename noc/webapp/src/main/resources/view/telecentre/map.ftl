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
			<div class="span12" id="map" style="height: 500px;">
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
			
			map.setView(new L.LatLng(13.78315, 100.54928), 13);
			map.setZoom(13);
			
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
			
			<#--
			var tele1 = new L.marker([13.78315, 100.54928], {icon : markerGreen});
			tele1.bindPopup('<p><strong>Tele 1</strong><p><table class="table table-condensed table-stripped"><tr><th>Client</th><th>IP</th><th>Connected</th></tr><tr><td>Comp 1</td><td>10.1.0.4</td><td><i class="icon-ok"></i></td></tr><tr><td>Comp 2</td><td>10.1.0.2</td><td><i class="icon-remove"></td></tr><tr><td>Comp 3</td><td>10.1.0.10</td><td><i class="icon-ok"></td></tr></table>');
			markers.addLayer(tele1);
			
			var tele2 = new L.marker([13.77723, 100.55992], {icon : markerGreen});
			tele2.bindPopup('Tele2');
			markers.addLayer(tele2);
			
			var tele3 = new L.marker([13.7889, 100.52696], {icon : markerRed});
			tele3.bindPopup('Tele 3');
			markers.addLayer(tele3);
			
			var tele4 = new L.marker(new L.LatLng(13.79574, 100.55357), {icon : markerRed});
			tele4.bindPopup('Tele 4');
			markers.addLayer(tele4);
			-->
						
			map.on('click', function(e) {
			
			});
		});
		</script>
	</body>
</html>