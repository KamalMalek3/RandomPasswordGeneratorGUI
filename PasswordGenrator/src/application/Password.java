package application;

import java.util.Random;

public class Password {
    private String password;

    public String getPassword() {
        return password;
    }

    public String Genrate(Boolean containsUppercase, Boolean containsSymbols, Boolean containsNumbers, int length) {
        String pass = "";
        int number = 0;
        Random random = new Random();
        String Numbers = "1234567890";
        String Symbols = "`~!@#$%^&*()-=_+[]\\{}|;':\",./<>? ";
        String Uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Letters = "qwertyuiopasdfghjklzxcvbnm";
        for (int i = 0; i < length; i++) {
            number = random.nextInt(4);
            switch (number) {
                case 0:
                    if (containsUppercase) {
                        pass += Uppercase.charAt(random.nextInt(Uppercase.length()));
                        break;
                    }
                case 1:
                    if (containsSymbols) {
                        pass += Symbols.charAt(random.nextInt(Symbols.length()));
                        break;
                    }
                case 2:
                    if (containsNumbers) {
                        pass += Numbers.charAt(random.nextInt(Numbers.length()));
                        break;
                    }
                default:
                    pass += Letters.charAt(random.nextInt(Letters.length()));
                    break;
            }

        }
        password = pass;
        return pass;
    }
}