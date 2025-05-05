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
        int n = cin.nextInt();
        int cntEven1=0,cntOdd1=0;
        for (int i = 0; i < n; i++) {
            int x = cin.nextInt();
            if (x % 2 == 0) cntEven1++;
            else cntOdd1++;
        }
        int m = cin.nextInt();
        int cntEven2=0,cntOdd2=0;
        for (int i = 0; i < m; i++) {
            int x = cin.nextInt();
            if (x % 2 == 0)cntEven2++;
            else cntOdd2++;
        }
        long ans = 0;
        ans += 1L * cntEven2 * cntEven1;
        ans += 1L * cntOdd2 * cntOdd1;
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
