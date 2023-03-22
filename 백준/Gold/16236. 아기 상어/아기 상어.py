from collections import deque
import sys
input = sys.stdin.readline

n = int(input())
s_x, s_y = -1, -1
s_level, eat = 2, 0
graph = []

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

for i in range(n):
    data = list(map(int, input().split()))
    graph.append(data)
    for j in range(n):
        if data[j] == 9:
            s_x, s_y = i, j


graph[s_x][s_y] = 0


def bfs(s_x, s_y, s_level):
    visited = [[False]*n for _ in range(n)]
    q = deque()
    q.append((s_x, s_y, 0))
    visited[s_x][s_y] = True
    fish = []
    while q:
        x, y, count = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny]:
                visited[nx][ny] = True
                if graph[nx][ny] != 0 and graph[nx][ny] < s_level:
                    fish.append((count+1, nx, ny))
                    q.append((nx, ny, count+1))
                    visited[nx][ny] = True
                elif graph[nx][ny] == 0 or graph[nx][ny] == s_level:
                    visited[nx][ny] = True
                    q.append((nx, ny, count+1))

    fish.sort()
    if fish:
        return [fish[0][1], fish[0][2], fish[0][0]]
    else:
        return []


answer = 0

while True:
    fish_eat = bfs(s_x, s_y, s_level)
    if fish_eat:
        x, y, move = fish_eat
        graph[x][y] = 0
        eat += 1
        answer += move
        if eat == s_level:
            s_level += 1
            eat = 0
        s_x, s_y = x, y
    else:
        break

print(answer)