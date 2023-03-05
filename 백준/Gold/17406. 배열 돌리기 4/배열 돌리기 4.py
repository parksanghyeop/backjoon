import sys
import copy

from itertools import permutations

input = sys.stdin.readline

N, M, K = map(int, input().split())
m = [list(map(int, input().split())) for _ in range(N)]


def rotate(x, y, r, m2):
    tmp = m2[x][y + r]
    for j in range(y + r, y, -1):
        m2[x][j] = m2[x][j - 1]

    tmp2 = m2[x + r][y + r]
    for i in range(x + r, x + 1, -1):
        m2[i][y + r] = m2[i - 1][y + r]
    m2[x + 1][y + r] = tmp

    tmp = m2[x + r][y]
    for j in range(y, y + r - 1):
        m2[x + r][j] = m2[x + r][j + 1]
    m2[x + r][y + r - 1] = tmp2

    for i in range(x, x + r - 1):
        m2[i][y] = m2[i + 1][y]
    m2[x + r - 1][y] = tmp

    return m2


def run(o, m2):
    mn = 5001
    for oo in o:
        r, c, s = oo[0]-1, oo[1]-1, oo[2]
        while s:
            m2 = rotate(r-s, c-s, s*2, m2)
            s -= 1

    mn = min(check(m2), mn)
    return mn


def check(m):
    mn = 5001
    for i in range(N):
        s = 0
        for j in range(M):
            s += m[i][j]
        mn = min(mn, s)
    return mn


mn = 5001
order = []
for _ in range(K):
    r, c, s = map(int, input().split())
    order.append((r, c, s))

p = list(permutations(order, K))
for o in p:
    m2 = copy.deepcopy(m)
    mn = min(mn, run(o, m2))
print(mn)