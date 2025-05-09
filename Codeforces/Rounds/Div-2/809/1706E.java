import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
    private static final class FastScanner {
        private final byte[] buf = new byte[1 << 16];
        private int len = 0, ptr = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws java.io.IOException {
            if (ptr >= len) {
                len = in.read(buf);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buf[ptr++];
        }

        int nextInt() throws java.io.IOException {
            int c, sign = 1, val = 0;
            do { c = readByte(); } while (c <= ' ' && c != -1);
            if (c == '-') { sign = -1; c = readByte(); }
            while (c > ' ' && c != -1) {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        long nextLong() throws java.io.IOException {
            long c, sign = 1, val = 0;
            do { c = readByte(); } while (c <= ' ' && c != -1);
            if (c == '-') { sign = -1; c = readByte(); }
            while (c > ' ' && c != -1) {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        String next() throws java.io.IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ' && c != -1);

            if (c == -1) return null;

            StringBuilder sb = new StringBuilder();
            while (c > ' ' && c != -1) {
                sb.append((char) c);
                c = readByte();
            }
            return sb.toString();
        }
    }

    static FastScanner cin = new FastScanner(System.in);

    public static <T extends Comparable<? super T>> boolean isSorted(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return a * b / gcd(a,b);
    }

    private static final int MAX = 500_000 + 5;

    private static int[] mobius = new int[MAX];
    private static int[] cnt    = new int[MAX];
    private static boolean[] onShelf;
    private static int[][] divisors;
    private static long answer = 0;

    static class Pair<F,S> {
        F first;
        S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        static <F,S> Pair<F,S> of(F first, S second) {
            return new Pair<>(first, second);
        }
    }

    private static final class DSU {
        final int[] parent;
        final HashSet<Integer>[] set;
        final int[] nt;
        final int n;


        DSU(int n) {
            this.n = n;
            parent = new int[n];
            nt = new int[n];
            Arrays.fill(nt, -1);
            set = new HashSet[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                set[i] = new HashSet<>();
                set[i].add(i);
            }
        }

        int find(int v) {
            if (parent[v] != v) parent[v] = find(parent[v]);
            return parent[v];
        }

        void unite(int x, int y, int edge) {
            int a = find(x);
            int b = find(y);
            if (a == b) return;

            if (set[a].size() < set[b].size()) {
                int tmp = a; a = b; b = tmp;
            }
            HashSet<Integer> big   = set[a];
            HashSet<Integer> small = set[b];

            for (int v : small) {
                if (v + 1 < n && nt[v] == -1 && big.contains(v + 1)) nt[v] = edge;
                if (v - 1 >= 0 && nt[v - 1] == -1 && big.contains(v - 1)) nt[v - 1] = edge;
            }
            big.addAll(small);
            small.clear();
            parent[b] = a;
        }
    }

    private static final class SegTree {
        final int[] tree;
        final int n;

        SegTree(int[] arr) {
            this.n = arr.length;
            tree = new int[4 * n];
            build(1, 0, n - 1, arr);
        }

        void build(int idx, int l, int r, int[] arr) {
            if (l == r) {
                tree[idx] = arr[l];
                return;
            }
            int mid = (l + r) >>> 1;
            build(idx << 1, l, mid, arr);
            build(idx << 1 | 1, mid + 1, r, arr);
            tree[idx] = Math.max(tree[idx << 1], tree[idx << 1 | 1]);
        }

        int query(int idx, int l, int r, int ql, int qr) {
            if (l > qr || r < ql) return -1;
            if (ql <= l && r <= qr) return tree[idx];
            int mid = (l + r) >>> 1;
            return Math.max(
                    query(idx << 1, l, mid, ql, qr),
                    query(idx << 1 | 1, mid + 1, r, ql, qr)
            );
        }

        int query(int l, int r) {
            return query(1, 0, n - 1, l, r);
        }
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int m = cin.nextInt();
        int q = cin.nextInt();

        DSU dsu = new DSU(n);
        for (int i = 0; i < m; i++) {
            int u = cin.nextInt() - 1;
            int v = cin.nextInt() - 1;
            dsu.unite(u, v, i);
        }

        SegTree seg = new SegTree(dsu.nt);
        while(q-->0) {
            int l = cin.nextInt();
            int r = cin.nextInt();
            if (l==r) {
                System.out.print(0 + " ");
            } else {
                int best = seg.query(l -1, r- 2);
                System.out.print(best+1 + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
