import heapq

MAX = 10001

n, d = map(int, input().split())

road = [[] * MAX for _ in range(MAX)]
heap = []
dp = [i for i in range(MAX)]

for _ in range(n):
    start, end, cost = map(int, input().split())
    heapq.heappush(heap, (end, start, cost))

while heap:
    newEnd, newStart, newCost = heapq.heappop(heap)
    
    # 지름길이 더 비효율이거나 범위를 벗어날 때
    if newCost > (dp[newEnd] - dp[newStart]) or newEnd > d:
        continue

    distance = dp[newStart] + newCost

    if dp[newEnd] > distance:
        dp[newEnd] = distance

    for i in range(newEnd + 1, MAX):
        dp[i] = min(dp[i], dp[i - 1] + 1)

print(dp[d])