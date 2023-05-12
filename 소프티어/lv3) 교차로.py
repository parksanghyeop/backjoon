import sys
from collections import deque
input = sys.stdin.readline

abcd = [deque([]),deque([]),deque([]),deque([])]

N = int(input())

for i in range(N):
    t, w = input().split()
    t = int(t)
    abcd[ord(w)-ord('A')].append((i, t))


result = [-1]*N
current_time = -1
is_waitng = [0,0,0,0]

while abcd[0] or abcd[1] or abcd[2] or abcd[3]:
    min_time = int(1e9)

    for i in range(4):
        if abcd[i]:
            time = abcd[i][0][1]
            min_time = min(min_time, time)
            if time <= current_time:
                is_waitng[i] = 1
                
    sum_wating = sum(is_waitng)
    
    if sum_wating == 0: # 차없음
        current_time = min_time
        continue
        
    if sum_wating == 4: # 교착
        break
    

    
    for i in range(4):
        if is_waitng[i] and not is_waitng[(i-1)%4]: # 내가 있고 오른쪽 없으면
            index, time = abcd[i].popleft()
            result[index] = current_time

    is_waitng = [0,0,0,0]
    
    current_time += 1

for t in result:
    print(t)



