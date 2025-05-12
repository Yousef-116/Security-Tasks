package Security;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class DiffieHellman {
    public List<Integer> getKeys(int q, int alpha, int xa, int xb) {
        // Calculate public keys
        int ya = modExp(alpha, xa, q); // A's public key
        int yb = modExp(alpha, xb, q); // B's public key

        // Calculate shared secret keys
        int ka = modExp(yb, xa, q); // A's computed key
        int kb = modExp(ya, xb, q); // B's computed key

        return Arrays.asList(ka, kb);
    }

    private int modExp(int base, int exp, int mod) {
        return BigInteger.valueOf(base).modPow(BigInteger.valueOf(exp), BigInteger.valueOf(mod)).intValue();
    }

}
