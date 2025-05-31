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

    static List<List<Integer>> graph;
    static int[] dp;


    static void dfs(int u, int parent, int k) {
        int cnt = 0;
        int min = Integer.MAX_VALUE;

        for (int v : graph.get(u)) {
            if (v == parent) continue;
            dfs(v, u, k);
            if (dp[v] >= k) {
                dp[u] = Integer.MAX_VALUE; // fail
                return;
            }
            if (dp[v] == k - 1) {
                cnt++;
            } else {
                min = Math.min(min, dp[v]);
            }
        }

        if (cnt > 1) {
            dp[u] = Integer.MAX_VALUE;
        } else if (cnt > 0) {
            dp[u] = k;
        } else if (min == Integer.MAX_VALUE) {
            dp[u] = 0;
        } else {
            dp[u] = min + 1;
        }
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < n - 1; i++) {
            int u = cin.nextInt()- 1;
            int v = cin.nextInt()- 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int left = 1, right = n - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            dp = new int[n];
            dfs(0, -1, mid);
            if (dp[0] <= mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
