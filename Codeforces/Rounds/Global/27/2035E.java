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

    record Pair<F, S>(F first, S second) {}

    static long x, y, z, k;

    static Pair<Long, Long> find(long c) {
        long ans = c * x + (c - 1) / k * y;
        long t = (c - 1) / k;
        long sum = t * (t + 1) / 2 * k;
        if (z / k < (t * (t + 1)) / 2) {
            return new Pair<>(ans, 0L);
        }
        long Z = Math.max(0L, z - sum);
        long T = (Z + c - 1) / c;
        return new Pair<>(T * y + ans, T);
    }

    static void solve() throws IOException {
        x = cin.nextLong();
        y = cin.nextLong();
        z = cin.nextLong();
        k = cin.nextLong();

        final int B = 50_000;
        long ans = (long) 1e18;

        for (int i = 1; i <= B; ++i) {
            ans = Math.min(ans, find(i).first);
        }

        int lim = (int) (1e8 / B);
        for (int i = 1; i <= lim; ++i) {
            long lo = 0, hi = z;
            while (lo + 1 < hi) {
                long mid = (lo + hi) >> 1;
                if (find(mid).second > i) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }
            ans = Math.min(ans, find(hi).first);
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
