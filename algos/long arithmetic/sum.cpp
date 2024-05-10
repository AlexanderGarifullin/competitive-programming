str sum(str a, str b) {
    bool add = false;
    int n = isz(a);
    int m = isz(b);
    str r(max(n,m),'0');
    for (int i = 0; i < min(n,m); ++i) {
        int c1 = a[n - 1 - i] - '0';
        int c2 = b[m - 1 - i] - '0';
        r[max(n,m) - 1 - i] += (c1+c2+add) % 10;
        add = (c1+c2+ add >9);
    }
    if (n>m){
        for (int i = m+1; i <= n; ++i) {
            int c = a[n - i] - '0';
            r[n - i] += (c + add) % 10;
            add = (c + add) > 9;
        }
    } else if (m>n) {
        for (int i = n+1; i <= m; ++i) {
            int c = b[m - i] - '0';
            r[m - i] += (c + add) % 10;
            add = (c + add) > 9;
        }
    }
    if (add) {
        r = "1" + r;
    }
    return r;
}
