void fill_factor(vector<long long> &fact) {
    fact[0] = 1;
    for (int i = 1; i < fact.size(); ++i) {
        fact[i] = fact[i-1] * i;
    }
}


// we know size of block on each digit of out number
// caluclate how mant blocks less than current element's block
vector<int> placement_by_number(int n, int k, int number, vector<long long> & factorials){
    vector<int> placement(k);
    vector<bool> use(n + 1);
    int n_ = n , k_ = k;
    number--;
    for (int i = 0; i < k; ++i) {
        int blockSize = factorials[n_ - 1]/factorials[n_ - k_];
        int j = number / blockSize + 1;
        int pos = 0;
        for (int l = 1; l <= n; ++l) {
            if (!use[l]) {
                pos++;
                if (j == pos) {
                    use[l] = true;
                    placement[i] = l;
                    n_--;
                    k_--;
                    number %= blockSize;
                    break;
                }
            }
        }
    }
    return placement;
}

i32 main() {
    //  freopen("input.txt", "rt", stdin);
    //  freopen("output.txt", "wt", stdout);


    cin.tie(nullptr); ios_base::sync_with_stdio(false);


    int n, k; cin >> n >> k;

    vector<int> placement(k);
    for (int i = 0; i < k; ++i) {
       placement[i] = i + 1;
    }
    int number = 0;
    vector<long long> factorials(n + 1);
    fill_factor(factorials);

    int maxCountPlacements = factorials[n] / factorials[n - k];

    vector<vector<int>> placements (maxCountPlacements);
    for (int i =1; i <= maxCountPlacements; ++i) {
        placement = placement_by_number(n ,k, i, factorials);
        placements[i-1] = placement;
    }


    for (int i = 0; i < maxCountPlacements; ++i) {
        for (int j = 0; j < k; ++j) {
            cout << placements[i][j];
            if (j != k - 1) cout << ' ';
        }
        if (i != maxCountPlacements - 1) cout << '\n';
    }

    return 0;
}
