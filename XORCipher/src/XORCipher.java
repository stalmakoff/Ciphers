public class XORCipher {


    static String encryptDecrypt(String inputString)
    {
        // сам ключ с произвольным символом
        char xorKey = 'P';

        // хранение строки
        String outputString = "";

        // длина строки
        int len = inputString.length();

        // ключ к каждому символу
        for (int i = 0; i < len; i++)
        {
            outputString = outputString +
                    Character.toString((char) (inputString.charAt(i) ^ xorKey));
        }

        System.out.println(outputString);
        return outputString;
    }


    public static void main(String[] args)
    {
        String sampleString = "Automobile";


        System.out.println("Encrypted String");
        String encryptedString = encryptDecrypt(sampleString);


        System.out.println("Decrypted String");
        encryptDecrypt(encryptedString);
    }
}
