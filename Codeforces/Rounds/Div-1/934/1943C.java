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

    static final int N = 2100;
    static List<Integer>[] g = new ArrayList[N];
    static int[] dep = new int[N];
    static List<Integer> path = new ArrayList<>();
    static List<int[]> ans = new ArrayList<>();

    static void solve() throws IOException {
        int n = cin.nextInt();
        for (int i = 1; i <= n; i++) g[i].clear();

        for (int i = 1; i < n; i++) {
            int u = cin.nextInt(), v = cin.nextInt();
            g[u].add(v);
            g[v].add(u);
        }

        int v = deep(1, 0, -1);
        int u = deep(v, 0, -1);

        path.clear();
        getPath(u, v, -1);

        ans.clear();
        int m = path.size();
        if (m % 2 != 0 || m % 4 != 0) {
            int center = path.get(m / 2);
            for (int i = 0; i <= m / 2; i++) {
                ans.add(new int[]{center, i});
            }
        } else {
            int c1 = path.get(m / 2);
            int c2 = path.get(m / 2 - 1);
            for (int i = 1; i < m / 2; i += 2) {
                ans.add(new int[]{c1, i});
                ans.add(new int[]{c2, i});
            }
        }

        System.out.println(ans.size());
        for (int[] op : ans) {
            System.out.println(op[0] + " " + op[1]);
        }
    }
    static int deep(int v, int d, int p) {
        dep[v] = d;
        int cur = v;
        for (int u : g[v]) {
            if (u != p) {
                int k = deep(u, d + 1, v);
                if (dep[k] > dep[cur]) {
                    cur = k;
                }
            }
        }
        return cur;
    }

    static boolean getPath(int u, int v, int p) {
        path.add(u);
        if (u == v) return true;
        for (int k : g[u]) {
            if (k != p && getPath(k, v, u)) {
                return true;
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
        for (int i = 0; i < N; i++) g[i] = new ArrayList<>();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
