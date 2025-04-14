package org.example;
import Security.DES;
import java.math.BigInteger;


public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
        String key = "000457799BBCDFF1"; // 64-bit key in hex
        String actualCipherText = DES.hexToBinary(key);

        System.out.println(actualCipherText);

        // Convert binary to integer
        BigInteger bigInt = new BigInteger(actualCipherText, 2);
        System.out.println("Decimal: " + bigInt.toString());

        // Convert to hex
        String hex = bigInt.toString(16).toUpperCase();
        while (hex.length() < 16) {
            hex = "0" + hex;
        }
        System.out.println("Hexadecimal: " + hex);

    }
}