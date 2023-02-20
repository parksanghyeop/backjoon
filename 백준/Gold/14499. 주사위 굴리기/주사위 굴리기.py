import sys

input = sys.stdin.readline
dr = [0, 0, -1, 1]
dc = [1, -1, 0, 0]


n, m, r, c, k = map(int, input().split())
a = [list(map(int, input().split())) for _ in range(n)]
order = list(map(int, input().split()))
dice = [0 for _ in range(6)]

for i in range(k):
    dir = order[i] - 1
    nr = r + dr[dir]
    nc = c + dc[dir]
    if not 0 <= nr < n or not 0 <= nc < m:
        continue

    if dir == 0:
        dice[0], dice[2], dice[3], dice[5] = dice[3], dice[0], dice[5], dice[2]
    elif dir == 1:
        dice[0], dice[2], dice[3], dice[5] = dice[2], dice[5], dice[0], dice[3]
    elif dir == 2:
        dice[0], dice[1], dice[4], dice[5] = dice[4], dice[0], dice[5], dice[1]
    else:
        dice[0], dice[1], dice[4], dice[5] = dice[1], dice[5], dice[0], dice[4]

    if a[nr][nc] == 0:
        a[nr][nc] = dice[5]
    else:
        dice[5] = a[nr][nc]
        a[nr][nc] = 0

    r, c = nr, nc
    print(dice[0])