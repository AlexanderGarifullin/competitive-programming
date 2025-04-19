import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        int z = cin.nextInt();

        List<Integer> a = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int x = cin.nextInt();
            a.add(x);
        }
        int ans = 0;
        for (int x : a) {
            ans = Math.max(ans, x | z);
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
