vld a(n); fin(a);
vvld dp(n, vld(n));
function<ld(ld, ld)> average = [](ld a, ld b){
    return (a+b) /2;
};
for (int i = 0; i < n; ++i) {
    dp[i][i] = a[i];
}
for (int len = 2; len <= n; ++len) {
    for (int l = 0;   l <=  n - len;  ++l) {
        int r = l + len - 1;
        for (int i = l; i < r; ++i) {
            dp[l][r] = max(dp[l][r], average(dp[l][i], dp[i+1][r]));
        }
    }
}
cout << dp[0][n-1] << en;