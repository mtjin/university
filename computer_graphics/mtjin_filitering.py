import cv2
import numpy as np
import time


# 바깥쪽 패딩 채우기
def my_padding(img, shape, boundary=0):
    '''
    :param img: boundary padding을 해야할 이미지
    :param shape: kernel의 shape
    :param boundary: default = 0, zero-padding : 0, repetition : 1, mirroring : 2
    :return: padding 된 이미지.
    '''
    row, col = len(img), len(img[0])
    pad_sizeY, pad_sizeX = shape[0] // 2, shape[1] // 2
    res = np.zeros((row + (2 * pad_sizeY), col + (2 * pad_sizeX)), dtype=np.float)
    pad_row, pad_col = len(res), len(res[0])
    if pad_sizeY == 0:
        res[pad_sizeY:, pad_sizeX:-pad_sizeX] = img.copy()
    elif pad_sizeX == 0:
        res[pad_sizeY:-pad_sizeY, pad_sizeX:] = img.copy()
    else:
        res[pad_sizeY:-pad_sizeY, pad_sizeX:-pad_sizeX] = img.copy()
    if boundary == 0:
        return res
    elif boundary == 1:
        res[0:pad_sizeY, 0:pad_sizeX] = img[0, 0]  # 좌측 상단
        res[-pad_sizeY:, 0:pad_sizeX] = img[row - 1, 0]  # 좌측 하단
        res[0:pad_sizeY, -pad_sizeX:] = img[0, col - 1]  # 우측 상단
        res[-pad_sizeY:, -pad_sizeX:] = img[row - 1, col - 1]  # 우측 하단
        # axis = 1, 열반복, axis = 0, 행반복. default 0
        res[0:pad_sizeY, pad_sizeX:pad_col - pad_sizeX] = np.repeat(img[0:1, 0:], [pad_sizeY], axis=0)  # 상단
        res[pad_row - pad_sizeY:, pad_sizeX:pad_col - pad_sizeX] = np.repeat(img[row - 1:row, 0:], [pad_sizeY],
                                                                             axis=0)  # 하단
        res[pad_sizeY:pad_row - pad_sizeY, 0:pad_sizeX] = np.repeat(img[0:, 0:1], [pad_sizeX], axis=1)  # 좌측
        res[pad_sizeY:pad_row - pad_sizeY, pad_col - pad_sizeX:] = np.repeat(img[0:, col - 1:col], [pad_sizeX],
                                                                             axis=1)  # 우측
        return res
    else:
        res[0:pad_sizeY, 0:pad_sizeX] = np.flip(img[0:pad_sizeY, 0:pad_sizeX])  # 좌측 상단
        res[-pad_sizeY:, 0:pad_sizeX] = np.flip(img[-pad_sizeY:, 0:pad_sizeX])  # 좌측 하단
        res[0:pad_sizeY, -pad_sizeX:] = np.flip(img[0:pad_sizeY, -pad_sizeX:])  # 우측 상단
        res[-pad_sizeY:, -pad_sizeX:] = np.flip(img[-pad_sizeY:, -pad_sizeX:])  # 우측 하단

        res[pad_sizeY:pad_row - pad_sizeY, 0:pad_sizeX] = np.flip(img[0:, 0:pad_sizeX], 1)  # 좌측
        res[pad_sizeY:pad_row - pad_sizeY, pad_col - pad_sizeX:] = np.flip(img[0:, col - pad_sizeX:], 1)  # 우측
        res[0:pad_sizeY, pad_sizeX:pad_col - pad_sizeX] = np.flip(img[0:pad_sizeY, 0:], 0)  # 상단
        res[pad_row - pad_sizeY:, pad_sizeX:pad_col - pad_sizeX] = np.flip(img[row - pad_sizeY:, 0:], 0)  # 하단
        return res


# Gaussian kernel 생성 코드를 작성해주세요.
def my_getGKernel(shape, sigma):
    '''
    :param shape: 생성하고자 하는 gaussian kernel의 shape입니다. (5,5) (1,5) 형태로 입력받습니다.
    :param sigma: Gaussian 분포에 사용될 표준편차입니다. shape가 커지면 sigma도 커지는게 좋습니다.
    :return: shape 형태의 Gaussian kernel
    '''
    # a = shape[0] , b = shape[1] , (s = 2a+1, t = 2b+1)
    s = (shape[0] - 1) / 2
    t = (shape[1] - 1) / 2

    # 𝑠,𝑡 가 –a~a, -b~b의 범위를 가짐 ,  np.ogrid[-m:m+] : -m~m까지 증가하는 array를 반환한다.
    # 𝑥 :−𝑏~𝑏 범위의 Kernel에서의 x좌표(열) , 𝑦 :−𝑎~𝑎 범위의 Kernel에서의 y좌표(행)
    y, x = np.ogrid[-s:s + 1, -t:t + 1]
    # e^-(x^2 + y^2)/2𝜎^2
    # -	np.exp(x) : 𝑒^𝑥 를 구한다
    gaus_kernel = np.exp(-(x * x + y * y)) / (2. * sigma * sigma)
    # arr.sum() : array의 값을 모두 더해 반환한다.
    sum = gaus_kernel.sum()
    gaus_kernel /= sum
    return gaus_kernel


def my_filtering(img, kernel, boundary=0):
    '''
    :param img: Gaussian filtering을 적용 할 이미지
    :param kernel: 이미지에 적용 할 Gaussian Kernel
    :param boundary: 경계 처리에 대한 parameter (0 : zero-padding, default, 1: repetition, 2:mirroring)
    :return: 입력된 Kernel로 gaussian filtering이 된 이미지.
    '''
    # 이미지 행열
    row, col = len(img), len(img[0])
    # 커널 행열, arr.shape : array의 shape를 나타낸다
    ksizeY, ksizeX = kernel.shape[0], kernel.shape[1]

    pad_image = my_padding(img, (ksizeY, ksizeX), boundary=boundary)  # 패딩처리
    filtered_img = np.zeros((row, col), dtype=np.float32)  # 음수 소수점 처리위해 float형
    # filtering 부분
    for i in range(row):
        for j in range(col):
            filtered_img[i, j] = np.sum(
                np.multiply(kernel, pad_image[i:i + ksizeY, j:j + ksizeX]))  # filter * image

    return filtered_img


# 2000x3000 img   read by grayscale(0)
src = cv2.imread('img/mtjin_filtering.png', 0)

# get Gaussian Kernal 필터 크기 홀수 x 홀수인 모든 필터를 만족해야한다.
gaus2D = my_getGKernel((51, 51), 13)
gaus1D = my_getGKernel((1, 51), 13)

start = time.perf_counter()  # 시간 측정
# 2D filtering
img2D = my_filtering(src, gaus2D)
end = time.perf_counter()
print("2D:", end - start)

start = time.perf_counter()
# 1D filtering
img1D = my_filtering(src, gaus1D)
img1D = my_filtering(img1D, gaus1D.T)
end = time.perf_counter()
print("1D:", end - start)

cv2.imshow("original", src)
cv2.imshow('img1D', img1D.astype(np.uint8))
cv2.imshow('img2D', img2D.astype(np.uint8))
cv2.waitKey()
cv2.destroyAllWindows()
