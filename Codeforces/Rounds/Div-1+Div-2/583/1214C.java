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
        int n = cin.nextInt();
        String s = cin.next();
        boolean ok = true;
        int balance = 0;
        for (int i = 0; i < n; i++) {
            int tek = (s.charAt(i) == '(' ? 1 : -1);
            balance += tek;
            if (balance < 0) {
                s = s.substring(0, i) + s.substring(i+1) + s.charAt(i);
                break;
            }
        }
        balance = 0;
        for (int i = 0; i < n; i++) {
            int tek = (s.charAt(i) == '(' ? 1 : -1);
            balance += tek;
            if (balance < 0) {
                break;
            }
        }
        ok = balance == 0;
        if (ok) System.out.println("Yes");
        else System.out.println("No");
    }


    public static void main(String[] args) {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
