import sys

input = sys.stdin.readline
import heapq
from collections import defaultdict

# 문제의 갯수
N = int(input())

# 문제 리스트
min_heap = []
max_heap = []
in_list = defaultdict(bool)

# N개의 문제 입력
for _ in range(N):
    P, L = map(int, input().split())
    heapq.heappush(min_heap, [L, P])
    heapq.heappush(max_heap, [-L, -P])
    in_list[P] = True

M = int(input())

for _ in range(M):
    command = input().split()

    if command[0] == "recommend":
        if command[1] == "1":
            while not in_list[-max_heap[0][1]]:
                heapq.heappop(max_heap)
            # print(max_heap)
            print(-max_heap[0][1])
        else:
            while not in_list[min_heap[0][1]]:
                heapq.heappop(min_heap)
            print(min_heap[0][1])
    elif command[0] == "solved":
        in_list[int(command[1])] = False
    else:  # add
        P = int(command[1])
        L = int(command[2])

        while not in_list[-max_heap[0][1]]:
            heapq.heappop(max_heap)
        while not in_list[min_heap[0][1]]:
            heapq.heappop(min_heap)

        in_list[P] = True
        heapq.heappush(max_heap, [-L, -P])
        heapq.heappush(min_heap, [L, P])

    # print(in_list)