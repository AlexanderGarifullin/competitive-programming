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
        int a = cin.nextInt();
        int b = cin.nextInt();
        if (Math.abs(a - b) > 1 || a + b + 2 > n) {
            System.out.println(-1);
            return;
        }
        int l = 1, r =n;
        if (a > b) {
            System.out.print((l++) + " ");
            for (int i = 0; i < a - 1; i++) {
                System.out.print((r--) + " " + (l++) + " ");
            }
            while(r >= l) {
                System.out.print((r--)  + " ");
            }
        } else if (a == b){
            System.out.print((l++) + " ");
            for (int i = 0; i < a; i++) {
                System.out.print((r--) + " " + (l++) + " ");
            }
            while(r >= l) {
                System.out.print((l++)  + " ");
            }
        } else {
            for (int i = 0; i < a+1; i++) {
                System.out.print((r--) + " " + (l++) + " ");
            }
            while(r >= l) {
                System.out.print((l++)  + " ");
            }
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int t = cin.nextInt();
        while (t-- > 0) {
            solve();
        }
    }
}
