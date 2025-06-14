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

    static final int MAXN = 110000;
    static final long INF = (long) 1e18;
    static List<Pair>[] g = new ArrayList[MAXN];
    static int[] val = new int[MAXN];
    static long[] dp = new long[1 << 20];
    static List<Integer> path = new ArrayList<>();

    static class Pair {
        int node, edge;
        Pair(int node, int edge) {
            this.node = node;
            this.edge = edge;
        }
    }

    static boolean dfs(int v, int t, int p) {
        if (v == t) return true;
        for (Pair pair : g[v]) {
            int u = pair.node, e = pair.edge;
            if (u != p) {
                path.add(e);
                if (dfs(u, t, v)) return true;
                path.removeLast();
            }
        }
        return false;
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
            val[i] = 0;
        }

        for (int i = 1; i <= n - 1; i++) {
            int u = cin.nextInt();
            int v = cin.nextInt();
            g[u].add(new Pair(v, i));
            g[v].add(new Pair(u, i));
        }

        int k = cin.nextInt();
        for (int i = 0; i < k; i++) {
            int a = cin.nextInt();
            int b = cin.nextInt();
            path.clear();
            dfs(a, b, -1);
            for (int x : path) {
                val[x] |= (1 << i);
            }
        }

        Set<Integer> st = new HashSet<>();
        for (int i = 1; i <= n - 1; i++) {
            if (val[i] != 0) {
                st.add(val[i]);
            }
        }

        Arrays.fill(dp, 0, 1<<k, INF);
        dp[0] = 0;
        for (int mask = 0; mask < (1 << k); mask++) {
            if (dp[mask] == INF) continue;
            for (int x : st) {
                int newMask = mask | x;
                dp[newMask] = Math.min(dp[newMask], dp[mask] + 1);
            }
        }

        System.out.println(dp[(1 << k) - 1]);
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
