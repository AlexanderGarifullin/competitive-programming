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

    static final int MAXN = 5005;
    static final int MOD = 998244353;

    static int n, ans;
    static int[] a = new int[MAXN];
    static int[] cnt = new int[MAXN];
    static int[][] dp = new int[MAXN][MAXN];
    static int[] inv = new int[MAXN];

    static int modInverse(int a) {
        return (int) modPow(a, MOD - 2, MOD);
    }

    static void solve() throws IOException {
        n = cin.nextInt();

        for (int i = 1; i <= n; i++) {
            a[i] = cin.nextInt();
            cnt[a[i]]++;
        }

        for (int i = 1; i <= n; i++) {
            inv[i] = modInverse(i);
        }

        for (int i = 1; i <= n; i++) {
            dp[1][a[i]] = (dp[1][a[i]] + inv[n]) % MOD;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= n; j++) {
                dp[i - 1][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % MOD;
            }

            for (int j = 1; j <= n; j++) {
                int ways = (cnt[j] - 1 + MOD) % MOD;
                int prob = (int)((1L * (dp[i - 1][j] - dp[i - 1][j - 1] + MOD) % MOD * ways % MOD * inv[n - i + 1]) % MOD);
                ans = (ans + prob) % MOD;

                dp[i][j] = (int)((1L * dp[i - 1][j - 1] * cnt[j] % MOD * inv[n - i + 1]) % MOD);
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
