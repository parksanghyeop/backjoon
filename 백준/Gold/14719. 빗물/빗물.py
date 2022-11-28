import sys

input = sys.stdin.readline


H, W = map(int, input().split())
walls = list(map(int, input().rstrip().split()))

max_wall = max(range(len(walls)), key=lambda x: walls[x])

# 가장 높은 벽 기준 왼쪽 합

temp = 0
result = 0
for i in range(max_wall + 1):
    temp = max(walls[i], temp)
    result += temp

# 가장 높으 벽 기준 오른쪽 합
temp = 0
for i in range(W - 1, max_wall, -1):
    temp = max(walls[i], temp)
    result += temp


print(result - sum(walls))