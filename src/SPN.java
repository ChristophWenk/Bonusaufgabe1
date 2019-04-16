import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SPN {

    int r = 4;
    int n = 4;
    int m = 4;
    int s = 32;
    long k = 0b00111010100101001101011000111111;
    long chiffretext;
    byte[] chiffre;

    int[] sBox = {15,4,14,1,2,16,11,8,3,10,6,12,5,9,0,7};
    int[] bitPerm ={0,4,8,12,1,5,9,13,2,6,10,14,3,7,11,15};

    //K(k,i) bestehe aus 16 aufeinanderfolgenden Bits von k beginned bei Position 4i



    long k0= 0b0011_1010_1001_0100;
    long k1= 0b1010_1001_0100_1101;
    long k2= 0b1001_0100_1101_0110;
    long k3= 0b0100_1101_0110_0011;

    public SPN() {}

    public void readChiffre(String file) {
        try {
            chiffre = Files.readAllBytes(Paths.get(file));
            int i = 2;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decipher(){


    }








}
