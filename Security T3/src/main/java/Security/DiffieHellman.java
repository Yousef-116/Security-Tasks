package Security;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class DiffieHellman {
    public List<Integer> getKeys(int q, int alpha, int xa, int xb) {
        // Calculate public keys
        long ya = longMod(alpha, xa, q); // A's public key
        long yb = longMod(alpha, xb, q); // B's public key

        // Calculate shared secret keys
        long ka = longMod(yb, xa, q); // A's computed key
        long kb = longMod(ya, xb, q); // B's computed key

        return Arrays.asList((int)ka, (int)kb);
    }



    public long longMod(long base, long exp, long mod) {
        long result = 1;
        for (long i = 0; i < exp; i++) {
            result = (result * base) % mod;
        }
        return result;
    }

    // Fast modular exponentiation: (base^exp) % mod
    private int modFast(int base, int exp, int mod) {
        int result = 1;
        base = base % mod;

        while (exp > 0) {
            if (exp % 2 == 1) { // If exponent is odd
                result = (int)(((long)result * base) % mod);
            }
            base = (int)(((long)base * base) % mod);
            exp = exp / 2;
        }

        return result;
    }

    private int modPow(int base, int exp, int mod) {
        return BigInteger.valueOf(base).modPow(BigInteger.valueOf(exp), BigInteger.valueOf(mod)).intValue();
    }



}
