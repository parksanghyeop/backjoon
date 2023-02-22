import sys
from collections import defaultdict
input = sys.stdin.readline

dr = [0, 0, -1, 1]
dc = [-1, 1, 0, 0]
N = int(input())
arr = [[0]*N for _ in range(N)]
likedict = defaultdict(list)
result = 0

for _ in range(N*N):
    _input = list(map(int, input().split()))
    likedict[_input[0]] = _input[1:]

    max_r = 0
    max_c = 0
    max_like = -1
    max_empty = -1
    for i in range(N):
        for j in range(N):
            if arr[i][j] == 0:
                likecnt = 0
                emptycnt = 0
                for k in range(4):
                    nr = i + dr[k]
                    nc = j + dc[k]
                    if 0 <= nr < N and 0 <= nc < N:
                        if arr[nr][nc] in _input:
                            likecnt += 1
                        if arr[nr][nc] == 0:
                            emptycnt += 1

                if max_like < likecnt or (max_like == likecnt and max_empty < emptycnt):
                    max_r = i
                    max_c = j
                    max_like = likecnt
                    max_empty = emptycnt

    arr[max_r][max_c] = _input[0]

for i in range(N):
    for j in range(N):
        cnt = 0
        like = likedict[arr[i][j]]
        for k in range(4):
            nr = i + dr[k]
            nc = j + dc[k]
            if 0 <= nr < N and 0 <= nc < N:
                if arr[nr][nc] in like:
                    cnt += 1
        if cnt != 0:
            result += 10 ** (cnt-1)

print(result)