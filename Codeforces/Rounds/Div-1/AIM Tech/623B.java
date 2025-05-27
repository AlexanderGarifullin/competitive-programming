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

    static int n;
    static long aCost, bCost;
    static int[] arr;
    static long[] dp;
    static final long INF = Long.MAX_VALUE / 4;

    static void solve() throws IOException {
        n     = cin.nextInt();
        aCost = cin.nextLong();
        bCost = cin.nextLong();

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = cin.nextInt();
        }

        dp = new long[n + 1];
        long answer = INF;

        int[] candidates = {
                arr[0] - 1, arr[0], arr[0] + 1,
                arr[n - 1] - 1, arr[n - 1], arr[n - 1] + 1
        };

        for (int x : candidates) {
            if (x >= 2) {
                answer = Math.min(answer, evaluateFor(x));
            }
        }

        System.out.println(answer);
    }


    static long evaluateFor(int x) {
        long res = INF;
        int tmp = x;
        for (int d = 2; (long)d * d <= tmp; d++) {
            if (tmp % d == 0) {
                res = Math.min(res, calc(d));
                while (tmp % d == 0) tmp /= d;
            }
        }
        if (tmp > 1) {
            res = Math.min(res, calc(tmp));
        }
        return res;
    }

    static long calc(int p) {
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            long c = costToAdjust(arr[i], p);
            dp[i + 1] = (dp[i] >= INF/2 || c >= INF/2)
                    ? INF
                    : dp[i] + c;
            if (dp[i + 1] > INF) dp[i + 1] = INF;
        }

        for (int i = 0; i < n; i++) {
            long withoutDel = dp[i + 1] - (i + 1) * aCost;
            dp[i + 1] = Math.min(dp[i], withoutDel);
            if (dp[i + 1] > INF) dp[i + 1] = INF;
        }

        long result    = INF;
        long suffixSum = 0;
        long curDel    = aCost * n;
        for (int i = n - 1; i >= 0; i--) {
            long c = costToAdjust(arr[i], p);
            suffixSum = (suffixSum >= INF/2 || c >= INF/2)
                    ? INF
                    : suffixSum + c;
            if (suffixSum > INF) suffixSum = INF;

            curDel = Math.min(curDel, suffixSum + i * aCost);
            result = Math.min(result, curDel + dp[i]);
        }
        return result;
    }

    static long costToAdjust(int x, int p) {
        int r = x % p;
        if (r == 0)         return 0;
        if (r == 1 || r == p - 1) return bCost;
        return INF;
    }
    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
