vector<vector<long long>> mul(const vector<vector<long long>> &v1, const vector<vector<long long>> &v2) {
    vector<vector<long long>> v(v1.size(), vector<long long>(v1[0].size()));

    for (size_t row = 0; row < v.size(); ++row) {
        for (size_t col = 0; col < v.size(); ++col) {
            for (size_t k = 0; k < v.size(); ++k) {
                v[row][col] += v1[row][k] * v2[k][col];
            }
        }
    }

    return v;
}

vector<vector<long long>> binpow(vector<vector<long long>> v, const int step){
    if (step == 0) {
        for (size_t i = 0; i < v.size(); ++i) {
            for (size_t j = 0; j < v.size(); ++j) {
               v[i][j] = 0;
               if (i == j) v[i][j] = 1;
            }
        }
        return v;
    }
    else if (step == 1) {
        return v;
    } else {
        if (step % 2) {
            return mul(v, binpow(v, step - 1));
        } else {
            vector<vector<long long>> v2 = binpow(v, step/2);
            return mul(v2, v2);
        }
    }
}


vector<vector<long long>> mul(const vector<vector<long long>> &v1, const vector<vector<long long>> &v2, const int mod) {
    vector<vector<long long>> v(v1.size(), vector<long long>(v1[0].size()));

    for (size_t row = 0; row < v.size(); ++row) {
        for (size_t col = 0; col < v.size(); ++col) {
            for (size_t k = 0; k < v.size(); ++k) {
                v[row][col] += v1[row][k] * v2[k][col] % mod;
                v[row][col] %= mod;
            }
        }
    }

    return v;
}

vector<vector<long long>> binpow(vector<vector<long long>> v, const int step, const int mod){
    if (step == 0) {
        for (size_t i = 0; i < v.size(); ++i) {
            for (size_t j = 0; j < v.size(); ++j) {
                v[i][j] = 0;
                if (i == j) v[i][j] = 1;
            }
        }
        return v;
    }
    else if (step == 1) {
        return v;
    } else {
        if (step % 2) {
            return mul(v, binpow(v, step - 1, mod), mod);
        } else {
            vector<vector<long long>> v2 = binpow(v, step/2, mod);
            return mul(v2, v2, mod);
        }
    }
}
