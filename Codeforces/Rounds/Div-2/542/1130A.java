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
        int n= cin.nextInt();
        int cntPos=0,cntNeg=0;
        for (int i = 0; i < n; i++) {
            int x = cin.nextInt();
            if (x>0)cntPos++;
            else if (x < 0) cntNeg++;
        }
        if (cntPos >= (n+1)/2) {
            System.out.println(1);
        } else if (cntNeg >= (n+1)/2) {
            System.out.println(-1);
        } else{
            System.out.println(0);
        }
    }


    public static void main(String[] args) {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
