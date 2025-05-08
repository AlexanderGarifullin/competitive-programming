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

    private static final int INF_NEG = -1_000_000;

    private static class Project {
        int a, b;
        Project(int a, int b) {this.a = a; this.b = b;}
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int r = cin.nextInt();
        List<Project> pos = new ArrayList<>(), neg = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int a = cin.nextInt();
            int b = cin.nextInt();
            if (b >= 0) pos.add(new Project(a,b));
            else neg.add(new Project(a, b));
        }
        pos.sort(Comparator.comparingInt(p -> p.a));
        int rating = r;
        int done   = 0;

        for (Project p : pos) {
            if (p.a <= rating) {
                rating += p.b;
                ++done;
            }
        }

        neg.sort((p1, p2) -> Integer.compare(p2.a + p2.b, p1.a + p1.b));


        final int MAX_R = 70_001;
        int[] dp = new int[MAX_R];
        Arrays.fill(dp, INF_NEG);
        if (rating < MAX_R) dp[rating] = 0;
        else {
            System.out.println(done);
            return;
        }

        for (Project p : neg) {
            int[] next = new int[MAX_R];
            Arrays.fill(next, INF_NEG);

            for (int rr = 0; rr < MAX_R; ++rr) if (dp[rr] >= 0) {

                next[rr] = Math.max(next[rr], dp[rr]);

                int after = rr + p.b;
                if (rr >= p.a && after >= 0 && after < MAX_R) {
                    next[after] = Math.max(next[after], dp[rr] + 1);
                }
            }
            dp = next;
        }

        int bestNeg = 0;
        for (int val : dp) bestNeg = Math.max(bestNeg, val);

        System.out.println(done + bestNeg);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
