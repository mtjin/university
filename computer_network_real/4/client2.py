import socket
FLAGS = None
class ClientSocket():

    def __init__(self):
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    def socket_send_recv(self, FLAGS):
        self.socket.connect((FLAGS.ip, FLAGS.port))
        self.socket.send('GET /index.html HTTP/1.1\r\n'.encode('utf-8'))
        print('send complete')
        data = self.socket.recv(1024)
        print('From Server:', data.decode())
        self.socket.close()

    def main(self, FLAGS):
        self.socket_send_recv(FLAGS)

if __name__ == '__main__':
    import argparse

    parser = argparse.ArgumentParser()
    parser.add_argument('-i', '--ip', type=str, default='localhost')
    parser.add_argument('-p', '--port', type=int, default=1234)
    FLAGS, _ = parser.parse_known_args()

    client_socket = ClientSocket()
    client_socket.main(FLAGS)

