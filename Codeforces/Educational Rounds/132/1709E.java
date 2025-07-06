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

    static int n;
    static int[] a, b;
    static List<Integer>[] g;
    static Set<Integer>[] vals;
    static int ans = 0;

    static void init(int v, int p) {
        b[v] = a[v];
        if (p != -1) b[v] ^= b[p];
        for (int u : g[v]) {
            if (u != p) {
                init(u, v);
            }
        }
    }

    static void calc(int v, int p) {
        boolean bad = false;
        vals[v].add(b[v]);
        for (int u : g[v]) {
            if (u == p) continue;
            calc(u, v);
            if (vals[v].size() < vals[u].size()) {
                Set<Integer> temp = vals[v];
                vals[v] = vals[u];
                vals[u] = temp;
            }
            for (int x : vals[u]) {
                if (vals[v].contains(x ^ a[v])) {
                    bad = true;
                }
            }
            vals[v].addAll(vals[u]);
            vals[u].clear();
        }
        if (bad) {
            ans++;
            vals[v].clear();
        }
    }

    static void solve() throws IOException {
        n = cin.nextInt();

        a = new int[n];
        b = new int[n];
        g = new ArrayList[n];
        vals = new HashSet[n];

        for (int i = 0; i < n; i++) {
            a[i] = cin.nextInt();
            g[i] = new ArrayList<>();
            vals[i] = new HashSet<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = cin.nextInt() - 1;
            int v = cin.nextInt() - 1;
            g[u].add(v);
            g[v].add(u);
        }

        init(0, -1);
        calc(0, -1);

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
