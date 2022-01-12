public class MuMethod {
    static final int mod = 1000000007;
    static final int maxn = 10000000 + 10;
    static long cnt = 0;
    static long[] d = new long[maxn];
    static long[] mu = new long[maxn];
    static long[] vis = new long[maxn];
    static long[] prim = new long[maxn];

    static long sum(int n) {
        long ans = 0;
        for (int l = 1, r; l <= n; l = r + 1)        //整除分块
        {
            r = n / (n / l);
            ans = (ans + (mu[r] - mu[l - 1] + mod) % mod * (n / l) % mod * (n / l) % mod) % mod;
        }
        return ans;
    }

    static void get_mu(int n) {
        mu[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (vis[i] == 0) {
                prim[(int) ++cnt] = i;
                mu[i] = -1;
            }
            for (int j = 1; j <= cnt && prim[j] * i <= n; j++) {
                vis[(int) prim[j] * i] = 1;
                if (i % prim[j] == 0) break;
                else mu[i * (int) prim[j]] = -mu[i];
            }
        }
        for (int i = 1; i <= n; i++) {
            d[i] = ((long) i * i) % mod;
        }
        for (int i = 2; i <= n; i++) {
            d[i] = (d[i - 1] + d[i]) % mod;            //d的前缀和
            mu[i] = (mu[i - 1] + mu[i] + mod) % mod;    //mu的前缀和
        }
        long ans = 0;
        for (int l = 1, r; l <= n; l = r + 1)            //整除分块
        {
            r = n / (n / l);
            ans = (ans + (d[r] - d[l - 1] + mod) % mod * sum(n / l) % mod) % mod;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int n = 10;
        get_mu(n);
    }
}
