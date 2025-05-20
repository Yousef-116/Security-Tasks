package Security;

import java.math.BigInteger;

public class RSA {

    public int encrypt(int p, int q, int M, int e) {
        int n = p * q;
        return (int) modExp(M, e, n); // Cipher = M^e mod n
    }

    public int decrypt(int p, int q, int C, int e) {
        int n = p * q;
        int alpha = (p - 1) * (q - 1);  // alpha = (p-1) * (q-1)
        int d = modExp(e, -1 ,alpha);
        return (int) modExp(C, d, n);  // M = C^d mod n
    }

    // Fast modular exponentiation: (base^exp) % mod
    private int modExp(long base, long exp, long mod) {
        return BigInteger.valueOf(base).modPow(BigInteger.valueOf(exp), BigInteger.valueOf(mod)).intValue();
    }

    // Extended Euclidean Algorithm to find modular inverse
//    private int modInverse(int a, int m) {
//        int m0 = m, t, q;
//        int x0 = 0, x1 = 1;
//
//        if (m == 1)
//            return 0;
//
//        while (a > 1) {
//            q = a / m;
//            t = m;
//
//            m = a % m;
//            a = t;
//
//            t = x0;
//            x0 = x1 - q * x0;
//            x1 = t;
//        }
//
//        if (x1 < 0)
//            x1 += m0;
//
//        return x1;
//    }
}
