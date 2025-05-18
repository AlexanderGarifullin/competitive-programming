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

    static final int MAX_C = 18;
    static int[] cnt = new int[MAX_C];
    static int[] dp = new int[1 << MAX_C];

    static void solve() throws IOException {
        int n = cin.nextInt();
        int c = cin.nextInt();
        int k = cin.nextInt();
        String s = cin.next();

        Arrays.fill(cnt, 0, c, 0);
        for (int i = 0; i < (1 << c); i++) dp[i] = 1;

        for (int i = 0; i < k; i++) {
            cnt[s.charAt(i) - 'A']++;
        }

        int msk = 0;
        for (int i = 0; i < c; i++) {
            if (cnt[i] == 0) {
                msk |= (1 << i);
            }
        }
        dp[msk] = 0;

        for (int i = 1; i + k - 1 < n; i++) {
            cnt[s.charAt(i - 1) - 'A']--;
            cnt[s.charAt(i + k - 1) - 'A']++;
            msk = 0;
            for (int j = 0; j < c; j++) {
                if (cnt[j] == 0) {
                    msk |= (1 << j);
                }
            }
            dp[msk] = 0;
        }


        int lastChar = s.charAt(n - 1) - 'A';
        dp[(1 << c) - 1 - (1 << lastChar)] = 0;

        for (int i = (1 << c) - 1; i >= 0; i--) {
            if (dp[i] != 0) continue;
            for (int j = 0; j < c; j++) {
                if ((i & (1 << j)) != 0) {
                    dp[i ^ (1 << j)] = 0;
                }
            }
        }

        int ans = c;
        for (int i = 0; i < (1 << c); i++) {
            if (dp[i] == 1) {
                ans = Math.min(ans, Integer.bitCount(i));
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
