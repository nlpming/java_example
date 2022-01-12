public class EulearMethod {
    static final int maxn = 10000009;
    static final int mod = 1000000007;
    static boolean[] vis = new boolean[maxn];
    static long[] prime = new long[maxn];
    static long[] phi = new long[maxn];
    static long[] s = new long[maxn];

    static void Euler(int n) {
        phi[1] = 1;
        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (!vis[i]) {
                prime[cnt++] = i;
                phi[i] = i - 1;
            }
            for (int j = 0; j < cnt && i * prime[j] <= n; j++) {
                vis[(int) prime[j] * i] = true;
                if (i % prime[j] != 0) {
                    phi[i * (int) prime[j]] = phi[i] * (prime[j] - 1);
                } else {
                    phi[i * (int) prime[j]] = phi[i] * prime[j];
                    break;
                }
            }
        }
        s[1] = phi[1];
        for (int i = 2; i < n; i++) {
            s[i] = s[i - 1] + 2 * phi[i];
        }
    }

    public static void main(String[] args) {
        int n = 10;
        Euler(n + 1);
        long sum = 0;

        for (long i = 1; i <= n; i++)
            sum = (sum + s[n / (int) i] % mod * i % mod * i % mod) % mod;
        System.out.println(sum);
    }
}