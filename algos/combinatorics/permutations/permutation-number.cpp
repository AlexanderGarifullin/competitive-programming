// cur solve O(n^2). in order to get O(n*log(n)) we need use smth like: pbds_tree, fenwick tree, segment tree


// let's calculate the factorials of the numbers in advance
void fill_factorials(vector<long long> &fac){
    fac[0] = 1;
    for (size_t i = 1; i < fac.size(); ++i) {
        fac[i] = fac[i-1] * i;
    }
}

// get permutation number
// We go through the permutation, we met the next element. We want to find out how many elements in
// the set are smaller than it, and add this number multiplied by (n − 1)! to the answer. And don't
// forget to remove an element from the set
long long get_number(vector<int> &v, vector<long long> &factorials){

    long long answ = 0;
    int n = int(v.size());
    vector<bool> use(n+1);
  
    for (size_t i = 0; i < n; ++i) {
        use[v[i]] = true;
        long long cntLessThenCur = accumulate(use.begin()+1, use.begin()+v[i], 0ll, [](long long cur, bool tek){
            return cur + !tek;
        });

        answ += cntLessThenCur * factorials[n - 1 - i];
    }
    return answ + 1;
}


i32 main() {
    //  freopen("success.in", "rt", stdin);
    //  freopen("success.out", "wt", stdout);

    cin.tie(nullptr); ios_base::sync_with_stdio(false);

  
    int n; cin >> n;

    vector<int> v(n);
    for (int i = 0; i < n; ++i) {
        cin >> v[i];
    }

    vector<long long> factorials(n);
  // calculate the factorials in avance 
    fill_factorials(factorials);
  // get permitation number
    cout << get_number(v, factorials)<< en;


    return 0;
}


