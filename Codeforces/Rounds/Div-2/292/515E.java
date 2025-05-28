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

    static final int N = 100020;
    static final int LG = 22;
    static final long INF = (long) 1e18;

    static int n, m;
    static long[] pos = new long[N];
    static long[] h = new long[N];
    static long[][] val1 = new long[LG][N];
    static long[][] val2 = new long[LG][N];
    static long[][] res = new long[LG][N];


    static void solve() throws IOException {
        n = cin.nextInt();
        m = cin.nextInt();

        for (int i = 0; i < n; i++) {
            long d = cin.nextLong();
            pos[i + 1] = pos[i] + d;
        }

        for (int i = 0; i < n; i++) {
            h[i] = cin.nextLong();
            val1[0][i] = 2 * h[i] - pos[i];
            val2[0][i] = 2 * h[i] + pos[i];
        }

        for (int j = 1, gap = 2; j < LG; j++, gap *= 2) {
            for (int i = 0; i + gap <= n; i++) {
                int mid = i + gap / 2;

                val1[j][i] = Math.max(val1[j - 1][i], val1[j - 1][mid]);
                val2[j][i] = Math.max(val2[j - 1][i], val2[j - 1][mid]);

                res[j][i] = Math.max(Math.max(res[j - 1][i], res[j - 1][mid]), val1[j - 1][i] + val2[j - 1][mid]);
            }
        }

        while (m-- > 0) {
            int l = cin.nextInt();
            int r = cin.nextInt();
            l--;

            int temp = l;
            l = r;
            r = temp;

            l %= n;
            if (r == 0) {
                r = n;
            }

            long ans = -1;

            if (l >= r) {
                long mx1 = -INF, mx2 = -INF, tmp = -INF;

                int p = l;
                for (int i = 0; i < LG; i++) {
                    if (((n - p) & (1 << i)) != 0) {
                        ans = Math.max(ans, res[i][p]);
                        ans = Math.max(ans, tmp + val2[i][p]);

                        tmp = Math.max(tmp, val1[i][p]);
                        mx1 = Math.max(mx1, val1[i][p]);

                        p += (1 << i);
                    }
                }

                p = 0;
                tmp = -INF;
                for (int i = 0; i < LG; i++) {
                    if (((r - p) & (1 << i)) != 0) {
                        ans = Math.max(ans, res[i][p]);
                        ans = Math.max(ans, tmp + val2[i][p]);

                        tmp = Math.max(tmp, val1[i][p]);
                        mx2 = Math.max(mx2, val2[i][p]);

                        p += (1 << i);
                    }
                }

                ans = Math.max(ans, mx1 + mx2 + pos[n]);
            } else {
                long mx = -INF;
                int p = l;
                for (int i = 0; i < LG; i++) {
                    if (((r - p) & (1 << i)) != 0) {
                        ans = Math.max(ans, res[i][p]);
                        ans = Math.max(ans, mx + val2[i][p]);

                        mx = Math.max(mx, val1[i][p]);

                        p += (1 << i);
                    }
                }
            }

            System.out.println(ans);
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
