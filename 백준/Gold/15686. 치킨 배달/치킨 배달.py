from itertools import combinations
import sys
input = sys.stdin.readline

house, chicken = [], []
n, m = map(int, input().split(' '))
maps = [list(map(int, input().split(' '))) for _ in range(n)]

for i in range(n):
    for j in range(n):
        if maps[i][j] == 1:
            house.append((i, j))
        elif maps[i][j] == 2:
            chicken.append((i, j))

result = 10000
for i in combinations(chicken, m):
    temp = 0
    for j in house:
        value = 10000
        for k in i:
            distance = abs(k[0] - j[0]) + abs(k[1] - j[1])
            value = min(value, distance)
        temp += value
    result = min(result, temp)

print(result)