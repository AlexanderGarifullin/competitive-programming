bool cmp(str s1, str s2){
    if (isz(s1) < isz(s2)) return true;
    if (isz(s1) > isz(s2)) return false;
    for (int i = 0; i < isz(s1); ++i) {
        if (s1[i] < s2[i]) return true;
        if (s1[i] > s2[i]) return false;
    }
    return false;
}
 
str sub(str a, str b) {
    bool minus = cmp(a, b);
    str r;
 
    // a > b
    if (minus) {
        swap(a,b);
    }
    reverse(all(a));
    reverse(all(b));
    int n = isz(a);
    int last = 1;
    while(isz(b) < n) b += '0';
    for (int i = 0; i < n; ++i) {
        last = max(last, i+1);
        int c1 = a[i] -'0';
        int c2 = b[i] - '0';
        if (c1 >= c2) {
            r += char('0' + (c1 - c2));
        } else{
            r += char('0' + (10 + c1 - c2));
            for (int j = last; j < n; ++j) {
                if (a[j] == '0') {
                    a[j] = '9';
                } else {
                    last = j;
                    a[j]--;
                    if (a[j] == '0') last++;
                    break;
                }
            }
        }
    }
 
    while(!r.empty() && r.back() == '0') r.pop_back();
    if (r.empty()) r = "0";
    reverse(all(r));
 
    if (minus) {
        r  = '-' + r;
    }
    return r;
}
 
 
str get(ll x) {
    str r;
    while(x) {
        r += char('0' + (x % 10));
        x /= 10;
    }
    if (r.empty()) r = "0";
    reverse(all(r));
    return r;
}
 
str div(str s, ll k) {
    str r;
    int n = isz(s);
    ll curNumber = 0;
    for (int i = 0; i < n; ++i) {
        int c = s[i] - '0';
        curNumber = curNumber * 10 + c;
        if (curNumber < k) {
            r += "0";
            continue;
        }
        ll d = curNumber / k;
        r += get(d);
        ll x = d * k;
        str res = sub(get(curNumber), get(x));
        ll newNumber = 0;
        for (int j = 0; j < isz(res); ++j) {
            newNumber = newNumber * 10 + (res[j] - '0');
        }
        curNumber = newNumber;
    }
     
    if (r[0] == '0'){
        reverse(all(r));
        while(!r.empty() && r.back() == '0') r.pop_back();
        if (r.empty()) r = "0";
        reverse(all(r));
    }
    return r;
}
