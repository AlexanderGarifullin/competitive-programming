import math

t = int(input())
for _ in range(t):
    n, c = map(int, input().split())
    s = list(map(int, input().split()))
    sum_s = sum(s)
    sum_squares = sum(si * si for si in s)
    b = 4 * sum_s
    cc = sum_squares - c
    a = 4 * n
    disk = b * b - 4 * a * cc
    number = (-b + math.sqrt(disk)) // (2 * a)
    result = "%.0f" % number
    print(result)
