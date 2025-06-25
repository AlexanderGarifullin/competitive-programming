import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.*;
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

    private static boolean canTransform(int n, int m, int[][] A, int[][] B) {
        for (int d = 0; d < 30; d++) {
            int[][][] x = new int[n][m][2];
            int[] rowCount = new int[n];
            int[] colCount = new int[m];
            Queue<Integer> queue = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int bit = (B[i][j] >> d) & 1;
                    x[i][j][bit] = 1;
                    if (bit == 0 && ++rowCount[i] == m) {
                        queue.offer(i);
                    }
                    if (bit == 1 && ++colCount[j] == n) {
                        queue.offer(n + j);
                    }
                }
            }

            while (!queue.isEmpty()) {
                int id = queue.poll();
                if (id < n) {
                    for (int j = 0; j < m; j++) {
                        if (x[id][j][1] == 0) {
                            x[id][j][1] = 1;
                            if (++colCount[j] == n) {
                                queue.offer(n + j);
                            }
                        }
                    }
                } else {
                    int col = id - n;
                    for (int i = 0; i < n; i++) {
                        if (x[i][col][0] == 0) {
                            x[i][col][0] = 1;
                            if (++rowCount[i] == m) {
                                queue.offer(i);
                            }
                        }
                    }
                }
            }
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int origBit = (A[i][j] >> d) & 1;
                    if (x[i][j][origBit] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int m = cin.nextInt();
        int[][] A = new int[n][m];
        int[][] B = new int[n][m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                A[i][j] = cin.nextInt();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                B[i][j] = cin.nextInt();

        if (canTransform(n, m, A, B)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
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
