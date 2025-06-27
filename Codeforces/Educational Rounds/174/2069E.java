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

    static void solve() throws IOException {
        String s = cin.next();
        int a = cin.nextInt();
        int b = cin.nextInt();
        int ab = cin.nextInt();
        int ba = cin.nextInt();

        int cntA = 0, cntB = 0;
        for (char c : s.toCharArray()) {
            if (c == 'A') cntA++;
            else cntB++;
        }

        int two = Math.max(0, Math.max(cntA - a, cntB - b));
        if (two > ab + ba) {
            System.out.println("NO");
            return;
        }

        int arbi = 0;
        int n = s.length();
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        for (int l = 0, r = 1; r <= n; r++) {
            if (r == n || s.charAt(r) == s.charAt(r - 1)) {
                int len = r - l;
                if (len % 2 == 1) {
                    arbi += len / 2;
                } else if (s.charAt(l) == 'A') {
                    listA.add(len / 2);
                } else {
                    listB.add(len / 2);
                }
                l = r;
            }
        }

        int suma = listA.stream().mapToInt(Integer::intValue).sum();
        int sumb = listB.stream().mapToInt(Integer::intValue).sum();
        int total = suma + sumb + arbi;

        listA.sort(Comparator.reverseOrder());
        listB.sort(Comparator.reverseOrder());

        if (suma > ab) {
            for (int x : listA) {
                if (suma > ab) {
                    suma -= x;
                    total--;
                }
            }
        } else if (sumb > ba) {
            for (int x : listB) {
                if (sumb > ba) {
                    sumb -= x;
                    total--;
                }
            }
        }

        if (total >= two) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
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
