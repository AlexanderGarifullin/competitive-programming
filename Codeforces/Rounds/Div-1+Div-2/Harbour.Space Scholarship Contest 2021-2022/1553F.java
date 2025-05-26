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

    static class Fenwick {
        private final int n;
        private final long[] bit;
        public Fenwick(int size) {
            n = size;
            bit = new long[n + 1];
        }

        public void add(int idx, long v) {
            for (; idx <= n; idx += idx & -idx) {
                bit[idx] += v;
            }
        }

        public long sum(int idx) {
            long s = 0;
            for (; idx > 0; idx -= idx & -idx) {
                s += bit[idx];
            }
            return s;
        }

        public long rangeSum(int l, int r) {
            if (l > r) return 0;
            return sum(r) - sum(l - 1);
        }
    }

    static void solve() throws IOException {
        int n = cin.nextInt();

        final int MAX = 300_000;
        Fenwick f1 = new Fenwick(MAX);
        Fenwick f2 = new Fenwick(MAX);

        long suma = 0;
        long ans = 0;

        for (int k = 1; k <= n; k++) {
            int x = cin.nextInt();

            ans += suma;

            for (int j = x; j <= MAX; j += x) {
                long cnt = f1.rangeSum(j, MAX);
                ans -= cnt * x;
            }

            for (int j = x; j <= MAX; j += x) {
                f2.add(j, x);
            }

            f1.add(x, 1);

            ans += 1L * x * k;
            ans -= f2.sum(x);

            suma += x;

            System.out.print(ans + " ");
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
