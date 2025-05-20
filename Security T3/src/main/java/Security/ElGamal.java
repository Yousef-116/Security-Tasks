package Security;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class ElGamal {

    public List<Long> encrypt(int q, int alpha, int y, int k, int m) {
        long c1 = modExp(alpha, k, q);         // a^k mod q
        long c2 = (m * modExp(y, k, q)) % q;   //  K  = Ya(private a) ^ k mod q
        return Arrays.asList(c1, c2);
    }

    public int decrypt(int c1, int c2, int x, int q) {
        long K = modExp(c1, x, q);         // K = c1^x mod q
        long K_inv = modExp(K,-1, q);    // modular inverse of s mod q
        long M = (c2 * K_inv) % q;        // decrypted message
        return (int) M;
    }

    // Modular exponentiation
    private int modExp(long base, long exp, long mod) {
        return BigInteger.valueOf(base).modPow(BigInteger.valueOf(exp), BigInteger.valueOf(mod)).intValue();
    }

}
