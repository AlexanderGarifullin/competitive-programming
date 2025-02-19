struct seg{
    vvi tree;
 
    seg(int n) {
        tree.resize(4*n+1);
        build(1, 0, n);
    }
 
    void build(int v, int l, int r) {
        if (l + 1 == r) {
            tree[v].pb(a[l]);
        } else {
            int mid = (l+r)/2;
            build(2*v, l, mid);
            build(2*v+1, mid, r);
            merge(
                    all(tree[2*v]),
                    all(tree[2*v+1]),
                    back_inserter(tree[v])
                    );
        }
    }
 
    int get(int v, int l, int r, int ql, int qr) {
        if (ql >= r || qr <= l) return 0;
        if (ql <= l && r <= qr) {
            return lower_bound(all(tree[v]), ql) - tree[v].begin();
        }
        int mid = (l+r)/2;
        return get(2*v, l, mid, ql, qr) + get(2*v+1, mid, r, ql, qr);
    }
};
