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

    static final int MAX_BITS = 30;
    static List<int[]> [] graph;
    static boolean[] visited;
    static int[] dist;
    static int[] basis = new int[MAX_BITS];

    static void dfs(int v, int xorVal) {
        visited[v] = true;
        dist[v] = xorVal;

        for (int[] edge : graph[v]) {
            int u = edge[0];
            int w = edge[1];
            if (!visited[u]) {
                dfs(u, xorVal ^ w);
            } else {
                int cycleXor = xorVal ^ w ^ dist[u];
                for (int i = MAX_BITS - 1; i >= 0; i--) {
                    if (((cycleXor >> i) & 1) == 1) {
                        if (basis[i] == 0) {
                            basis[i] = cycleXor;
                            break;
                        }
                        cycleXor ^= basis[i];
                    }
                }
            }
        }
    }


    static void solve() throws IOException {
        int n = cin.nextInt();
        int m = cin.nextInt();

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int x = cin.nextInt();
            int y = cin.nextInt();
            int w = cin.nextInt();
            graph[x].add(new int[]{y, w});
            graph[y].add(new int[]{x, w});
        }

        visited = new boolean[n + 1];
        dist = new int[n + 1];
        dfs(1, 0);

        int ans = dist[n];
        for (int i = MAX_BITS - 1; i >= 0; i--) {
            if ((ans ^ basis[i]) < ans) {
                ans ^= basis[i];
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
