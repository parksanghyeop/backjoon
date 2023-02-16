from collections import deque
import sys
input = sys.stdin.readline
dr = [-1, -2, -2, -1, 1, 2, 2, 1]
dc = [2, 1, -1, -2, -2, -1, 1, 2]


def bfs(sr, sc, ar, ac):
    q = deque()
    q.append([sr, sc])
    s[sr][sc] = 1
    while q:
        a, b = q.popleft()
        if a == ar and b == ac:
            print(s[ar][ac] - 1)
            return
        for i in range(8):
            r = a + dr[i]
            c = b + dc[i]
            if 0 <= r < n and 0 <= c < n and s[r][c] == 0:
                q.append([r, c])
                s[r][c] = s[a][b] + 1


t = int(input())
for i in range(t):
    n = int(input())
    sx, sy = map(int, input().split())
    ax, ay = map(int, input().split())
    s = [[0] * n for i in range(n)]
    bfs(sx, sy, ax, ay)