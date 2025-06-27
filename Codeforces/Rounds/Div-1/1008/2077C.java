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

    static final long N = 210_000;
    static final long MOD = 998_244_353;
    static final long INV4 = (MOD * 3 + 1) / 4;

    static int[] t = new int[(int)N];

    static void solve() throws IOException {
        int n = cin.nextInt();
        int q = cin.nextInt();
        String s = cin.next();

        int a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                t[i + 1] = -1;
                a++;
            } else {
                t[i + 1] = 1;
                b++;
            }
        }

        long p2n1 = 1;
        for (int i = 1; i <= n - 1; i++) {
            p2n1 = (p2n1 * 2) % MOD;
        }

        long p2n2 = 1;
        for (int i = 1; i <= n - 2; i++) {
            p2n2 = (p2n2 * 2) % MOD;
        }

        while (q-- > 0) {
            int i = cin.nextInt();
            if (t[i] == -1) {
                a--;
                b++;
            } else {
                a++;
                b--;
            }
            t[i] = -t[i];

            long term1 = ((long)a * (a - 1) % MOD + (long)b * (b - 1) % MOD - 2L * a * b % MOD + MOD) % MOD;
            long ans = term1 * p2n2 % MOD;
            ans = (ans + (long)(a + b) * p2n1 % MOD) % MOD;
            ans = (ans + MOD - p2n1) % MOD;
            ans = ans * INV4 % MOD;

            System.out.println(ans);
        }
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
