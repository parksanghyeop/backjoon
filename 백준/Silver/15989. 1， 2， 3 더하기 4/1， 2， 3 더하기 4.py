MAX = 10001

dp = [1] * MAX

for i in range(2, MAX):
    dp[i] += dp[i-2]

for i in range(3, MAX):
    dp[i] += dp[i-3]

t = int(input())

for _ in range(t):
    n = int(input())
    print(dp[n])