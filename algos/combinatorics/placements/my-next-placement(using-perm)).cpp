
// add to placement all elements from permutation 1..n that don't use in placement in descending order
// generate next permutation
// take first k elements
bool next_placement(int n, vector<int> &place){
    int k = int(place.size());
    vector<bool> used(n + 1);
    vector<int> permutation(n);
    for (int i = k - 1; i >=0 ; --i) {
        used[place[i]] = true;
        permutation[i] = place[i];
    }
    int j = n - 1;
    for (int i = 1; i <= n ; ++i) {
        if (!used[i]) {
            permutation[j--] = i;
        }
    }
    bool can = next_permutation(permutation.begin(), permutation.end());
    if (!can) return can;
    for (int i = 0; i < k; ++i) {
        place[i] = permutation[i];
    }
    return true;
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

    bool next = true;
    do {
        for (int i = 0; i < placement.size(); ++i) {
            cout << placement[i];
            if (i != int(placement.size()) - 1) cout << ' ';
        }
        next = next_placement(n, placement);
        if (next) cout <<'\n';
    } while(next);

    return 0;
}
