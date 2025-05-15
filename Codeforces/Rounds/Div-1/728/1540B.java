import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
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

    static final int N = 210;
    static final int MOD = 1_000_000_007;
    static int n;
    static long[][] dp = new long[N][N];
    static List<Integer>[] g = new ArrayList[N];
    static boolean[] path = new boolean[N];
    static int[] cnt = new int[N];
    static int[] dist = new int[N];
    static long inv2, invn;
    static long modInv(long x) {
        return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(MOD)).longValue();
    }

    static boolean dfs1(int v, int t, int p) {
        if (v == t) {
            dist[v] = 0;
            path[v] = true;
            return true;
        }
        for (int u : g[v]) {
            if (u != p && dfs1(u, t, v)) {
                dist[v] = dist[u] + 1;
                path[v] = true;
                return true;
            }
        }
        return false;
    }

    static void dfs2(int v, int p, int last) {
        if (path[v]) last = v;
        cnt[last]++;
        for (int u : g[v]) {
            if (u != p) {
                dfs2(u, v, last);
            }
        }
    }
    static void solve() throws IOException {
        n =cin.nextInt();
        inv2 = modInv(2);
        invn = modInv(n);
        for (int i = 0; i <= n; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            int x = cin.nextInt(), y = cin.nextInt();
            g[x].add(y);
            g[y].add(x);
        }

        for (int i = 0; i < N; i++) {
            dp[i][0] = 0;
            dp[0][i] = 1;
        }

        for (int i = 1; i < N; i++)
            for (int j = 1; j < N; j++)
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) * inv2 % MOD;

        long ans = 0;

        for (int a = 1; a <= n; a++) {
            for (int b = 1; b < a; b++) {
                Arrays.fill(path, false);
                dfs1(a, b, -1);

                Arrays.fill(cnt, 0);
                dfs2(a, -1, -1);

                for (int i = 1; i <= n; i++) {
                    if (path[i]) {
                        int r = dist[i];
                        int l = dist[a] - r;
                        ans = (ans + dp[l][r] * cnt[i] % MOD * invn % MOD) % MOD;
                    }
                }
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
