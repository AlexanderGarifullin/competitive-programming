// blocks are of different size, therefore use C[n - i][k - 1] 
long long combination_number(vector<int> &combination, int n, int k, vector<vector<long long>> C){
    long long num = 0;
    int pred = 0;
    for (int i = 0; i < k; ++i) {
        for (int j = pred + 1; j < combination[i]; ++j) {
            num += C[n - j][k - 1 - i];
        }
        pred = combination[i];
    }
    return num;
}
void fill_fact(vector<long long> &fact){
    fact[0] = 1;
    for (int i = 1; i < isz(fact) ; ++i) {
        fact[i] = fact[i -1] * i;
    }
}

void fill_c(vector<vector<long long>> &C, vector<long long> &fact, int n){
    for (int i = 0; i <= n; ++i) {
        for (int j = 0; j <= i; ++j) {
            C[i][j] = fact[i] / (fact[j] * (fact[i - j]));
        }
    }
}
