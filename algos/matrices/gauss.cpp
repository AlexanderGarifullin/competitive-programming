vector<long long> gauss(vector<std::vector<long long>>& a, vector<long long>& b) {
    int n = a.size();
    vector<int> where(n, -1);
    for (int col = 0, row = 0; col < n && row < n; ++col) {
        int sel = row;
        for (int i = row; i < n; ++i) {
            if (std::abs(a[i][col]) > std::abs(a[sel][col])) {
                sel = i;
            }
        }
        if (a[sel][col] == 0)
            continue;
        for (int i = col; i < n; ++i)
            std::swap(a[sel][i], a[row][i]);
        std::swap(b[sel], b[row]);
        where[col] = row;
 
        long long div = a[row][col];
        for (int i = col; i < n; ++i)
            a[row][i] /= div;
        b[row] /= div;
 
        for (int i = 0; i < n; ++i)
            if (i != row) {
                long long c = a[i][col];
                for (int j = col; j < n; ++j)
                    a[i][j] -= a[row][j] * c;
                b[i] -= b[row] * c;
            }
        ++row;
    }
 
    vector<long long> x(n, 0);
    for (int i = 0; i < n; ++i)
        if (where[i] != -1)
            x[i] = b[where[i]];
    return x;
}
