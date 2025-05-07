import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
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

    static int que(String s, int i, int j) throws IOException {
        System.out.println(s + " " + i + " " + j);
        System.out.flush();
        int x = cin.nextInt();
        if (x == -1) System.exit(0);
        return x;
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        List<Integer>[] pos = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            pos[i] = new ArrayList<Integer>();
        }
        int xorvals[] = new int[n+1];
        int ans[] = new int[n+1];
        xorvals[1] = 0;
        pos[0].add(1);

        for (int i = 2; i <= n; i++) {
            xorvals[i] = que("XOR",1,i);
            pos[xorvals[i]].add(i);
        }

        int a=1, b= -1, c = -1;

        int same = -1;
        for (int i = 0; i < n; i++) {
            if (pos[i].size() > 1) {
                b = pos[i].get(0);
                c = pos[i].get(1);
                same = i;
            }
        }
        if (same == -1) {
            for (int i = 2; i <= 3; i++) {
                for (int j = i+1; j <= n; j++) {
                    if ((xorvals[i] ^ xorvals[j]) == n-1) {
                        b = i;
                        c = j;
                    }
                }
            }
            int xorab = xorvals[a] ^ xorvals[b], xorac = xorvals[a] ^ xorvals[c], xorbc = xorvals[b] ^ xorvals[c];
            int andab = que("AND", a, b);
            int andac = que("AND", a, c);
            int andbc = 0;
            int x = xorab + 2 * andab;
            int y = xorac + 2 * andac;
            int z = xorbc + 2 * andbc;
            assert((x + y - z) % 2 == 0);
            ans[a] = (x + y - z) / 2;
        } else {
            ans[b] =que("AND", b, c);
            ans[1] = xorvals[b] ^ ans[b];
        }
        for (int i = 2; i <= n; i++) {
            ans[i] = xorvals[i] ^ ans[1];
        }
        System.out.println("! ");
        for (int i = 1; i <= n; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
        System.out.flush();
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
