from collections import deque


def bfs():
    q = deque()
    q.append((0, 0))
    while q:
        r, c = q.popleft()
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if 0 <= nr < n and 0 <= nc < n:
                if costs[nr][nc] > costs[r][c] + board[nr][nc]:
                    costs[nr][nc] = costs[r][c] + board[nr][nc]
                    q.append((nr, nc))


t = 1
dr = [-1, 1, 0, 0]
dc = [0, 0, 1, -1]

MAX = 1e9

while True:
    n = int(input())
    if n == 0:
        break
    board = []
    costs = [[int(MAX)] * n for _ in range(n)]
    for _ in range(n):
        board.append(list(map(int, input().split())))

    costs[0][0] = board[0][0]
    bfs()
    print(f'Problem {t}: {costs[n - 1][n - 1]}')
    t += 1