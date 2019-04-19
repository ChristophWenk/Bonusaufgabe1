public class Program {
    public static void main(String[] args) {
        SPN spn = new SPN(4,4,4,32);
        Tools tools = new Tools();
        String chiffreText = tools.readChiffreText("src/main/resources/chiffre.txt");
        //spn.dechiffre(file);

    }
}
