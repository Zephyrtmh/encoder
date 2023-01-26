import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Encoder {
    private static ArrayList<String> table = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "(", ")", "*", "+", ",", "-", ".", "/"));

    public static String encode(String plainText) {
        plainText = plainText.toUpperCase();
        String encodedText;
        String offset;
        // user input for offset
        while (true) {
            System.out.print("What is the offset? ");
            Scanner scanner = new Scanner(System.in);
            offset = scanner.next().toUpperCase();
            if (!table.contains(offset)) {
                System.out.println("Char not found within table. Please input valid char.");
            } else {
                break;
            }
        }

        // offset original table
        int offsetIndex = table.indexOf(offset);
        ArrayList<String> newTable = new ArrayList<String>(table); // create shallow copy of ArrayList
        for (int i=0;i<offsetIndex;i++) {
            String lastChar = newTable.get(newTable.size() - 1);
            newTable.add(0, lastChar);
            newTable.remove(newTable.size() - 1);
        }

        // get new String based on newTable
        ArrayList<String> encodedTextList = new ArrayList<String>(Arrays.asList(offset));
        ArrayList<String> plainTextList = new ArrayList<String>(Arrays.asList(plainText.split("")));

        for (int i=0; i<plainTextList.size(); i++) {
            String letter = plainTextList.get(i);

            // check for space
            if (letter.trim().length()==0) {
                encodedTextList.add(" ");
                continue;
            }

            int index = table.indexOf(plainTextList.get(i));
            String newLetter = newTable.get(index);
            encodedTextList.add(newLetter);
        }
        encodedText = String.join("",encodedTextList);
        return encodedText;
    }

    public static String decode(String encodedText) {
        encodedText = encodedText.toUpperCase();
        String decodedText;
        String offset = Character.toString(encodedText.charAt(0));
        encodedText = encodedText.substring(1,encodedText.length());

        // offset original table
        int offsetIndex = table.indexOf(offset);
        ArrayList<String> newTable = new ArrayList<String>(table); // create shallow copy of ArrayList
        for (int i=0;i<offsetIndex;i++) {
            String lastChar = newTable.get(newTable.size() - 1);
            newTable.add(0, lastChar);
            newTable.remove(newTable.size() - 1);
        }


        ArrayList<String> decodedTextList = new ArrayList<String>();
        //split string to ArrayList to iterate over
        ArrayList<String> plainTextList = new ArrayList<String>(Arrays.asList(plainText.split("", 0)));

        // map decoded string based on original table
        for (int i=0; i<plainTextList.size(); i++) {
            String letter = plainTextList.get(i);

            // check for space
            if (letter.trim().length()==0) {
                decodedTextList.add(" ");
                continue;
            }

            int index = newTable.indexOf(plainTextList.get(i));
            String newLetter = table.get(index);

            decodedTextList.add(newLetter);
        }
        decodedText = String.join("",decodedTextList);
        return decodedText;
    }

    public static void main(String[] args) {

        String testText = "HELLO WORLD";
        System.out.println(String.format("Original text: %s", testText));
        System.out.println("encoding text...");
        String encodedText = encode(testText);
        System.out.println(encodedText);
        System.out.println("decoding text...");
        String decodedText = decode(encodedText);
        System.out.println(decodedText);
    }
}
