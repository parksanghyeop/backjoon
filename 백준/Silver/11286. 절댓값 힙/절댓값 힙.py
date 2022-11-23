import heapq
import sys

N = int(input())
heap = []
for _ in range(N):
    x = int(sys.stdin.readline().rstrip())
    if x != 0:
        heapq.heappush(heap, (abs(x), x))
    else:
        try:
            print(heapq.heappop(heap)[1])
        except:
            print(0)