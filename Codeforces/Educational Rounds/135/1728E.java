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

    static int exgcd(int a, int b, int[] xy) {
        if (b == 0) {
            xy[0] = 1;
            xy[1] = 0;
            return a;
        }
        int d = exgcd(b, a % b, xy);
        int nx = xy[1], ny = xy[0] - (a / b) * xy[1];
        xy[0] = nx;
        xy[1] = ny;
        return d;
    }

    static void solve() throws IOException {
        int n = cin.nextInt();

        Integer[] diff = new Integer[n];
        long sum = 0;
        for (int i = 0; i < n; ++i) {
            int a = cin.nextInt();
            int b = cin.nextInt();
            diff[i] = a - b;
            sum += b;
        }

        Arrays.sort(diff, Comparator.reverseOrder());

        long[] pref = new long[n + 1];
        pref[0] = sum;
        for (int i = 1; i <= n; ++i) {
            pref[i] = pref[i - 1] + diff[i - 1];
        }

        int peak = 0;
        for (int i = 1; i <= n; ++i) {
            if (pref[i] > pref[peak]) {
                peak = i;
            }
        }

        int m = cin.nextInt();
        while (m-- > 0) {
            int a = cin.nextInt();
            int b = cin.nextInt();

            int[] xy = new int[2];
            int d = exgcd(a, b, xy);
            int x = xy[0];
            int y = xy[1];

            if (n % d != 0) {
                System.out.println(-1);
                continue;
            }

            x = (int) ((1L * x * (n / d)) % (b / d));
            if (x < 0) {
                x += b / d;
            }

            if (1L * a * x > n) {
                System.out.println(-1);
                continue;
            }

            int c = (int) (1L * a * b / d);
            int s = a * x;
            int t = n - (n - s) % c;

            long ans = Math.max(pref[s], pref[t]);
            if (s <= peak && peak <= t) {
                s = peak - (peak - s) % c;
                t = peak + (t - peak) % c;
                ans = Math.max(ans, Math.max(pref[s], pref[t]));
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
