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

    static class Node {
        long[] cnt = new long[5];
        int num;
    }

    static int n;
    static String[] ops;
    static int[] values;
    static List<Integer> sorted = new ArrayList<>();
    static Map<Integer, Integer> indexMap = new HashMap<>();
    static Node[] seg;


    static void solve() throws IOException {
        n = cin.nextInt();
        ops = new String[n];
        values = new int[n];

        for (int i = 0; i < n; i++) {
            ops[i] = cin.next();
            if (!ops[i].equals("sum")) {
                values[i] = cin.nextInt();
                sorted.add(values[i]);
            }
        }
        Collections.sort(sorted);
        int idx = 0;
        for (int val : sorted) {
            if (!indexMap.containsKey(val)) {
                indexMap.put(val, idx++);
            }
        }

        seg = new Node[((int)1e5 + 20) * 4];
        for (int i = 0; i < seg.length; i++) seg[i] = new Node();

        for (int i = 0; i < n; i++) {
            String op = ops[i];
            if (op.equals("add")) {
                modify(1, 0, idx, indexMap.get(values[i]), values[i]);
            } else if (op.equals("del")) {
                modify(1, 0, idx, indexMap.get(values[i]), -values[i]);
            } else {
                System.out.println(seg[1].cnt[2]);
            }
        }
    }

    static void modify(int v, int l, int r, int pos, int val) {
        if (l + 1 == r) {
            if (val > 0) {
                seg[v].cnt[0] += val;
                seg[v].num = (seg[v].num + 1) % 5;
            } else {
                seg[v].cnt[0] += val;
                seg[v].num = (seg[v].num + 4) % 5;
            }
            return;
        }

        int mid = (l + r) / 2;
        if (pos < mid) modify(2 * v, l, mid, pos, val);
        else modify(2 * v + 1, mid, r, pos, val);

        merge(v);
    }

    static void merge(int v) {
        Node left = seg[2 * v];
        Node right = seg[2 * v + 1];

        for (int i = 0; i < 5; i++) {
            seg[v].cnt[i] = left.cnt[i] + right.cnt[(i + 5 - left.num) % 5];
        }

        seg[v].num = (left.num + right.num) % 5;
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
