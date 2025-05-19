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


    static class Pattern implements Comparable<Pattern> {
        String s;
        int index;

        Pattern(String s, int index) {
            this.s = s;
            this.index = index;
        }

        public int compareTo(Pattern other) {
            return this.s.compareTo(other.s);
        }
    }

    static List<List<Integer>> graph;
    static boolean[] visited;
    static int[] state;
    static List<Integer> order;
    static boolean hasCycle = false;

    static void dfs(int v) {
        state[v] = 1;
        for (int u : graph.get(v)) {
            if (state[u] == 0) dfs(u);
            else if (state[u] == 1) hasCycle = true;
            if (hasCycle) return;
        }
        state[v] = 2;
        order.add(v);
    }


    static void solve() throws IOException {

        int n = cin.nextInt(), m = cin.nextInt(), k = cin.nextInt();
        Pattern[] patterns = new Pattern[n];
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String s = cin.next();
            patterns[i] = new Pattern(s, i);
            graph.add(new ArrayList<>());
        }

        Arrays.sort(patterns);
        Map<String, Integer> patternMap = new HashMap<>();
        for (Pattern p : patterns) {
            patternMap.put(p.s, p.index);
        }

        for (int i = 0; i < m; i++) {
            String str = cin.next();
            int mt = cin.nextInt() - 1;
            boolean found = false;

            for (int mask = 0; mask < (1 << k); mask++) {
                char[] temp = new char[k];
                for (int j = 0; j < k; j++) {
                    temp[j] = ((mask & (1 << j)) != 0) ? str.charAt(j) : '_';
                }
                String patternStr = new String(temp);
                Integer idx = patternMap.get(patternStr);
                if (idx != null) {
                    if (idx == mt) found = true;
                    else graph.get(mt).add(idx);
                }
            }

            if (!found) {
                System.out.println("NO");
                return;
            }
        }

        state = new int[n];
        order = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (state[i] == 0) dfs(i);
            if (hasCycle) {
                System.out.println("NO");
                return;
            }
        }

        Collections.reverse(order);
        System.out.println("YES");
        for (int idx : order) {
            System.out.print((idx + 1) + " ");
        }
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
