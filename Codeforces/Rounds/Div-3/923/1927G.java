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

    static void solve() throws IOException {
        int n = cin.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = cin.nextInt();

        int INF = Integer.MAX_VALUE / 2;
        int[][][] dp = new int[n + 1][n + 1][n + 1];
        for (int[][] d2 : dp)
            for (int[] d1 : d2)
                Arrays.fill(d1, INF);

        dp[0][0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <= n; k++) {
                    if (dp[i][j][k] >= INF) continue;

                    int ai = a[i];

                    // Z - ничего не делать
                    {
                        int ni = i + 1;
                        int nj = j > 0 ? j + 1 : (k == 0 ? 1 : 0);
                        int nk = Math.max(0, k - 1);
                        dp[ni][nj][nk] = Math.min(dp[ni][nj][nk], dp[i][j][k]);
                    }

                    // L - использовать влево
                    {
                        int ni = i + 1;
                        int nj = j > 0 ? j + 1 : 0;
                        if (nj <= ai)
                            nj = 0;
                        int nk = Math.max(0, k - 1);
                        dp[ni][nj][nk] = Math.min(dp[ni][nj][nk], dp[i][j][k] + 1);
                    }

                    // R - использовать вправо
                    {
                        int ni = i + 1;
                        int nj = j > 0 ? j + 1 : 0;
                        int nk = Math.max(ai - 1, k - 1);
                        dp[ni][nj][nk] = Math.min(dp[ni][nj][nk], dp[i][j][k] + 1);
                    }
                }
            }
        }

        int ans = INF;
        for (int x = 0; x <= n; x++) {
            ans = Math.min(ans, dp[n][0][x]);
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
