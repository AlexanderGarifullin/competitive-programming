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
 
 
str mult(str a, str b) {
    str r;
    reverse(all(a));
    reverse(all(b));
    int n = isz(a);
    int m = isz(b);
    for (int i = 0; i < m; ++i) {
        int c = b[i] - '0';
        str cur(i, '0');
        int add = 0;
        for (int j = 0; j < n; ++j) {
            int c1 = a[j] - '0';
            c1 *= c;
            c1 += add;
            cur += char('0' + (c1 % 10));
            add = c1 / 10;
        }
        if (add) {
            cur += char('0' + add);
        }
        while(!cur.empty() && cur.back() == '0') cur.pop_back();
        if (cur.empty()) cur = "0";
        reverse(all(cur));
        if (r.empty()) r =cur;
        else {
            r = sum(r,cur);
        }
    }
    return r;
}
