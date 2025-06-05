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

    static int MOD = (int) 1e9 + 7;

    static void solve() throws IOException {
        int n = cin.nextInt();
        int m = cin.nextInt();

        char[][] grid = new char[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            String line = cin.next();
            for (int j = 1; j <= m; j++) {
                grid[i][j] = line.charAt(j-1);
            }
        }
        int[][][] dp = new int[2][n+2][n+2];

        dp[1][0][n+1] = 1;

        int o = ( (n + m - 2) / 2 ) + 2;
        int answer = 0;

        for (int t = 2; t <= o; t++) {
            int curr = t & 1;
            int prev = curr ^ 1;

            for (int i = 0; i <= n+1; i++) {
                Arrays.fill(dp[curr][i], 0);
            }

            int jam1 = t;
            int jam2 = n + m - t + 2;


            int iMin = Math.max(1, jam1 - m);
            int iMax = Math.min(n,    jam1 - 1);

            int jMin = Math.max(1, jam2 - m);
            int jMax = Math.min(n,    jam2 - 1);

            for (int i = iMin; i <= iMax; i++) {
                int c1 = jam1 - i;
                if (c1 < 1 || c1 > m) continue;
                for (int j = jMin; j <= jMax; j++) {
                    int c2 = jam2 - j;
                    if (c2 < 1 || c2 > m) continue;

                    if (i > j) continue;
                    if (c1 > c2) continue;

                    if (grid[i][c1] != grid[j][c2]) continue;


                    int sum = dp[prev][i][j];

                    if (i-1 >= 0) {
                        sum = (sum + dp[prev][i-1][j]) % MOD;
                    }

                    if (j+1 <= n+1) {
                        sum = (sum + dp[prev][i][j+1]) % MOD;
                    }

                    if (i-1 >= 0 && j+1 <= n+1) {
                        sum = (sum + dp[prev][i-1][j+1]) % MOD;
                    }

                    dp[curr][i][j] = sum;

                    if (t == o) {
                        answer = (answer + dp[curr][i][j]) % MOD;
                    }
                }
            }
        }

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
