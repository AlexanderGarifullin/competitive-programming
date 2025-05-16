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
        int n = cin.nextInt();
        int k = cin.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int u = cin.nextInt() - 1;
            int v = cin.nextInt() - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        if (k == 1) {
            System.out.println(n - 1);
            return;
        }

        int[] deg = new int[n];
        int[] leaf = new int[n];
        for (int i = 0; i < n; i++) {
            deg[i] = graph.get(i).size();
            if (deg[i] == 1) {
                int neighbor = graph.get(i).get(0);
                leaf[neighbor]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] inQueue = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (leaf[i] >= k) {
                queue.add(i);
                inQueue[i] = true;
            }
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            inQueue[u] = false;
            ans++;
            leaf[u] -= k;
            deg[u] -= k;

            if (leaf[u] >= k && !inQueue[u]) {
                queue.add(u);
                inQueue[u] = true;
            }

            if (deg[u] == 1) {
                deg[u] = 0;
                for (int v : graph.get(u)) {
                    if (deg[v] > 0) {
                        leaf[v]++;
                        if (leaf[v] >= k && !inQueue[v]) {
                            queue.add(v);
                            inQueue[v] = true;
                        }
                    }
                }
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
