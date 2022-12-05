import sys

input = sys.stdin.readline

n, m = map(int, input().rstrip().split())
arr = [int(input()) for _ in range(n)]
    
low, high = 0, 1000000000 * m
while low <= high:
    mid = (low + high)//2
    cnt = 0
    for time in arr:
        cnt += mid//time
    if cnt>=m:
        high = mid - 1
    else:
        low = mid + 1

print(low)