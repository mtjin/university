from threading import Thread
import socket
import os

def send_recv(client_socket, address):
    data = client_socket.recv(1024)
    print("[client {} ] {}".format(os.getpid(), data.decode()))
    msg = "HTTP/1.1 200 OK\r\n"
    client_socket.send(msg.encode('utf-8'))
    client_socket.send(data)
    client_socket.close()

def main(FLAGS):
    serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    serversocket.bind(('', FLAGS.port))
    print("listening...")
    serversocket.listen(5)
    client_socket = list()

    while True:
        client, address = serversocket.accept()
        print("accept client from", address)

        th = Thread(target=send_recv, args=(client,address))
        th.start()
        th.join()

if __name__ == "__main__":
    import argparse

    parser = argparse.ArgumentParser()
    parser.add_argument('-p', '--port', type = int, default=8000, help="input port number")
    FLAGS, _ = parser.parse_known_args()
    main(FLAGS)

