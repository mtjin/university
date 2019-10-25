from multiprocessing import Process
import socket
import os

def send_recv(client, address):
    msg = client.recv(1024)
    print("[client {}] {}".format(os.getpid(), msg.decode()))
    response = "HTTP/1.1 200 OK\r\n"
    client.send(response.encode('utf-8'))
    client.close()

def main(FLAGS):
    serversocket  = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    serversocket.bind(('', FLAGS.port))
    serversocket.listen(5)
    clients = list()

    while True:
        client, address =serversocket.accept()
        print("accept client from", address)
        clients.append(client)
        proc = Process(target=send_recv, args = (client, address))
        proc.start()
        proc.join()
        
if __name__ == "__main__":
    import argparse
    
    parser = argparse.ArgumentParser()
    parser.add_argument('-p', '--port', type = int, default=8000, help="input port number")
    FLAGS, _ = parser.parse_known_args()
    main(FLAGS)
