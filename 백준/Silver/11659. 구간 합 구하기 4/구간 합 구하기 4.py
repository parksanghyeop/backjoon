import sys
input = sys.stdin.readline


N, M = map(int, input().split())
numbers = list(map(int, input().split()))

dp = [0] * (N+1)

for k in range(N):
    dp[k+1] = dp[k] + numbers[k]

for _ in range(M):
    i, j = map(int, input().split())
    print(dp[j] - dp[i-1])