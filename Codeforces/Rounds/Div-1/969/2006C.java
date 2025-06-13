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

    static final int MAXN = 410000;
    static final int LOG = 20;
    static int[][] table = new int[LOG][MAXN];

    static int getGcd(int l, int r) {
        if (l > r) return 0;
        int sz = 31 - Integer.numberOfLeadingZeros(r - l + 1);
        return (int) gcd(table[sz][l], table[sz][r - (1 << sz) + 1]);
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int[] a = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            a[i] = cin.nextInt();
        }

        long ans = n;
        if (n == 1) {
            System.out.println(ans);
            return;
        }

        n--;
        for (int i = 1; i <= n; i++) {
            a[i] = Math.abs(a[i + 1] - a[i]);
        }

        for (int i = 1; i <= n; i++) {
            while (a[i] % 2 == 0 && a[i] != 0) {
                a[i] /= 2;
            }
        }

        for (int i = 1; i <= n; i++) {
            table[0][i] = a[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 1; i + (1 << k) - 1 <= n; i++) {
                table[k][i] = (int) gcd(table[k - 1][i], table[k - 1][i + (1 << (k - 1))]);
            }
        }

        int l = 1;
        for (int r = 1; r <= n; r++) {
            while (l <= r && getGcd(l, r) == 1) {
                l++;
            }
            ans += l - 1;
        }

        int cnt = 0;
        for (int r = 1; r <= n; r++) {
            if (a[r] != 0) {
                cnt = 0;
            } else {
                cnt++;
                ans += cnt;
            }
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
