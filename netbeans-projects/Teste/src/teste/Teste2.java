package teste;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author crhobus
 */
public class Teste2 {

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        random.generateSeed(10);
        String str = new BigInteger(50, random).toString(32);
        System.out.println(str);
    }
}
