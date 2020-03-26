import cv2
import numpy as np


def mtjin_bgr2gray(bgr_img):
    # BGR 색상값
    b = bgr_img[:, :, 0]
    g = bgr_img[:, :, 1]
    r = bgr_img[:, :, 2]
    result = ((0.299 * r) + (0.587 * g) + (0.114 * b))
    # imshow 는 CV_8UC3 이나 CV_8UC1 형식을 위한 함수이므로 타입변환
    return result.astype(np.uint8)


input_img = cv2.imread("img/Lena.png", cv2.IMREAD_COLOR)
bgr_img = mtjin_bgr2gray(input_img)
cv2.namedWindow('GrayScale Image')
# 지정한윈도우에 이미지를 보여준다.
cv2.imshow("GrayScale Image", bgr_img)
# 지정한 시간만큼 사용자의 키보드입력을 대기한다. 0으로하면 키보드대기입력을 무한히 대기하도록한다.
cv2.waitKey(0)
cv2.imwrite("img/mtjin_bgr2gray_Lena.jpg", bgr_img)