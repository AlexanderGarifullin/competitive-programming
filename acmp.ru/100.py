n = int(input())

dp = [[0] * (n//2 * 9 + 1) for _ in range(n//2+1)] 

    
for i in range(10):
	dp[1][i] = 1;


for i in range(2, n//2+1):
	for j in range(10):
		for s in range(0, n//2 * 9 - j + 1):
			dp[i][j+s] += dp[i-1][s]

r = 0
for s in range(0, n//2 * 9 + 1):
	r += dp[n//2][s] * dp[n//2][s];
print(r)
