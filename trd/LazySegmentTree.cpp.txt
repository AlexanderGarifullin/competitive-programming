
vi a;
int n;

struct SegmentTree {
    vl tree;
    vl lazy;

    SegmentTree(int n) {
        tree.resize(4 * n);
        lazy.resize(4*n);
        build(1,0,n);
    }

    void accumulate(int v) {
        int vl = v * 2, vr = vl + 1;
        tree[v] = tree[vl] + tree[vr];
    }

    void build(int v, int l, int r) {
        if (l + 1 == r) {
            tree[v] = a[l];
            return;
        }
        int m = (l + r) / 2, vl = v * 2, vr = vl + 1;
        build(vl, l, m);
        build(vr, m, r);
        accumulate(v);
    }

    void push(int v, int l, int r) {
        if (lazy[v] != 0) {
            tree[v] += 1ll * (r - l) * lazy[v];
            if (l + 1 != r) {
                int vl = v * 2, vr = vl + 1;
                lazy[vl] += lazy[v];
                lazy[vr] += lazy[v];
            }
            lazy[v] = 0;
        }
    }

    ll get(int ql, int qr, int v = 1, int l = 0, int r = n)  {
        if (l >= qr || r <= ql) {
            return 0ll;
        }
        push(v, l, r);
        if (ql <= l && r <= qr) {
            return tree[v];
        }
        int m = (l + r) / 2, vl = v * 2, vr = vl + 1;
        return get(ql, qr, vl, l, m) + get(ql, qr, vr, m, r);
    }

    void update(int ql, int qr, int val, int v= 1, int l = 0, int r = n) {
        push(v, l, r);
        if (l >= qr || r <= ql) {
            return;
        }
        if (ql <= l && r <= qr) {
            lazy[v] += val;
            push(v, l, r);
            return;
        }
        int m = (l + r) / 2, vl = v * 2, vr = vl + 1;
        update(ql, qr, val, vl, l, m);
        update(ql, qr, val, vr, m, r);
        accumulate(v);
    }
};

void solve() {
    cin >> n;
    int m; cin >> m;
    a.resize(n);
    SegmentTree segmentTree(n);
    while(m--) {
        int t; cin >> t;
        if (t == 1) {
            int l,r,v; cin >> l >> r >> v;
            segmentTree.update(l, r, v);
        } else {
            int l,r; cin >> l >> r;
            cout << segmentTree.get(l, r) << en;
        }
    }
}