k, n = map(int, input().split())

dp = [0] * (n+1)
dp[0] = 1

for i in range(1, n+1):
    for j in range(1, min(i+1, k+1)):
        dp[i] += dp[i-j]

print(dp[n])
