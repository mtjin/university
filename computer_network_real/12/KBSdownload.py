import signal
import os
import subprocess
from datetime import datetime
import time
import mutagen
import mutagen.mp3
from mutagen.easyid3 import EasyID3

def __get_current_time(is_month):
    today_time = datetime.today()
    if is_month == 'month':
        return today_time.strftime("%Y%n%d")
    return today_time.strftime("%Y%m%d%H%M%S")

def __change_meta_data(file_path, file_name):
    file_path = "/home/u201404377/u201404377/network_week12/KBS/" + file_name + ".mp3"
    try:
        meta = EasyID3(file_path)
    except mutagen.id3.ID3NoHeaderError:
        meta = mutagen.File(file_path, easy = True)
        meta.add_tags()
    meta['title'] = __get_current_time("month") + "_jinSeungEon"
    meta["artist"] = "201404377_KBS"
    meta["genre"] =   "201404377_RADIO"
    meta["album"] = "201404377_album"
    meta.save()
    print("META", str(meta))

def __flv_to_mp3(absolute_path):
    try:
        subprocess.run("ffmpeg -i "+ str(absolute_path)+".flv -acodec mp3 " + absolute_path + ".mp3", stdout=subprocess.PIPE, shell = True)
    except subprocess.CalledProcessError as sub_exception:
        print("ERROR With RTMP:" , sub_exception)

def his():
    file_name = file_name = __get_current_time("day") + "FILE_KBS"
    absolute_path = "/home/u201404377/u201404377/network_week12/EBS/" + file_name
    print("CHECK", absolute_path)
    url_KBS= ""
    #curl_KBS = "ffmpeg -i $(curl -s 'http://onair.kbs.co.kr/index.html?sname=onair&stype=live&ch_code=24&ch_type=radioList' | grep 'service_url' | cut -d\" -f 16 | cut -d\\ -f 1 | tail -1) -acodec mp3 " + absolute_path + ".mp3"

    curl_KBS= """mplayer $(curl -s "http://onair.kbs.co.kr/index.html?sname=onair&stype=live&ch_code=24&ch_type=radioList" | grep service_url | tail -1 | cut -d\\" -f16 | cut -d\\\\ -f1) -ao pcm:file="""  + absolute_path +  '.flv -vc dummy -vo null'
    process = subprocess.Popen(curl_KBS,shell=True, preexec_fn=os.setsid)
    time.sleep(15)
    os.killpg(os.getpgid(process.pid), signal.SIGTERM)
    __flv_to_mp3(absolute_path)
    __change_meta_data(absolute_path, file_name)

if __name__ == "__main__":
    #download ebs
    his()

