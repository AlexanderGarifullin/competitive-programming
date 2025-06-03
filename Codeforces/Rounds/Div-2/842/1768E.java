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

    static final int N = 3100000;
    static int n, MOD;
    static long[] ff = new long[N];
    static long[] gg = new long[N];
    static long[] vv = new long[N];

    static void init() {
        ff[0] = 1;
        gg[0] = 1;
        for (int i = 1; i < 3 * n + 10; i++) {
            if (i == 1) {
                vv[i] = 1;
            } else {
                long temp = (MOD / i);
                temp = (temp * vv[MOD % i]) % MOD;
                vv[i] = (MOD - temp) % MOD;
            }
            ff[i] = (ff[i - 1] * i) % MOD;
            gg[i] = (gg[i - 1] * vv[i]) % MOD;
        }
    }

    static long c(int x, int y) {
        if (x < y) return 0;
        return ((ff[x] * gg[y]) % MOD * gg[x - y]) % MOD;
    }

    static void solve() throws IOException {
        n = cin.nextInt();
        MOD = cin.nextInt();

        init();

        long sum = 0;
        long fn = ff[n];
        for (int k = 0; k <= n; k++) {
            long term = c(n, k);
            term = (term * c(n, n - k)) % MOD;
            term = (term * c(2 * n - k, n)) % MOD;
            term = (term * fn) % MOD;
            term = (term * fn) % MOD;
            term = (term * fn) % MOD;
            sum = (sum + term) % MOD;
        }

        long ans0 = 1;

        long f2n = ff[2 * n];
        long ans1 = ( (f2n * 2) % MOD - fn + MOD - ans0 + MOD ) % MOD;

        long comb2nn = c(2 * n, n);
        long ans2 = comb2nn;
        ans2 = (ans2 * fn) % MOD;
        ans2 = (ans2 * f2n) % MOD;
        ans2 = (ans2 * 2) % MOD;
        ans2 = (ans2 - sum + MOD) % MOD;
        ans2 = (ans2 - ans1 + MOD) % MOD;
        ans2 = (ans2 - ans0 + MOD) % MOD;

        long f3n = ff[3 * n];
        long ans3 = (f3n - ans2 + MOD) % MOD;
        ans3 = (ans3 - ans1 + MOD) % MOD;
        ans3 = (ans3 - ans0 + MOD) % MOD;

        long answer = (ans1 + (ans2 * 2) % MOD + (ans3 * 3) % MOD) % MOD;
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
