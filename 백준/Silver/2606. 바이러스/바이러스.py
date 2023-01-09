import sys
input = sys.stdin.readline

n = int(input())
v = int(input())

graph = [[] for i in range(n+1)]
visited = [0]*(n+1)
for i in range(v):
    a, b = map(int, input().split())
    graph[a] += [b]
    graph[b] += [a]

visited[1] = 1
q = [1]
while q:
    current = q.pop(0)
    for i in graph[current]:
        if visited[i] == 0:
            q.append(i)
            visited[i] = 1

print(sum(visited)-1)