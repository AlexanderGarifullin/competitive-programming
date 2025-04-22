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
        List<Integer> a = new ArrayList<>(),b = new ArrayList<>(), c = new ArrayList<>();
        for (int i = 0, x; i < n; i++) {
            x = cin.nextInt();
            x--;
            a.add(x);
        }
        for (int i = 0, x; i < n; i++) {
            x = cin.nextInt();
            x--;
            b.add(x);
        }
        for (int i = 0; i < n; i++) {
            c.add(0);
        }
        for (int i = 0; i < n; i++) {
            c.set(a.get(i), b.get(i));
        }
        int ans = 1;
        int mod = (int) (1e9 + 7);
        List<Boolean> visit = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            visit.add(false);
        }
        for (int i = 0; i < n; i++) {
            if (!visit.get(i)) {
                ans = 2 * ans % mod;
                int j = i;
                while(!visit.get(j)) {
                    visit.set(j, true);
                    j = c.get(j);
                }
            }
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
