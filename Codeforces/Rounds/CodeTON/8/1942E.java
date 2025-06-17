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

    static final int N = 1100000;
    static final int MOD = 998244353;
    static long[] gg = new long[N];
    static long[] ff = new long[N];
    static long[] vv = new long[N];

    static void init() {
        ff[0] = gg[0] = 1;
        for (int i = 1; i < N; i++) {
            vv[i] = (i == 1) ? 1 : MOD - MOD / i * vv[(int)(MOD % i)] % MOD;
            gg[i] = gg[i - 1] * vv[i] % MOD;
            ff[i] = ff[i - 1] * i % MOD;
        }
    }

    static long c(int n, int k) {
        if (k > n || k < 0) return 0;
        return ff[n] * gg[k] % MOD * gg[n - k] % MOD;
    }


    static void solve() throws IOException {
        int l = cin.nextInt();
        int n = cin.nextInt();

        long ans = c(l, 2 * n);
        for (int sz = 2 * n; sz <= l; sz += 2) {
            int other = l - sz + n;
            long dist = c(sz / 2 - 1, n - 1);
            long ways = c(other, n);
            ans = (ans + MOD - ways * dist % MOD) % MOD;
        }
        
        System.out.println(ans * 2 % MOD);
    }

    public static void main(String[] args) throws IOException {
        init();
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
