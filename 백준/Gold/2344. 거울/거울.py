import sys

input = sys.stdin.readline

dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]
dir = [1, 0, 3, 2]


def out(x, y):  # 빛이 나가는 좌표에 따른 번호
    if x == -1:
        return 2 * N + 2 * M - y
    elif y == -1:
        return x + 1
    elif x == N:
        return N + y + 1
    else:
        return 2 * N + M - x


def search(start, direction):
    x, y = start
    while 0 <= x < N and 0 <= y < M:
        if board[x][y] == 1:
            direction = dir[direction]
        x += dr[direction]
        y += dc[direction]
    return str(out(x, y))


N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
result = []

for i in range(N):
    direction = 1
    result.append(search([i, 0], direction))
for i in range(M):
    direction = 0
    result.append(search([N - 1, i], direction))
for i in range(N - 1, -1, -1):
    direction = 3
    result.append(search([i, M - 1], direction))
for i in range(M - 1, -1, -1):
    direction = 2
    result.append(search([0, i], direction))
print(' '.join(result))