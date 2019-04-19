import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * This class offers methods commonly used by ciphers
 */
public class Tools {

    private String fileContent = "";

    public Tools() {
    }

    /*
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

    /*
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
        String missingZeroes = "";
        if (output.length() < input1.length()) {
            int difference = input1.length() - output.length();
            for (int i = 0; i < difference; i++) {
                missingZeroes += "0";
            }
        }
        output = missingZeroes + output;
        return output;
    }
}
