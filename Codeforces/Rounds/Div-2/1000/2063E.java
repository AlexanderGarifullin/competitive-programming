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
    static ArrayList<Integer>[] adj;
    static int[] depth;
    static int[] subSize;
    static long[] cntDepth;
    static long[] suffixCnt;
    static long ansPart1Raw;
    static long correctionDepth;
    static long ansPart2;

    static void solve() throws IOException {
        int n = cin.nextInt();
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = cin.nextInt() - 1;
            int v = cin.nextInt() - 1;
            adj[u].add(v);
            adj[v].add(u);
        }

        depth = new int[n];
        subSize = new int[n];
        cntDepth = new long[n];
        suffixCnt = new long[n];

        ansPart1Raw = 0;
        correctionDepth = 0;
        ansPart2 = 0;
        Arrays.fill(cntDepth, 0L);

        depth[0] = 0;
        dfs1(0, -1);

        suffixCnt[n - 1] = cntDepth[n - 1];
        for (int d = n - 2; d >= 0; d--) {
            suffixCnt[d] = suffixCnt[d + 1] + cntDepth[d];
        }

        dfs2(0, -1);

        for (int k = 0; k < n; k++) {
            if (cntDepth[k] >= 2) {
                correctionDepth += 2L * k * (cntDepth[k] * (cntDepth[k] - 1) / 2);
            }
        }

        long answer = (ansPart1Raw - correctionDepth) - ansPart2;
        System.out.println(answer);
    }

    static void dfs1(int v, int parent) {
        cntDepth[ depth[v] ]++;
        subSize[v] = 1;
        for (int to : adj[v]) {
            if (to == parent) continue;
            depth[to] = depth[v] + 1;
            dfs1(to, v);
            subSize[v] += subSize[to];
        }
    }

    static void dfs2(int v, int parent) {
        long cntAllowed = suffixCnt[ depth[v] ] - subSize[v];
        ansPart1Raw += 2L * depth[v] * cntAllowed;

        long S = subSize[v] - 1;
        long T_w = 0;
        for (int to : adj[v]) {
            if (to == parent) continue;
            long sc = subSize[to];
            T_w += sc * (S - sc);
        }


        ansPart2 += (2L * depth[v] + 1L) * (T_w / 2);

        for (int to : adj[v]) {
            if (to == parent) continue;
            dfs2(to, v);
        }
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
