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

        List<Integer> a = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int x = cin.nextInt();
            a.add(x);
        }

        long cnt0 = a.stream()
                .filter(x -> x == 0)
                .count();
        if (cnt0 == n)  {
            System.out.println(0);
            return;
        }
        if (cnt0 == 0) {
            System.out.println(1);
            return;
        }
        int ans = 1;
        int last = -1;
        for (int i = 0; i < n; i++) {
            if (a.get(i) != 0 && (last == -1 || last + 1 == i)) {
                last = i;
            } else if (a.get(i) != 0) {
                ans = 2;
                break;
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
