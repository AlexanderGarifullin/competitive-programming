import java.util.ArrayList;
import java.util.Arrays;
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
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0,x; i < n; i++) {
            x = cin.nextInt();
            list.add(x);
        }
        list = list.reversed();
        int ans=0;
        int p = list.getFirst();
        for (int x : list) {
            if (x > p) {
                p = x;
                ans++;
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
