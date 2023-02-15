import sys
input = sys.stdin.readline

n, m = map(int, input().split())
link = [[] for _ in range(n)]
visited = [False] * n
for _ in range(m):
    a, b = map(int, input().split())
    link[a].append(b)
    link[b].append(a)


def dfs(v, d):
    if d == 4:
        print(1)
        sys.exit()
    for i in link[v]:
        if visited[i]:
            continue
        visited[i] = True
        dfs(i, d+1)
        visited[i] = False


for start in range(n):
    visited[start] = True
    dfs(start, 0)
    visited[start] = False

print(0)