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

    static final int B = 400;
    static final int N = 100_000;
    static final int SHIFT = B * N + 10;

    static void solve() throws IOException {
        int n = cin.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = cin.nextInt();
        }

        int[] cnt = new int[SHIFT * 2];
        int ans = 0;

        for (int d = -B; d <= B; d++) {
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                if (d >= 0) {
                    b[i] = a[i] + i * d;
                } else {
                    b[i] = a[i] + (n - i) * (-d);
                }

                ans = Math.max(ans, ++cnt[b[i]]);
            }

            for (int i = 0; i < n; i++) {
                cnt[b[i]] = 0;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = Math.max(0, i - B); j < i; j++) {
                int delta = a[i] - a[j];
                int len = i - j;

                if (delta % len == 0) {
                    int d = delta / len;
                    int key = d + N;
                    ans = Math.max(ans, ++cnt[key] + 1);
                }
            }

            for (int j = Math.max(0, i - B); j < i; j++) {
                int delta = a[i] - a[j];
                int len = i - j;

                if (delta % len == 0) {
                    int d = delta / len;
                    int key = d + N;
                    cnt[key] = 0;
                }
            }
        }

        System.out.println(n - ans);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
