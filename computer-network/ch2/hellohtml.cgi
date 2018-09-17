#!/usr/bin/python3
import cgi, cgitb
import sys
import pygeoip
import subprocess
import re

form = cgi.FieldStorage()

print ("Content-type:text/html")
print ()
print ('<html>')
print ('<head>')
print ('<meta charset = "utf-8">')
print ('<title>Hello World</title>')
print('<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bc87aed3dea5c171d9b2483da79b6021"></script>')
print('<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bc87aed3dea5c171d9b2483da79b6021&libraries=services,clusterer,drawing"></script>')

print ('</head>')
print ('<body>')
print ('<h2>Traceroute - Visualize on Kakao Map</h2>')
print ("<form method='get', action='hellohtml.cgi'>")
print ("<input type= 'text' name='target'/>","<input type='submit'/>")
print ("</form>")


#map content(Daum Maps API)
print('<div id="map" style="width:500px;height:400px;"></div>')
print('<script>')
print("var container = document.getElementById('map');")
print('var options = {center: new daum.maps.LatLng(33.450701, 126.570667),level: 3};')
print('var map = new daum.maps.Map(container, options);')
print('</script>')

#get value of submit
argv_data = form.getvalue('target')
print ('<H2>Destination: ', argv_data,'</H2>')

#subprocess start
proc = subprocess.Popen(["traceroute", argv_data,], stdout = subprocess.PIPE)
out, err = proc.communicate()

#Get IP parsing and saved(ip_result)
pat = r'(?<=\().+?(?=\))'
ip_result = re.findall(pat, str(out))
gi = pygeoip.GeoIP('GeoLiteCity.dat')


lat = list()
lng = list()

#print 'latitude' and 'longitude' && append result to lat,lbg(list)
for i in range(0, len(ip_result)):
	result = gi.record_by_addr(ip_result[i])
	if result == None:
		print()
	else:
		print('(', result['latitude'],') - ','(',result['longitude'],')')
		lat.append(result['latitude'])
		lng.append(result['longitude'])
		print('<p>')

#remove same values
lat = list(set(lat))
lng = list(set(lng))


#marker indication with DaumMaps API
print('<script>')
print('var positions = [')
for i in range(0, len(lat)):
	if i != len(lat)-1:
		print('{title: "',argv_data, '",')
		print('latlng: new daum.maps.LatLng(',lat[i], ', ',lng[i],')  },')
	else:
		print('{title: "', argv_data, '",')		
		print('latlng: new daum.maps.LatLng(',lat[i], ', ',lng[i],')  }')
print('];')
print('var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";')
print('for (var i = 0; i < positions.length; i ++) {')
print('var imageSize = new daum.maps.Size(24, 35);')
print('var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize);')
print('var marker = new daum.maps.Marker({')
print('map: map, position: positions[i].latlng, title : positions[i].title,})};')
print('</script>')
print('</body>')
print('</html>')

