import socket
import struct
import subprocess
import time
from phue import Bridge
import binascii
import threading
from scapy.all import*

def main():
    bridge = Bridge('192.168.0.211')
    bridge.connect()
    lights = bridge.lights
    
    RecvSocket  = socket.socket(socket.AF_PACKET, socket.SOCK_RAW, socket.htons(0x0003))

     
    raw_socket = socket.socket(socket.PF_PACKET,
            socket.SOCK_RAW,
            socket.htons(0x0800))
    raw_socket.bind(("ens33", socket.htons(0x800)))

    protocol = 0x0806
    source_mac = bytes.fromhex('5076af6f5991')
    dest_mac = bytes.fromhex('ffffffffffff')

    eth_header = struct.pack("!6s6sH", dest_mac, source_mac, protocol)

    hardware_type = 0x0001
    protocol_type = 0x0800
    hardware_size = 0x06
    protocol_size = 0x04

    opcode = 1 #request
     
    sender_mac = bytes.fromhex('5076af6f5991')
    #d0b128275cf9
    target_mac = bytes.fromhex('000000000000')
    sender_ip = socket.inet_aton('192.168.0.164')
    target_ip = socket.inet_aton('192.168.0.210')

    arp_header = struct.pack("!HHBBH6s4s6s4s", hardware_type, protocol_type, hardware_size, protocol_size , opcode, sender_mac, sender_ip, target_mac, target_ip)
    packet = eth_header + arp_header 
    raw_socket.sendall(packet)

    #Receive
    packet = RecvSocket.recvfrom(65535)
    phone_ip = struct.unpack('!BBBB', packet[0][38:42] )
    phone_ip = str(phone_ip[0])+ '.'+ str(phone_ip[1]) + '.' + str(phone_ip[2]) +'.' +str(phone_ip[3])
    print("My PHONE IP !! : ", phone_ip)

    while True:
        try:
            time.sleep(1)
            # ping to my phone ip that I parsed in packet
            status, result = subprocess.getstatusoutput("ping -c1 -w2 " + phone_ip)
            if status == 0:
                print("My PHONE IP RESPONSE!! : ", phone_ip)
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
        except Exception as ex:
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

    


if __name__ == "__main__":
    arp = main()
