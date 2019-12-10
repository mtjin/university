import socket
import signal
import os
import time
import subprocess
from datetime import datetime
import mutagen
import mutagen.mp3
from mutagen.easyid3 import EasyID3
from http.server import BaseHTTPRequestHandler



def open_server_socket(IP):
    s_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s_socket.bind((IP.ipaddress, IP.port))
    s_socket.listen()
    return s_socket

# search mp3 file dir
def search(dirname):
    result = []
    filenames = os.listdir(dirname)
    for filename in filenames:
        full_filename = os.path.join(dirname, filename)
        ext = os.path.splitext(full_filename)[-1]
        if ext == '.mp3':
            result.append(full_filename)
            print("SEARCH => " , full_filename)

    return result

# add audio html related mp3 (size : mp3 count)
def __html_templates(srces, download_name, file_name):
    tr_templates ="""<tr><td>
        <audio controls>
        <source src ='""" + srces + """' type = 'audio/mpeg'>
        </audio>
        </td>
        <td>
        <a href = '""" + srces + """' download ='""" + download_name + """'>
        """+file_name+"""</a>
        </td>
        </tr>
        """
    return tr_templates

def accept_client(s_socket):
    #save my mp3 file html
    ebsFile = search("/home/u201404377/u201404377/network_week12/EBS/")
    mp3_html=""    
    global mp3Byte
    mp3Byte = []
    for ebs in ebsFile:
        fileName = ebs.split("/")[-1]
        mp3_html += __html_templates(ebs,ebs,fileName)

    while True:
        print("wait ... ")
        conn, addr = s_socket.accept()
        data = conn.recv(1024).decode('utf-8')
        #http_method is POST or GET
        http_method = data.split(" ")[0]
        
        #response mp3 byte data
        if http_method == "GET":
            data2 = data.split(" ")[1]
            print("data ===> ", data2)
            if ".mp3" in data2:
                conn.send('HTTP/1.1 200 OK\r\n'.encode('utf-8'))
                conn.send('Content-Type: text/html\n\n'.encode('utf-8'))
                # read mp3 byte 
                with open(data2, "rb") as byteData:
                    conn.send(byteData.read())
                conn.close()
                continue
        try:
            # response audio html
            if http_method == "GET":
                #HTML 
                outputdata = """<!DOCTYPE html>
                           <html>
                            <head>
                               </head>
                         <body>
                         <h1 style = 'text-align:center'>
                            mp3
    </h1>
    <table style='margin-left: auto; margin-right: auto;'>
    <tr>
    <th> music </th>
    <th> download </th>
    </tr>
"""
                outputdata += mp3_html
                outputdata += """</table></body></html>"""
                print(outputdata)
                #SEND
                conn.send('HTTP/1.1 200 OK\r\n'.encode('utf-8'))
                conn.send('Content-Type: text/html\n\n'.encode('utf-8'))      
                conn.send(outputdata.encode('utf-8'))
                conn.close()

        except Exception as ex:
            print(ex)
            print('exception state')
            f = open('noIndex.html')
            outputdata = f.read()
            print("connected by ", end='')
            print(conn.getpeername())
            print("connect client...  ", end='')
            print(s_socket.getsockname()[1])
            print("IP:{0}, data:{1} ".format(addr[0], data))
            print(outputdata)
            conn.send('HTTP/1.1 404 Not Found\r\n'.encode('utf-8'))
            conn.send('Content-Type: text/html\n\n'.encode('utf-8'))
            conn.send(outputdata.encode('utf-8'))
            conn.close()

def main(FLAGS):
    server_socket = open_server_socket(FLAGS)
    accept_client(server_socket)

if __name__ == "__main__":
    import argparse

    parser = argparse.ArgumentParser()
    parser.add_argument('-i', '--ipaddress', type=str, default='127.0.0.1')
    parser.add_argument('-p', '--port', type=int, default=8080)
    FLAGS, _ = parser.parse_known_args()
    main(FLAGS)
