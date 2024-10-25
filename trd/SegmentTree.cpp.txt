vi a;
int n;

struct SegmentTree {
    vl tree;
    SegmentTree(int n) {
        tree.resize(4 * n);
        build(1,0,n);
    }

    void build(int v, int l, int r) {
        if (l + 1 == r) {
            tree[v] = a[l];
            return;
        }
        int m = (l + r) / 2, vl = v * 2, vr = vl + 1;
        build(vl, l, m);
        build(vr, m, r);
        tree[v] = tree[vl] + tree[vr];
    }

    ll get(int ql, int qr, int v = 1, int l = 0, int r = n)  {
        if (l >= qr || r <= ql) {
            return 0ll;
        }
        if (ql <= l && r <= qr) {
            return tree[v];
        }
        int m = (l + r) / 2, vl = v * 2, vr = vl + 1;
        return get(ql, qr, vl, l, m) + get(ql, qr, vr, m, r);
    }
    void update(int pos, int val, int v= 1, int l = 0, int r = n) {
        if (pos < l || pos >= r) {
            return;
        }
        if (l + 1 == r) {
            tree[v] = val;
            return;
        }
        int m = (l + r) / 2, vl = v * 2, vr = vl + 1;
        update(pos,val, vl, l, m);
        update(pos,val, vr,m, r);
        tree[v] = tree[vl] + tree[vr];
    }
};

void solve() {
    cin >> n;
    int m; cin >> m;
    a.resize(n);
    fin(a);
    SegmentTree segmentTree(n);
    while(m--) {
        int t; cin >> t;
        if (t == 1) {
            int i,v; cin >> i >> v;
            segmentTree.update(i, v);
        } else {
            int l,r; cin >> l >> r;
            cout << segmentTree.get(l, r) << en;
        }
    }
}