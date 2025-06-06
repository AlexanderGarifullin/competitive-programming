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

    static ArrayList<Edge>[] g;
    static ArrayList<Integer>[] ng;
    static ArrayList<Integer>[] sub;
    static ArrayList<Integer> ord = new ArrayList<>();
    static int[] tin, tout;
    static int T = 0;
    static int[] sz;
    static int[] wp;
    static int[][] dp;
    static int n;
    static class Edge {
        int to, w;
        Edge(int _to, int _w) {
            to = _to;
            w = _w;
        }
    }

    static void dfs1(int v, int p) {
        ord.add(v);
        sz[v] = 1;
        tin[v] = T++;
        for (Edge e : g[v]) {
            int to = e.to;
            int w = e.w;
            if (to == p) continue;
            wp[to] = w;
            dfs1(to, v);
            sz[v] += sz[to];
        }
        tout[v] = T;
    }

    static long solve(int v, int x) {
        dp[0][v] = sz[v];
        dp[1][v] = 0;
        long ans = 0;

        for (int to : ng[v]) {
            ans += solve(to, x);
            dp[0][v] -= sz[to];
        }

        for (int to : ng[v]) {
            if (wp[to] == x) {
                ans += (long) dp[0][v] * dp[0][to];
                dp[1][v] += dp[0][to];
            } else {
                ans += (long) dp[1][v] * dp[0][to] + (long) dp[0][v] * dp[1][to];
                dp[1][v] += dp[1][to];
                dp[0][v] += dp[0][to];
            }
        }

        return ans;
    }

    static boolean isParent(int a, int b) {
        return tin[a] <= tin[b] && tout[a] >= tout[b];
    }

    static void solve() throws IOException {
        n = cin.nextInt();

        g = new ArrayList[n];
        ng = new ArrayList[n];
        sub = new ArrayList[n];

        tin = new int[n];
        tout = new int[n];
        sz = new int[n];
        wp = new int[n];
        dp = new int[2][n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            ng[i] = new ArrayList<>();
            sub[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            int a = cin.nextInt() - 1;
            int b = cin.nextInt() - 1;
            int c = cin.nextInt() - 1;
            g[a].add(new Edge(b, c));
            g[b].add(new Edge(a, c));
        }

        dfs1(0, -1);

        for (int w = 0; w < n; w++) {
            sub[w].add(0);
        }

        for (int v : ord) {
            for (Edge e : g[v]) {
                int w = e.w;
                sub[w].add(v);
            }
        }

        long answer = 0;

        for (int x = 0; x < n; x++) {
            if (!sub[x].isEmpty()) {
                ArrayList<Integer> uniq = new ArrayList<>();
                Integer prev = null;
                for (Integer v : sub[x]) {
                    if (prev == null || !prev.equals(v)) {
                        uniq.add(v);
                    }
                    prev = v;
                }
                sub[x] = uniq;


                ArrayList<Integer> stk = new ArrayList<>();
                for (int v : sub[x]) {
                    if (v != 0) {
                        while (!stk.isEmpty() && !isParent(stk.get(stk.size() - 1), v)) {
                            stk.remove(stk.size() - 1);
                        }
                        ng[stk.get(stk.size() - 1)].add(v);
                    }
                    stk.add(v);
                }

                answer += solve(0, x);

                for (int v : sub[x]) {
                    ng[v].clear();
                }
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
