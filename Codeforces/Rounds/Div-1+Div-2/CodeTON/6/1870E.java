import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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

    static void solve() throws IOException {
        final int N = 5001;

        int n = cin.nextInt();
        int[] a = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = cin.nextInt();
        }

        boolean[][] dp = new boolean[n + 1][n + 1];
        dp[0][0] = true;

        int[] in = new int[n + 1];
        int[] mx = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int k = 0; k <= n; k++) {
                dp[i][k] = dp[i - 1][k];
            }

            if (a[i] < n) {
                int mex = 0;
                Arrays.fill(in, 0);

                for (int j = i; j > 0; j--) {
                    in[a[j]] = i;
                    while (mex <= n && in[mex] == i) {
                        mex++;
                    }

                    if (mx[mex] < j) {
                        mx[mex] = j;
                        for (int k = 0; k <= n; k++) {
                            if (dp[j - 1][k]) {
                                dp[i][k ^ mex] = true;
                            }
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int k = 0; k <= n; k++) {
            if (dp[n][k]) {
                ans = k;
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
