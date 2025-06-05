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

    static class Pair implements Comparable<Pair> {
        long cost;
        int id;
        Pair(long cost, int id) {
            this.cost = cost;
            this.id = id;
        }
        @Override
        public int compareTo(Pair other) {
            if (this.cost != other.cost) {
                return Long.compare(this.cost, other.cost);
            }
            return Integer.compare(this.id, other.id);
        }
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int m = cin.nextInt();
        ArrayList<Long> vec1 = new ArrayList<>();
        ArrayList<Long> vec2 = new ArrayList<>();
        ArrayList<Long> vec3 = new ArrayList<>();

        TreeSet<Pair> st1 = new TreeSet<>();
        TreeSet<Pair> st2 = new TreeSet<>();
        TreeSet<Pair> wh = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            int w = cin.nextInt();
            long c = cin.nextLong();
            if (w == 1) {
                st1.add(new Pair(c, i));
                vec1.add(c);
            } else if (w == 2) {
                st2.add(new Pair(c, i));
                vec2.add(c);
            } else {
                vec3.add(c);
            }
        }

        Collections.sort(vec1);
        Collections.sort(vec2);
        Collections.sort(vec3);

        long[] dp = new long[m + 1];
        Arrays.fill(dp, 0L);

        long sm = 0L;
        int cUsed = 0;

        for (int i = 1; i <= m; i++) {
            dp[i] = dp[i - 1];

            long val1 = -1L, val2 = -1L;

            if (!st1.isEmpty()) {
                Pair best1 = st1.last();
                val1 = best1.cost;
            }

            if (!st2.isEmpty() && !wh.isEmpty()) {
                Pair best2 = st2.last();
                Pair worstTaken1 = wh.first();
                val2 = best2.cost - worstTaken1.cost;
            }


            if (val1 < 0 && val2 < 0) {
                if (cUsed <= i - 2 && !st2.isEmpty()) {
                    Pair best2 = st2.last();
                    sm += best2.cost;
                    st2.remove(best2);
                    cUsed += 2;
                }
                dp[i] = sm;
                continue;
            }

            if (val1 > val2) {
                Pair best1 = st1.last();
                sm += best1.cost;
                st1.remove(best1);
                wh.add(best1);
                cUsed += 1;
            } else {
                Pair best2 = st2.last();
                Pair worstTaken1 = wh.first();
                sm += (best2.cost - worstTaken1.cost);

                wh.remove(worstTaken1);
                st1.add(worstTaken1);
                st2.remove(best2);

                wh.add(best2);

                cUsed += 1;
            }
            dp[i] = sm;
        }

        long ans = dp[m];

        long sum3 = 0L;
        int maxTriples = Math.min(vec3.size(), m / 3);
        for (int t = 0; t < maxTriples; t++) {
            int lastIdx = vec3.size() - 1;
            sum3 += vec3.get(lastIdx);
            vec3.remove(lastIdx);

            int remW = m - 3 * (t + 1);
            if (remW >= 0) {
                ans = Math.max(ans, sum3 + dp[remW]);
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
