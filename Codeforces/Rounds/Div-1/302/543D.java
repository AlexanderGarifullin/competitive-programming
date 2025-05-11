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

    static final int MOD = 1_000_000_007;
    static int n;
    static int[][] children;
    static int[][] pref, suf;
    static int[] d;
    static int[] ans;

    static void solve() throws IOException {
        n = cin.nextInt();
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();

        for (int v = 1; v < n; v++) {
            int p = cin.nextInt() - 1;
            g[p].add(v);
        }

        children = new int[n][];
        for (int i = 0; i < n; i++) {
            children[i] = g[i].stream().mapToInt(Integer::intValue).toArray();
        }

        d    = new int[n];
        pref = new int[n][];
        suf  = new int[n][];
        ans  = new int[n];

        dfsDown(0);
        dfsReroot(0, -1);

        for (int i = 0; i < n; i++) {
            System.out.print(ans[i] + " ");
        }
    }

    static void dfsDown(int v) {
        int m = children[v].length;
        long prod = 1;
        for (int u : children[v]) {
            dfsDown(u);
            prod = prod * (d[u] + 1L) % MOD;
        }
        d[v] = (int) prod;

        pref[v] = new int[m];
        suf [v] = new int[m];
        long cur = 1;
        for (int i = 0; i < m; i++) {
            pref[v][i] = (int) cur;
            int u = children[v][i];
            cur = cur * (d[u] + 1L) % MOD;
        }
        cur = 1;
        for (int i = m - 1; i >= 0; i--) {
            suf[v][i] = (int) cur;
            int u = children[v][i];
            cur = cur * (d[u] + 1L) % MOD;
        }
    }

    static void dfsReroot(int v, int parent) {
        ans[v] = d[v];

        int m = children[v].length;
        for (int i = 0; i < m; i++) {
            int u = children[v][i];

            int oldDv = d[v];
            int oldDu = d[u];

            long withoutChild = (long) pref[v][i] * suf[v][i] % MOD;
            if (parent != -1) {
                withoutChild = withoutChild * (d[parent] + 1L) % MOD;
            }
            d[v] = (int) withoutChild;

            d[u] = (int) ((long) d[u] * (d[v] + 1L) % MOD);

            dfsReroot(u, v);

            d[v] = oldDv;
            d[u] = oldDu;
        }
    }


    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
