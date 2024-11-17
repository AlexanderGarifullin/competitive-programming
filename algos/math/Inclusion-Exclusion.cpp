// +A +B +C - AB - BC - AC + ABC
// 2 3 4 5  6 7 8 9 10 11 12 13 14 15
// 1 1 0 1 -1 1 0 0 -1  1  0  1 -1 -1
for (int i = 2; i <= maxA; ++i) {
        for (int j = 2*i; j <= maxA; j += i) {
            coef[j] -= coef[i];
        }
    }
