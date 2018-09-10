import sys
import subprocess
import re
import pygeoip
if len(sys.argv) < 2:
	print("Input argument is one")
	sys.exit()

print('[Destination]', sys.argv[1])
#subprocess start
proc = subprocess.Popen(["traceroute", sys.argv[1],], stdout = subprocess.PIPE)
out, err = proc.communicate()

#Get IP parsing and saved(ip_result)
pat = r'(?<=\().+?(?=\))'
ip_result = re.findall(pat, str(out))
gi = pygeoip.GeoIP('GeoLiteCity.dat')

#Use IP to get record(save in result), then get and print lat,lon 
for i in range(0, len(ip_result)):
	result = gi.record_by_addr(ip_result[i])
	if result == None:
		print('[IP]  ' ,ip_result[i] ,'- No Geoloaction Info')
	else:
		print('[IP]  ' ,ip_result[i] ,'- Lat: ', result['latitude'] ,' ,Lon: ', result['longitude'])

