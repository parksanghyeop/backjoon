import sys
input = sys.stdin.readline

T = int(input())

d = [0] * 12
d[1] = 1
d[2] = 2
d[3] = 4

for _ in range(T):
    N = int(input())
    for i in range(4, N+1):
        d[i] = d[i-1] + d[i-2] + d[i-3]

    print(d[N])