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
        long K_inv = modInverse((int)K, q);    // modular inverse of s mod q
        long M = (c2 * K_inv) % q;        // decrypted message
        return (int) M;
    }

    // Modular exponentiation
    private int modExp(long base, long exp, long mod) {
        return BigInteger.valueOf(base).modPow(BigInteger.valueOf(exp), BigInteger.valueOf(mod)).intValue();
    }

    // Returns modular inverse of a mod m using Extended Euclidean Algorithm
    private int modInverse(int a, int m) {
        int A1 = 1, A2 = 0, A3 = m;  // m becomes initial A3
        int B1 = 0, B2 = 1, B3 = a;  // a becomes initial B3

        while (B3 != 0 && B3 != 1) {
            int Q = A3 / B3;

            // Save current values
            int T1 = A1 - Q * B1;
            int T2 = A2 - Q * B2;
            int T3 = A3 - Q * B3;

            // Shift B to A and T to B
            A1 = B1; A2 = B2; A3 = B3;
            B1 = T1; B2 = T2; B3 = T3;
        }

        if (B3 == 0) {
            // Inverse does not exist
            throw new ArithmeticException("Inverse does not exist.");
        }

        // B2 might be negative, so normalize it
        return (B2 % m + m) % m;
    }


}
