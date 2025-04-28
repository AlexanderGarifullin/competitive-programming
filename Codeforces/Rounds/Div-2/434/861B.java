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

    static void solve() {
        int n = cin.nextInt();
        int m = cin.nextInt();
        List<Integer> kk = new ArrayList<>(m), ff = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            int k,f;
            k = cin.nextInt();
            f = cin.nextInt();
            kk.add(k);
            ff.add(f);
        }
        List<Integer> answ = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            boolean ok = true;
            for (int j = 0; j < m; j++) {
                if ((kk.get(j) + i - 1) / i != ff.get(j)) {
                    ok = false;
                    break;
                }
            }
            if (ok) answ.add(i);
        }
        if (answ.isEmpty() || answ.size() > 1) {
            System.out.println(-1);
            return;
        }
        System.out.println((n + answ.getFirst() - 1) / answ.getFirst());
    }


    public static void main(String[] args) {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
