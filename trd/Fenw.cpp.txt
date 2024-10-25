vi v(1e6 + 1, 1);

int const N = 3e5 + 20;
ll fen[N];

void add(int p, int val) {
    for (p++; p < N; p += p & -p) {
        fen[p] += val;
    }
}

ll get(int p){
    ll res=0;
    for (;p; p -= p & -p) res += fen[p];
    return res;
}

int sum (int l, int r) {
    return get(r) - get(l-1);
}

int lower_bound (int s) {
    int k = 0;
    for (int l = logn; l >= 0; l--) {
        if (k + (1<<l) <= n && t[k + (1<<l)] < s) {
            k += (1<<l);
            s -= t[k];
        }
    }
    return k;
}