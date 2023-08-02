vector<int> get_combination( int n, int k, long long p, vector<vector<unsigned long long>> C){
    vector<int> combination(k);
    int next = 1;
    int pos = 0;
    while(k > 0){
        if (p < C[n - 1][k - 1]) {
            combination[pos++] = next;
            k--;
        } else {
            p -= C[n-1][k-1];
        }
        n--;
        next++;
    }
    return combination;
}
 
 
void fill_c(vector<vector<unsigned long long>> &C, int n){
    for (int i = 0; i <= n; ++i) {
        C[i][0]=1;
    }
    for (int i = 1; i <=n; ++i) {
        for (int j = 1; j < i+1; ++j) {
            C[i][j] = C[i-1][j] + C[i-1][j-1];
        }
    }
}
 
