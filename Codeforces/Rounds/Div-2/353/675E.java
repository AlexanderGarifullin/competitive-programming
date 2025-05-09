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

    private static final int MAX = 500_000 + 5;

    private static int[] mobius = new int[MAX];
    private static int[] cnt    = new int[MAX];
    private static boolean[] onShelf;
    private static int[][] divisors;
    private static long answer = 0;

    static class Pair<F,S> {
        F first;
        S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        static <F,S> Pair<F,S> of(F first, S second) {
            return new Pair<>(first, second);
        }
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int [] a = new int[n-1];
        for (int i = 0; i <n-1; i++) {
            a[i] = cin.nextInt() - 1;
        }
        long [] val = new long[n];
        long ans=0;
        int INF = (int) 1e9;
        TreeSet<Integer> set =new TreeSet<>();

        set.add(n-2);
        val[n-2] = 1;
        ans = 1;
        set.add(INF);

        for (int i = n-3; i >= 0; i--) {
            int k = set.floor(a[i]);
            val[i] = val[k] + (long) (n - i - 1) - (long) (a[i] - k);
            ans += val[i];
            while(true) {
                int first = set.first();
                if (first == INF) break;
                if (a[i] >= a[first]) set.pollFirst();
                else break;
            }
            set.add(i);
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
