n = int(input())
m = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
parents = list(range(n))
plan = list(map(int, input().split()))


def find(a):
    if parents[a] != a:
        parents[a] = find(parents[a])
    return parents[a]


def union(a, b):
    a = find(a)
    b = find(b)
    if a > b:
        parents[a] = b
    else:
        parents[b] = a


for i in range(n):
    for j in range(n):
        if graph[i][j] == 1:
            union(i, j)

result = "YES"
for i in range(1, m):
    if parents[plan[i] - 1] != parents[plan[0] - 1]:
        result = "NO"
        break

print(result)