import java.sql.Array;

public class roundkeyGenerator {

    public static void main (String[] args){

        //https://www.java-forum.org/thema/wie-eine-int-zahl-in-die-einzelnen-ziffern-zerlegen.23009/
        long k = 0b00111010100101001101011000111111;
        String str = Long.toString(k);
        long[] ia = new long[str.length()];
        int r = 2;

        int j = 0;
        while (j<r) {
            for (int i = 0; i < ia.length; i++) {
                i= i-1;
                int n = 0;
                while (n >15){
                    long[] key = new long[str.length()];
                    key = ia;

                    n++;

                    System.out.println(k);
                }


            }
        }
        System.out.println(Long.toBinaryString(k));

    }




}
