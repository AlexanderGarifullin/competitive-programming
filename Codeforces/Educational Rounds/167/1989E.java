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

    static final int MOD = 998244353;
    static final int MAX_N = 200005;
    static final int MAX_K = 12;

    static void solve() throws IOException {
        int n = cin.nextInt();
        int k = cin.nextInt();

        int[][] dp = new int[MAX_N][MAX_K];
        int[] sum = new int[MAX_K];

        dp[0][0] = 1;
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;

        sum[0] = 1;
        sum[1] = 2;
        sum[2] = 1;

        for (int i = 3; i <= n; i++) {
            for (int j = k; j >= 1; j--) {
                dp[i][j] = sum[j - 1];
                if (j == k) {
                    dp[i][j] = (dp[i][j] + sum[j]) % MOD;
                }

                if (i >= 2) {
                    dp[i][j] = (dp[i][j] - dp[i - 2][j - 1] + MOD) % MOD;
                    if (j == k) {
                        dp[i][j] = (dp[i][j] - dp[i - 2][j] + MOD) % MOD;
                    }
                }

                sum[j] = (sum[j] + dp[i][j]) % MOD;
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = (ans + dp[i][k - 1]) % MOD;
            ans = (ans + dp[i][k]) % MOD;
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
