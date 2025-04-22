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

    static void solve() {
        int n = cin.nextInt();
        List<Integer> a  = new ArrayList<>(n+2);
        a.add(0);
        for (int i = 0,x; i < n; i++) {
            x = cin.nextInt();
            a.add(x);
        }
        a.add(0);
        long ans = 0;
        for (int i = 0; i <= n; i++) {
            ans += Math.abs(a.get(i) - a.get(i+1));
        }
        for (int i = 1; i <= n; i++) {
            ans -= Math.max(0, a.get(i) - Math.max(a.get(i+1), a.get(i-1)));
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int t = cin.nextInt();
        while (t-- > 0) {
            solve();
        }
    }
}
