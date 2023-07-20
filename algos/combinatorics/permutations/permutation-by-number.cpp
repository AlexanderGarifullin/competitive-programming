void fill_factorials(vector<long long> &fact){
    fact[0] = 1;
    for (int i = 1; i < int(fact.size()); ++i) {
        fact[i] = fact[i-1] * i;
    }
}
 
// n - size of permutation
// k - number of permutation (need to minus 1 from k because numeration start from 0
// factorials - calculated factorials in advance
vector<int> get_permitation(int n, long long k, vector<long long> &factorials) {
    vector<int> perm(n);
    vector<bool> use(n + 1);
    k--;
    for (int i = 0; i < n; ++i) {
        int blockNum = k / factorials[n - 1 - i] + 1;
        int pos = 0;
        for (int j = 1; j < use.size(); ++j) {
            if (!use[j]) {
                pos++;
                if (pos == blockNum) {
                    use[j] = true;
                    perm[i] =j;
                    k %= factorials[n - 1 - i];
                    break;
                }
            }
        }
    }
    return perm;
}
 
i32 main() {
    //  freopen("success.in", "rt", stdin);
    //  freopen("success.out", "wt", stdout);
 
    cin.tie(nullptr); ios_base::sync_with_stdio(false);
 
    int n; cin >> n;
    int k; cin >> k;
 
    vector<long long> factorials(n);
    fill_factorials(factorials);
 
    vector<int> perm = get_permitation(n, k, factorials);
 
    for (const int &x : perm) cout << x << ' ';
 
    return 0;
}
