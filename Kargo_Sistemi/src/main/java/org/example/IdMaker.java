package org.example;
import java.util.Random;

public class IdMaker {

    public static String CreateID(int sign) {

        StringBuilder randomPart = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A');
            randomPart.append(randomChar);
        }

        String formattedSign = String.format("%04d", sign);

        return randomPart.toString() + formattedSign;
    }
}
