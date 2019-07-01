import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class offers methods commonly used by ciphers
 */
public class Tools {

    private String fileContent = "";

    /**
     * Read an input file
     *
     * @param file the file path
     * @return the file content
     */
    public String readChiffreText(String file) {
        try {
            Files.lines(Paths.get(file), StandardCharsets.UTF_8).forEach(s -> fileContent += s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return fileContent;
        }
    }

    /**
     * Increment y-n by 1
     *
     * @param y The y to increment
     * @return The incremented y
     */
    public String add1ToY(String y) {
        int a = Integer.parseInt(y,2);
        a = a + 1;
        String newY = Integer.toBinaryString(a);

        // Add missing zeroes to beginning of String
        newY = getMissingZeroes(y, newY);

        return newY;
    }

    /**
     * Convert a string in binary representation to human readable ASCII format
     *
     * @param binaryString The string e.g. "1001" to be converted to ASCII format
     * @return The converted ASCII string e.g. "a"
     */
    public String convertBinaryToASCII(String binaryString) {
        int binaryInt = Integer.parseInt(binaryString,2);
        String str = Character.toString((char)binaryInt);

        return str;
    }

    /**
     * Convert a string array to a simple string
     *
     * @param strArray The String array to be converted
     * @return The flat String to be retrieved
     */
    public static String convertArrayToString(String[] strArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strArray.length; i++) {
            stringBuilder.append(strArray[i]);
        }
        return stringBuilder.toString();
    }

    /**
     * Get y-1 from an input file
     *
     * @return the retrieved y-1
     */
    public String yMinus1(){
        return fileContent.substring(0,16);
    }

    /**
     * Normalize a Text so that it can be processed. Add one 1 and so many 0 so that the input is
     * dividable without rest by the normValue.
     *
     * @param input The string that should be normalized
     * @param normValue The Value to base the normalization on
     * @return The normalized String
     */
    public String normalizeText(String input, int normValue) {
        String output = input;

        if (input.length() % normValue != 0) {
           int difference = input.length() % normValue;
           String normalization = "1";
           for (int i = 0; i < difference - 1; i++) {
               normalization += "0";
           }
           output = output + normalization;
        }

        return output;
    }

    /**
     * Xor 2 strings with each other.
     *
     * @param input1 the first input string to be xor'd with input2
     * @param input2 the second input string to be xor'd with input1
     * @return the xor'd String made from input1 and input 2
     */
    public String xorStrings(String input1, String input2) {
        // Parse the strings as binary integer
        int a = Integer.parseInt(input1,2);
        int b = Integer.parseInt(input2,2);

        // Xor the 2 values
        int c = a ^ b;
        String output = Integer.toBinaryString(c);

        // Add missing zeroes to beginning of String
        output = getMissingZeroes(input1, output);
        return output;
    }

    /**
     * Calculates the modulo like this: input % 2^exponent
     *
     * @param input Input to be divided
     * @return The rest resulting out of the modulo operation
     */
    public String calculateModuloToBase2(String input) {
        int dividend = Integer.parseInt(input,2);
        Double doubleResult = (dividend % Math.pow(2,input.length()));
        int result = doubleResult.intValue();

        String rest = Integer.toBinaryString(result);
        rest = getMissingZeroes(input, rest);

        return rest;
    }

    /**
     * Add missing zeroes in front of the String to match the target length
     *
     * @param actualLength Length of the String that needs the missing zeroes
     * @param targetLength Length that should be matched
     * @return String with missing zeroes attached at the beginning
     */
    public String getMissingZeroes(String actualLength, String targetLength) {
        // Add missing zeroes to beginning of String
        String missingZeroes = "";
        if (targetLength.length() < actualLength.length()) {
            int difference = actualLength.length() - targetLength.length();
            for (int i = 0; i < difference; i++) {
                missingZeroes += "0";
            }
        }
        targetLength = missingZeroes + targetLength;
        return targetLength;
    }
}
