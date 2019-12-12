package SyntaxTest;

/**
 * @author Yu Yang
 * @create 2019-12-12 20:58
 */
public class Coins {
    public int dp(int n, int[] coins, int[] arr) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 5 || n == 10 || n ==25 || n == 50) {
            return 1;
        }
        if (arr[n] != Integer.MAX_VALUE) {
            return arr[n];
        }
        for (int i = 0; i < coins.length; i++) {
            if (n >= coins[i]) {
                arr[n] = Math.min(arr[n], dp(n - coins[i], coins, arr) + 1);
            }
        }
        return arr[n];
    }

    public static void main(String[] args) {
        int[] coins = {1, 5, 10, 25, 50};
        int n = 110;
        int[] arr = new int[n + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.MAX_VALUE;
        }
        Coins coin = new Coins();
        System.out.println(coin.dp(n, coins, arr));
    }
}
