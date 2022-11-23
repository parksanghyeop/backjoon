import sys
import heapq

n = int(input())
arr = []
for _ in range(n):
    temp = list(map(int, sys.stdin.readline().rstrip().split()))

    if not arr:
        for t in temp:
            heapq.heappush(arr, t)
    else:
        for t in temp:
            if arr[0] < t:
                heapq.heappush(arr, t)
                heapq.heappop(arr)

print(arr[0])