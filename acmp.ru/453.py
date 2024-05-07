# # f[x] = f[x]/2 + x % 2
# # convert x -> bin 
# # we want n zero in bin : 0..0
# # x = a[0] + 10 * (a[1] + 10 * a[2] + ... + 10^(n-1) * a[n])
# # bin() a[0] / 2 + 10 / 2 * (a[1] + 10 * a[2] + ... + 10^(n-1) * a[n])
# # a[0] / 2 + 5 * (a[1] + 10 * a[2] + ... + 10^(n-1) * a[n])
# # a[0] / 2 + 5 * f(n-1)
# # 2 + 10 * f(n-1)
# # 1 + 5 * f(n-1)
# # 1 + 5 * (a[1] + 10 * a[2] + ... + 10^(n-1) * a[n])
# #         we want take a[1]
# # (1 + 5 * a[1]) % 2 = 0
# # (1 + 5 * a[1]) / 2 + 25 *(a[2] + ...)


# def f(n, a, b):
# 	if (n > 0):
# 		if ((a + b) % 2 == 0):
# 			return f(n-1, (a + b) // 2, 5*b) + "1";
# 		else:
# 			return f(n-1, (a + b * 2) // 2, 5*b) + "2";
# 	return "1"

# n = int(input())
# print(f(n, 0, 1))


n = int(input())

def check(x, n):
	for i in range(n):
		if (x % 2 != 0):
			return False
		x = x // 2
	return True

cur = 2
st = 10
for i in range(2, n+1):
	c1 = cur + st
	c2 = cur + 2 * st
	ok = check(c1, i)
	if (ok):
		cur = c1
	else:
		cur = c2
	st *= 10
print(cur)
