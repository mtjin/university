import socket
import struct
import subprocess
import time
from phue import Bridge

def main():
    raw_socket = socket.socket(socket.AF_PACKET,
            socket.SOCK_RAW,
            socket.ntohs(0x0003))
    my_macip = "d0b128275cf9"
    #hue bridge connect
    
    bridge = Bridge('192.168.0.218')
    bridge.connect()
    lights = bridge.lights
    
    
    #sniffing
    while True:
        recv_packet = raw_socket.recvfrom(5000)
        ethernet_protocol = struct.unpack('!6s6sH', (recv_packet[0])[:14])[2]
        if ethernet_protocol == 0x800:
            ip_protocol = struct.unpack('!BBHHHBBH4s4s', recv_packet[0][14:34])[6]
            if ip_protocol == 17:
                udp_src_port = struct.unpack('!H', (recv_packet[0])[34:34+2])[0]
                if udp_src_port == 68:
                    packet_data = recv_packet[0][42:]
                    #parsing phone mac ip
                    come_macip = packet_data.hex()[56:68]
                    print("Come MAC Ip => ", come_macip)
                    if my_macip == come_macip:
                        print("My MAC IP !!!!!!!!!!!")
                        print(recv_packet[0][0:])
                        print("HEX DATA : ", recv_packet[0][0:].hex()[0:])
                        phone_ip = struct.unpack('!BBBB', (recv_packet[0])[296:300])
                        phone_ip = str(phone_ip[0])+ '.'+ str(phone_ip[1]) + '.' + str(phone_ip[2]) +'.' +str(phone_ip[3])
                        print("PHONE => " , phone_ip)
                        
                        while True:
                            # 1 second sleep
                            time.sleep(1)
                            # ping to my phone ip that I parsed in packet
                            status, result = subprocess.getstatusoutput("ping -c1 -w2 " + phone_ip)
                            if status == 0:
                                print('respone OK')                                
                                lights[0].on = True
                                lights[0].brightness = int(250)
                                lights[0].xy = [float(0.9), float(0.9)]
                                lights[1].on = True
                                lights[1].brightness = int(250)
                                lights[1].xy = [float(0.9), float(0.9)]
                                lights[2].on = True
                                lights[2].brightness = int(250)
                                lights[2].xy = [float(0.9), float(0.9)]

                                
                            else :
                                print("NOT respond")
                                lights[0].on = False
                                lights[0].brightness = int(0)
                                lights[0].xy = [float(0.0), float(0.0)]
                                lights[1].on = False
                                lights[1].brightness = int(0)
                                lights[1].xy = [float(0.0), float(0.0)]
                                lights[2].on = False
                                lights[2].brightness = int(0)
                                lights[2].xy = [float(0.0), float(0.0)]
                                break


if __name__ == "__main__":
    main()

