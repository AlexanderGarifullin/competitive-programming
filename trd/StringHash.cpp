const ull k = 31, mod = 1'000'000'000'000'037LL;
 
void solve() {
    set<ull> table;
    int n,m; cin >> n >> m;
    for (int i = 0; i < n; ++i) {
        str s; cin >> s;
        ull hash = 0;
        ll p = 1;
        for (int j = 0; j < isz(s); ++j) {
            hash = (hash + s[j] * p % mod) % mod;
            p = p * k % mod;
        }
        table.insert(hash);
    }
 
    for (int i = 0; i < m; ++i) {
        str s; cin >> s;
        ull hash = 0;
        ll p = 1;
        for (int j = 0; j < isz(s); ++j) {
            hash = (hash + s[j] * p % mod) % mod;
            p = p * k % mod;
        }
        p=1;
        bool found = false;
        for (int j = 0; j < isz(s) && !found; ++j) {
            for (char l = 'a'; l <= 'c'; ++l) {
                if (l == s[j]) continue;
                ull newHash = (hash -(s[j] * p) % mod + mod) % mod;
                newHash = (newHash + l * p % mod) % mod;
                if (table.contains(newHash)) {
                    found = true;
                    break;
                }
            }
            p = p * k % mod;
        }
        ifyn(found);
    }
}