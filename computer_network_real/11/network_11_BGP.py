import os
import pickle

import matplotlib as mpl
import matplotlib.pyplot as plt
import numpy as np

mpl.rcParams['figure.dpi'] = 300
import time


class Node(object):
    def __init__(self, key, data=None):
        self.key = key
        self.data = data
        self.children = {}


class Trie(object):
    def __init__(self):
        self.head = Node(None)

    def insert(self, string, data):
        currentNode = self.head
        for char in string:
            if char not in currentNode.children:
                currentNode.children[char] = Node(char)
            currentNode = currentNode.children[char]

        if not currentNode.data:
            currentNode.data = data

    def search(self, string):
        currentNode = self.head
        rdata = None
        for char in string:
            if char in currentNode.children:
                currentNode = currentNode.children[char]
                if currentNode.data:
                    rdata = currentNode.data
            else:
                break

        if currentNode.data != None:
            return rdata
        else:
            return rdata


if __name__ == "__main__":
    # 전처리 시작
    print("전처리 시작")
    txt_result = []  # 전처리 결과
    pickle_result = [{}]
    before_network = ['', '', 0, 0, 0]  # 입력데이터가 오름차순으로 되있으니깐 바로 이전꺼와 비교해줌
    if not os.path.isfile("preProcessingData.pickle"):

        f = open('oix-full-snapshot-2019-11-28-0600', 'r')
        print("실행증... 시간이 약 1분정도 소요됩니다")
        # 필요없는줄 제거
        for i in range(0, 5):
            f.readline()

        line = f.readline()
        while line:
            oneline = line.split()
            network = oneline[1]
            next_hop = oneline[2]
            weight = int(oneline[5])
            loc_prf = int(oneline[4])
            path = len(oneline) - 7

            # 같은 아이피일 경우 비교 후 하나만 남김 (# Big Weight > High Local Pref > Short AS_PATH 탐색 전처리 기준)
            if before_network[0] == network:
                if weight > before_network[2]:
                    before_network = [network, next_hop, weight, loc_prf, path]
                elif weight == before_network[2] and loc_prf > before_network[3]:
                    before_network = [network, next_hop, weight, loc_prf, path]
                elif weight == before_network[2] and loc_prf == before_network[3] and path < before_network[4]:
                    before_network = [network, next_hop, weight, loc_prf, path]

            # 다른 아이피
            else:
                before_network = [network, next_hop, weight, loc_prf, path]

            # 마지막라인 이거나 해당 네트워크에서 가장 우선순위 높은 것으로 판명났을 경우 결과에 추가 해줌
            nextLine = f.readline()
            if nextLine == '':
                txt_result.append([network, before_network[1]])
                pickle_result.append(before_network)
                break

            splitNextLine = nextLine.split()
            if splitNextLine[1] != network:
                txt_result.append([network, before_network[1]])
                pickle_result.append(before_network)

            # cus while line
            line = nextLine

        # 텍스트파일로 저장 (hash와 trie에 사용하기 좋게 고침)
        with open('preProcessingData.txt', 'w') as f:
            for i in txt_result:
                tmpResult = i[0] + ' ' + i[1] + '\n'
                f.write(tmpResult)

        # pickle로 저장
        with open('preProcessingData.pickle', 'wb') as p_file:
            pickle.dump(pickle_result, p_file)

        print("전처리 끝(preProcessingData.pickle, preProcessingData.txt )")
    print("전처리된 파일이 있으므로 바로 탐색시작")
    # ----------------------------------------------------------------------------탐색 시작---------------------------------------------------------

    print("탐색부분 시작 (시간이 좀 걸릴 수 있습니다)")
    hashTimeList = []
    trieTimeList = []
    inputIpList = []
    lineCount = sum(1 for line in open('preProcessingData.txt'))
    hashIpTabel = [0 for i in range(lineCount)]

    hashingResult = []
    # 전처리한 데이터 추가 및 처리
    f = open('preProcessingData.txt')
    while True:
        line = f.readline()
        line = line[0:len(line) - 1]
        if not line:
            break
        # bit로 변환
        tmpId = ['', '', '']
        netwrokIdMask = line.split()[0]
        networkId = netwrokIdMask.split('/')[0]
        networkSplitId = networkId.split('.')
        networkMask = netwrokIdMask.split('/')[1]
        for i in range(0, len(networkSplitId)):
            networkSplitId[i] = str("{0:b}".format(int(networkSplitId[i])).zfill(8))
            tmpId[0] += networkSplitId[i]
        tmpId[0] = tmpId[0][:int(networkMask)]  # bit IP
        tmpId[1] = networkId  # IP
        tmpId[2] = line.split()[1]  # next hop
        bitIp = tmpId

        value = [bitIp[0], bitIp[2]]
        index = hash(bitIp[0]) % lineCount
        # 해시테이블에 데이터 추가
        if not hashIpTabel[index]:
            hashIpTabel[index] = [value]
        else:
            hashIpTabel[index].append(value)

    # ip 입력파일 생성
    txtFile = open("1000000.txt", "r")
    while True:
        line = txtFile.readline()
        if not line:
            break
        inputIpList.append(line)

    print("Hash 탐색 시작")
    # Hash탐색 시작
    hashStartTime = time.time()
    for ipLIst in inputIpList:
        mostSameHop = None

        bitIp = ''  # a.a.a.a -> 32 bit
        splitedIP = ipLIst.split('.')

        for i in range(0, len(splitedIP)):
            splitedIP[i] = str("{0:b}".format(int(splitedIP[i])).zfill(8))
            bitIp += splitedIP[i]
        # 8~32 비트별로 모두 탐색
        for i in range(8, 33):
            indexNum = hash(bitIp[0:i]) % lineCount
            isSearch = hashIpTabel[indexNum]
            if isSearch:  # 리스트 순회
                for kv in isSearch:  # [] in value's list
                    if kv[0] == bitIp[0:i]:
                        mostSameHop = kv[1]
                        break
        tmp = mostSameHop
        hashingResult.append(tmp)
        # 시간리스트 추가
        hashEndtime = time.time()
        hashResultTime = hashEndtime - hashStartTime
        hashTimeList.append(hashResultTime)

    hashEndtime = time.time()
    hashResultTime = hashEndtime - hashStartTime
    # HASH 탐색 결과값 텍스트파일로 저장
    with open('HashSearchResult.txt', 'w') as f:
        for i in hashingResult:
            tmpResult = str(i) + '\n'
            f.write(tmpResult)
    # ---------------------------------------------------------------------------위에까지 해시----------------------------------------------------------------
    print("TRIE 탐색 시작")
    f = open('preProcessingData.txt')
    t = Trie()
    while True:
        tmpId = ''
        line = f.readline()
        # 줄개행 자름
        line = line[0:len(line) - 1]

        # 마지막 줄이면 종료
        if not line:
            break
        netwrokIdMask = line.split(' ')[0]
        hop = line.split(' ')[1]
        networkId = netwrokIdMask.split('/')[0]
        networkSplitId = networkId.split('.')
        networkMask = netwrokIdMask.split('/')[1]

        if networkMask != '0':
            for i in range(0, len(networkSplitId)):
                networkSplitId[i] = str("{0:b}".format(int(networkSplitId[i])).zfill(8))
                tmpId += networkSplitId[i]
            t.insert(tmpId[0:int(networkMask)], hop)

    # Trie 탐색 시작
    trieStartTime = time.time()
    treeResultList = []
    for ip in inputIpList:
        tmp = ip.split('.')
        tmp2 = ''
        for i in range(0, len(tmp)):
            tmp2 += "{0:b}".format(int(tmp[i])).zfill(8)
        treeResultList.append(t.search(tmp2))
        # 시간 리스트 추가
        trieEndTime = time.time()
        trieResultTime = trieEndTime - trieStartTime
        trieTimeList.append(trieResultTime)
    trieEndTime = time.time()
    trieResultTime = trieEndTime - trieStartTime
    print("Trie 탐색 끝")

    # HASH 탐색 결과값 텍스트파일로 저장
    with open('TrieSearchResult.txt', 'w') as f:
        for i in treeResultList:
            tmpResult = str(i) + '\n'
            f.write(tmpResult)

    print("txt 파일로 결과 출력했습니다.(HashSearchResult.txt , TrieSearchResult.txt")
    print("Hash 탐색 걸린시간 => ", hashResultTime)
    print("Trie 탐색 걸린시간 => ", trieResultTime)

    # Hash, Trie 탐색 걸린시간 그래프 시각화
    plt.plot(hashTimeList)
    plt.plot(trieTimeList)
    plt.xlabel("X")
    plt.ylabel("TIME(second)")
    plt.title("Hash, Trie Compare")
    plt.legend(["Hash", "Trie"])
    plt.show()
