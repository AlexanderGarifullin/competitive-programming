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

    static int n, m;
    static int[] mark;
    static long[] delta, sum;
    static int l, r, x;

    static void build(int k, int q, int h) {
        if (q < h) {
            int mid = (q + h) / 2;
            build(k * 2, q, mid);
            build(k * 2 + 1, mid + 1, h);
            mark[k] = 0;
        } else {
            mark[k] = q;
        }
    }

    static void clear(int k, int q, int h) {
        if (mark[k] > 0) {
            delta[k] += Math.abs(mark[k] - x);
            sum[k] += (long) Math.abs(mark[k] - x) * (h - q + 1);
        } else {
            int mid = (q + h) / 2;
            clear(k * 2, q, mid);
            clear(k * 2 + 1, mid + 1, h);
            sum[k] = sum[k * 2] + sum[k * 2 + 1] + delta[k] * (h - q + 1);
        }
        mark[k] = -1;
    }

    static void modify(int k, int q, int h) {
        if (l <= q && h <= r) {
            clear(k, q, h);
            mark[k] = x;
        } else {
            if (mark[k] > 0) {
                mark[k * 2] = mark[k * 2 + 1] = mark[k];
                mark[k] = 0;
            }
            int mid = (q + h) / 2;
            if (r <= mid) {
                modify(k * 2, q, mid);
            } else if (l > mid) {
                modify(k * 2 + 1, mid + 1, h);
            } else {
                modify(k * 2, q, mid);
                modify(k * 2 + 1, mid + 1, h);
            }
            mark[k] = 0;
            sum[k] = sum[k * 2] + sum[k * 2 + 1] + delta[k] * (h - q + 1);
        }
    }

    static long query(int k, int q, int h, int L, int R) {
        if (L <= q && h <= R) {
            return sum[k];
        }
        int mid = (q + h) / 2;
        if (mark[k] > 0) {
            mark[k * 2] = mark[k * 2 + 1] = mark[k];
            mark[k] = 0;
        }
        if (R <= mid) {
            return query(k * 2, q, mid, L, R) + delta[k] * (R - L + 1);
        } else if (L > mid) {
            return query(k * 2 + 1, mid + 1, h, L, R) + delta[k] * (R - L + 1);
        } else {
            return query(k * 2, q, mid, L, mid) + query(k * 2 + 1, mid + 1, h, mid + 1, R) + delta[k] * (R - L + 1);
        }
    }

    static void solve() throws IOException {
        n = cin.nextInt();
        m = cin.nextInt();

        int size = 4 * n + 10;
        mark = new int[size];
        delta = new long[size];
        sum = new long[size];

        build(1, 1, n);

        while (m-- > 0) {
            int t = cin.nextInt();
            l = cin.nextInt();
            r = cin.nextInt();

            if (t == 1) {
                x = cin.nextInt();
                modify(1, 1, n);
            } else {
                System.out.println(query(1, 1, n, l, r));
            }
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
