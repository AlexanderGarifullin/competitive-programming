import javax.crypto.spec.PSource;
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
        String s = cin.next();
        int n = s.length();
        StringBuilder ans = new StringBuilder();
        boolean isLeft = true;
        if (n % 2 == 0) {
            isLeft = false;
        }
        int l =0, r = n-1;
        for (int i = 0; i < n; i++) {
            if (isLeft) {
                ans.append(s.charAt(l++));
            } else{
                ans.append(s.charAt(r--));
            }
            isLeft = !isLeft;
        }
        ans.reverse();
        System.out.println(ans);
    }


    public static void main(String[] args) {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
