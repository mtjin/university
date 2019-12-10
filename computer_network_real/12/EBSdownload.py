import signal
import os
import time
import subprocess
from datetime import datetime
import mutagen
import mutagen.mp3
from mutagen.easyid3 import EasyID3


def __get_current_time(is_month):
    today_time = datetime.today()
    if is_month == 'month':
        return today_time.strftime("%Y%n%d")
    return today_time.strftime("%Y%m%d%H%M%S")

def rtmp():
    file_name = __get_current_time("day") + "FILE_EBS"
    absolute_path = "/home/u201404377/u201404377/network_week12/EBS/" + file_name
    try:
        subprocess.run(("rtmpdump -r rtmp://new_iradio.ebs.co.kr/iradio/iradiolive_m4a -B 15 -o " + absolute_path+".flv"), stdout=subprocess.PIPE, shell=True)
        __flv_to_mp3(absolute_path)
        __change_meta_data(absolute_path, file_name)
    except subprocess.CalledProcessError as sub_exception:
        print("ERROR With RTMP:" , sub_exception)

def __flv_to_mp3(absolute_path):
    try:
        subprocess.run("ffmpeg -i "+ str(absolute_path)+".flv -acodec mp3 " + absolute_path + ".mp3", stdout=subprocess.PIPE, shell = True)
    except subprocess.CalledProcessError as sub_exception:
        print("ERROR With RTMP:" , sub_exception)


def __change_meta_data(file_path, file_name):
    file_path = "/home/u201404377/u201404377/network_week12/EBS/" + file_name + ".mp3"
    try:
        meta = EasyID3(file_path)
    except mutagen.id3.ID3NoHeaderError:
        meta = mutagen.File(file_path, easy = True)
        meta.add_tags()
    meta['title'] = __get_current_time("month") + "_jinSeungEon"
    meta["artist"] = "201404377"
    meta["genre"] =   "201404377_RADIO"
    meta["album"] = "201404377_album"
    meta.save()
    print("META", str(meta))

if __name__ == "__main__":
    #download ebs
    rtmp()
