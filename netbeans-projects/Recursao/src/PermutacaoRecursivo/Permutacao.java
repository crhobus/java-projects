package PermutacaoRecursivo;

public class Permutacao {

    public void permutarString(String comecoString, String finalString) {
        if (finalString.length() <= 1) {
            System.out.println(comecoString + finalString);
        } else {
            for (int i = 0; i < finalString.length(); i++) {
                try {
                    String novaString = finalString.substring(0, i) + finalString.substring(i + 1);
                    permutarString(comecoString + finalString.charAt(i), novaString);
                } catch (StringIndexOutOfBoundsException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
