import java.io.IOException;
import java.io.InputStream;
import java.util.*;
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

    static final int N = 3000;
    static int n;
    static List<Integer>[] e = new ArrayList[N];
    static int[][] dis = new int[N][N];
    static int[][] sz = new int[N][N];
    static long[][] dp = new long[N][N];

    static void dfs(int u, int p, int root) {
        sz[root][u] = 1;
        for (int v : e[u]) {
            if (v != p) {
                dfs(v, u, root);
                sz[root][u] += sz[root][v];
            }
        }
    }

    static void solve() throws IOException {
        n = cin.nextInt();

        for (int i = 0; i < n; ++i)
            e[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; ++i) {
            int u = cin.nextInt() - 1;
            int v = cin.nextInt() - 1;
            e[u].add(v);
            e[v].add(u);
        }

        for (int i = 0; i < n; ++i)
            Arrays.fill(dis[i], -1);

        for (int i = 0; i < n; ++i)
            dfs(i, -1, i);

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            dis[i][i] = 0;
            queue.add(new int[]{i, i});
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int u = curr[0], v = curr[1];

            for (int x : e[u]) {
                int y = v;
                int a = Math.min(x, y), b = Math.max(x, y);
                if (dis[a][b] == -1) {
                    dis[a][b] = dis[u][v] + 1;
                    dp[a][b] = dp[u][v] + 1L * sz[a][b] * sz[b][a];
                    queue.add(new int[]{a, b});
                } else if (dis[a][b] == dis[u][v] + 1) {
                    dp[a][b] = Math.max(dp[a][b], dp[u][v] + 1L * sz[a][b] * sz[b][a]);
                }
            }

            for (int x : e[v]) {
                int y = u;
                int a = Math.min(x, y), b = Math.max(x, y);
                if (dis[a][b] == -1) {
                    dis[a][b] = dis[u][v] + 1;
                    dp[a][b] = dp[u][v] + 1L * sz[a][b] * sz[b][a];
                    queue.add(new int[]{a, b});
                } else if (dis[a][b] == dis[u][v] + 1) {
                    dp[a][b] = Math.max(dp[a][b], dp[u][v] + 1L * sz[a][b] * sz[b][a]);
                }
            }
        }

        long ans = 0;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                ans = Math.max(ans, dp[i][j]);

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
//       int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
