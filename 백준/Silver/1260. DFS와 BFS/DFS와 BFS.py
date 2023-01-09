import sys

input = sys.stdin.readline

# N = 정점의 갯수 | M = 간선의 갯수 | V = 탐색을 시작할 정점의 번호
N, M, V = map(int, input().split())

# 인접 행렬
arr = [[0] * (N+1) for i in range(N+1)]

# 방문 여부 번호가 1부터 시작이라 N+1
visited = [0] * (N+1)

# 인접 행렬에 간선 정보 입력
for i in range(M):
    a, b = map(int, input().split())
    arr[a][b] = arr[b][a] = 1


def dfs(V):
    visited[V] = 1
    print(V, end=' ')
    for i in range(1, N+1):
        if visited[i] == 0 and arr[V][i] == 1:
            dfs(i)


def bfs(V):
    q = [V]

    visited[V] = 0

    while q:
        V = q.pop(0)
        print(V, end=' ')
        for i in range(1, N+1):
            if visited[i] == 1 and arr[V][i] == 1:
                q.append(i)
                visited[i] = 0


dfs(V)
print()
bfs(V)