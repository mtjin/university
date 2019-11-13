import socket
import select
import os

def main(FLAGS):
    serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    serversocket.bind(('', FLAGS.port))
    serversocket.listen()
    print("listening ...." )
    readable = [serversocket]
    writable = []
    error = []

    while True:
        try:
            r_list, w_list, e_list = select.select(readable, writable, error)
            for read_desc in r_list:
                if read_desc == serversocket:
                    client_socket, client_addr = serversocket.accept()
                    r_list.append(client_socket)
                    print("from {}".format(client_addr))
                else:
                    data = read_desc.recv(1024)
                    if data:
                        response = "HTTP/1.1 200 OK\r\n"
                        read_desc.send(response.encode('utf-8'))
                    else:
                        read_desc.close()
                        r_list.remove(read_desc)
        except Exception as err:
            print("EXCEPTION !!!", err)

            

if __name__ == "__main__":
    import argparse

    parser = argparse.ArgumentParser()
    parser.add_argument('-p', '--port', type = int, default=8000, help="input port number")
    FLAGS, _ = parser.parse_known_args()
    main(FLAGS)
