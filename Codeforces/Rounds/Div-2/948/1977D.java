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

    static class Pair {
        long first, second;

        Pair(long f, long s) {
            this.first = f;
            this.second = s;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair p = (Pair) o;
            return first == p.first && second == p.second;
        }

        @Override
        public int hashCode() {
            int h1 = Long.hashCode(first);
            int h2 = Long.hashCode(second);
            return h1 * 31 + h2;
        }
    }

    static Random rnd = new Random();

    static void solve() throws IOException {
        int n = cin.nextInt();
        int m = cin.nextInt();
        
        boolean[][] table = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String line = cin.next();
            for (int j = 0; j < m; j++) {
                table[i][j] = (line.charAt(j) == '1');
            }
        }

        long[] rands  = new long[n];
        long[] rands2 = new long[n];
        for (int i = 0; i < n; i++) {
            rands[i]  = rnd.nextLong();
            rands2[i] = rnd.nextLong();
        }
        
        Map<Pair, Integer> ans = new HashMap<>();
        int res = 0;
        int bestJ = 0, bestI = 0;
        
        for (int j = 0; j < m; j++) {
            long summ  = 0L;
            long summ2 = 0L;
            for (int i = 0; i < n; i++) {
                if (table[i][j]) {
                    summ  ^= rands[i];
                    summ2 ^= rands2[i];
                }
            }
            
            for (int i = 0; i < n; i++) {
                summ  ^= rands[i];
                summ2 ^= rands2[i];

                Pair key = new Pair(summ, summ2);
                int cnt = ans.getOrDefault(key, 0) + 1;
                ans.put(key, cnt);
                if (cnt > res) {
                    res = cnt;
                    bestJ = j;
                    bestI = i;
                }

                summ  ^= rands[i];
                summ2 ^= rands2[i];
            }
        }
        
        System.out.println(res);
        
        char[] inds = new char[n];
        Arrays.fill(inds, '0');
        for (int i = 0; i < n; i++) {
            if (table[i][bestJ]) {
                if (i != bestI) {
                    inds[i] = '1';
                }
            } else {
                if (i == bestI) {
                    inds[i] = '1';
                }
            }
        }

        System.out.println(new String(inds));
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
