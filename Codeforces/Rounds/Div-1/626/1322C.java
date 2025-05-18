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

    static final int N = 510000;
    static long[] c = new long[N];
    static long[] h = new long[N];
    static long[] x = new long[N];
    static Map<Long, Long> sums = new HashMap<>();
    static Random rng = new Random();

    static void solve() throws IOException {
        int n = cin.nextInt();
        int m = cin.nextInt();

        for (int i = 1; i <= n; i++) {
            c[i] = cin.nextLong();
            x[i] = 0;
        }
        for (int i = 0; i < m; i++) {
            int u = cin.nextInt();
            int v = cin.nextInt();
            x[v] ^= h[u];
        }

        sums.clear();
        for (int i = 1; i <= n; i++) {
            if (x[i] != 0) {
                sums.put(x[i], sums.getOrDefault(x[i], 0L) + c[i]);
            }
        }

        long g = 0;
        for (long sum : sums.values()) {
            g = gcd(g, sum);
        }

        System.out.println(g);
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        for (int i = 0; i < N; i++) {
            h[i] = rng.nextLong();
        }
        while (t-- > 0) {
            solve();
        }
    }
}
