import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.BiConsumer;

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

    static long modPow(long base, int exp, int mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = result * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return result;
    }

    static final int N = 200005;
    static int[] st = new int[N], ft = new int[N], h = new int[N], ans;
    static int tme = 0, n, m;
    static List<Integer>[] g = new ArrayList[N];
    static List<int[]>[] H = new ArrayList[N];
    static List<List<int[]>>[] vec = new ArrayList[N];
    static String[] names = new String[N];

    static class Node {
        int val = 0, l = -1;
    }

    static Node[] seg = new Node[N * 4];

    static void dfs(int v) {
        st[v] = tme++;
        if (v != 0) H[h[v]].add(new int[]{st[v], v});
        for (int u : g[v]) {
            h[u] = h[v] + 1;
            dfs(u);
        }
        ft[v] = tme;
    }

    static void refresh(int reval, int v) {
        if (seg[v].l != reval) {
            seg[v].l = reval;
            seg[v].val = 0;
        }
    }

    static void modify(int reval, int idx, int val, int v, int s, int e) {
        if (s + 1 == e) {
            seg[v].val += val;
            return;
        }
        int m = (s + e) / 2, lc = v * 2, rc = v * 2 + 1;
        refresh(reval, lc);
        refresh(reval, rc);
        if (idx < m)
            modify(reval, idx, val, lc, s, m);
        else
            modify(reval, idx, val, rc, m, e);
        seg[v].val = seg[lc].val + seg[rc].val;
    }

    static int get(int reval, int l, int r, int v, int s, int e) {
        refresh(reval, v);
        if (s >= l && e <= r) return seg[v].val;
        if (s >= r || e <= l) return 0;
        int m = (s + e) / 2, lc = v * 2, rc = v * 2 + 1;
        return get(reval, l, r, lc, s, m) + get(reval, l, r, rc, m, e);
    }

    static int upperBound(List<int[]> list, int val) {
        int l = 0, r = list.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (list.get(m)[0] <= val) l = m + 1;
            else r = m;
        }
        return l;
    }

    static void solve() throws IOException {
        for (int i = 0; i < N; i++) {
            g[i] = new ArrayList<>();
            H[i] = new ArrayList<>();
            vec[i] = new ArrayList<>();
        }
        for (int i = 0; i < seg.length; i++) seg[i] = new Node();

        n = cin.nextInt();
        for (int i = 1; i <= n; i++) {
            names[i] = cin.next();
            int p = cin.nextInt();
            g[p].add(i);
        }

        h[0] = -1;
        dfs(0);

        for (int i = 0; i < n; i++) {
            H[i].sort(Comparator.comparingInt(a -> a[0]));
            vec[i] = new ArrayList<>();
            for (int j = 0; j < H[i].size(); j++) vec[i].add(new ArrayList<>());
        }

        m = cin.nextInt();
        ans = new int[m];
        for (int i = 0; i < m; i++) {
            int v = cin.nextInt();
            int k = cin.nextInt();
            int depth = h[v] + k;
            if (depth >= N || H[depth].isEmpty()) continue;

            List<int[]> list = H[depth];
            int l = upperBound(list, st[v]);
            int r = upperBound(list, ft[v]);

            if (r > 0) vec[depth].get(r - 1).add(new int[]{l, i});
        }

        for (int i = 0; i < n; i++) {
            Map<String, Integer> last = new HashMap<>();
            seg[1].val = 0;
            seg[1].l = i;

            for (int j = 0; j < H[i].size(); j++) {
                int u = H[i].get(j)[1];
                String name = names[u];
                if (last.containsKey(name)) {
                    modify(i, last.get(name), -1, 1, 0, n);
                }
                last.put(name, j);
                modify(i, j, 1, 1, 0, n);
                for (int[] x : vec[i].get(j)) {
                    ans[x[1]] = get(i, x[0], n, 1, 0, n);
                }
            }
        }

        for (int a : ans) System.out.println(a);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
