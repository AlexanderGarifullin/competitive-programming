void fill_factor(vector<long long> &fact) {
    fact[0] = 1;
    for (int i = 1; i < fact.size(); ++i) {
        fact[i] = fact[i-1] * i;
    }
}


// in each block we have (n - 1)! / (n - m)!. Let [L:R] current block with our placement
// Let B = (n - 1)! / (n - m)! - current size of block
// Therefore L = B * LESS, LESS - count of elements less than first el of placement
// R = L + B - 1
// Number of cur placement = Sum every L borders
// indexes start with 0, but we need a number => +1 to the answer
long long get_number_placement(int n, vector<int> place, vector<long long> fact){
    long long answ = 0;
    int k = int(place.size());
    int n_ = n;
    int k_ = k;
    vector<bool> used(n + 1);
    for (int i = 0; i < k; ++i) {
        int cntLess = 0;
        for (int j = 1; j < place[i]; ++j) {
            cntLess += !used[j];
        }
        used[place[i]] = true;
        long long curBlock = fact[n_ - 1]/fact[n_ - k_] ;
        answ += curBlock * cntLess;
        --n_; --k_;
    }
    return answ + 1;
}

i32 main() {
    //  freopen("input.txt", "rt", stdin);
    //  freopen("output.txt", "wt", stdout);


    cin.tie(nullptr); ios_base::sync_with_stdio(false);


    int n, k; cin >> n >> k;

    vector<int> placement(k);
    for (int i = 0; i < k; ++i) {
        cin >> placement[i];
    }

    vector<long long> factorials(n);
    fill_factor(factorials);
    cout << get_number_placement(n, placement, factorials)<< en;


    return 0;
}
