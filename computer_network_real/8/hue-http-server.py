import socket
#import html
#import cgi
#import cgitb; cgitb.enable()
from http.server import BaseHTTPRequestHandler
from phue import Bridge

def open_server_socket(IP):
    s_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s_socket.bind((IP.ipaddress, IP.port))
    s_socket.listen()
    return s_socket

def accept_client(s_socket):

    while True:
        print("wait ... ")
        conn, addr = s_socket.accept()
        print(conn)
        print(addr)
        data = conn.recv(5000).decode('utf-8')
        #data example  GET /index.html /HTTP1.1...content-type...
        print("data ==> " + data)    
        #http_method is POST or GET
        http_method = data.split(" ")[0]

        try:
            if http_method == "GET": 
                #http_dir example /index.html
                http_dir = data.split(' ')[1]
                print('http_dir(GET) ==> '+http_dir)
                #remove /    and open that html file
                f = open(http_dir[1:])
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
            elif http_method == "POST":
                http_dir = data.split(' ')[1]
                print("http_dir(POST) ==> " + http_dir)
                f = open("hueController.html")
                #parsing post data ex)  light=on&brightness=1&color_x=0&color_y=0
                post_data = data.split('\r\n\r\n')[1]
                print(post_data)
                #parsing post value (ex on, 1)
                hue_num = http_dir[1:]
                print(hue_num)
                power = post_data.split('&')[0].split('=')[1]
                print(power)
                brightness = post_data.split('&')[1].split('=')[1]
                print(brightness)
                color_x = post_data.split('&')[2].split('=')[1]
                print(color_x)
                color_y = post_data.split('&')[3].split('=')[1]
                print(color_y)
               
                #serving page
                outputdata = f.read()
                conn.send('HTTP/1.1 200 OK\r\n'.encode('utf-8'))
                conn.send('Content-Type: text/html\n\n'.encode('utf-8'))
                conn.send(outputdata.encode('utf-8'))
                conn.close()

                #connect bridge
                
                bridge = Bridge('192.168.0.209')
                print("connect!!!!!!!!!!!")
                bridge.connect()
                lights = bridge.lights
                print(lights)
                if power == "on":
                    lights[int(hue_num)-1].on =True
                else:
                    lights[int(hue_num)-1].on = False

                lights[int(hue_num)-1].brightness = int(brightness)
                lights[int(hue_num)-1].xy = [float(color_x), float(color_y)]
                
                #power_control(hue_num, power)
                #brightness_control(hue_num, brightness)
                #color_control(hue_num, color_x, color_y) 
        #if http_dir is not exist exception
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

def power_control(bulb_num, power):
    print(bulb_num, power)
    try:
        if power == "on":
            lights[int(bulb_num)-1].on =True
        else:
            lights[int(bulb_num)-1].on = False
    except Exception as e:
        print("[power control] error a occur with " , e)

def brightness_control(bulb_num, brightness):
    print(bulb_num , brightness)
    try:
        lights[int(bulb_num)-1].brightness = int(brightness)
    except Exception as e:
        print("[brightness] error occur with " , e)

def color_control(bulb_num, colors_x, colors_y):
    print(bulb_num, colors_x, colors_y)
    try:
        lights[int(bulb_num)-1].xy = [float(colors_x), float(colors_y)]
    except Exception as e:
        print("[color] error occur with ", e)


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
