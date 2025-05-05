import java.security.PublicKey;
import java.util.*;

public class Main {
    static Scanner cin = new Scanner(System.in);

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

    static int getHash(int r, int c, int n) {
        return r * n + c;
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

    static void solve() {
        int a = cin.nextInt();
        int b = cin.nextInt();
        int d = cin.nextInt();
        int ans = 0;

        for (int i = 0; i < (1<<7); i++) {
            int x=0,y=0,z=0;
            for (int j = 1; j <= 7; j++) {
                if ((i >> (j-1) & 1) == 1) {
                    x += j & 1;
                    y += j >> 1 & 1;
                    z += j >> 2;
                }
            }
            if (x <= a && y<= b && z <= d) {
                int c = 0;
                for (int j = 0; j < 7; j++) {
                    if ((i >> j & 1) == 1) c++;
                }
                ans = Math.max(ans, c);
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
