/*
 * This class offers methods commonly used by ciphers
 */
public class Tools {

    public Tools() {
    }

    /*
     * Xor 2 strings together.
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
