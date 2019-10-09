import socket
from http.server import BaseHTTPRequestHandler


def open_server_socket(IP):
    s_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s_socket.bind((IP.ipaddress, IP.port))
    s_socket.listen()
    return s_socket

def accept_client(s_socket):

    while True:
        print("wait ... ")
        conn, addr = s_socket.accept()
        data = conn.recv(1024).decode('utf-8')
        try:
            html_dir = data.split(' ')[1]
            print('html_dir ==> '+html_dir)
            print(html_dir[1:])
            f = open(html_dir[1:])
            outputdata = f.read()
            print("connected by ", end='')
            print(conn.getpeername())
            print("connect client...  ", end='')
            print(s_socket.getsockname()[1])
            print("IP:{0}, data:{1} ".format(addr[0], data))
            print(outputdata)
            conn.send('HTTP/1.1 200 OK\r\n'.encode('utf-8'))
            conn.send('Content-Type: text/html\n\n'.encode('utf-8'))
            conn.send(outputdata.encode('utf-8'))
            conn.close()
        except Exception as ex:
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
    parser.add_argument('-i', '--ipaddress', type=str, default='172.17.0.2')
    parser.add_argument('-p', '--port', type=int, default=80)

    FLAGS, _ = parser.parse_known_args()
    main(FLAGS)

